import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Character {

    public Enemy(String name, int hp, int maxHp, int attack, String skill1, String skill2, String skill3, int sk1Cost, int sk2Cost, int sk3Cost, int sk1Damage, int sk2Damage, int sk3Damage, int mana) {
        super(name, hp, maxHp, attack, skill1, skill2, skill3, sk1Cost, sk2Cost, sk3Cost, sk1Damage, sk2Damage, sk3Damage, mana);  //to call the constructor of parent character class since same raman ug data types for stats g extends or inherit nalangni sya
    }

    @Override    //<-- put because we are copying a method from the parent class. // salamat men.
    void displayStats() {
        System.out.println("\nEnemy: " + name);
        System.out.println("Stats - HP: " + hp + " | Attack: " + attack + " | Mana: " + mana);
        System.out.println("Skill 1: " + skill1);
        System.out.println("Skill 2: " + skill2);
        System.out.println("Skill 3: " + skill3);
    }

    public static Enemy getRandomEnemy() { //arr list of enemy heroes.
        List<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("Loki", 100, 100, 15, "Illusion - avoids damage", "Scepter Strike - 20 damage", "Mind Control - 30 damage", 20, 20, 30, 20, 20, 30, 100));
        enemies.add(new Enemy("Ultron", 120, 120, 18, "Laser Blast - 25 damage", "Metal Punch - 15 damage", "Self-Repair - heals 20 HP", 25, 15, 20, 25, 15, 20, 100));
        enemies.add(new Enemy("Red Skull", 110, 110, 16, "Cosmic Blast - 30 damage", "Tactical Strike - 20 damage", "Rally Troops - doubles attack", 30, 20, 20, 30, 20, 20, 100));
        enemies.add(new Enemy("Venom", 130, 130, 17, "Symbiote Strike - 25 damage", "Web Trap - 15 damage", "Rage - doubles attack", 25, 15, 20, 30, 20, 20, 100));
        Random rand = new Random(); //// random generated numberszzzzzzzzzz.
        return enemies.get(rand.nextInt(enemies.size()));
    }
}
