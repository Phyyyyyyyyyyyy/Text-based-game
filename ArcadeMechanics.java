
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.File;

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
        String skillName = skillNumber == 1 ? user.getSkill1() : (skillNumber == 2 ? user.getSkill2() : user.getSkill3());

        if (!cd.canUseSkill(skillNumber)) {
            System.out.println("\t\t\t\t" + BRIGHT_YELLOW + "Skill is on cooldown! " + RESET + "(" + cd.getFormattedCooldown(skillNumber) + ")" + ", " + BRIGHT_RED + "You missed your turn, Enemy's turn now." + RESET);
            System.out.println("\t\t\t\tPress ENTER to continue...");
            sc.nextLine();
            return;
        }

        if (user.mana < cost) {
            System.out.println("\t\t\t\t" + BRIGHT_RED + "Not enough mana!" + RESET);
            System.out.println("\t\t\t\tPress ENTER to continue...");
            sc.nextLine();
            return;
        }

        target.hp -= damage;
        //System.out.println(user.getName() + " uses " + skillName + "!"); 
        System.out.println("\t\t\t\t" + BRIGHT_RED + target.getName() + " takes " + damage + " damage!" + RESET);

        user.mana -= cost;
        cd.applyCooldown(skillNumber);
    }

    private int calculateBasicAttackDamage(int baseAttack) {
        double minMultiplier = 0.8;
        double maxMultiplier = 1.2;
        double multiplier = minMultiplier + (maxMultiplier - minMultiplier) * rand.nextDouble();
        return (int) (baseAttack * multiplier);
    }

    public void game() {
        playSound("GameTheme.wav");

        int currentMana = enemy.mana;
        enemy.regenerateMana(10);
        int enemyManaGained = enemy.mana - currentMana;
        boolean playerTurn = true;

        System.out.println("\n\t\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
        System.out.println(GREEN + "\t\t\t\t\t\tYour Character: " + RESET);
        String playerHPColor = getHPColor(player.hp, player.maxHp);
        System.out.println("\t\t\t\t\t\t" + player.getName() + " - " + playerHPColor + "HP: " + player.hp + "/" + player.maxHp + RESET
                + " | " + BRIGHT_BLUE + "Mana: " + player.mana + "/" + MAX_MANA + RESET);

        System.out.println("\t\t\t\t\t\t" + RED + "\nEnemy: " + RESET);
        String enemyHPColor = getHPColor(enemy.hp, enemy.maxHp);
        if (enemyManaGained > 0 && enemy.mana < MAX_MANA) {
            System.out.println("\t\t\t\t\t\t" + enemy.getName() + " - " + enemyHPColor + "HP: " + enemy.hp + "/" + enemy.maxHp + RESET
                    + " | " + BRIGHT_BLUE + "Mana: " + enemy.mana + "/" + MAX_MANA + RESET + BRIGHT_GREEN + " (+" + enemyManaGained + ")" + RESET);
        } else {
            System.out.println("\t\t\t\t\t\t" + enemy.getName() + " - " + enemyHPColor + "HP: " + enemy.hp + "/" + enemy.maxHp + RESET
                    + " | " + BRIGHT_BLUE + "Mana: " + enemy.mana + "/" + MAX_MANA + RESET);
        }
        System.out.println("\t\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);

        while (player.hp > 0 && enemy.hp > 0) {
            if (playerTurn) {
                clearScreen();
                System.out.println("\n\t\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
                System.out.println("\t\t\t\t\t\t" + BRIGHT_YELLOW + "Turn " + turnCount + RESET);
                displayPlayerStats();
                System.out.println("\t\t\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
                turnCount++;

                System.out.println("\t\t\t\t\t\t" + player.getName() + BRIGHT_YELLOW + " - Choose your action: " + RESET);
                System.out.println("\t\t\t\t\t\t" + BRIGHT_YELLOW + "Cooldowns - S1: " + playerCD.getFormattedCooldown(1) + " |" + BRIGHT_YELLOW
                        + " S2: " + playerCD.getFormattedCooldown(2)
                        + " | " + "S3: " + playerCD.getFormattedCooldown(3) + RESET);
                System.out.println("\t\t\t\t\t\t0. Basic Attack");
                System.out.println("\t\t\t\t\t\t1. " + player.getSkill1() + " - " + BRIGHT_BLUE + player.sk1Cost + " mana" + RESET);
                System.out.println("\t\t\t\t\t\t2. " + player.getSkill2() + " - " + BRIGHT_BLUE + player.sk2Cost + " mana" + RESET);
                System.out.println("\t\t\t\t\t\t3. " + player.getSkill3() + " - " + BRIGHT_BLUE + player.sk3Cost + " mana" + RESET);
                System.out.print("\t\t\t\t\t\t> ");
                try {
                    int action = sc.nextInt();
                    sc.nextLine();

                    switch (action) {
                        case 0:
                            int damage = calculateBasicAttackDamage(player.attack);
                            System.out.println("\t\t\t\tYou perform a basic attack!");
                            enemy.hp -= damage;
                            System.out.println("\t\t\t\tYou deal " + BRIGHT_RED + damage + " damage!" + RESET);
                            break;
                        case 1:
                        case 2:
                        case 3:
                            useSkill(action, player, enemy, playerCD);
                            break;
                        default:
                            System.out.println("\t\t\t\t" + BRIGHT_RED + "Invalid action! You lose your turn." + RESET);
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("\t\t\t\t" + BRIGHT_RED + "Invalid action! You lose your turn." + RESET);
                    sc.nextLine();
                }
                player.regenerateMana(10);

                System.out.println();
                displayPlayerStats();

            } else {
                clearScreen();
                System.out.println("\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
                System.out.println("\n\t\t\t\t" + BRIGHT_RED + "Enemy's Turn!" + RESET);
                System.out.println("\t\t\t\tCooldowns - S1: " + enemyCD.getFormattedCooldown(1)
                        + " | S2: " + enemyCD.getFormattedCooldown(2)
                        + " | S3: " + enemyCD.getFormattedCooldown(3));

                int action;
                if (enemy.mana >= enemy.sk3Cost && enemyCD.canUseSkill(3)) {
                    action = 4;
                } else if (enemy.mana >= enemy.sk1Cost && enemyCD.canUseSkill(1)) {
                    action = 2;
                } else if (enemy.mana >= enemy.sk2Cost && enemyCD.canUseSkill(2)) {
                    action = 3;
                } else {
                    action = 1;
                }

                switch (action) {
                    case 1:
                        int damage = calculateBasicAttackDamage(enemy.attack);
                        System.out.println("\t\t\t\t" + enemy.name + " attacks!");
                        player.hp -= damage;
                        System.out.println("\t\t\t\t" + enemy.name + " deals " + BRIGHT_RED + damage + " damage!" + RESET);
                        break;
                    case 2:
                        if (!enemyCD.canUseSkill(1)) {
                            System.out.println("\t\t\t\t" + enemy.name + " tried to use " + enemy.skill1 + " but it's on cooldown!");
                        } else if (enemy.mana >= enemy.sk1Cost) {
                            System.out.println("\t\t\t\t" + enemy.name + " uses " + enemy.skill1 + "!");
                            useSkill(1, enemy, player, enemyCD);
                        } else {
                            System.out.println("\t\t\t\t" + enemy.name + " tried to use " + enemy.skill1 + " but has no mana!");
                        }
                        break;
                    case 3:
                        if (!enemyCD.canUseSkill(2)) {
                            System.out.println("\t\t\t\t" + enemy.name + " tried to use " + enemy.skill2 + " but it's on cooldown!");
                        } else if (enemy.mana >= enemy.sk2Cost) {
                            System.out.println("\t\t\t\t" + enemy.name + " uses " + enemy.skill2 + "!");
                            useSkill(2, enemy, player, enemyCD);
                        } else {
                            System.out.println("\t\t\t\t" + enemy.name + " tried to use " + enemy.skill2 + " but has no mana!");
                        }
                        break;
                    case 4:
                        if (!enemyCD.canUseSkill(3)) {
                            System.out.println("\t\t\t\t" + enemy.name + " tried to use " + enemy.skill3 + " but it's on cooldown!");
                        } else if (enemy.mana >= enemy.sk3Cost) {
                            System.out.println("\t\t\t\t" + enemy.name + " uses " + enemy.skill3 + "!");
                            useSkill(3, enemy, player, enemyCD);
                        } else {
                            System.out.println("\t\t\t\t" + enemy.name + " tried to use " + enemy.skill3 + " but has no mana!");
                        }
                        break;
                }
                System.out.println();
                System.out.println("\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
                System.out.println("\t\t\t\t" + BRIGHT_GREEN + "Your character: " + RESET);
                displayPlayerStats();

                System.out.println("\t\t\t\t" + BRIGHT_RED + "Enemy: " + RESET);
                displayEnemyStats();
                System.out.println("\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
                player.regenerateMana(10);
                enemy.regenerateMana(10);
            }

            if (player.hp < 0) {
                player.hp = 0;
            }
            if (enemy.hp < 0) {
                enemy.hp = 0;
            }

            if (!playerTurn) {
                playerCD.reduceCooldowns();
                enemyCD.reduceCooldowns();
            }
            playerTurn = !playerTurn;

            System.out.println("\n\t\t\t\tPress ENTER to continue...");
            sc.nextLine();
        }

        System.out.println("\n\t\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
        clearScreen();
        if (player.hp <= 0 && enemy.hp <= 0) {
            System.out.println("\t\t\t\t" + BRIGHT_YELLOW + "It's a draw!" + RESET);
        } else if (player.hp <= 0) {
            ////write file for lose sfx here
            stopBackgroundMusic();
            playSound("LoseSFX.wav");

            System.out.println(RED + "\t\t\t\t_____                                           _____ " + RESET);
            System.out.println(RED + "\t\t\t\t( ___ )-----------------------------------------( ___ )" + RESET);
            System.out.println(RED + "\t\t\t\t |   |                                           |   | " + RESET);
            System.out.println(RED + "\t\t\t\t |   | __   __            _                   _  |   | " + RESET);
            System.out.println(RED + "\t\t\t\t |   | \\ \\ / /__  _   _  | |    ___  ___  ___| | |   | " + RESET);
            System.out.println(RED + "\t\t\t\t |   |  \\ V / _ \\| | | | | |   / _ \\/ __|/ _ \\ | |   | " + RESET);
            System.out.println(RED + "\t\t\t\t |   |   | | (_) | |_| | | |__| (_) \\__ \\  __/_| |   | " + RESET);
            System.out.println(RED + "\t\t\t\t |   |   |_|\\___/ \\__,_| |_____\\___/|___/\\___(_) |   | " + RESET);
            System.out.println(RED + "\t\t\t\t |___|                                           |___| " + RESET);
            System.out.println(RED + "\t\t\t\t(_____)-----------------------------------------(_____)" + RESET);
            System.out.println(BRIGHT_RED + "\t\t\t\tYou lost! " + enemy.name + " wins!" + RESET);
            System.out.println("\n\t\t\t\tPress ENTER to continue...");
            sc.nextLine();
            clearScreen();
        } else {
            ///write file for win sfx here
            stopBackgroundMusic();
            playSound("WinSFX.wav");

            System.out.println(GREEN + "\t\t\t\t _____                                          _____ " + RESET);
            System.out.println(GREEN + "\t\t\t\t( ___ )----------------------------------------( ___ )" + RESET);
            System.out.println(GREEN + "\t\t\t\t |   |                                          |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t |   | __   __           __        ___       _  |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t |   | \\ \\ / /__  _   _  \\ \\      / (_)_ __ | | |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t |   |  \\ V / _ \\| | | |  \\ \\ /\\ / /| | '_ \\| | |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t |   |   | | (_) | |_| |   \\ V  V / | | | | |_| |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t |   |   |_|\\___/ \\__,_|    \\_/\\_/  |_|_| |_(_) |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t |___|                                          |___| " + RESET);
            System.out.println(GREEN + "\t\t\t\t(_____)----------------------------------------(_____)" + RESET);
            System.out.println(BRIGHT_GREEN + "\t\t\t\tCongratulations! You defeated " + enemy.name + "!" + RESET);
            System.out.println("\n\t\t\t\tPress ENTER to continue...");
            sc.nextLine();
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
        System.out.println("\t\t\t\t" + player.getName() + " - " + playerHPColor + "HP: " + player.hp + "/" + player.maxHp + RESET
                + " | " + BRIGHT_BLUE + "Mana: " + player.mana + "/" + MAX_MANA + RESET
                + " | " + RED + "Attack: " + player.attack + RESET);
    }

    private void displayEnemyStats() {
        String enemyHPColor = getHPColor(enemy.hp, enemy.maxHp);
        System.out.println("\t\t\t\t" + enemy.getName() + " - " + enemyHPColor + "HP: " + enemy.hp + "/" + enemy.maxHp + RESET
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
