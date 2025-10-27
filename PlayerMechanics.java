// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
import java.io.PrintStream;
import java.util.Scanner;

public class PlayerMechanics {
   Character player1;
   Character player2;
   public int turnCount = 1;
   private Scanner sc;
   private CooldownManager p1CD;
   private CooldownManager p2CD;

   public PlayerMechanics(Character var1, Character var2) {
      this.sc = new Scanner(System.in);
      this.p1CD = new CooldownManager();
      this.p2CD = new CooldownManager();
      this.player1 = var1;
      this.player2 = var2;
   }

   public void game() {
      System.out.println("\n==============================");
      System.out.println("Player 1: ");
      this.player1.displayStats();
      System.out.println("\nPlayer 2: ");
      this.player2.displayStats();
      System.out.println("==============================");

      while(this.player1.hp > 0 && this.player2.hp > 0) {
         System.out.println("\n==============================");
         System.out.println("Turn " + this.turnCount);
         System.out.println("==============================");
         System.out.println(this.player1.getName() + "'s Turn");
         PrintStream var10000 = System.out;
         String var10001 = this.p1CD.getFormattedCooldown(1);
         var10000.println("Player 1 Cooldowns - S1: " + var10001 + " | S2: " + this.p1CD.getFormattedCooldown(2) + " | S3: " + this.p1CD.getFormattedCooldown(3));
         System.out.println();
         this.playerAction(this.player1, this.player2, this.p1CD);
         if (this.player2.hp <= 0) {
            break;
         }

         System.out.println("\n" + this.player2.getName() + "'s Turn");
         var10000 = System.out;
         var10001 = this.p2CD.getFormattedCooldown(1);
         var10000.println("Player 2 Cooldowns - S1: " + var10001 + " | S2: " + this.p2CD.getFormattedCooldown(2) + " | S3: " + this.p2CD.getFormattedCooldown(3));
         System.out.println();
         this.playerAction(this.player2, this.player1, this.p2CD);
         this.p1CD.reduceCooldowns();
         this.p2CD.reduceCooldowns();
         ++this.turnCount;
      }

      System.out.println("\n==============================");
      if (this.player1.hp <= 0 && this.player2.hp <= 0) {
         System.out.println("It's a draw!");
      } else if (this.player1.hp <= 0) {
         System.out.println(this.player2.getName() + " wins!");
      } else {
         System.out.println(this.player1.getName() + " wins!");
      }

      System.out.println("==============================");
   }

   private int calculateBasicAttackDamage(int var1) {
      double var2 = 0.8;
      double var4 = 1.2;
      double var6 = var2 + (var4 - var2) * Math.random();
      return (int)((double)var1 * var6);
   }

   private void useSkill(int var1, Character var2, Character var3, CooldownManager var4) {
      PrintStream var10000;
      String var10001;
      if (!var4.canUseSkill(var1)) {
         var10000 = System.out;
         var10001 = var4.getFormattedCooldown(var1);
         var10000.println("Skill is on cooldown! (" + var10001 + ")");
      } else {
         boolean var5 = false;
         boolean var6 = false;
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
               System.out.println("Invalid skill number!");
               return;
         }

         if (var2.mana < var8) {
            System.out.println("Not enough mana!");
         } else {
            var10000 = System.out;
            var10001 = var2.getName();
            var10000.println(var10001 + " uses " + var7 + "!");
            if (!var7.toLowerCase().contains("heal") && !var7.toLowerCase().contains("repair")) {
               if (var7.toLowerCase().contains("double")) {
                  var2.attack *= 2;
                  System.out.println(var2.getName() + "'s attack doubled!");
               } else {
                  var3.hp -= var9;
                  System.out.println(var2.getName() + " dealt " + var9 + " damage to " + var3.getName() + "!");
               }
            } else {
               var2.hp = Math.min(var2.hp + var9, var2.maxHp);
               var10000 = System.out;
               var10001 = var2.getName();
               var10000.println(var10001 + " heals for " + var9 + " HP!");
            }

            var2.mana -= var8;
            var4.applyCooldown(var1);
         }
      }
   }

   private void playerAction(Character var1, Character var2, CooldownManager var3) {
      boolean var4 = false;

      while(!var4 && var1.hp > 0 && var2.hp > 0) {
         System.out.println("Choose your action:");
         System.out.println("0. Basic Attack");
         PrintStream var10000 = System.out;
         String var10001 = var1.getSkill1();
         var10000.println("1. " + var10001 + " - " + var1.sk1Cost + " mana");
         var10000 = System.out;
         var10001 = var1.getSkill2();
         var10000.println("2. " + var10001 + " - " + var1.sk2Cost + " mana");
         var10000 = System.out;
         var10001 = var1.getSkill3();
         var10000.println("3. " + var10001 + " - " + var1.sk3Cost + " mana");
         System.out.print("> ");

         int var5;
         try {
            var5 = Integer.parseInt(this.sc.nextLine());
         } catch (NumberFormatException var7) {
            System.out.println("Invalid choice!");
            continue;
         }

         switch (var5) {
            case 0:
               System.out.println(var1.getName() + " performs a basic attack!");
               var2.hp -= this.calculateBasicAttackDamage(var1.attack);
               var4 = true;
               break;
            case 1:
            case 2:
            case 3:
               this.useSkill(var5, var1, var2, var3);
               var4 = true;
               break;
            default:
               System.out.println("Invalid choice!");
         }
      }

      var1.regenerateMana(10);
      if (var2.hp < 0) {
         var2.hp = 0;
      }

      if (var1.hp < 0) {
         var1.hp = 0;
      }

      System.out.println();
      System.out.println("Player 1: ");
      this.player1.displayStats();
      System.out.println("Player 2: ");
      this.player2.displayStats();
   }

   public void clearScreen() {
      System.out.print("\u001b[H\u001b[2J");
      System.out.flush();
   }
}
