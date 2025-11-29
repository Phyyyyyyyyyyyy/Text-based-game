import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.File;

public class GameMechanics {

    private final String GREEN = "\u001B[32m";
    private final String RED = "\u001B[31m";
    private final String BRIGHT_GREEN = "\u001B[92m";
    private final String BRIGHT_YELLOW = "\u001B[93m";
    private final String BRIGHT_RED = "\u001B[91m";
    private final String BRIGHT_BLUE = "\u001B[94m";
    private final String RESET = "\u001B[0m";
  
    private Character player;
    private Enemy enemy;
    private int turnCount;
    private Random rand = new Random();
    private CooldownManager playerCD = new CooldownManager();
    private CooldownManager enemyCD = new CooldownManager();
    private Scanner sc = new Scanner(System.in);
    private Clip backgroundMusicClip;
   
    private boolean playerStartsNextRound;

    public GameMechanics(Character player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        enableColors();
    }

    private void enableColors() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "color").inheritIO().start().waitFor();
            } catch (Exception ignored) {}
        }
    }

    private int calculateBasicAttackDamage(int baseAttack) {
        double minMultiplier = 0.8;
        double maxMultiplier = 1.2;
        double multiplier = minMultiplier + (maxMultiplier - minMultiplier) * rand.nextDouble();
        return (int) Math.round(baseAttack * multiplier);
    }

    public void startMatch() {
        playerStartsNextRound = rand.nextBoolean();
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("\n" + BRIGHT_YELLOW + "\t\t\t\t\t\t\t\t==================================================" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t\tMATCH START - Best of 3 Rounds" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t\tFirst to 2 wins the match!" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t\t==================================================" + RESET);

        System.out.println("\n\t\t\t\t\t\t\t\tPlayer 1: ");
        System.out.println("\t\t\t\t\t\t\t\t" + player.getName() + " - " + BRIGHT_GREEN + "HP: " + player.hp + "/" + player.maxHp + RESET + " | " + BRIGHT_BLUE + "Mana: " + player.mana + "/100" + RESET);
        System.out.println("\n\t\t\t\t\t\t\t\tEnemy: ");
        System.out.println("\t\t\t\t\t\t\t\t" + enemy.getName() + " - " + BRIGHT_GREEN + "HP: " + enemy.hp + "/" + enemy.maxHp + RESET + " | " + BRIGHT_BLUE + "Mana: " + enemy.mana + "/100" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t\t==================================================" + RESET);

        int playerWins = 0;
        int enemyWins = 0;
        int round = 1;

        while (playerWins < 2 && enemyWins < 2) {
            playSound("GameTheme.wav");
            System.out.println("\n" + BRIGHT_BLUE + "\t\t\t\t\t\t\t\t--- ROUND " + round + " ---" + RESET);
            System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t\tPress ENTER to start the round..." + RESET);
            System.out.print("\t\t\t\t\t\t\t\t> ");
            sc.nextLine();

            resetForRound();

            boolean playerWonRound = playRound(playerStartsNextRound);
            if (playerWonRound) {
                playerWins++;
                playerStartsNextRound = true;
                stopBackgroundMusic();
                playAndStoreSound("WinSFX.wav");
                System.err.println("\n\n\n\n\n\n\n");
                System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\t\t===================================" + RESET);
                System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\t\tCongratulations! You defeated " + enemy.name + "!" + RESET);
                System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\t\t===================================" + RESET);

                System.out.println("\n\t\t\t\t\t\t\t\tPress ENTER to continue...");
                System.out.print("\t\t\t\t\t\t\t\t> ");
                sc.nextLine();
                stopBackgroundMusic();
               
                clearScreen();
            } else {
                enemyWins++;
                playerStartsNextRound = false;
                stopBackgroundMusic();
                playAndStoreSound("LoseSFX.wav");

                System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\t===================================" + RESET);
                System.out.println(BRIGHT_RED + "\t\t\t\t\t\t\tYou lost! " + enemy.name + " wins!" + RESET);
                System.out.println(BRIGHT_RED + "\t\t\t\t\t\t\t===================================" + RESET);

                System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue...");
                System.out.print("\t\t\t\t\t\t\t > ");
                sc.nextLine();

                

                clearScreen();
            }
            System.out.print("\t\n\n\n\n\n ");
            System.out.println("\n\t\t\t\t\t\t\tScore: " + BRIGHT_GREEN + playerWins + RESET + " - " + BRIGHT_RED + enemyWins + RESET);

            if (playerWins < 2 && enemyWins < 2) {
                playSound("GameTheme.wav");
                System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue to next round...");
                System.out.print("\t\t\t\t\t\t\t> ");
                sc.nextLine();
            }

            stopBackgroundMusic();
            round++;
        }

        clearScreen();
        stopBackgroundMusic();

        if (playerWins >= 2) {
           playAndStoreSound("WinSFX.wav");

            System.out.println(GREEN + "\t\t\t\t\t\t\t _____                                          _____ " + RESET);
            System.out.println(GREEN + "\t\t\t\t\t\t\t( ___ )----------------------------------------( ___ )" + RESET);
            System.out.println(GREEN + "\t\t\t\t\t\t\t |   |                                          |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t\t\t\t |   | __   __           __        ___       _  |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t\t\t\t |   | \\ \\ / /__  _   _  \\ \\      / (_)_ __ | | |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t\t\t\t |   |  \\ V / _ \\| | | |  \\ \\ /\\ / /| | '_ \\| | |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t\t\t\t |   |   | | (_) | |_| |   \\ V  V / | | | | |_| |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t\t\t\t |   |   |_|\\___/ \\__,_|    \\_/\\_/  |_|_| |_(_) |   | " + RESET);
            System.out.println(GREEN + "\t\t\t\t\t\t\t |___|                                          |___| " + RESET);
            System.out.println(GREEN + "\t\t\t\t\t\t\t(_____)----------------------------------------(_____)" + RESET);
            System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\tYou defeated " + enemy.getName() + "!" + RESET);
            System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\t===================================" + RESET);
        } else {
           playAndStoreSound("LoseSFX.wav");
            System.out.println(RED + "\t\t\t\t\t\t\t_____                                           _____ " + RESET);
            System.out.println(RED + "\t\t\t\t\t\t\t( ___ )-----------------------------------------( ___ )" + RESET);
            System.out.println(RED + "\t\t\t\t\t\t\t |   |                                           |   | " + RESET);
            System.out.println(RED + "\t\t\t\t\t\t\t |   | __   __            _                   _  |   | " + RESET);
            System.out.println(RED + "\t\t\t\t\t\t\t |   | \\ \\ / /__  _   _  | |    ___  ___  ___| | |   | " + RESET);
            System.out.println(RED + "\t\t\t\t\t\t\t |   |  \\ V / _ \\| | | | | |   / _ \\/ __|/ _ \\ | |   | " + RESET);
            System.out.println(RED + "\t\t\t\t\t\t\t |   |   | | (_) | |_| | | |__| (_) \\__ \\  __/_| |   | " + RESET);
            System.out.println(RED + "\t\t\t\t\t\t\t |   |   |_|\\___/ \\__,_| |_____\\___/|___/\\___(_) |   | " + RESET);
            System.out.println(RED + "\t\t\t\t\t\t\t |___|                                           |___| " + RESET);
            System.out.println(RED + "\t\t\t\t\t\t\t(_____)-----------------------------------------(_____)" + RESET);
            System.out.println(BRIGHT_RED + "\t\t\t\t\t\t\t" + enemy.getName() + " defeated you." + RESET);
            System.out.println(BRIGHT_RED + "\t\t\t\t\t\t\t===================================" + RESET);
        }

        System.out.println("\n\t\t\t\t\t\t\tPress ENTER to return...");
        System.out.print("\t\t\t\t\t\t\t");
        sc.nextLine();
        System.err.println("\n\n\n");

        
        
    }

    private boolean playRound(boolean playerStarts) {
        boolean playerTurn = playerStarts;
        turnCount = 1;

        while (player.hp > 0 && enemy.hp > 0) {
            if (playerTurn) {
                clearScreen();
                displayTurnHeader();
                displayPlayerStats();
                displaySkillOptions();

                int action = getPlayerAction(); // ← Will skip turn on invalid/empty
                executePlayerAction(action);
            } else {
                clearScreen();
                System.out.println("\n\n\n\n\n\n\n\n");
                System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\t" + enemy.getName() + "'s Turn!" + RESET);
                executeEnemyAction();
            }

            if (player.hp < 0) player.hp = 0;
            if (enemy.hp < 0) enemy.hp = 0;

            if (!playerTurn) {
                // Save current mana before regen
                int playerManaBefore = player.mana;
                int enemyManaBefore = enemy.mana;

                // Regenerate 10-20 mana
                player.regenerateMana(10 + rand.nextInt(11));
                enemy.regenerateMana(10 + rand.nextInt(11));

                // Update cooldowns
                playerCD.reduceCooldowns();
                enemyCD.reduceCooldowns();

                // Calculate actual gain (0 if already full)
                int playerGain = player.mana - playerManaBefore;
                int enemyGain = enemy.mana - enemyManaBefore;

                // Only show message if someone actually gained mana
                if (playerGain > 0 || enemyGain > 0) {
                    System.out.print("\n" + BRIGHT_BLUE + "\t\t\t\t\t\t\t");

                    if (playerGain > 0) {
                        System.out.print("+" + playerGain + " mana (you)");
                    } else {
                        System.out.print("mana full (you)");
                    }

                    System.out.print(" | ");

                    if (enemyGain > 0) {
                        System.out.print("+" + enemyGain + " mana (" + enemy.getName() + ")");
                    } else {
                        System.out.print("mana full (" + enemy.getName() + ")");
                    }

                    System.out.println(RESET);
                }
            }
            playerTurn = !playerTurn;

            System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue...");
            System.out.print("\t\t\t\t\t\t\t> ");
            sc.nextLine();
            // Removed duplicate playSound("Hit.wav") here — already played in actions
        }

        clearScreen();
        return player.hp > 0;
    }

    private void displayTurnHeader() {
        System.out.println("\n\n\n\n");
        System.out.println("\n" + BRIGHT_YELLOW + "\t\t\t\t\t\t\t============================" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tTurn " + turnCount + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t============================" + RESET);
        turnCount++;
    }

    private void displayPlayerStats() {
        String playerHPColor = getHPColor(player.hp, player.maxHp);
        String enemyHPColor = getHPColor(enemy.hp, enemy.maxHp);
        System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\t" + player.getName() + "'s Turn" + RESET);
        
        // INLINE COOLDOWN DISPLAY (like screenshot)
        System.out.println("\t\t\t\t\t\t\tCooldowns: " +
            "S1: " + (playerCD.canUseSkill(1) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + playerCD.getFormattedCooldown(1)) + RESET +
            " | S2: " + (playerCD.canUseSkill(2) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + playerCD.getFormattedCooldown(2)) + RESET +
            " | S3: " + (playerCD.canUseSkill(3) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + playerCD.getFormattedCooldown(3)) + RESET);
        System.out.println("\t\t\t\t\t\t\t" + BRIGHT_GREEN + "HP: " + playerHPColor + player.hp + "/" + player.maxHp + RESET +
                        " | " + BRIGHT_BLUE + "Mana: " + player.mana + "/100" + RESET);
        System.out.println("\n\t\t\t\t\t\t\t" + BRIGHT_RED + "Enemy: " + enemy.getName() + RESET);
        System.out.println("\t\t\t\t\t\t\t" + BRIGHT_GREEN + "HP: " + enemyHPColor + enemy.hp + "/" + enemy.maxHp + RESET +
                        " | " + BRIGHT_BLUE + "Mana: " + enemy.mana + "/100" + RESET);
    }

    private void displaySkillOptions() {
        System.out.println("\n\t\t\t\t\t\t\tChoose your action (0-3):" + RESET);
        System.out.println("\t\t\t\t\t\t\t0. " + BRIGHT_YELLOW + "Basic Attack (no mana)" + RESET);
        System.out.println("\t\t\t\t\t\t\t1. " + BRIGHT_YELLOW + player.getSkill1() + " " + RESET + " - " + BRIGHT_BLUE + player.sk1Cost + " mana" + RESET +
            (!playerCD.canUseSkill(1) ? " (" + BRIGHT_RED + playerCD.getFormattedCooldown(1) + RESET + ")" : ""));
        System.out.println("\t\t\t\t\t\t\t2. " + BRIGHT_YELLOW + player.getSkill2()   + " " + RESET + " - " + BRIGHT_BLUE + player.sk2Cost + " mana" + RESET +
            (!playerCD.canUseSkill(2) ? " (" + BRIGHT_RED + playerCD.getFormattedCooldown(2) + RESET + ")" : ""));
        System.out.println("\t\t\t\t\t\t\t3. " + BRIGHT_YELLOW + player.getSkill3()  + " " + RESET + " - " + BRIGHT_BLUE + player.sk3Cost + " mana" + RESET +
            (!playerCD.canUseSkill(3) ? " (" + BRIGHT_RED + playerCD.getFormattedCooldown(3) + RESET + ")" : ""));
        System.out.print("\t\t\t\t\t\t\t> ");
    }

    // ✅ KEEP THIS EXACTLY AS IS — PRESSING ENTER SKIPS TURN
    private int getPlayerAction() {
        try {
            String input = sc.nextLine().trim(); // Read entire line
            if (input.isEmpty()) {
                System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tYou hesitated! Turn skipped." + RESET);
                return -1;
            }
            int action = Integer.parseInt(input);
            if (action >= 0 && action <= 3) {
                return action;
            }
        } catch (Exception e) {
            System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tInvalid input! Turn skipped." + RESET);
        }
        return -1;
    }

    private void executePlayerAction(int action) {
        if (action == -1) return; // Turn skipped

        if (action == 0) {
            int damage = calculateBasicAttackDamage(player.attack); // use player.attack, not hard-coded 15
            enemy.hp -= damage;
            System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\tBasic Attack! " + BRIGHT_RED + damage + " damage!" + RESET);
            playSound("Hit.wav");
        } else {
            int cost = action == 1 ? player.sk1Cost : (action == 2 ? player.sk2Cost : player.sk3Cost);
            int damage = action == 1 ? player.sk1Damage : (action == 2 ? player.sk2Damage : player.sk3Damage);

            if (!playerCD.canUseSkill(action)) {
                System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tSkill on cooldown! Turn lost." + RESET);
                return;
            }
            if (player.mana < cost) {
                System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tNot enough mana! Turn lost." + RESET);
                return;
            }

            enemy.hp -= damage;
            player.mana -= cost;
            playerCD.applyCooldown(action);
            String skill = action == 1 ? player.getSkill1() : (action == 2 ? player.getSkill2() : player.getSkill3());
            System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\t" + player.getName() + " uses " + skill + "!" + RESET);
            System.out.println(BRIGHT_RED + "\t\t\t\t\t\t\t" + damage + " damage!" + RESET);
            playSound("Hit.wav");
        }
    }

    private void executeEnemyAction() {
        int action = 0; // default to basic

        if (enemy.mana >= enemy.sk3Cost && enemyCD.canUseSkill(3)) {
            action = 3;
        } else if (enemy.mana >= enemy.sk2Cost && enemyCD.canUseSkill(2)) {
            action = 2;
        } else if (enemy.mana >= enemy.sk1Cost && enemyCD.canUseSkill(1)) {
            action = 1;
        }

        if (action == 0) {
            int damage = calculateBasicAttackDamage(enemy.attack);
            player.hp -= damage;
            System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\t" + enemy.getName() + " uses Basic Attack!" + RESET);
            System.out.println(BRIGHT_RED + "\t\t\t\t\t\t\t" + damage + " damage!" + RESET);
            playSound("Hit.wav");
        } else {
            int cost = action == 1 ? enemy.sk1Cost : (action == 2 ? enemy.sk2Cost : enemy.sk3Cost);
            int damage = action == 1 ? enemy.sk1Damage : (action == 2 ? enemy.sk2Damage : enemy.sk3Damage);
            String skill = action == 1 ? enemy.getSkill1() : (action == 2 ? enemy.getSkill2() : enemy.getSkill3());

            enemy.mana -= cost;
            enemyCD.applyCooldown(action);
            player.hp -= damage;
            System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\t" + enemy.getName() + " uses " + skill + "!" + RESET);
            System.out.println(BRIGHT_RED + "\t\t\t\t\t\t\t" + damage + " damage!" + RESET);
            playSound("Hit.wav");
        }
    }

    private void resetForRound() {
        player.hp = player.maxHp;
        player.mana = 100;
        playerCD.resetCooldowns();
        enemy.hp = enemy.maxHp;
        enemy.mana = 100;
        enemyCD.resetCooldowns();
        turnCount = 1;
    }

    private String getHPColor(int current, int max) {
        double pct = (double) current / max;
        if (pct > 0.5) return BRIGHT_GREEN;
        else if (pct > 0.25) return BRIGHT_YELLOW;
        else return BRIGHT_RED;
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
            System.out.println("\n".repeat(50));
        }
    }

    public void playSound(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) return;
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.addLineListener(e -> {
                if (e.getType() == LineEvent.Type.STOP) clip.close();
            });
            clip.start();
            if ("GameTheme.wav".equals(filename)) {
                this.backgroundMusicClip = clip;
            }
        } catch (Exception ignored) {}
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

    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
            backgroundMusicClip = null;
        }
    }
}
