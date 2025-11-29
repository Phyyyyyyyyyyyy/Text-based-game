import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class ArcadeMechanics {

    private final String BLACK = "\u001B[30m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String BLUE = "\u001B[34m";
    private final String PURPLE = "\u001B[35m";
    private final String CYAN = "\u001B[36m";
    private final String WHITE = "\u001B[37m";
    private final String BRIGHT_GREEN = "\u001B[92m";
    private final String BRIGHT_YELLOW = "\u001B[93m";
    private final String BRIGHT_RED = "\u001B[91m";
    private final String BRIGHT_BLUE = "\u001B[94m";
    private final String RESET = "\u001B[0m";
    private static final int MAX_MANA = 100;

    Character player;
    Enemy enemy;
    public int turnCount = 1;
    private Random rand = new Random();
    private CooldownManager playerCD = new CooldownManager();
    private CooldownManager enemyCD = new CooldownManager();
    Scanner sc = new Scanner(System.in);
    private Clip backgroundMusicClip;
    private Clip winSFXClip;
    private Clip loseSFXClip;

    public ArcadeMechanics(Character player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        enableColors();
    }

    private void enableColors() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "color").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println("\t\t\t\tNote: Some colors may not display properly in this terminal.");
            }
        }
    }

    public void playSound(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("\t\t\t\t" + BRIGHT_YELLOW + "Sound file not found: " + filename + RESET);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();

            if (filename.equals("GameTheme.wav")) {
                this.backgroundMusicClip = clip;
            }

        } catch (Exception e) {
            System.out.println("\t\t\t\t" + BRIGHT_YELLOW + "Could not play sound: " + e.getMessage() + RESET);
        }
    }

    private Clip playAndStoreSound(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) return null;
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
            return clip;
        } catch (Exception e) {
            return null;
        }
    }

    private String getHPColor(int currentHP, int maxHP) {
        double percentage = (double) currentHP / maxHP;
        if (percentage > 0.5) {
            return BRIGHT_GREEN;
        } else if (percentage > 0.25) {
            return BRIGHT_YELLOW;
        } else {
            return BRIGHT_RED;
        }
    }

    private void useSkill(int skillNumber, Character user, Character target, CooldownManager cd) {
        int cost = skillNumber == 1 ? user.sk1Cost : (skillNumber == 2 ? user.sk2Cost : user.sk3Cost);
        int damage = skillNumber == 1 ? user.sk1Damage : (skillNumber == 2 ? user.sk2Damage : user.sk3Damage);
      
        if (!cd.canUseSkill(skillNumber)) {
            System.out.println("\t\t\t\t" + BRIGHT_YELLOW + "Skill is on cooldown! " + RESET + "(" + cd.getFormattedCooldown(skillNumber) + ")" + ", " + BRIGHT_RED + "You missed your turn, Enemy's turn now." + RESET);
            System.out.println("\t\t\t\tPress ENTER to continue...");
            sc.nextLine();
            return;
        }

        if (user.mana < cost) {
            System.out.println();
            System.out.println("\t\t\t\t\t" + BRIGHT_RED + "Not enough mana!" + RESET);
            System.out.println("\t\t\t\t\tPress ENTER to continue...");
            sc.nextLine();
            return;
        }

        target.hp -= damage;
        System.out.println();
        System.out.println("\t\t\t\t\t" + BRIGHT_RED + target.getName() + " takes " + damage + " damage!" + RESET);

        user.mana -= cost;
        cd.applyCooldown(skillNumber);
    }

    private int calculateBasicAttackDamage(int baseAttack) {
        double minMultiplier = 0.8;
        double maxMultiplier = 1.2;
        double multiplier = minMultiplier + (maxMultiplier - minMultiplier) * rand.nextDouble();
        return (int) (baseAttack * multiplier);
    }

    // ✅ NEW HELPER: Handles input with skip-on-ENTER logic
    private int getPlayerAction() {
        String input = sc.nextLine().trim();
        
        if (input.isEmpty()) {
            System.out.println("\n\t\t\t\t" + BRIGHT_RED + "You hesitated! Turn skipped." + RESET);
            System.out.println("\t\t\t\tPress ENTER to continue...");
            sc.nextLine();
            return -1;
        }
        
        try {
            int action = Integer.parseInt(input);
            if (action >= 0 && action <= 3) {
                return action;
            } else {
                System.out.println("\n\t\t\t\t" + BRIGHT_RED + "Invalid action! Turn skipped." + RESET);
                System.out.println("\t\t\t\tPress ENTER to continue...");
                sc.nextLine();
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("\n\t\t\t\t" + BRIGHT_RED + "Invalid action! Turn skipped." + RESET);
            System.out.println("\t\t\t\tPress ENTER to continue...");
            sc.nextLine();
            return -1;
        }
    }

    public void game() {
        playSound("GameTheme.wav");

        System.out.println("\n\t\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
        System.out.println(GREEN + "\t\t\t\t\t\tYour Character: " + RESET);
        String playerHPColor = getHPColor(player.hp, player.maxHp);
        System.out.println("\t\t\t\t\t\t" + player.getName() + " - " + playerHPColor + "HP: " + player.hp + "/" + player.maxHp + RESET
                + " | " + BRIGHT_BLUE + "Mana: " + player.mana + "/" + MAX_MANA + RESET);

        System.out.println("\t\t\t\t\t\t" + RED + "\nEnemy: " + RESET);
        String enemyHPColor = getHPColor(enemy.hp, enemy.maxHp);
        System.out.println("\t\t\t\t\t\t" + enemy.getName() + " - " + enemyHPColor + "HP: " + enemy.hp + "/" + enemy.maxHp + RESET
                + " | " + BRIGHT_BLUE + "Mana: " + enemy.mana + "/" + MAX_MANA + RESET);
        System.out.println("\t\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);

        boolean playerTurn = true;

        while (player.hp > 0 && enemy.hp > 0) {
            if (playerTurn) {
                clearScreen();
                System.out.println();
                System.out.println("\n\t\t\t\t\t\t" + BRIGHT_GREEN + player.getName() + "'s Turn" + RESET);
                System.out.println("\t\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
                System.out.println("\t\t\t\t\t\t" + BRIGHT_YELLOW + "Turn " + turnCount + RESET);
                displayPlayerStats();
                System.out.println("\t\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
                turnCount++;

                // ✅ INLINE COOLDOWN DISPLAY (MATCHING SCREENSHOT STYLE)
                String s1Status = playerCD.canUseSkill(1) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + playerCD.getFormattedCooldown(1);
                String s2Status = playerCD.canUseSkill(2) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + playerCD.getFormattedCooldown(2);
                String s3Status = playerCD.canUseSkill(3) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + playerCD.getFormattedCooldown(3);

                System.out.println("\t\t\t\t\t\t" + player.getName() + BRIGHT_YELLOW + " - Choose your action: " + RESET);
                System.out.println("\t\t\t\t\t\t" + BRIGHT_YELLOW + "Skill Cooldowns: " + RESET +
                    "S1: " + s1Status + RESET + " | " +
                    "S2: " + s2Status + RESET + " | " +
                    "S3: " + s3Status + RESET);
                System.out.println();

                // ✅ SKILL OPTIONS WITH DAMAGE, MANA, AND COOLDOWN HINT
                System.out.println("\t\t\t\t\t\t0. Basic Attack (no mana, no cooldown)");
                System.out.println("\t\t\t\t\t\t1. " + player.getSkill1() + " - Deals " + BRIGHT_RED + player.sk1Damage + " damage" + RESET + " - " + BRIGHT_BLUE + player.sk1Cost + " mana" + RESET);
                System.out.println("\t\t\t\t\t\t2. " + player.getSkill2() + " - Deals " + BRIGHT_RED + player.sk2Damage + " damage" + RESET + " - " + BRIGHT_BLUE + player.sk2Cost + " mana" + RESET);
                System.out.print("\t\t\t\t\t\t3. " + player.getSkill3() + " - Deals " + BRIGHT_RED + player.sk3Damage + " damage" + RESET + " - " + BRIGHT_BLUE + player.sk3Cost + " mana" + RESET);
                if (!playerCD.canUseSkill(3)) {
                    System.out.print(" (" + BRIGHT_RED + playerCD.getFormattedCooldown(3) + RESET + ")");
                }
                System.out.println();

                System.out.print("\t\t\t\t\t\t> ");
                int action = getPlayerAction(); // ✅ Uses new safe input handler

                if (action == -1) {
                    // Turn skipped — message already shown
                } else {
                    switch (action) {
                        case 0:
                            playSound("Hit.wav");
                            int damage = calculateBasicAttackDamage(player.attack);
                            System.out.println("\n\t\t\t\t\t\tYou perform a basic attack!");
                            enemy.hp -= damage;
                            System.out.println("\t\t\t\t\t\tYou deal " + BRIGHT_RED + damage + " damage!" + RESET);
                            break;
                        case 1:
                        case 2:
                        case 3:
                            playSound("Hit.wav");
                            useSkill(action, player, enemy, playerCD);
                            break;
                    }
                }

                System.out.println();
                displayPlayerStats();

            } else {
                clearScreen();
                System.out.println();
                System.out.println("\t\t\t\t\t" + BRIGHT_RED + enemy.getName() + "'s Turn!" + RESET);
                System.out.println("\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);

                // ✅ ENEMY COOLDOWN DISPLAY (OPTIONAL, FOR CONSISTENCY)
                String es1 = enemyCD.canUseSkill(1) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + enemyCD.getFormattedCooldown(1);
                String es2 = enemyCD.canUseSkill(2) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + enemyCD.getFormattedCooldown(2);
                String es3 = enemyCD.canUseSkill(3) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + enemyCD.getFormattedCooldown(3);

                System.out.println("\t\t\t\t\t" + BRIGHT_YELLOW + "Enemy Cooldowns: " + RESET +
                    "S1: " + es1 + RESET + " | " +
                    "S2: " + es2 + RESET + " | " +
                    "S3: " + es3 + RESET);

                int action;
                if (enemy.mana >= enemy.sk3Cost && enemyCD.canUseSkill(3)) {
                    action = 3;
                } else if (enemy.mana >= enemy.sk2Cost && enemyCD.canUseSkill(2)) {
                    action = 2;
                } else if (enemy.mana >= enemy.sk1Cost && enemyCD.canUseSkill(1)) {
                    action = 1;
                } else {
                    action = 0; // basic attack
                }

                switch (action) {
                    case 0:
                        int damage = calculateBasicAttackDamage(enemy.attack);
                        System.out.println("\t\t\t\t\t" + enemy.name + " attacks!");
                        playSound("Hit.wav");
                        player.hp -= damage;
                        System.out.println("\t\t\t\t\t" + enemy.name + " deals " + BRIGHT_RED + damage + " damage!" + RESET);
                        break;
                    case 1:
                        System.out.println("\t\t\t\t\t" + enemy.name + " uses " + enemy.skill1 + "!");
                        playSound("Hit.wav");
                        useSkill(1, enemy, player, enemyCD);
                        break;
                    case 2:
                        System.out.println("\t\t\t\t\t" + enemy.name + " uses " + enemy.skill2 + "!");
                        playSound("Hit.wav");
                        useSkill(2, enemy, player, enemyCD);
                        break;
                    case 3:
                        System.out.println("\t\t\t\t\t" + enemy.name + " uses " + enemy.skill3 + "!");
                        playSound("Hit.wav");
                        useSkill(3, enemy, player, enemyCD);
                        break;
                }

                System.out.println();
                System.out.println("\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
                System.out.println("\t\t\t\t\t" + BRIGHT_GREEN + "Your character: " + RESET);
                displayPlayerStats();

                System.out.println("\t\t\t\t\t" + BRIGHT_RED + "Enemy: " + RESET);
                displayEnemyStats();
                System.out.println("\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);

                // ===== 💎 BOTH GET +10 MANA =====
                int playerManaBeforeBase = player.mana;
                int enemyManaBeforeBase = enemy.mana;

                player.regenerateMana(10);
                enemy.regenerateMana(10);

                int playerBaseGain = player.mana - playerManaBeforeBase;
                int enemyBaseGain = enemy.mana - enemyManaBeforeBase;

                if (playerBaseGain > 0) {
                    System.out.println("\t\t\t\t\t" + BRIGHT_BLUE + "+" + playerBaseGain + " mana (you) - base regen" + RESET);
                }
                if (enemyBaseGain > 0) {
                    System.out.println("\t\t\t\t\t" + BRIGHT_BLUE + "+" + enemyBaseGain + " mana (" + enemy.getName() + ") - base regen" + RESET);
                }

                // ===== ✨ BONUS: +5 to +10 MORE =====
                int playerBonus = ThreadLocalRandom.current().nextInt(5, 11);
                int enemyBonus = ThreadLocalRandom.current().nextInt(5, 11);

                int playerManaBeforeBonus = player.mana;
                int enemyManaBeforeBonus = enemy.mana;

                player.regenerateMana(playerBonus);
                enemy.regenerateMana(enemyBonus);

                int playerBonusGain = player.mana - playerManaBeforeBonus;
                int enemyBonusGain = enemy.mana - enemyManaBeforeBonus;

                if (playerBonusGain > 0) {
                    System.out.println("\t\t\t\t\t" + BRIGHT_GREEN + "+" + playerBonusGain + " mana (you) - bonus regen" + RESET);
                }
                if (enemyBonusGain > 0) {
                    System.out.println("\t\t\t\t\t" + BRIGHT_GREEN + "+" + enemyBonusGain + " mana (" + enemy.getName() + ") - bonus regen" + RESET);
                }
            }

            if (player.hp < 0) player.hp = 0;
            if (enemy.hp < 0) enemy.hp = 0;

            if (!playerTurn) {
                playerCD.reduceCooldowns();
                enemyCD.reduceCooldowns();
            }
            playerTurn = !playerTurn;

            System.out.println("\n\t\t\t\t\tPress ENTER to continue...");
            System.out.print("\t\t\t\t\t\t> ");
            sc.nextLine();
        }

        System.out.println("\n\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
        clearScreen();

        stopBackgroundMusic();

        if (player.hp <= 0 && enemy.hp <= 0) {
            System.out.println("\t\t\t\t" + BRIGHT_YELLOW + "It's a draw!" + RESET);
        } else if (player.hp <= 0) {
            loseSFXClip = playAndStoreSound("LoseSFX.wav");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(BRIGHT_RED + "\t\t\t\tYou lost! " + enemy.name + " wins!" + RESET);
            System.out.println("\n\t\t\t\tPress ENTER to continue...");
            System.out.print("\t\t\t\t");
            sc.nextLine();

            if (loseSFXClip != null && loseSFXClip.isRunning()) {
                loseSFXClip.stop();
                loseSFXClip.close();
            }
            loseSFXClip = null;
            clearScreen();
        } else {
            winSFXClip = playAndStoreSound("WinSFX.wav");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(BRIGHT_GREEN + "\t\t\t\tCongratulations! You defeated " + enemy.name + "!" + RESET);
            System.out.println("\n\t\t\t\tPress ENTER to continue...");
            System.out.print("\t\t\t\t");
            sc.nextLine();

            if (winSFXClip != null && winSFXClip.isRunning()) {
                winSFXClip.stop();
                winSFXClip.close();
            }
            winSFXClip = null;
            clearScreen();
        }
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    private void displayPlayerStats() {
        String playerHPColor = getHPColor(player.hp, player.maxHp);
        System.out.println("\t\t\t\t\t\t" + player.getName() + " - " + playerHPColor + "HP: " + player.hp + "/" + player.maxHp + RESET
                + " | " + BRIGHT_BLUE + "Mana: " + player.mana + "/" + MAX_MANA + RESET
                + " | " + RED + "Attack: " + player.attack + RESET);
    }

    private void displayEnemyStats() {
        String enemyHPColor = getHPColor(enemy.hp, enemy.maxHp);
        System.out.println("\t\t\t\t\t\t" + enemy.getName() + " - " + enemyHPColor + "HP: " + enemy.hp + "/" + enemy.maxHp + RESET
                + " | " + BRIGHT_BLUE + "Mana: " + enemy.mana + "/" + MAX_MANA + RESET
                + " | " + RED + "Attack: " + enemy.attack + RESET);
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
            backgroundMusicClip = null;
        }
    }
}
