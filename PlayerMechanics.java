import java.util.Scanner;
import java.util.Random;
import java.io.File;
import javax.sound.sampled.*;

public class PlayerMechanics {

    final String BRIGHT_GREEN = "\u001B[92m";
    final String BRIGHT_YELLOW = "\u001B[93m";
    final String BRIGHT_RED = "\u001B[91m";
    final String BRIGHT_BLUE = "\u001B[94m";
    final String RESET = "\u001B[0m";
    
    private Clip backgroundMusicClip;
    private Clip winSFXClip;

    private Character player1;
    private Character player2;
    private int turnCount = 1;
    private Scanner sc;
    private Random rand;
    private CooldownManager p1CD;
    private CooldownManager p2CD;
    
    private int player1RoundWins = 0;
    private int player2RoundWins = 0;
    private int currentRound = 1;
    private final int ROUNDS_TO_WIN = 2;
    private boolean player1StartsNextRound;

    public PlayerMechanics(Character player1, Character player2) {
        this.sc = new Scanner(System.in);
        this.rand = new Random();
        this.p1CD = new CooldownManager();
        this.p2CD = new CooldownManager();
        this.player1 = player1;
        this.player2 = player2;
        this.player1StartsNextRound = rand.nextBoolean();
    }

    private String getHPColor(int currentHP, int maxHP) {
        double percentage = (double) currentHP / maxHP;
        if (percentage > 0.5) return BRIGHT_GREEN;
        else if (percentage > 0.25) return BRIGHT_YELLOW;
        else return BRIGHT_RED;
    }

    public void game() {
        clearScreen();
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("\n" + BRIGHT_YELLOW + "\t\t\t\t\t\t\t==================================================" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tPVP MATCH START - Best of 3 Rounds" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tFirst to " + ROUNDS_TO_WIN + " wins the match!" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t==================================================" + RESET);

        System.out.println("\n\t\t\t\t\t\t\tPlayer 1: ");
        System.out.println("\t\t\t\t\t\t\t" + player1.getName() + " - " + BRIGHT_GREEN + "HP: " + player1.getHp() + "/" + player1.getMaxHp() + RESET + " | " + BRIGHT_BLUE + "Mana: " + player1.getMana() + "/100" + RESET);
        System.out.println("\n\t\t\t\t\t\t\tPlayer 2: ");
        System.out.println("\t\t\t\t\t\t\t" + player2.getName() + " - " + BRIGHT_GREEN + "HP: " + player2.getHp() + "/" + player2.getMaxHp() + RESET + " | " + BRIGHT_BLUE + "Mana: " + player2.getMana() + "/100" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t==================================================" + RESET);

        while (player1RoundWins < ROUNDS_TO_WIN && player2RoundWins < ROUNDS_TO_WIN) {
            playSound("GameTheme.wav");
            System.out.println("\n" + BRIGHT_BLUE + "\t\t\t\t\t\t\t--- ROUND " + currentRound + " ---" + RESET);
            System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tStarting Player: " + (player1StartsNextRound ? "Player 1 (" + player1.getName() + ")" : "Player 2 (" + player2.getName() + ")") + RESET);
            System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tPress ENTER to start the round..." + RESET);
            System.out.print("\t\t\t\t\t\t\t> ");
            sc.nextLine();

            resetRound();

            boolean roundWonByPlayer1 = playSingleRound();
            if (roundWonByPlayer1) {
                player1RoundWins++;
                player1StartsNextRound = true;
            } else {
                player2RoundWins++;
                player1StartsNextRound = false;
            }

            stopBackgroundMusic();
            winSFXClip = playAndStoreSound("WinSFX.wav");
            clearScreen();

            if (roundWonByPlayer1) {
                System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\t===================================" + RESET);
                System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\tPlayer 1: " + player1.getName() + " wins the round!" + RESET);
            } else {
                System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\t===================================" + RESET);
                System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\tPlayer 2: " + player2.getName() + " wins the round!" + RESET);
            }
            System.out.println(BRIGHT_GREEN + "\t\t\t\t\t\t\t===================================" + RESET);

            System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue...");
            System.out.print("\t\t\t\t\t\t\t> ");
            sc.nextLine();

            // Stop win sound
            if (winSFXClip != null && winSFXClip.isRunning()) {
                winSFXClip.stop();
                winSFXClip.close();
            }
            winSFXClip = null;

            System.out.println("\n\t\t\t\t\t\t\tScore: " + BRIGHT_GREEN + "P1: " + player1RoundWins + RESET + " - " + BRIGHT_RED + "P2: " + player2RoundWins + RESET);
            
            if (player1RoundWins < ROUNDS_TO_WIN && player2RoundWins < ROUNDS_TO_WIN) {
                System.out.println("\n\t\t\t\t\t\t\tPress ENTER to next round...");
                System.out.print("\t\t\t\t\t\t\t> ");
                sc.nextLine();
            }
            currentRound++;
        }

        displayMatchResult();
    }

    private void resetRound() {
        player1.setHp(player1.getMaxHp());
        player1.setMana(100);
        player2.setHp(player2.getMaxHp());
        player2.setMana(100);
        p1CD.resetCooldowns();
        p2CD.resetCooldowns();
        turnCount = 1;
    }

    private boolean playSingleRound() {
        boolean player1Turn = player1StartsNextRound;

        while (player1.getHp() > 0 && player2.getHp() > 0) {
            clearScreen();
            displayTurnHeader();
            displayPlayerStats();

            if (player1Turn) {
                System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\tPlayer 1: " + player1.getName() + "'s Turn" + RESET);
                playerAction(player1, player2, p1CD, "Player 1");
            } else {
                System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tPlayer 2: " + player2.getName() + "'s Turn" + RESET);
                playerAction(player2, player1, p2CD, "Player 2");
            }

            // Regenerate mana & reduce cooldowns AFTER EVERY FULL TURN (both players)
            if (!player1Turn) {
                int p1Regen = 10 + rand.nextInt(11);
                int p2Regen = 10 + rand.nextInt(11);
                player1.regenerateMana(p1Regen);
                player2.regenerateMana(p2Regen);
                p1CD.reduceCooldowns();
                p2CD.reduceCooldowns();
            }

            // Clamp HP to 0
            if (player1.getHp() < 0) player1.setHp(0);
            if (player2.getHp() < 0) player2.setHp(0);

            if (player1.getHp() <= 0 || player2.getHp() <= 0) break;

            player1Turn = !player1Turn;
            turnCount++;
        }

        clearScreen();
        return player1.getHp() > 0;
    }

    private void displayTurnHeader() {
        System.out.println("\n\n\n\n");
        System.out.println("\n" + BRIGHT_YELLOW + "\t\t\t\t\t\t\t============================" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\tTurn " + turnCount + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\t\t============================" + RESET);
    }

    private void displayPlayerStats() {
        String p1HPColor = getHPColor(player1.getHp(), player1.getMaxHp());
        String p2HPColor = getHPColor(player2.getHp(), player2.getMaxHp());

        System.out.println("\t\t\t\t\t\t\tPlayer 1: " + player1.getName() + " - Cooldowns: " +
            "S1: " + (p1CD.canUseSkill(1) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p1CD.getFormattedCooldown(1)) + RESET +
            " | S2: " + (p1CD.canUseSkill(2) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p1CD.getFormattedCooldown(2)) + RESET +
            " | S3: " + (p1CD.canUseSkill(3) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p1CD.getFormattedCooldown(3)) + RESET);
        System.out.println("\t\t\t\t\t\t\t" + BRIGHT_GREEN + "HP: " + p1HPColor + player1.getHp() + "/" + player1.getMaxHp() + RESET + 
                        " | " + BRIGHT_BLUE + "Mana: " + player1.getMana() + "/100" + RESET);

        System.out.println("\n\t\t\t\t\t\t\tPlayer 2: " + player2.getName() + " - Cooldowns: " +
            "S1: " + (p2CD.canUseSkill(1) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p2CD.getFormattedCooldown(1)) + RESET +
            " | S2: " + (p2CD.canUseSkill(2) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p2CD.getFormattedCooldown(2)) + RESET +
            " | S3: " + (p2CD.canUseSkill(3) ? BRIGHT_GREEN + "READY" : BRIGHT_RED + p2CD.getFormattedCooldown(3)) + RESET);
        System.out.println("\t\t\t\t\t\t\t" + BRIGHT_GREEN + "HP: " + p2HPColor + player2.getHp() + "/" + player2.getMaxHp() + RESET + 
                        " | " + BRIGHT_BLUE + "Mana: " + player2.getMana() + "/100" + RESET);
    }

    private void playerAction(Character attacker, Character target, CooldownManager cd, String playerLabel) {
        displaySkillOptions(attacker, cd);
        int action = getPlayerAction();

        if (action == -1) {
            System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue...");
            sc.nextLine();
            return;
        }

        if (action == 0) {
            int damage = calculateBasicAttackDamage(attacker.getAttack());
            target.setHp(target.getHp() - damage);
            System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\tBasic Attack! " + BRIGHT_RED + damage + " damage!" + RESET);
            playSound("Hit.wav");
        } else {
            useSkill(action, attacker, target, cd);
        }

        System.out.println("\n\t\t\t\t\t\t\tPress ENTER to continue...");
        sc.nextLine();
    }

    private void displaySkillOptions(Character attacker, CooldownManager cd) {
        System.out.println("\n\t\t\t\t\t\t\tChoose your action (0-3):" + RESET);
        System.out.println("\t\t\t\t\t\t\t0. " + BRIGHT_YELLOW + "Basic Attack (no mana)" + RESET);
        System.out.println("\t\t\t\t\t\t\t1. " + BRIGHT_YELLOW + attacker.getSkill1() + RESET + " - " + BRIGHT_BLUE + attacker.getSk1Cost() + " mana" + RESET +
            (!cd.canUseSkill(1) ? " (" + BRIGHT_RED + cd.getFormattedCooldown(1) + RESET + ")" : ""));
        System.out.println("\t\t\t\t\t\t\t2. " + BRIGHT_YELLOW + attacker.getSkill2() + RESET + " - " + BRIGHT_BLUE + attacker.getSk2Cost() + " mana" + RESET +
            (!cd.canUseSkill(2) ? " (" + BRIGHT_RED + cd.getFormattedCooldown(2) + RESET + ")" : ""));
        System.out.println("\t\t\t\t\t\t\t3. " + BRIGHT_YELLOW + attacker.getSkill3() + RESET + " - " + BRIGHT_BLUE + attacker.getSk3Cost() + " mana" + RESET +
            (!cd.canUseSkill(3) ? " (" + BRIGHT_RED + cd.getFormattedCooldown(3) + RESET + ")" : ""));
        System.out.print("\t\t\t\t\t\t\t> ");
    }

    private int getPlayerAction() {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tTurn skipped (no input)." + RESET);
            return -1;
        }
        try {
            int action = Integer.parseInt(input);
            if (action >= 0 && action <= 3) return action;
        } catch (NumberFormatException ignored) {}
        System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tInvalid input! Turn skipped." + RESET);
        return -1;
    }

    private boolean useSkill(int skillNumber, Character attacker, Character target, CooldownManager cd) {
        if (!cd.canUseSkill(skillNumber)) {
            System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tSkill on cooldown! Turn lost." + RESET);
            return false;
        }

        int cost, damage;
        String skillName;
        switch (skillNumber) {
            case 1:
                cost = attacker.getSk1Cost();
                damage = attacker.getSk1Damage();
                skillName = attacker.getSkill1();
                break;
            case 2:
                cost = attacker.getSk2Cost();
                damage = attacker.getSk2Damage();
                skillName = attacker.getSkill2();
                break;
            case 3:
                cost = attacker.getSk3Cost();
                damage = attacker.getSk3Damage();
                skillName = attacker.getSkill3();
                break;
            default:
                return false;
        }

        if (attacker.getMana() < cost) {
            System.out.println("\n" + BRIGHT_RED + "\t\t\t\t\t\t\tNot enough mana! Turn lost." + RESET);
            return false;
        }

        target.setHp(target.getHp() - damage);
        attacker.setMana(attacker.getMana() - cost);
        cd.applyCooldown(skillNumber);
        System.out.println("\n" + BRIGHT_GREEN + "\t\t\t\t\t\t\t" + attacker.getName() + " uses " + skillName + "!" + RESET);
        System.out.println(BRIGHT_RED + "\t\t\t\t\t\t\t" + damage + " damage!" + RESET);
        playSound("Hit.wav");
        return true;
    }

    private int calculateBasicAttackDamage(int baseAttack) {
        double multiplier = 0.8 + (0.4 * rand.nextDouble());
        return (int) Math.round(baseAttack * multiplier);
    }

    // ===== AUDIO & UTILS =====
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

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    // ===== END GAME =====
    private void displayMatchResult() {
        clearScreen();
        stopBackgroundMusic();
        winSFXClip = playAndStoreSound("WinSFX.wav");

        if (player1RoundWins >= ROUNDS_TO_WIN) {
            System.out.println("\t\t\t\t" + BRIGHT_GREEN + "Player 1: " + player1.getName() + " WINS THE MATCH!" + RESET);
        } else {
            System.out.println("\t\t\t\t" + BRIGHT_GREEN + "Player 2: " + player2.getName() + " WINS THE MATCH!" + RESET);
        }

        System.out.println("\n\t\t\t\t\t\t\tFinal Score: P1: " + player1RoundWins + " - P2: " + player2RoundWins);
        System.out.println("\n\t\t\t\t\t\t\tPress ENTER to return...");
        sc.nextLine();

        if (winSFXClip != null && winSFXClip.isRunning()) {
            winSFXClip.stop();
            winSFXClip.close();
        }
    }
}
