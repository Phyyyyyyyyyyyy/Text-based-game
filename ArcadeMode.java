
import java.util.Scanner;

public class ArcadeMode {

    private Character player;
    private int matchWins = 0;
    private int totalArcadeWins = 0;
    private Scanner sc = new Scanner(System.in);

    private final String BRIGHT_BLUE = "\u001B[94m";
    private final String BRIGHT_GREEN = "\u001B[92m";
    private final String BRIGHT_RED = "\u001B[91m";
    private final String BRIGHT_YELLOW = "\u001B[93m";
    private final String RESET = "\u001B[0m";

    public ArcadeMode(Character player) {
        this.player = player;
    }

    public void startArcadeRun() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\n" + BRIGHT_BLUE + "\t\t\t\t\t\t========== WELCOME TO ARCADE MODE ==========" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\tWin 5 consecutive matches to beat Arcade Mode!" + RESET);
        System.out.println(BRIGHT_YELLOW + "\t\t\t\t\t\tEach match is Best of 3 (First to 2 wins)" + RESET);
        System.out.println(BRIGHT_BLUE + "\t\t\t\t\t\t==========================================\n" + RESET);

        // Display player stats overview
        System.out.println("\t\t\t\t\t\t" + BRIGHT_GREEN + "YOUR CHARACTER OVERVIEW:" + RESET);
        System.out.println("\t\t\t\t\t\t" + BRIGHT_GREEN + "Name: " + RESET + player.getName());
        System.out.println("\t\t\t\t\t\t" + BRIGHT_GREEN + "HP: " + RESET + player.hp + "/" + player.maxHp);
        System.out.println("\t\t\t\t\t\t" + BRIGHT_GREEN + "Mana: " + RESET + "10/100 (Range, 5-15 regeneration per turn)");
        System.out.println("\t\t\t\t\t\t" + BRIGHT_GREEN + "Attack: " + RESET + player.attack + " (Damage randomly multiplied by 0.8 to 1.2)");
        System.out.println("\t\t\t\t\t\t" + BRIGHT_GREEN + "\n\t\t\t\t\t\tSKILLS:" + RESET);
        System.out.println("\t\t\t\t\t\t1. " + player.getSkill1() + " - " + BRIGHT_BLUE + player.sk1Cost + " mana" + RESET);
        System.out.println("\t\t\t\t\t\t2. " + player.getSkill2() + " - " + BRIGHT_BLUE + player.sk2Cost + " mana" + RESET);
        System.out.println("\t\t\t\t\t\t3. " + player.getSkill3() + " - " + BRIGHT_BLUE + player.sk3Cost + " mana" + RESET);
        System.out.println("\n\t\t\t\t\t\t" + BRIGHT_BLUE + "==========================================" + RESET);

        EnemyHierarchy.displayAllEnemies();

        player.hp = player.maxHp;
        player.mana = 10;

        for (int match = 0; match < 5; match++) {
            EnemyHierarchy.Difficulty difficulty;
            if (match == 0) {
                difficulty = EnemyHierarchy.Difficulty.EASY;
            } else if (match == 1 || match == 2) {
                difficulty = EnemyHierarchy.Difficulty.MEDIUM;
            } else if (match == 3) {
                difficulty = EnemyHierarchy.Difficulty.HARD;
            } else {
                difficulty = EnemyHierarchy.Difficulty.BOSS;
            }

            boolean won = playBestOf3Match(difficulty, match + 1);
            if (!won) {
                System.out.println("\n\t\t\t\t\t\t" + BRIGHT_RED + "Arcade run failed! Try again." + RESET);
                matchWins = 0;
                break;
            }

            player.hp = player.maxHp;
            player.mana = 10;

            System.out.println("\n\t\t\t\t\t\t" + BRIGHT_YELLOW + "Press ENTER to continue to the next match..." + RESET);
            System.out.print("\t\t\t\t\t\t");
            sc.nextLine();
        }

        displayArcadeModeBeat();
    }

    private boolean playBestOf3Match(EnemyHierarchy.Difficulty difficulty, int matchNumber) {
        Enemy enemy = EnemyHierarchy.getEnemyByDifficulty(difficulty);

        System.out.println("\n\t\t\t\t\t\t" + BRIGHT_BLUE + "========== MATCH " + matchNumber + " ==========" + RESET);
        System.out.println("\t\t\t\t\t\t" + BRIGHT_YELLOW + "Difficulty: " + difficulty + RESET);
        System.out.println("\t\t\t\t\t\tOpponent: " + BRIGHT_RED + enemy.getName() + RESET);
        System.out.println("\t\t\t\t\t\t" + BRIGHT_BLUE + "==============================\n" + RESET);

        int playerRoundWins = 0;
        int enemyRoundWins = 0;
        int roundNumber = 1;

        // Play rounds until someone wins 2 (or max 5 rounds)
        while (playerRoundWins < 2 && enemyRoundWins < 2 && roundNumber <= 5) {
            playRound(roundNumber, enemy);

            // Check who won the round (based on HP)
            if (player.hp > 0 && enemy.hp <= 0) {
                playerRoundWins++;
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("\n\t\t\t\t\t\t" + BRIGHT_GREEN + "ROUND " + roundNumber + " WON!" + RESET);
                totalArcadeWins++;
            } else if (enemy.hp > 0 && player.hp <= 0) {
                enemyRoundWins++;
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("\n\t\t\t\t\t\t" + BRIGHT_RED + "ROUND " + roundNumber + " LOST!" + RESET);
            } else {
                // Draw - doesn't count
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("\n\t\t\t\t\t\t" + BRIGHT_YELLOW + "ROUND " + roundNumber + " DRAW!" + RESET);
            }

            displayRoundScore(playerRoundWins, enemyRoundWins, roundNumber);

            if (playerRoundWins < 2 && enemyRoundWins < 2 && roundNumber < 5) {
                System.out.println("\n\t\t\t\t\t\tPress ENTER for the next round...");
                System.out.print("\n\t\t\t\t\t\t>");
                sc.nextLine();
                resetRound(enemy);
            }

            roundNumber++;
        }

        if (playerRoundWins >= 2) {
            matchWins++;
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("\n\t\t\t\t\t\t" + BRIGHT_GREEN + "MATCH WON! Consecutive Wins: " + matchWins + "/5" + RESET);
            return true;
        } else {
            matchWins = 0;
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("\n\t\t\t\t\t\t" + BRIGHT_RED + "MATCH LOST! Arcade Streak Reset to 0" + RESET);
            return false;
        }
    }

    private void playRound(int roundNumber, Enemy enemy) {
        System.out.println("\n\t\t\t\t\t\t\t" + BRIGHT_BLUE + "--- ROUND " + roundNumber + " ---" + RESET);
        System.out.println("\t\t\t\t\t\t\t" + BRIGHT_YELLOW + "\n\t\t\t\t\t\tPress ENTER to start the round..." + RESET);
        System.out.print("\t\t\t\t\t\t");
        sc.nextLine();

        player.mana = 10;
        enemy.mana = 10;

        try {
            Thread.sleep(1000); // Add 1 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ArcadeMechanics mechanics = new ArcadeMechanics(player, enemy);
        mechanics.game();

    

    //////////naa ra HAHAHAHADHHA 
    }

    private void resetRound(Enemy enemy) {
        player.hp = player.maxHp;
        player.mana = 10;

        enemy.hp = enemy.maxHp;
        enemy.mana = 10;
    }

    

    private void displayRoundScore(int playerWins, int enemyWins, int currentRound) {
        System.out.println("\n\t\t\t\t\t\t" + BRIGHT_BLUE + "Round Score: " + BRIGHT_GREEN + playerWins + RESET
                + " - " + BRIGHT_RED + enemyWins + RESET);
        if (currentRound < 5) {
            System.out.println("\t\t\t\t\t\t(First to 2 wins the match)");
        }
    }

    private void displayArcadeModeBeat() {
        System.out.println("\n\n\t\t\t\t\t\t" + BRIGHT_BLUE + "========================================" + RESET);
        System.out.println("\t\t\t\t\t\t" + BRIGHT_BLUE + "    ARCADE MODE STREAK FINISHED!" + RESET);
        System.out.println("\t\t\t\t\t\t" + BRIGHT_BLUE + "========================================" + RESET);
        System.out.println("\t\t\t\t\t\t" + BRIGHT_YELLOW + "Total Matches Won: " + matchWins + RESET);
        System.out.println("\t\t\t\t\t\t" + BRIGHT_YELLOW + "Total Rounds Won: " + totalArcadeWins + RESET);
        System.out.println("\t\t\t\t\t\t" + BRIGHT_BLUE + "========================================\n" + RESET);
    }

   

    public boolean isArcadeModeBeat() {
        return matchWins >= 5;
    }

    public int getMatchWins() {
        return matchWins;
    }

    public int getTotalArcadeWins() {
        return totalArcadeWins;
    }
}
