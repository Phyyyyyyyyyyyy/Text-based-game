import java.util.*;

public class MarvelGame {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }    // para tangtang sa ni sunod nga output/strings/numbers basta naay pang clear samokag exlpain/.sfbjkaghsvfghjkcvasfghjkvasf

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  //scanner ni siya duh
        Character player = SelectScreen.select(); // select method from selectscreen class
        player.displayStats();  // display stats method from character class
        clearScreen();   // clear screen after displaying player stats
        Enemy enemy = Enemy.getRandomEnemy(); //enemyclass
        enemy.displayStats();         //displaystats() from enemy class
        clearScreen();
        GameMechanics game = new GameMechanics(player, enemy);
        game.game();
        
    }
}
