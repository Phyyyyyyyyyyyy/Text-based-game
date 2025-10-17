import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Character {

    public Enemy(String name, int hp, int maxHp, int attack, String skill1, String skill2, String skill3,
                 int sk1Cost, int sk2Cost, int sk3Cost, int sk1Damage, int sk2Damage, int sk3Damage, int mana) {
        super(name, hp, maxHp, attack, skill1, skill2, skill3,
                sk1Cost, sk2Cost, sk3Cost, sk1Damage, sk2Damage, sk3Damage, mana);
    }

    @Override
    void displayIntro() {
        System.out.println("\nEnemy: " + name);
        System.out.println("Stats - HP: " + hp + " | Attack: " + attack + " | Mana: " + mana);
        System.out.println("1 " + skill1 + " - " +sk1Cost+" mana");
        System.out.println("2 " + skill2 + " - " +sk2Cost+" mana");
        System.out.println("3 " + skill3 + " - " +sk3Cost+" mana");
        System.out.println();
    }

    @Override
    void displayStats() {
        System.out.println(name + " - HP: " + hp + "/" + maxHp + " | Mana: " + mana);
    }

    public static Enemy getRandomEnemy() {
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("Loki", 100, 100, 15,
                "Illusion - avoids damage", "Scepter Strike - 20 damage", "Mind Control - 30 damage",
                20, 20, 30, 20, 20, 30, 100));
        enemies.add(new Enemy("Ultron", 120, 120, 18,
                "Laser Blast - 25 damage", "Metal Punch - 15 damage", "Self-Repair - heals 20 HP",
                25, 15, 20, 25, 15, 20, 100));
        enemies.add(new Enemy("Red Skull", 110, 110, 16,
                "Cosmic Blast - 30 damage", "Tactical Strike - 20 damage", "Rally Troops - doubles attack",
                30, 20, 20, 30, 20, 20, 100));
        enemies.add(new Enemy("Venom", 130, 130, 17,
                "Symbiote Strike - 25 damage", "Web Trap - 15 damage", "Rage - doubles attack",
                25, 15, 20, 30, 20, 20, 100));
        enemies.add(new Enemy("Thanos", 150, 150, 20,
                "Power Stone Punch - 30 damage", "Space Stone Snap - eliminates opponent", "Reality Warp - avoids damage",
                30, 40, 20, 30, 0, 0, 100));

        Random rand = new Random();
        return enemies.get(rand.nextInt(enemies.size()));
    }
}

