import java.util.Scanner;

public class PlayerMechanics {
    Character player1;
    Character player2;
    public int turnCount = 1;
    private Scanner sc = new Scanner(System.in);

    private CooldownManager p1CD = new CooldownManager();
    private CooldownManager p2CD = new CooldownManager();

    public PlayerMechanics(Character player1, Character player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void game() {
        System.out.println("\n==============================");
        System.out.println("Player 1: ");
        player1.displayStats();

        System.out.println("\nPlayer 2: ");
        player2.displayStats();
        System.out.println("==============================");

        while (player1.hp > 0 && player2.hp > 0) {
            System.out.println("\n==============================");
            System.out.println("Turn " + turnCount);
            System.out.println("==============================");

            // Player 1 Turn
            System.out.println(player1.getName() + "'s Turn");
            System.out.println("Player 1 Cooldowns - S1: " + p1CD.getFormattedCooldown(1) +
                               " | S2: " + p1CD.getFormattedCooldown(2) +
                               " | S3: " + p1CD.getFormattedCooldown(3));
            System.out.println();

            playerAction(player1, player2, p1CD);

            if (player2.hp <= 0) break;

            // Player 2 Turn
            System.out.println("\n" + player2.getName() + "'s Turn");
            System.out.println("Player 2 Cooldowns - S1: " + p2CD.getFormattedCooldown(1) +
                               " | S2: " + p2CD.getFormattedCooldown(2) +
                               " | S3: " + p2CD.getFormattedCooldown(3));
            System.out.println();

            playerAction(player2, player1, p2CD);

            // Reduce cooldowns after both players act
            p1CD.reduceCooldowns();
            p2CD.reduceCooldowns();

            turnCount++;
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
    }

    private int calculateBasicAttackDamage(int baseAttack) {
        double minMultiplier = 0.8;
        double maxMultiplier = 1.2;
        double multiplier = minMultiplier + (maxMultiplier - minMultiplier) * Math.random();
        return (int) (baseAttack * multiplier);
    }

    private void useSkill(int skillNumber, Character user, Character target, CooldownManager cd) {
        if (!cd.canUseSkill(skillNumber)) {
            System.out.println("Skill is on cooldown! (" + cd.getFormattedCooldown(skillNumber) + ")");
            return;
        }

        int cost = 0;
        int damage = 0;
        String skillName = "";

        // ✅ Connect directly to Character attributes
        switch (skillNumber) {
            case 1:
                cost = user.sk1Cost;
                damage = user.sk1Damage;
                skillName = user.getSkill1();
                break;
            case 2:
                cost = user.sk2Cost;
                damage = user.sk2Damage;
                skillName = user.getSkill2();
                break;
            case 3:
                cost = user.sk3Cost;
                damage = user.sk3Damage;
                skillName = user.getSkill3();
                break;
            default:
                System.out.println("Invalid skill number!");
                return;
        }

        if (user.mana < cost) {
            System.out.println("Not enough mana!");
            return;
        }

        System.out.println(user.getName() + " uses " + skillName + "!");

        // ✅ Apply special skill effects or normal damage
        if (skillName.toLowerCase().contains("heal") || skillName.toLowerCase().contains("repair")) {
            user.hp = Math.min(user.hp + damage, user.maxHp);
            System.out.println(user.getName() + " heals for " + damage + " HP!");
        } 
        else if (skillName.toLowerCase().contains("double")) {
            user.attack *= 2;
            System.out.println(user.getName() + "'s attack doubled!");
        } 
        else {
            target.hp -= damage;
            System.out.println(user.getName() + " dealt " + damage + " damage to " + target.getName() + "!");
        }

        user.mana -= cost;
        cd.applyCooldown(skillNumber);
    }

    private void playerAction(Character currentPlayer, Character opponent, CooldownManager currentCD) {
        boolean actionTaken = false;
        while (!actionTaken && currentPlayer.hp > 0 && opponent.hp > 0) {
            System.out.println("Choose your action:");
            System.out.println("0. Basic Attack");
            System.out.println("1. " + currentPlayer.getSkill1() + " - " + currentPlayer.sk1Cost + " mana");
            System.out.println("2. " + currentPlayer.getSkill2() + " - " + currentPlayer.sk2Cost + " mana");
            System.out.println("3. " + currentPlayer.getSkill3() + " - " + currentPlayer.sk3Cost + " mana");
            System.out.print("> ");

            int action;
            try {
                action = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice!");
                continue;
            }

            switch (action) {
                case 0:
                    System.out.println(currentPlayer.getName() + " performs a basic attack!");
                    opponent.hp -= calculateBasicAttackDamage(currentPlayer.attack);
                    actionTaken = true;
                    break;
                case 1:
                case 2:
                case 3:
                    useSkill(action, currentPlayer, opponent, currentCD);
                    actionTaken = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        currentPlayer.regenerateMana(10);

        if (opponent.hp < 0) opponent.hp = 0;
        if (currentPlayer.hp < 0) currentPlayer.hp = 0;

        System.out.println();
        System.out.println("Player 1: ");
        player1.displayStats();
        System.out.println("Player 2: ");
        player2.displayStats();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
