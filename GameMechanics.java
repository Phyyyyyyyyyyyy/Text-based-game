import java.util.Random;
import java.util.Scanner;

public class GameMechanics{
    Character player;
    Enemy enemy;
    public int turnCount = 1;
    private Random rand = new Random();
    private CooldownManager playerCD = new CooldownManager();
    private CooldownManager enemyCD  = new CooldownManager();
     Scanner sc = new Scanner(System.in);
    public GameMechanics(Character player, Enemy enemy) {
        
        this.player = player;
        this.enemy = enemy;
    }

    private void useSkill(int skillNumber, Character user, Character target, CooldownManager cd) {
        int cost = skillNumber == 1 ? user.sk1Cost : (skillNumber == 2 ? user.sk2Cost : user.sk3Cost);
        int damage = skillNumber == 1 ? user.sk1Damage : (skillNumber == 2 ? user.sk2Damage : user.sk3Damage);
        String skillName = skillNumber == 1 ? user.getSkill1() : (skillNumber == 2 ? user.getSkill2() : user.getSkill3());

        if (!cd.canUseSkill(skillNumber)) {
            
            System.out.println("Skill is on cooldown! (" + cd.getFormattedCooldown(skillNumber) + ")");
            
            System.out.println("Press ENTER to continue...");
             sc.nextLine();
            sc.nextLine();
            
           
            return;
            
        }
       
        if (damage < 0) {
        user.hp = Math.min(user.hp - damage, user.maxHp); // negative damage = heal
        System.out.println(user.getName() + " heals for " + (-damage) + " HP!");
    }
    // Handle instant elimination
    else if (skillName.toLowerCase().contains("eliminates")) {
        target.hp = 0;
        System.out.println(target.getName() + " was instantly eliminated!");
    }
    
    else if (skillName.toLowerCase().contains("doubles attack") || skillName.toLowerCase().contains("rage")) {
        user.attack *= 2;
        System.out.println(user.getName() + "'s attack is doubled for this turn!");
        if (damage > 0) { // still deal damage if specified
            target.hp -= damage;
            System.out.println(target.getName() + " takes " + damage + " damage!");
        }
    }
    // Handle avoidance buffs
    else if (skillName.toLowerCase().contains("avoids") || skillName.toLowerCase().contains("invisible") ||
             skillName.toLowerCase().contains("dodges")) {
        System.out.println(user.getName() + " becomes harder to hit this turn!");
        if (damage > 0) {
            target.hp -= damage;
            System.out.println(target.getName() + " takes " + damage + " damage!");
        }
    }
    // Default: deal damage
    else {
        target.hp -= damage;
        System.out.println(target.getName() + " takes " + damage + " damage!");
    }
        
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
       
        boolean playerTurn = true;

        System.out.println("\n==============================");
        System.out.println("Your Character: ");
        player.displayStats();
        System.out.println("\nEnemy: ");
        enemy.displayStats();
        System.out.println("==============================");

        while (player.hp > 0 && enemy.hp > 0) {

            if (playerTurn) {
                System.out.println("\n==============================");
                System.out.println("Turn " + turnCount);
                player.displayStats();
                System.out.println("==============================");
                turnCount++;
                System.out.println(player.getName() + " - Choose your action:");
                System.out.println("Cooldowns - S1: " + playerCD.getFormattedCooldown(1) +
                                   " | S2: " + playerCD.getFormattedCooldown(2) +
                                   " | S3: " + playerCD.getFormattedCooldown(3));
                System.out.println("0. Basic Attack");
                System.out.println("1. " + player.getSkill1() + " - "+player.sk1Cost+" mana");
                System.out.println("2. " + player.getSkill2() + " - "+player.sk2Cost+" mana");
                System.out.println("3. " + player.getSkill3() + " - "+player.sk3Cost+" mana");
                System.out.print("> ");
                int action = sc.nextInt();

                switch (action) {
                    case 0:
                        System.out.println("You perform a basic attack!");
                        enemy.hp -= calculateBasicAttackDamage(player.attack);
                        break;
                    case 1:
                    case 2:
                    case 3:
                        
                        useSkill(action, player, enemy, playerCD);
                        break;
                    default:
                        System.out.println("Invalid action! You lose your turn.");
                        break;
                }

                player.regenerateMana(10);

                System.out.println();
                player.displayStats();

            } else {
                System.out.println("\nEnemy's Turn!");
                System.out.println("Cooldowns - S1: " + enemyCD.getFormattedCooldown(1) +
                                   " | S2: " + enemyCD.getFormattedCooldown(2) +
                                   " | S3: " + enemyCD.getFormattedCooldown(3));
                
               
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
                        System.out.println(enemy.name + " attacks!");
                        player.hp -= calculateBasicAttackDamage(enemy.attack);
                        break;
                    case 2:
                        if (!enemyCD.canUseSkill(1)) {
                            System.out.println(enemy.name + " tried to use " + enemy.skill1 + " but it's on cooldown!");
                        } else if (enemy.mana >= enemy.sk1Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill1 + "!");
                            player.hp -= enemy.sk1Damage;
                            enemy.mana -= enemy.sk1Cost;
                            enemyCD.applyCooldown(1);
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill1 + " but has no mana!");
                        }
                        break;
                    case 3:
                        if (!enemyCD.canUseSkill(2)) {
                            System.out.println(enemy.name + " tried to use " + enemy.skill2 + " but it's on cooldown!");
                        } else if (enemy.mana >= enemy.sk2Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill2 + "!");
                            player.hp -= enemy.sk2Damage;
                            enemy.mana -= enemy.sk2Cost;
                            enemyCD.applyCooldown(2);
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill2 + " but has no mana!");
                        }
                        break;
                    case 4:
                        if (!enemyCD.canUseSkill(3)) {
                            System.out.println(enemy.name + " tried to use " + enemy.skill3 + " but it's on cooldown!");
                        } else if (enemy.mana >= enemy.sk3Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill3 + "!");
                            player.hp -= enemy.sk3Damage;
                            enemy.mana -= enemy.sk3Cost;
                            enemyCD.applyCooldown(3);
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill3 + " but has no mana!");
                        }
                        break;
                }
                System.out.println();

                System.out.println("Your character: ");
                player.displayStats();

                System.out.println("Enemy: ");
                enemy.displayStats();

                player.regenerateMana(10);
                enemy.regenerateMana(10);
            }
            if (player.hp < 0) player.hp = 0;
            if (enemy.hp < 0) enemy.hp = 0;

            // Reduce cooldowns at the end of each full turn
            if (!playerTurn) {
                playerCD.reduceCooldowns();
                enemyCD.reduceCooldowns();
            }
            playerTurn = !playerTurn;
            System.out.println("\nPress ENTER to continue...");
            sc.nextLine();
            MarvelGame.clearScreen();
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
