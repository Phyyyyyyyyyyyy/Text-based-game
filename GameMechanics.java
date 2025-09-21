import java.util.Random;
import java.util.Scanner;

public class GameMechanics {
    Character player; // player from Chacracfer anjsfhjkasbfhjkas class.
    Enemy enemy;  //enemy from Enemy class

    public GameMechanics(Character player, Enemy  enemy) {
        this.player = player;
        this.enemy = enemy;       //constructor includes player and enemy obj
    }
    

    public void game() {
        Scanner sc = new Scanner(System.in);
        boolean playerTurn = true;

        while (player.hp > 0 && enemy.hp > 0) {
            System.out.println("\n==============================");
            player.displayStats();
            enemy.displayStats();
            System.out.println("==============================");

            if (playerTurn) {
                System.out.println("Your Turn! Choose your action:");
                System.out.println("1. Attack");
                System.out.println("2. Skill 1");
                System.out.println("3. Skill 2");
                System.out.println("4. Skill 3");
                System.out.print("> ");
                int action = sc.nextInt();

                switch (action) {
                    case 1:
                        System.out.println("You attack!");
                        enemy.hp -= player.attack;
                        break;
                    case 2:
                        if (player.mana >= player.sk1Cost) {
                            System.out.println("You use " + player.skill1 + "!");
                            enemy.hp -= player.sk1Damage;
                            player.mana -= player.sk1Cost;
                        } else {
                            System.out.println("Not enough mana!");
                        }
                        break;
                    case 3:
                        if (player.mana >= player.sk2Cost) {
                            System.out.println("You use " + player.skill2 + "!");
                            enemy.hp -= player.sk2Damage;
                            player.mana -= player.sk2Cost;
                        } else {
                            System.out.println("Not enough mana!");
                        }
                        break;
                    case 4:
                        if (player.mana >= player.sk3Cost) {
                            System.out.println("You use " + player.skill3 + "!");
                            enemy.hp -= player.sk3Damage;
                            player.mana -= player.sk3Cost;
                        } else {
                            System.out.println("Not enough mana!");
                        }
                        break;
                    default:
                        System.out.println("Invalid action! You lose your turn.");
                        break;
                }
            } else {
                
                Random rand = new Random();
                int action = rand.nextInt(4) + 1;
                System.out.println("Enemy's Turn!");
                switch (action) {
                    case 1:
                        System.out.println(enemy.name + " attacks!");
                        player.hp -= enemy.attack;
                        break;
                    case 2:
                        if (enemy.mana >= enemy.sk1Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill1 + "!");
                            player.hp -= enemy.sk1Damage;
                            enemy.mana -= enemy.sk1Cost;
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill1 + " but failed!");
                        }
                        break;
                    case 3:
                        if (enemy.mana >= enemy.sk2Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill2 + "!");
                            player.hp -= enemy.sk2Damage;
                            enemy.mana -= enemy.sk2Cost;
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill2 + " but failed!");
                        }
                        break;
                    case 4:
                        if (enemy.mana >= enemy.sk3Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill3 + "!");
                            player.hp -= enemy.sk3Damage;
                            enemy.mana -= enemy.sk3Cost;
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill3 + " but failed!");
                        }
                        break;
                }
            }

            
            if (player.hp < 0) player.hp = 0;
            if (enemy.hp < 0) enemy.hp = 0;

            playerTurn = !playerTurn; 
        }

        
        if (player.hp <= 0 && enemy.hp <= 0) {
            System.out.println("It's a draw!");
        } else if (player.hp <= 0) {
            System.out.println("You lost! " + enemy.name + " wins!");
        } else {
            System.out.println("Congratulations! You defeated " + enemy.name + "!");
        }
    }

     
}

