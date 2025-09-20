import java.util.*;

public class GameMechanics {
    Character player; // player from Chacracfer anjsfhjkasbfhjkas class.
    Enemy enemy;  //enemy from Enemy class

    public GameMechanics(Character player, Enemy  enemy) {
        this.player = player;
        this.enemy = enemy;       //constructor includes player and enemy obj
    }
     
    public void startBattle() {
       Scanner sc = new Scanner(System.in);
       boolean playerTurn = true;

       while(player.hp > 0 && enemy.hp > 0) {   // loop wont stop if both playerhp and enemyhp is greater than 0 for short dapat naay mapilde gyud.
          System.out.print("It's Your Turn!");
          player.displayStats();
          enemy.displayStats();
          System.out.println("Choose your action: \n1. Attack\n2. Skill 1\n3. Skill 2\n Skill 3");
          System.out.print("> ");
          int action = sc.nextInt();

          switch(action) {
            
            case 1: System.out.println("\nYou attack!");
            enemy.hp -= player.attack;                          /// skill1,2 and 3 mana system      cases1-4
            break;

            case 2: if (player.mana >= player.sk1Cost) {
                System.out.println("You use " + player.skill1 + "!");
                enemy.hp -= player.sk1Damage;
                player.mana -= player.sk1Cost;
            } else {
                System.out.println("Not enough mana to attack!");
            }
            break;
                  
            case 3: if (player.mana >= player.sk2Cost) {
                System.out.println("You use " + player.skill2 + "!");
                enemy.hp -= player.sk2Damage;
                player.mana -= player.sk2Cost;
            } else {
                System.out.println("Not enough mana to attack!");
            }
            break;

            case 4: if (player.mana >= player.sk3Cost) {
                System.out.println("You use " + player.skill3 + "!");
                enemy.hp -= player.sk3Damage;
                player.mana -= player.sk3Cost;
            } else {
                System.out.println("Not enough mana to attack!");
            }
            break;

            
          } 
       }


       
    }




}



