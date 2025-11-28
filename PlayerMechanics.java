import java.io.PrintStream;
import java.util.Scanner;
import java.util.Random;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

public class PlayerMechanics {

    // Color constants
    final String BLACK = "\u001B[30m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String BLUE = "\u001B[34m";
    final String PURPLE = "\u001B[35m";
    final String CYAN = "\u001B[36m";
    final String WHITE = "\u001B[37m";
    final String BRIGHT_GREEN = "\u001B[92m";
    final String BRIGHT_YELLOW = "\u001B[93m";
    final String BRIGHT_RED = "\u001B[91m";
    final String BRIGHT_BLUE = "\u001B[94m";
    final String RESET = "\u001B[0m";
    private Clip backgroundMusicClip;
    Character player1;
    Character player2;
    public int turnCount = 1;
    private Scanner sc;
    private Random rand;
    private CooldownManager p1CD;
    private CooldownManager p2CD;
    private static final int MAX_MANA = 100;
    
    private int player1RoundWins = 0;
    private int player2RoundWins = 0;
    private int currentRound = 1;
    private final int MAX_ROUNDS = 5;
    private final int ROUNDS_TO_WIN = 2;
    private boolean player1StartsNextRound;

    public PlayerMechanics(Character var1, Character var2) {
        this.sc = new Scanner(System.in);
        this.rand = new Random();
        this.p1CD = new CooldownManager();
        this.p2CD = new CooldownManager();
        this.player1 = var1;
        this.player2 = var2;
        this.backgroundMusicClip = null;
        this.player1StartsNextRound = rand.nextBoolean();
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

    public void game() {
        clearScreen();
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("\n" + BRIGHT_YELLOW + "\t\t\t\t\t\t\t==================================================" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tPVP MATCH START - Best of 3 Rounds" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tFirst to " + ROUNDS_TO_WIN + " wins the match!" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t==================================================" + RESET);

        System.out.println("\n\t\t\t\t\t\t\tPlayer 1: ");
        System.out.println("\t\t\t\t\t\t\t" + player1.getName() + " - " + BRIGHT_GREEN + "HP: " + player1.hp + "/" + player1.maxHp + RESET + " | " + BRIGHT_BLUE + "Mana: " + player1.mana + "/100" + RESET);
        System.out.println("\n\t\t\t\t\t\t\tPlayer 2: ");
        System.out.println("\t\t\t\t\t\t\t" + player2.getName() + " - " + BRIGHT_GREEN + "HP: " + player2.hp + "/" + player2.maxHp + RESET + " | " + BRIGHT_BLUE + "Mana: " + player2.mana + "/100" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t==================================================" + RESET);

        while (player1RoundWins < ROUNDS_TO_WIN && player2RoundWins < ROUNDS_TO_WIN && currentRound <= MAX_ROUNDS) {
            boolean roundWonByPlayer1 = playRound();
            
            if (roundWonByPlayer1) {
                player1RoundWins++;
                player1StartsNextRound = true;
                stopBackgroundMusic();
                playSound("WinSFX.wav");
                System.err.println("\n\n\n\n\n\n\n");
                System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\t===================================" + RESET);
                System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\tPlayer 1 defeated " + player2.getName() + "!" + RESET);
                System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\t===================================" + RESET);

                System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue...");
                System.out.print("\t\t\t\t\t\t\t> ");
                sc.nextLine();
                stopBackgroundMusic();
                clearScreen();
            } else {
                player2RoundWins++;
                player1StartsNextRound = false;
                stopBackgroundMusic();
                playSound("WinSFX.wav");

                System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\t===================================" + RESET);
                System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\tPlayer 2 defeated " + player1.getName() + "!" + RESET);
                System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\t===================================" + RESET);

                System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue...");
                System.out.print("\t\t\t\t\t\t\t> ");
                sc.nextLine();
                clearScreen();
            }
            
            System.out.print("\t\n\n\n\n\n ");
            System.out.println("\n\t\t\t\t\t\t\tScore: " + BRIGHT_GREEN + "Player 1: " + player1RoundWins + RESET + " - " + BRIGHT_RED + "Player 2: " + player2RoundWins + RESET);

            if (player1RoundWins < ROUNDS_TO_WIN && player2RoundWins < ROUNDS_TO_WIN) {
                playSound("GameTheme.wav");
                System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue to next round...");
                System.out.print("\t\t\t\t\t\t\t> ");
                sc.nextLine();
            }

            stopBackgroundMusic();
            currentRound++;
        }

        displayMatchResult();
    }

    private boolean playRound() {
        playSound("GameTheme.wav");
        System.out.println("\n" + BRIGHT_BLUE + "\t\t\t\t\t\t\t--- ROUND " + currentRound + " ---" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tStarting Player: " + (player1StartsNextRound ? "Player 1" : "Player 2") + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tPress ENTER to start the round..." + RESET);
        System.out.print("\t\t\t\t\t\t\t> ");
        sc.nextLine();

        resetRound();
        boolean roundWonByPlayer1 = playSingleRound();
        
        return roundWonByPlayer1;
    }

    private boolean playSingleRound() {
        turnCount = 1;
        boolean player1Turn = player1StartsNextRound;
        
        while (this.player1.hp > 0 && this.player2.hp > 0) {
            if (player1Turn) {
                clearScreen();
                displayTurnHeader();
                displayPlayerStats();
                System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\t" + player1.getName() + "'s Turn" + RESET);
                this.playerAction(this.player1, this.player2, this.p1CD, "Player 1");
            } else {
                clearScreen();
                displayTurnHeader();
                displayPlayerStats();
                System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\t" + player2.getName() + "'s Turn" + RESET);
                this.playerAction(this.player2, this.player1, this.p2CD, "Player 2");
                
                this.p1CD.reduceCooldowns();
                this.p2CD.reduceCooldowns();
                this.player1.regenerateMana(10);
                this.player2.regenerateMana(10);
            }

            if (this.player1.hp <= 0 || this.player2.hp <= 0) {
                break;
            }

            player1Turn = !player1Turn;
            turnCount++;
        }
        
        clearScreen();
        return player1.hp > 0 && player2.hp <= 0;
    }

    private void displayTurnHeader() {
        System.out.println("\n\n\n\n");
        System.out.println("\n" + BRIGHT_YELLOW + "\t\t\t\t\t\t\t============================" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tTurn " + turnCount + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t============================" + RESET);
    }

    private void displayPlayerStats() {
        String p1HPColor = getHPColor(player1.hp, player1.maxHp);
        String p2HPColor = getHPColor(player2.hp, player2.maxHp);
        
        System.out.println("\t\t\t\t\t\t\tPlayer 1 - Cooldowns: " +
            "S1: " + (p1CD.canUseSkill(1) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p1CD.getFormattedCooldown(1)) + RESET +
            " | S2: " + (p1CD.canUseSkill(2) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p1CD.getFormattedCooldown(2)) + RESET +
            " | S3: " + (p1CD.canUseSkill(3) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p1CD.getFormattedCooldown(3)) + RESET);
        System.out.println("\t\t\t\t\t\t\t" + BRIGHT_GREEN + "HP: " + p1HPColor + player1.hp + "/" + player1.maxHp + RESET + 
                        " | " + BRIGHT_BLUE + "Mana: " + player1.mana + "/100" + RESET);
        
        System.out.println("\n\t\t\t\t\t\t\t" + BRIGHT_RED + "Player 2: " + player2.getName() + RESET);
        System.out.println("\t\t\t\t\t\t\tPlayer 2 - Cooldowns: " +
            "S1: " + (p2CD.canUseSkill(1) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p2CD.getFormattedCooldown(1)) + RESET +
            " | S2: " + (p2CD.canUseSkill(2) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p2CD.getFormattedCooldown(2)) + RESET +
            " | S3: " + (p2CD.canUseSkill(3) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p2CD.getFormattedCooldown(3)) + RESET);
        System.out.println("\t\t\t\t\t\t\t" + BRIGHT_GREEN + "HP: " + p2HPColor + player2.hp + "/" + player2.maxHp + RESET + 
                        " | " + BRIGHT_BLUE + "Mana: " + player2.mana + "/100" + RESET);
    }

    private void resetRound() {
        player1.hp = player1.maxHp;
        player1.mana = 100;
        player2.hp = player2.maxHp;
        player2.mana = 100;
        
        p1CD.resetCooldowns();
        p2CD.resetCooldowns();
        turnCount = 1;
    }

    private void displayMatchResult() {
        clearScreen();
        stopBackgroundMusic();

        if (player1RoundWins >= ROUNDS_TO_WIN) {
            playSound("WinSFX.wav");
            
            // KEEPING THE ASCII ART
            System.out.println("\t\t\t\t" + GREEN + " _____                                                                    _____ " + RESET);
            System.out.println("\t\t\t\t" + GREEN + "( ___ )------------------------------------------------------------------( ___ )" + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   |                                                                    |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   |  ____  _                           _    __        ___           _  |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   | |  _ \\| | __ _ _   _  ___ _ __    / |   \\ \\      / (_)_ __  ___| | |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   | | |_) | |/ _` | | | |/ _ \\ '__|   | |    \\ \\ /\\ / /| | '_ \\/ __| | |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   | |  __/| | (_| | |_| |  __/ |      | |     \\ V  V / | | | | \\__ \\_| |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   | |_|   |_|\\__,_|\\__, |\\___|_|      |_|      \\_/\\_/  |_|_| |_|___(_) |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   |                |___/                                               |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |___|                                                                    |___| " + RESET);
            System.out.println("\t\t\t\t" + GREEN + "(_____)------------------------------------------------------------------(_____)" + RESET);

            System.out.println("\t\t\t\t\t\t" + BRIGHT_GREEN + "PLAYER 1 WINS THE MATCH!" + RESET);
        
        } else {
            playSound("WinSFX.wav");
            
            // KEEPING THE ASCII ART
            System.out.println("\t\t\t\t" + GREEN + " _____                                                                    _____ " + RESET);
            System.out.println("\t\t\t\t" + GREEN + "( ___ )------------------------------------------------------------------( ___ )" + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   |                                                                    |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   |  ____  _                         ____   __        ___           _  |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   | |  _ \\| | __ _ _   _  ___ _ __  |___ \\  \\ \\      / (_)_ __  ___| | |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   | | |_) | |/ _` | | | |/ _ \\ '__|   __) |  \\ \\ /\\ / /| | '_ \\/ __| | |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   | |  __/| | (_| | |_| |  __/ |     / __/    \\ V  V / | | | | \\__ \\_| |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   | |_|   |_|\\__,_|\\__, |\\___|_|    |_____|    \\_/\\_/  |_|_| |_|___(_) |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |   |                |___/                                               |   | " + RESET);
            System.out.println("\t\t\t\t" + GREEN + " |___|                                                                    |___| " + RESET);
            System.out.println("\t\t\t\t" + GREEN + "(_____)------------------------------------------------------------------(_____)" + RESET);
            System.out.println("\t\t\t\t\t\t" + BRIGHT_GREEN + "PLAYER 2 WINS THE MATCH!" + RESET);
        }

        System.out.println("\n\t\t\t\t\t\t\tFinal Score: " + BRIGHT_GREEN + "Player 1: " + player1RoundWins + RESET + " - " + BRIGHT_RED + "Player 2: " + player2RoundWins + RESET);
        System.out.println("\n\t\t\t\t\t\t\tPress ENTER to return...");
        System.out.print("\t\t\t\t\t\t\t");
        sc.nextLine();
        System.err.println("\n\n\n");
    }

    private int calculateBasicAttackDamage(int baseAttack) {
        double minMultiplier = 0.8;
        double maxMultiplier = 1.2;
        double multiplier = minMultiplier + (maxMultiplier - minMultiplier) * rand.nextDouble();
        return (int) Math.round(baseAttack * multiplier);
    }

    private void useSkill(int skillNumber, Character attacker, Character target, CooldownManager cooldownManager) {
        if (!cooldownManager.canUseSkill(skillNumber)) {
            System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tSkill on cooldown! Turn lost." + RESET);
            return;
        }

        String skillName = "";
        int manaCost;
        int damage;
        
        switch (skillNumber) {
            case 1:
                manaCost = attacker.sk1Cost;
                damage = attacker.sk1Damage;
                skillName = attacker.getSkill1();
                break;
            case 2:
                manaCost = attacker.sk2Cost;
                damage = attacker.sk2Damage;
                skillName = attacker.getSkill2();
                break;
            case 3:
                manaCost = attacker.sk3Cost;
                damage = attacker.sk3Damage;
                skillName = attacker.getSkill3();
                break;
            default:
                System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tInvalid skill number!" + RESET);
                return;
        }

        if (attacker.mana < manaCost) {
            System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tNot enough mana! Turn lost." + RESET);
            return;
        }

        target.hp -= damage;
        attacker.mana -= manaCost;
        cooldownManager.applyCooldown(skillNumber);
        
        System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\t" + attacker.getName() + " uses " + skillName + "!" + RESET);
        System.out.println(BRIGHT_RED + "\t\t\t\t\t\t\t" + damage + " damage!" + RESET);
        playSound("Hit.wav");
    }

    private void displaySkillOptions(Character attacker, CooldownManager cooldownManager) {
        System.out.println("\n\t\t\t\t\t\t\tChoose your action:" + RESET);
        System.out.println("\t\t\t\t\t\t\t0. "+BRIGHT_YELLOW +"Basic Attack (no mana)"+RESET);
        String s1Cooldown = cooldownManager.canUseSkill(1) ? "" : " (" + cooldownManager.getFormattedCooldown(1) + ")";
        System.out.println("\t\t\t\t\t\t\t1. " + BRIGHT_YELLOW + attacker.getSkill1() + " - Deals " + attacker.sk1Damage + " damage - " + BRIGHT_BLUE + attacker.sk1Cost + " mana" + RESET + s1Cooldown);
        String s2Cooldown = cooldownManager.canUseSkill(2) ? "" : " (" + cooldownManager.getFormattedCooldown(2) + ")";
        System.out.println("\t\t\t\t\t\t\t2. "+BRIGHT_YELLOW + attacker.getSkill2() + " - Deals " + attacker.sk2Damage + " damage - " + BRIGHT_BLUE + attacker.sk2Cost + " mana" + RESET + s2Cooldown);
        String s3Cooldown = cooldownManager.canUseSkill(3) ? "" : " (" + cooldownManager.getFormattedCooldown(3) + ")";
        System.out.println("\t\t\t\t\t\t\t3. "+BRIGHT_YELLOW + attacker.getSkill3() + " - Deals " + attacker.sk3Damage + " damage - " + BRIGHT_BLUE + attacker.sk3Cost + " mana" + RESET + s3Cooldown);
        System.out.print("\t\t\t\t\t\t\t> ");
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
        System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tInvalid input! Turn skipped." + RESET);
        return -1;
    }

    private void playerAction(Character attacker, Character target, CooldownManager cooldownManager, String playerLabel) {
        displaySkillOptions(attacker, cooldownManager);
        int action = getPlayerAction();

        if (action == -1) return;

        if (action == 0) {
            int damage = calculateBasicAttackDamage(15);
            target.hp -= damage;
            System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\tBasic Attack! " + BRIGHT_RED + damage + " damage!" + RESET);
            playSound("Hit.wav");
        } else {
            useSkill(action, attacker, target, cooldownManager);
        }

        if (target.hp < 0) target.hp = 0;
        if (attacker.hp < 0) attacker.hp = 0;

        System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue...");
        System.out.print("\t\t\t\t\t\t\t> ");
        sc.nextLine();
        playSound("Hit.wav");
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

    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
            backgroundMusicClip = null;
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
            System.out.println("\n".repeat(50));
        }
    }
}
