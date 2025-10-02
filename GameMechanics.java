import java.util.Random;
import java.util.Scanner;

public class GameMechanics extends Character {
    Character player;
    Enemy enemy;
    public int turnCount = 1;
    private Random rand = new Random();

    public GameMechanics(Character player, Enemy enemy) {
        super(player.name, player.hp, player.maxHp, player.attack,
                player.skill1, player.skill2, player.skill3,
                player.sk1Cost, player.sk2Cost, player.sk3Cost,
                player.sk1Damage, player.sk2Damage, player.sk3Damage, player.mana);
        this.player = player;
        this.enemy = enemy;
    }

    private int calculateBasicAttackDamage(int baseAttack) {
        double minMultiplier = 0.8;
        double maxMultiplier = 1.2;
        double multiplier = minMultiplier + (maxMultiplier - minMultiplier) * rand.nextDouble();
        return (int) (baseAttack * multiplier);
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
                System.out.println("Turn " + turnCount);
                turnCount++;
                System.out.println(player.getName() + " - Choose your action:");
                System.out.println("1. " + player.getSkill1());
                System.out.println("2. " + player.getSkill2());
                System.out.println("3. " + player.getSkill3());
                System.out.print("> ");
                int action = sc.nextInt();

                switch (action) {
                    case 1:
                        if (player.mana >= player.sk1Cost) {
                            System.out.println("You use " + player.getSkill1() + "!");
                            enemy.hp -= player.sk1Damage;
                            player.mana -= player.sk1Cost;
                        } else {
                            System.out.println("Not enough mana!");
                        }
                        break;
                    case 2:
                        if (player.mana >= player.sk2Cost) {
                            System.out.println("You use " + player.getSkill2() + "!");
                            enemy.hp -= player.sk2Damage;
                            player.mana -= player.sk2Cost;
                        } else {
                            System.out.println("Not enough mana!");
                        }
                        break;
                    case 3:
                        if (player.mana >= player.sk3Cost) {
                            System.out.println("You use " + player.getSkill3() + "!");
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
                int action = rand.nextInt(4) + 1;
                System.out.println("Enemy's Turn!");
                switch (action) {
                    case 1:
                        System.out.println(enemy.name + " attacks!");
                        player.hp -= calculateBasicAttackDamage(enemy.attack);
                        break;
                    case 2:
                        if (enemy.mana >= enemy.sk1Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill1 + "!");
                            player.hp -= enemy.sk1Damage;
                            enemy.mana -= enemy.sk1Cost;
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill1 + " but has no mana!");
                        }
                        break;
                    case 3:
                        if (enemy.mana >= enemy.sk2Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill2 + "!");
                            player.hp -= enemy.sk2Damage;
                            enemy.mana -= enemy.sk2Cost;
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill2 + " but has no mana!");
                        }
                        break;
                    case 4:
                        if (enemy.mana >= enemy.sk3Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill3 + "!");
                            player.hp -= enemy.sk3Damage;
                            enemy.mana -= enemy.sk3Cost;
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill3 + " but has no mana!");
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
