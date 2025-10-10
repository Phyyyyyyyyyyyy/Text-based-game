import java.util.Scanner;

public class PlayerMechanics {
    Character player1;
    Character player2;
    public int turnCount = 1;

    public PlayerMechanics(Character player1, Character player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void game() {
        Scanner sc = new Scanner(System.in);
        boolean player1Turn = true;

        System.out.println("\n==============================");
        System.out.println("Player 1: ");
        player1.displayStats();

        System.out.println("\nPlayer 2: ");
        player2.displayStats();
        System.out.println("==============================");

        while (player1.hp > 0 && player2.hp > 0) {
            Character currentPlayer;
            Character opponent;
            String turnLabel;

            if (player1Turn) {
                currentPlayer = player1;
                opponent = player2;
                turnLabel = "Player 1's Turn (" + player1.getName() + ")";
            } else {
                currentPlayer = player2;
                opponent = player1;
                turnLabel = "Player 2's Turn (" + player2.getName() + ")";
            }

            System.out.println("\n==============================");
            System.out.println("Turn " + turnCount + " - " + turnLabel);
            System.out.println("==============================");
            System.out.println("Choose your action:");
            System.out.println("1. " + currentPlayer.getSkill1() + " - " + currentPlayer.sk1Cost + " mana");
            System.out.println("2. " + currentPlayer.getSkill2() + " - " + currentPlayer.sk2Cost + " mana");
            System.out.println("3. " + currentPlayer.getSkill3() + " - " + currentPlayer.sk3Cost + " mana");
            System.out.print("> ");

            int action = sc.nextInt();

            switch (action) {
                case 1:
                    if (currentPlayer.mana >= currentPlayer.sk1Cost) {
                        System.out.println(currentPlayer.getName() + " uses " + currentPlayer.getSkill1() + "!");
                        opponent.hp -= currentPlayer.sk1Damage;
                        currentPlayer.mana -= currentPlayer.sk1Cost;
                    } else {
                        System.out.println("Not enough mana!");
                    }
                    break;
                case 2:
                    if (currentPlayer.mana >= currentPlayer.sk2Cost) {
                        System.out.println(currentPlayer.getName() + " uses " + currentPlayer.getSkill2() + "!");
                        opponent.hp -= currentPlayer.sk2Damage;
                        currentPlayer.mana -= currentPlayer.sk2Cost;
                    } else {
                        System.out.println("Not enough mana!");
                    }
                    break;
                case 3:
                    if (currentPlayer.mana >= currentPlayer.sk3Cost) {
                        System.out.println(currentPlayer.getName() + " uses " + currentPlayer.getSkill3() + "!");
                        opponent.hp -= currentPlayer.sk3Damage;
                        currentPlayer.mana -= currentPlayer.sk3Cost;
                    } else {
                        System.out.println("Not enough mana!");
                    }
                    break;
                default:
                    System.out.println("Invalid action! You lose your turn.");
                    break;
            }

          
            if (opponent.hp < 0) opponent.hp = 0;
            if (currentPlayer.hp < 0) currentPlayer.hp = 0;

         
            System.out.println();
            System.out.println("Player 1: ");
            player1.displayStats();
            System.out.println("Player 2: ");
            player2.displayStats();

            player1Turn = !player1Turn;
            
                player1.regenerateMana(10);
                player2.regenerateMana(10);
        }

     
        System.out.println("\n==============================");
        if (player1.hp <= 0 && player2.hp <= 0) {
            System.out.println("It's a draw!");
        } else if (player1.hp <= 0) {
            System.out.println(player2.getName() + " wins!");
        } else {
            System.out.println(player1.getName() + " wins!");
        }
        System.out.println("==============================");

        sc.close();
    }
}
