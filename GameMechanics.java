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
    private static final int MAX_MANA = 100;

    private Character player;
    private Enemy enemy;
    private int turnCount;
    private Random rand = new Random();
    private CooldownManager playerCD = new CooldownManager();
    private CooldownManager enemyCD = new CooldownManager();
    private Scanner sc = new Scanner(System.in);
    private Clip backgroundMusicClip;
    private Clip winSFXClip;
    private Clip loseSFXClip;

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

    public void startMatch() {
        playSound("GameTheme.wav");

        System.out.println("\n\t\t\t\t" + BRIGHT_YELLOW + "========== PLAYER vs AI ==========" + RESET);
        System.out.println("\t\t\t\t" + BRIGHT_YELLOW + "Best of 3 Rounds  - First to 2 Wins!" + RESET);
        System.out.println("\t\t\t\t" + BRIGHT_BLUE + "==================================" + RESET);

        int playerWins = 0;
        int enemyWins = 0;
        int round = 1;

        while (playerWins < 2 && enemyWins < 2) {
            System.out.println("\n\t\t\t\t" + BRIGHT_GREEN + "ROUND " + round + " - FIGHT!" + RESET);
            System.out.println("\t\t\t\tPress ENTER to begin...");
            System.out.print("\t\t\t\t");
            sc.nextLine();

            resetForRound();

            if (playRound()) {
                playerWins++;
                stopBackgroundMusic();
                winSFXClip = playAndStoreSound("WinSFX.wav");

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

                // Stop win sound immediately
                if (winSFXClip != null && winSFXClip.isRunning()) {
                    winSFXClip.stop();
                    winSFXClip.close();
                }
                winSFXClip = null;

                clearScreen();
            } else {
                enemyWins++;
                stopBackgroundMusic();
                loseSFXClip = playAndStoreSound("LoseSFX.wav");

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

                // Stop lose sound immediately
                if (loseSFXClip != null && loseSFXClip.isRunning()) {
                    loseSFXClip.stop();
                    loseSFXClip.close();
                }
                loseSFXClip = null;

                clearScreen();
            }

            System.out.println("\t\t\t\tScore: " + BRIGHT_GREEN + playerWins + RESET + " - " + BRIGHT_RED + enemyWins + RESET);

            if (playerWins < 2 && enemyWins < 2) {
                System.out.println("\t\t\t\tPress ENTER to continue...");
                System.out.print("\t\t\t\t");
                sc.nextLine();
            }

            stopBackgroundMusic();
            round++;
        }

        clearScreen();
        stopBackgroundMusic();

        if (playerWins >= 2) {
            winSFXClip = playAndStoreSound("WinSFX.wav");
            System.out.println("\n\t\t\t\t" + BRIGHT_GREEN + " VICTORY! " + RESET);
            System.out.println("\t\t\t\tYou defeated " + enemy.getName() + "!");
        } else {
            loseSFXClip = playAndStoreSound("LoseSFX.wav");
            System.out.println("\n\t\t\t\t" + BRIGHT_RED + " DEFEAT! " + RESET);
            System.out.println("\t\t\t\t" + enemy.getName() + " defeated you.");
        }

        System.out.println("\n\t\t\t\tPress ENTER to return...");
        sc.nextLine();

        // Stop final result sound
        if (winSFXClip != null && winSFXClip.isRunning()) {
            winSFXClip.stop();
            winSFXClip.close();
        }
        if (loseSFXClip != null && loseSFXClip.isRunning()) {
            loseSFXClip.stop();
            loseSFXClip.close();
        }
    }

    private boolean playRound() {
        boolean playerTurn = true;
        turnCount = 1;

        displayBattleStart();

        while (player.hp > 0 && enemy.hp > 0) {
            if (playerTurn) {
                clearScreen();
                displayTurnHeader();
                displayPlayerStats();
                displaySkillOptions();

                int action = getPlayerAction();
                executePlayerAction(action);
            } else {
                clearScreen();
                System.out.println("\n\t\t\t\t" + BRIGHT_RED + enemy.getName() + "'s Turn!" + RESET);
                executeEnemyAction();
            }

            if (player.hp < 0) player.hp = 0;
            if (enemy.hp < 0) enemy.hp = 0;

            if (!playerTurn) {
                player.regenerateMana(10);
                enemy.regenerateMana(10);
                playerCD.reduceCooldowns();
                enemyCD.reduceCooldowns();
            }

            playerTurn = !playerTurn;

            System.out.println("\n\t\t\t\tPress ENTER to continue...");
            System.out.print("\t\t\t\t");
            sc.nextLine();
        }

        clearScreen();
        return player.hp > 0;
    }

    private void displayBattleStart() {
        System.out.println("\n\t\t\t\t" + BRIGHT_BLUE + "========================" + RESET);
        System.out.println("\t\t\t\t" + BRIGHT_GREEN + player.getName() + RESET + " vs " + BRIGHT_RED + enemy.getName() + RESET);
        System.out.println("\t\t\t\t" + BRIGHT_BLUE + "========================" + RESET);
    }

    private void displayTurnHeader() {
        System.out.println("\n\t\t\t\t" + BRIGHT_BLUE + "========================" + RESET);
        System.out.println("\t\t\t\t" + BRIGHT_YELLOW + "Turn " + turnCount + RESET);
        System.out.println("\t\t\t\t" + BRIGHT_BLUE + "========================" + RESET);
        turnCount++;
    }

    private void displayPlayerStats() {
        String playerHPColor = getHPColor(player.hp, player.maxHp);
        String enemyHPColor = getHPColor(enemy.hp, enemy.maxHp);
        System.out.println("\n\t\t\t\t" + BRIGHT_GREEN + player.getName() + ": " + playerHPColor + player.hp + "/" + player.maxHp + " HP" + RESET
                + " | " + BRIGHT_BLUE + player.mana + "/" + MAX_MANA + " Mana" + RESET);
        System.out.println("\t\t\t\t" + BRIGHT_RED + enemy.getName() + ": " + enemyHPColor + enemy.hp + "/" + enemy.maxHp + " HP" + RESET
                + " | " + BRIGHT_BLUE + enemy.mana + "/" + MAX_MANA + " Mana" + RESET);
    }

    private void displaySkillOptions() {
        System.out.println("\n\t\t\t\t" + BRIGHT_YELLOW + "ACTIONS:" + RESET);
        System.out.println("\t\t\t\t0. Basic Attack (no mana)");
        System.out.println("\t\t\t\t1. " + player.getSkill1() + " (" + player.sk1Cost + " mana) - " + (playerCD.canUseSkill(1) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + playerCD.getFormattedCooldown(1)) + RESET);
        System.out.println("\t\t\t\t2. " + player.getSkill2() + " (" + player.sk2Cost + " mana) - " + (playerCD.canUseSkill(2) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + playerCD.getFormattedCooldown(2)) + RESET);
        System.out.println("\t\t\t\t3. " + player.getSkill3() + " (" + player.sk3Cost + " mana) - " + (playerCD.canUseSkill(3) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + playerCD.getFormattedCooldown(3)) + RESET);
        System.out.print("\t\t\t\t> ");
    }

    private int getPlayerAction() {
        try {
            int action = sc.nextInt();
            sc.nextLine();
            if (action >= 0 && action <= 3) {
                return action;
            }
        } catch (Exception e) {
            sc.nextLine();
        }
        System.out.println("\t\t\t\t" + BRIGHT_RED + "Invalid! Turn skipped." + RESET);
        return -1;
    }

    private void executePlayerAction(int action) {
        if (action == -1) return;

        if (action == 0) {
            int damage = 10 + rand.nextInt(11);
            enemy.hp -= damage;
            System.out.println("\n\t\t\t\t" + BRIGHT_GREEN + "Basic Attack! " + BRIGHT_RED + damage + " damage!" + RESET);
        } else {
            int cost = action == 1 ? player.sk1Cost : (action == 2 ? player.sk2Cost : player.sk3Cost);
            int damage = action == 1 ? player.sk1Damage : (action == 2 ? player.sk2Damage : player.sk3Damage);

            if (!playerCD.canUseSkill(action)) {
                System.out.println("\n\t\t\t\t" + BRIGHT_RED + "Skill on cooldown! Turn lost." + RESET);
                return;
            }
            if (player.mana < cost) {
                System.out.println("\n\t\t\t\t" + BRIGHT_RED + "Not enough mana! Turn lost." + RESET);
                return;
            }

            enemy.hp -= damage;
            player.mana -= cost;
            playerCD.applyCooldown(action);
            String skill = action == 1 ? player.getSkill1() : (action == 2 ? player.getSkill2() : player.getSkill3());
            System.out.println("\n\t\t\t\t" + BRIGHT_GREEN + player.getName() + " uses " + skill + "!" + RESET);
            System.out.println("\t\t\t\t" + BRIGHT_RED + damage + " damage!" + RESET);
        }
    }

    private void executeEnemyAction() {
        int action = 1;

        if (enemy.mana >= enemy.sk3Cost && enemyCD.canUseSkill(3)) {
            action = 3;
        } else if (enemy.mana >= enemy.sk2Cost && enemyCD.canUseSkill(2)) {
            action = 2;
        } else if (enemy.mana >= enemy.sk1Cost && enemyCD.canUseSkill(1)) {
            action = 1;
        }

        if (action == 1) {
            int damage = 10 + rand.nextInt(11);
            player.hp -= damage;
            System.out.println("\n\t\t\t\t" + BRIGHT_RED + enemy.getName() + " uses Basic Attack!" + RESET);
            System.out.println("\t\t\t\t" + BRIGHT_RED + damage + " damage!" + RESET);
        } else {
            int cost = action == 1 ? enemy.sk1Cost : (action == 2 ? enemy.sk2Cost : enemy.sk3Cost);
            int damage = action == 1 ? enemy.sk1Damage : (action == 2 ? enemy.sk2Damage : enemy.sk3Damage);
            String skill = action == 1 ? enemy.getSkill1() : (action == 2 ? enemy.getSkill2() : enemy.getSkill3());

            enemy.mana -= cost;
            enemyCD.applyCooldown(action);
            player.hp -= damage;
            System.out.println("\n\t\t\t\t" + BRIGHT_RED + enemy.getName() + " uses " + skill + "!" + RESET);
            System.out.println("\t\t\t\t" + BRIGHT_RED + damage + " damage!" + RESET);
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

    // === Sound Methods ===

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
