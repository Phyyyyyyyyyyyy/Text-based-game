import java.util.Random;
import java.util.Scanner;

public class GameMechanics {
    private final String BLACK   = "\u001B[30m";
    private final String RED     = "\u001B[31m";
    private final String GREEN   = "\u001B[32m";
    private final String YELLOW  = "\u001B[33m";
    private final String BLUE    = "\u001B[34m";
    private final String PURPLE  = "\u001B[35m";
    private final String CYAN    = "\u001B[36m";
    private final String WHITE   = "\u001B[37m";
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
    private CooldownManager enemyCD  = new CooldownManager();
    Scanner sc = new Scanner(System.in);

    public GameMechanics(Character player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        enableColors();
    }

    private void enableColors() {
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "color").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println("Note: Some colors may not display properly in this terminal.");
            }
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
            System.out.println(BRIGHT_YELLOW + "Skill is on cooldown! (" + cd.getFormattedCooldown(skillNumber) + ")" + RESET + RED + ", You missed your turn, Enemy's turn now." + RESET);
            System.out.println("Press ENTER to continue...");
            sc.nextLine();
            return;
        }
       
        if (user.mana < cost) {
            System.out.println(BRIGHT_RED + "Not enough mana!" + RESET);
            System.out.println("Press ENTER to continue...");
            sc.nextLine();
            return;
        }

        target.hp -= damage;
        //System.out.println(user.getName() + " uses " + skillName + "!"); 
        System.out.println(target.getName() + " takes " + BRIGHT_RED + damage + " damage!" + RESET);
        
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
        int currentMana = enemy.mana;
        enemy.regenerateMana(10);
        int enemyManaGained = enemy.mana - currentMana;
        boolean playerTurn = true;

        System.out.println("\n" + BRIGHT_BLUE + "==============================" + RESET);
        System.out.println(GREEN + "Your Character: " + RESET);
        String playerHPColor = getHPColor(player.hp, player.maxHp);
        System.out.println(player.getName() + " - " + playerHPColor + "HP: " + player.hp + "/" + player.maxHp + RESET + 
                         " | " + BRIGHT_BLUE + "Mana: " + player.mana + "/" + MAX_MANA + RESET);

        System.out.println(RED + "\nEnemy: " + RESET);
        String enemyHPColor = getHPColor(enemy.hp, enemy.maxHp);
        if (enemyManaGained > 0 && enemy.mana < MAX_MANA) {
            System.out.println(enemy.getName() + " - " + enemyHPColor + "HP: " + enemy.hp + "/" + enemy.maxHp + RESET + 
                             " | " + BRIGHT_BLUE + "Mana: " + enemy.mana + "/" + MAX_MANA + RESET + BRIGHT_GREEN + " (+" + enemyManaGained + ")" + RESET);
        } else {
            System.out.println(enemy.getName() + " - " + enemyHPColor + "HP: " + enemy.hp + "/" + enemy.maxHp + RESET + 
                             " | " + BRIGHT_BLUE + "Mana: " + enemy.mana + "/" + MAX_MANA + RESET);
        }
        System.out.println(BRIGHT_BLUE + "==============================" + RESET);
      
        while (player.hp > 0 && enemy.hp > 0) {
            if (playerTurn) {
                clearScreen();
                System.out.println("\n" + BRIGHT_BLUE + "==============================" + RESET);
                System.out.println(BRIGHT_YELLOW + "Turn " + turnCount + RESET);
                displayPlayerStats();
                System.out.println(BRIGHT_BLUE + "==============================" + RESET);
                turnCount++;
                 
                System.out.println(player.getName() + BRIGHT_YELLOW  + " - Choose your action: " + RESET); 
                System.out.println("Cooldowns - S1: " + playerCD.getFormattedCooldown(1) +
                                   " | S2: " + playerCD.getFormattedCooldown(2) +
                                   " | S3: " + playerCD.getFormattedCooldown(3));
                System.out.println("0. Basic Attack");
                System.out.println("1. " +  player.getSkill1() + " - " + BRIGHT_BLUE + player.sk1Cost + " mana" + RESET);
                System.out.println("2. " + player.getSkill2() + " - " + BRIGHT_BLUE + player.sk2Cost + " mana" + RESET);
                System.out.println("3. " + player.getSkill3() + " - " + BRIGHT_BLUE + player.sk3Cost + " mana" + RESET);
                System.out.print("> ");
               try {
                int action = sc.nextInt();
                sc.nextLine();
                
                switch (action) {
                    case 0:
                        int damage = calculateBasicAttackDamage(player.attack);
                        System.out.println("You perform a basic attack!");
                        enemy.hp -= damage;
                        System.out.println("You deal " + BRIGHT_RED + damage + " damage!" + RESET);
                        break;
                    case 1:
                    case 2:
                    case 3:
                        useSkill(action, player, enemy, playerCD);
                        break;
                    default:
                        System.out.println(BRIGHT_RED + "Invalid action! You lose your turn." + RESET);
                        break;
                }
            } catch(Exception e) {
                System.out.println(BRIGHT_RED + "Invalid action! You lose your turn." + RESET);
                sc.nextLine();
            }
                player.regenerateMana(10);

                System.out.println();
                displayPlayerStats();

            } else {
                clearScreen();
                System.out.println("\n" + BRIGHT_RED + "Enemy's Turn!" + RESET);
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
                        int damage = calculateBasicAttackDamage(enemy.attack);
                        System.out.println(enemy.name + " attacks!");
                        player.hp -= damage;
                        System.out.println(enemy.name + " deals " + BRIGHT_RED + damage + " damage!" + RESET);
                        break;
                    case 2:
                        if (!enemyCD.canUseSkill(1)) {
                            System.out.println(enemy.name + " tried to use " + enemy.skill1 + " but it's on cooldown!");
                        } else if (enemy.mana >= enemy.sk1Cost) {
                           System.out.println(enemy.name + " uses " + enemy.skill1 + "!");
                            useSkill(1, enemy, player, enemyCD);
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill1 + " but has no mana!");
                        }
                        break;
                    case 3:
                        if (!enemyCD.canUseSkill(2)) {
                            System.out.println(enemy.name + " tried to use " + enemy.skill2 + " but it's on cooldown!");
                        } else if (enemy.mana >= enemy.sk2Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill2 + "!");
                            useSkill(2, enemy, player, enemyCD);
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill2 + " but has no mana!");
                        }
                        break;
                    case 4:
                        if (!enemyCD.canUseSkill(3)) {
                            System.out.println(enemy.name + " tried to use " + enemy.skill3 + " but it's on cooldown!");
                        } else if (enemy.mana >= enemy.sk3Cost) {
                            System.out.println(enemy.name + " uses " + enemy.skill3 + "!");
                            useSkill(3, enemy, player, enemyCD);
                        } else {
                            System.out.println(enemy.name + " tried to use " + enemy.skill3 + " but has no mana!");
                        }
                        break;
                }
                System.out.println();
                System.out.println(BRIGHT_BLUE + "==============================" + RESET);
                System.out.println(BRIGHT_GREEN + "Your character: " + RESET);
                displayPlayerStats();

                System.out.println(BRIGHT_RED + "Enemy: " + RESET);
                displayEnemyStats();
                 System.out.println(BRIGHT_BLUE + "==============================" + RESET);
                player.regenerateMana(10);
                enemy.regenerateMana(10);
            }
            
            if (player.hp < 0) player.hp = 0;
            if (enemy.hp < 0) enemy.hp = 0;

            if (!playerTurn) {
                playerCD.reduceCooldowns();
                enemyCD.reduceCooldowns();
            }
            playerTurn = !playerTurn;

            System.out.println("\nPress ENTER to continue...");
            sc.nextLine();
        }
        
        System.out.println("\n" + BRIGHT_BLUE + "==============================" + RESET);
        clearScreen();
        if (player.hp <= 0 && enemy.hp <= 0) {
            System.out.println(BRIGHT_YELLOW + "It's a draw!" + RESET);
        } else if (player.hp <= 0) {
            System.out.println(RED + "_____                                           _____ " + RESET);
            System.out.println( RED + "( ___ )-----------------------------------------( ___ )" + RESET);
            System.out.println( RED + " |   |                                           |   | " + RESET);
            System.out.println(RED + " |   | __   __            _                   _  |   | " + RESET);
            System.out.println(RED + " |   | \\ \\ / /__  _   _  | |    ___  ___  ___| | |   | " + RESET);
            System.out.println(RED + " |   |  \\ V / _ \\| | | | | |   / _ \\/ __|/ _ \\ | |   | " + RESET);
            System.out.println(RED + " |   |   | | (_) | |_| | | |__| (_) \\__ \\  __/_| |   | " + RESET);
            System.out.println(RED + " |   |   |_|\\___/ \\__,_| |_____\\___/|___/\\___(_) |   | " + RESET);
            System.out.println(RED + " |___|                                           |___| " + RESET);
            System.out.println(RED + "(_____)-----------------------------------------(_____)" + RESET);
            System.out.println(BRIGHT_RED + "You lost! " + enemy.name + " wins!" + RESET);
            System.out.println("\nPress ENTER to continue...");
            sc.nextLine();
            clearScreen();
        } else {
            System.out.println(GREEN + " _____                                          _____ " + RESET);
            System.out.println(GREEN + "( ___ )----------------------------------------( ___ )" + RESET);
            System.out.println(GREEN + " |   |                                          |   | " + RESET);
            System.out.println(GREEN + " |   | __   __           __        ___       _  |   | " + RESET);
            System.out.println(GREEN + " |   | \\ \\ / /__  _   _  \\ \\      / (_)_ __ | | |   | " + RESET);
            System.out.println(GREEN + " |   |  \\ V / _ \\| | | |  \\ \\ /\\ / /| | '_ \\| | |   | " + RESET);
            System.out.println(GREEN + " |   |   | | (_) | |_| |   \\ V  V / | | | | |_| |   | " + RESET);
            System.out.println(GREEN + " |   |   |_|\\___/ \\__,_|    \\_/\\_/  |_|_| |_(_) |   | " + RESET);
            System.out.println(GREEN + " |___|                                          |___| " + RESET);
            System.out.println(GREEN + "(_____)----------------------------------------(_____)" + RESET);
            System.out.println(BRIGHT_GREEN + "Congratulations! You defeated " + enemy.name + "!" + RESET);
            System.out.println("\nPress ENTER to continue...");
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
        System.out.println(player.getName() + " - " + playerHPColor + "HP: " + player.hp + "/" + player.maxHp + RESET + 
                         " | " + BRIGHT_BLUE + "Mana: " + player.mana + "/" + MAX_MANA + RESET +
                         " | " + RED + "Attack: " + player.attack + RESET);
    }

    private void displayEnemyStats() {
        String enemyHPColor = getHPColor(enemy.hp, enemy.maxHp);
        System.out.println(enemy.getName() + " - " + enemyHPColor + "HP: " + enemy.hp + "/" + enemy.maxHp + RESET + 
                         " | " + BRIGHT_BLUE + "Mana: " + enemy.mana + "/" + MAX_MANA + RESET +
                         " | " + RED + "Attack: " + enemy.attack + RESET);
    }
}
