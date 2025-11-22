
import java.io.PrintStream;
import java.util.Scanner;

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

    Character player1;
    Character player2;
    public int turnCount = 1;
    private Scanner sc;
    private CooldownManager p1CD;
    private CooldownManager p2CD;
    private static final int MAX_MANA = 100;

    public PlayerMechanics(Character var1, Character var2) {
        this.sc = new Scanner(System.in);
        this.p1CD = new CooldownManager();
        this.p2CD = new CooldownManager();
        this.player1 = var1;
        this.player2 = var2;
    }

    // Helper method to get HP color based on percentage
    private String getHPColor(int currentHP, int maxHP) {
        double percentage = (double) currentHP / maxHP;
        if (percentage > 0.5) {
            return BRIGHT_GREEN; // Above 50% - Green
        } else if (percentage > 0.25) {
            return BRIGHT_YELLOW; // 25% to 50% - Yellow
        } else {
            return BRIGHT_RED; // Below 25% - Red
        }
    }

    public void game() {

        
        System.out.println("\n\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
        System.out.println("\t\t\tPlayer 1: ");
        displayPlayer1Stats();
        System.out.println("\n\t\t\tPlayer 2: ");
        displayPlayer2Stats();
        System.out.println(BRIGHT_BLUE + "\t\t\t==============================" + RESET);

        while (this.player1.hp > 0 && this.player2.hp > 0) {

            System.out.println("\n\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
            System.out.println("\t\t\t" + BRIGHT_YELLOW + "Turn " + this.turnCount + RESET);
            System.out.println("\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
            System.out.println("\t\t\t" + this.player1.getName() + "'s Turn");
            String p1S1Color = p1CD.canUseSkill(1) ? BRIGHT_YELLOW : BRIGHT_RED;
            String p1S2Color = p1CD.canUseSkill(2) ? BRIGHT_YELLOW : BRIGHT_RED;
            String p1S3Color = p1CD.canUseSkill(3) ? BRIGHT_YELLOW : BRIGHT_RED;
            System.out.println("\t\t\t" + BRIGHT_YELLOW + "Player 1 - Cooldowns: " + RESET + "S1: " + p1S1Color + p1CD.getFormattedCooldown(1) + RESET + " | S2: " + p1S2Color + p1CD.getFormattedCooldown(2) + RESET + " | S3: " + p1S3Color + p1CD.getFormattedCooldown(3) + RESET);
            System.out.println();
            this.playerAction(this.player1, this.player2, this.p1CD);
            if (this.player2.hp <= 0) {
                break;
            }

            System.out.println("\n\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
            System.out.println("\t\t\t" + BRIGHT_YELLOW + "Turn " + ++this.turnCount + RESET);
            System.out.println("\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
            System.out.println("\t\t\t" + this.player2.getName() + "'s Turn");
            String p2S1Color = p2CD.canUseSkill(1) ? BRIGHT_YELLOW : BRIGHT_RED;
            String p2S2Color = p2CD.canUseSkill(2) ? BRIGHT_YELLOW : BRIGHT_RED;
            String p2S3Color = p2CD.canUseSkill(3) ? BRIGHT_YELLOW : BRIGHT_RED;
            System.out.println("\t\t\tPlayer 2 Cooldowns - S1: " + p2S1Color + p2CD.getFormattedCooldown(1) + RESET + " | S2: " + p2S2Color + p2CD.getFormattedCooldown(2) + RESET + " | S3: " + p2S3Color + p2CD.getFormattedCooldown(3) + RESET);
            System.out.println();
            this.playerAction(this.player2, this.player1, this.p2CD);
            this.p1CD.reduceCooldowns();
            this.p2CD.reduceCooldowns();
            ++this.turnCount;
        }

        System.out.println("\n\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
        clearScreen();
        if (this.player1.hp <= 0 && this.player2.hp <= 0) {
            System.out.println(BRIGHT_YELLOW + "It's a draw!" + RESET);
        } else if (this.player1.hp <= 0) {
            System.out.println("\t\t\t" + GREEN + "( ___ )------------------------------------------------------------------( ___ )" + RESET);
            System.out.println("\t\t\t" + GREEN + " |   |                                                                    |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   |  ____  _                         ____   __        ___           _  |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   | |  _ \\| | __ _ _   _  ___ _ __  |___ \\  \\ \\      / (_)_ __  ___| | |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   | | |_) | |/ _` | | | |/ _ \\ '__|   __) |  \\ \\ /\\ / /| | '_ \\/ __| | |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   | |  __/| | (_| | |_| |  __/ |     / __/    \\ V  V / | | | | \\__ \\_| |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   | |_|   |_|\\__,_|\\__, |\\___|_|    |_____|    \\_/\\_/  |_|_| |_|___(_) |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   |                |___/                                               |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |___|                                                                    |___| " + RESET);
            System.out.println("\t\t\t" + GREEN + "(_____)------------------------------------------------------------------(_____)" + RESET);
            System.out.println("\t\t\t" + BRIGHT_GREEN + this.player2.getName() + " wins!" + RESET);

            System.out.println("\nPress ENTER to continue...");
            sc.nextLine();
            clearScreen();
        } else {
            System.out.println("\t\t\t" + GREEN + "_____                                                                _____ " + RESET);
            System.out.println("\t\t\t" + GREEN + "( ___ )--------------------------------------------------------------( ___ )" + RESET);
            System.out.println("\t\t\t" + GREEN + " |   |                                                                |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   |  ____  _                         _  __        ___           _  |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   | |  _ \\| | __ _ _   _  ___ _ __  / | \\ \\      / (_)_ __  ___| | |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   | | |_) | |/ _` | | | |/ _ \\ '__| | |  \\ \\ /\\ / /| | '_ \\/ __| | |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   | |  __/| | (_| | |_| |  __/ |    | |   \\ V  V / | | | | \\__ \\_| |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   | |_|   |_|\\__,_|\\__, |\\___|_|    |_|    \\_/\\_/  |_|_| |_|___(_) |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |   |                |___/                                           |   | " + RESET);
            System.out.println("\t\t\t" + GREEN + " |___|                                                                |___| " + RESET);
            System.out.println("\t\t\t" + GREEN + "(_____)--------------------------------------------------------------(_____)" + RESET);
            System.out.println("\t\t\t" + BRIGHT_GREEN + this.player1.getName() + " wins!" + RESET);

            System.out.println("\n\t\t\tPress ENTER to continue...");
            sc.nextLine();
            sc.nextLine();
            clearScreen();
        }
    }

    private int calculateBasicAttackDamage(int var1) {
        double var2 = 0.8;
        double var4 = 1.2;
        double var6 = var2 + (var4 - var2) * Math.random();
        return (int) ((double) var1 * var6);
    }

    private void useSkill(int var1, Character var2, Character var3, CooldownManager var4) {
        PrintStream var10000;
        String var10001;
        if (!var4.canUseSkill(var1)) {
            var10000 = System.out;
            var10001 = var4.getFormattedCooldown(var1);
            var10000.println("\t\t\t" + BRIGHT_YELLOW + "Skill is on cooldown! (" + var10001 + ")" + RESET);
        } else {
            String var7 = "";
            int var8;
            int var9;
            switch (var1) {
                case 1:
                    var8 = var2.sk1Cost;
                    var9 = var2.sk1Damage;
                    var7 = var2.getSkill1();
                    break;
                case 2:
                    var8 = var2.sk2Cost;
                    var9 = var2.sk2Damage;
                    var7 = var2.getSkill2();
                    break;
                case 3:
                    var8 = var2.sk3Cost;
                    var9 = var2.sk3Damage;
                    var7 = var2.getSkill3();
                    break;
                default:
                    System.out.println("\t\t\t" + BRIGHT_RED + "Invalid skill number!" + RESET);
                    return;
            }

            clearScreen();
            if (var2.mana < var8) {
                System.out.println("\t\t\t" + BRIGHT_RED + "Not enough mana!" + RESET);
            } else {
                var10000 = System.out;
                var10001 = var2.getName();
                var10000.println("\t\t\t" + var10001 + " uses " + var7 + "!");
                if (!var7.toLowerCase().contains("heal") && !var7.toLowerCase().contains("repair")) {
                    if (var7.toLowerCase().contains("double")) {
                        var2.attack *= 2;
                        System.out.println("\t\t\t" + BRIGHT_YELLOW + var2.getName() + "'s attack doubled!" + RESET);
                    } else {
                        var3.hp -= var9;
                        System.out.println("\t\t\t" + var2.getName() + " dealt " + BRIGHT_RED + var9 + " damage" + RESET + " to " + var3.getName() + "!");
                    }
                } else {
                    var2.hp = Math.min(var2.hp + var9, var2.maxHp);
                    var10000 = System.out;
                    var10001 = var2.getName();
                    var10000.println("\t\t\t" + var10001 + " heals for " + BRIGHT_GREEN + var9 + " HP" + RESET + "!");
                }

                var2.mana -= var8;
                var4.applyCooldown(var1);
            }
        }
    }

    private void playerAction(Character var1, Character var2, CooldownManager var3) {
        boolean var4 = false;

        while (!var4 && var1.hp > 0 && var2.hp > 0) {
            System.out.println("\t\t\tChoose your action:");
            String s1Color = var3.canUseSkill(1) ? BRIGHT_YELLOW : BRIGHT_RED;
            String s2Color = var3.canUseSkill(2) ? BRIGHT_YELLOW : BRIGHT_RED;
            String s3Color = var3.canUseSkill(3) ? BRIGHT_YELLOW : BRIGHT_RED;
            PrintStream var10000 = System.out;
            String var10001 = var1.getSkill1();
            var10000.println("\t\t\t" + "1. " + s1Color + var10001 + RESET + " - " + BRIGHT_BLUE + var1.sk1Cost + " mana" + RESET + " " + (s1Color == BRIGHT_RED ? "(" + var3.getFormattedCooldown(1) + ")" : ""));
            var10000 = System.out;
            var10001 = var1.getSkill2();
            var10000.println("\t\t\t" + "2. " + s2Color + var10001 + RESET + " - " + BRIGHT_BLUE + var1.sk2Cost + " mana" + RESET + " " + (s2Color == BRIGHT_RED ? "(" + var3.getFormattedCooldown(2) + ")" : ""));
            var10000 = System.out;
            var10001 = var1.getSkill3();
            var10000.println("\t\t\t" + "3. " + s3Color + var10001 + RESET + " - " + BRIGHT_BLUE + var1.sk3Cost + " mana" + RESET + " " + (s3Color == BRIGHT_RED ? "(" + var3.getFormattedCooldown(3) + ")" : ""));
            System.out.print("\t\t\t" + "> ");

            int var5;

            try {
                var5 = Integer.parseInt(this.sc.nextLine());
            } catch (NumberFormatException var7) {
                clearScreen();
                System.out.println("\t\t\t" + BRIGHT_RED + "Invalid choice!, You missed your turn!" + RESET);
                continue;
            }

            switch (var5) {
                case 0:
                    int damage = this.calculateBasicAttackDamage(var1.attack);
                    System.out.println("\t\t\t" + var1.getName() + " performs a basic attack!");
                    var2.hp -= damage;
                    System.out.println("\t\t\t" + var1.getName() + " dealt " + BRIGHT_RED + damage + " damage" + RESET + " to " + var2.getName() + "!");
                    var4 = true;
                    break;
                case 1:
                case 2:
                case 3:
                    this.useSkill(var5, var1, var2, var3);
                    var4 = true;
                    break;
                default:
                    System.out.println("\t\t\t" + BRIGHT_RED + "Invalid choice!" + RESET);
            }
        }

        int currentMana = var1.mana;
        var1.regenerateMana(10);
        int manaGained = var1.mana - currentMana;

        if (var2.hp < 0) {
            var2.hp = 0;
        }
        if (var1.hp < 0) {
            var1.hp = 0;
        }

        System.out.println();

        System.out.println("\n\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
        System.out.println("\t\t\t" + "Player 1: ");
        displayPlayer1StatsWithMana(manaGained);
        System.out.println();
        System.out.println("\t\t\t" + "Player 2: ");
        displayPlayer2StatsWithMana(manaGained);
        System.out.println("\t\t\t" + BRIGHT_BLUE + "==============================" + RESET);
    }

    // Helper methods for displaying stats with colors
    private void displayPlayer1Stats() {
        String p1HPColor = getHPColor(player1.hp, player1.maxHp);
        System.out.println("\t\t\t" + player1.getName() + " - " + p1HPColor + "HP: " + player1.hp + "/" + player1.maxHp + RESET
                + " | " + BRIGHT_BLUE + "Mana: " + player1.mana + "/" + MAX_MANA + RESET);
    }

    private void displayPlayer2Stats() {
        String p2HPColor = getHPColor(player2.hp, player2.maxHp);
        System.out.println("\t\t\t" + player2.getName() + " - " + p2HPColor + "HP: " + player2.hp + "/" + player2.maxHp + RESET
                + " | " + BRIGHT_BLUE + "Mana: " + player2.mana + "/" + MAX_MANA + RESET);
    }

    private void displayPlayer1StatsWithMana(int manaGained) {
        String p1HPColor = getHPColor(player1.hp, player1.maxHp);
        if (manaGained > 0 && player1.mana < MAX_MANA) {
            System.out.println("\t\t\t" + player1.getName() + " - " + p1HPColor + "HP: " + player1.hp + "/" + player1.maxHp + RESET
                    + " | " + BRIGHT_BLUE + "Mana: " + player1.mana + "/" + MAX_MANA + RESET
                    + BRIGHT_GREEN + " (+" + manaGained + ")" + RESET);
        } else {
            System.out.println("\t\t\t" + player1.getName() + " - " + p1HPColor + "HP: " + player1.hp + "/" + player1.maxHp + RESET
                    + " | " + BRIGHT_BLUE + "Mana: " + player1.mana + "/" + MAX_MANA + RESET);
        }
    }

    private void displayPlayer2StatsWithMana(int manaGained) {
        String p2HPColor = getHPColor(player2.hp, player2.maxHp);
        if (manaGained > 0 && player2.mana < MAX_MANA) {
            System.out.println("\t\t\t" + player2.getName() + " - " + p2HPColor + "HP: " + player2.hp + "/" + player2.maxHp + RESET
                    + " | " + BRIGHT_BLUE + "Mana: " + player2.mana + "/" + MAX_MANA + RESET
                    + BRIGHT_GREEN + " (+" + manaGained + ")" + RESET);
        } else {
            System.out.println("\t\t\t" + player2.getName() + " - " + p2HPColor + "HP: " + player2.hp + "/" + player2.maxHp + RESET
                    + " | " + BRIGHT_BLUE + "Mana: " + player2.mana + "/" + MAX_MANA + RESET);
        }
    }

    public void clearScreen() {
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
    }
}
