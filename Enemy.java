import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Character {

    public Enemy(String name, int hp, int maxHp, int attack,
                 String skill1, String skill2, String skill3,
                 int sk1Cost, int sk2Cost, int sk3Cost,
                 int sk1Damage, int sk2Damage, int sk3Damage,
                 int mana) {
        super(name, hp, maxHp, attack, skill1, skill2, skill3,
              sk1Cost, sk2Cost, sk3Cost, sk1Damage, sk2Damage, sk3Damage, mana);
    }

    @Override
    public void displayIntro() {
        System.out.println("\n\t\t\t\t\t\t\tEnemy: " + getName());
        System.out.println("\t\t\t\t\t\t\tStats - HP: " + getHp() + " | Attack: " + getAttack() + " | Mana: " + getMana());
        System.out.println("\t\t\t\t\t\t\tSkills:");
        System.out.println("\t\t\t\t\t\t\t" + getSkill1() + " - " + getSk1Cost() + " mana");
        System.out.println("\t\t\t\t\t\t\t" + getSkill2() + " - " + getSk2Cost() + " mana");
        System.out.println("\t\t\t\t\t\t\t" + getSkill3() + " - " + getSk3Cost() + " mana");
        System.out.println();
    }

    @Override
    public void displayStats() {
        System.out.println("\t\t\t\t" + getName() + " - HP: " + getHp() + "/" + getMaxHp() + " | Mana: " + getMana());
    }

    public static Enemy getRandomEnemy() {
        List<Enemy> enemies = new ArrayList<>();
        Random rand = new Random();

        
        enemies.add(new Enemy("Loki", 100, 100, 15,
                "Illusion Sneak Attack", "Scepter Strike", "Mind Control",
                15, 20, 25, 15, 20, 25, 100));

        enemies.add(new Enemy("Mystique", 100, 100, 16,
                "Shape Shift Strike", "Mimic Attack", "Invisible Hit",
                15, 20, 25, 15, 20, 25, 100));

        enemies.add(new Enemy("Green Goblin", 100, 100, 16,
                "Pumpkin Bomb", "Glider Attack", "Goblin's Rage",
                15, 20, 25, 15, 20, 25, 100));

       
        enemies.add(new Enemy("Ultron", 110, 110, 18,
                "Laser Blast", "Metal Punch", "Flight Thrust Attack",
                15, 15, 20, 15, 15, 20, 100));

        enemies.add(new Enemy("Red Skull", 110, 110, 16,
                "Cosmic Blast", "Tactical Strike", "Rally Troops",
                15, 15, 20, 15, 15, 20, 100));

        enemies.add(new Enemy("Electro", 110, 110, 17,
                "Electric Shock", "Thunderbolt", "Overcharge",
                15, 15, 20, 15, 15, 20, 100));

        enemies.add(new Enemy("Sabretooth", 110, 110, 18,
                "Ferocious Claw", "Savage Bite", "Howling Vengeance",
                15, 15, 20, 15, 15, 20, 100));

        enemies.add(new Enemy("Sandman", 110, 110, 18,
                "Sand Blast", "Morphing Strike", "Sand Smash",
                15, 15, 20, 15, 15, 20, 100));

       
        enemies.add(new Enemy("Venom", 120, 120, 17,
                "Symbiote Strike", "Web Trap", "Rage",
                12, 13, 15, 12, 13, 15, 100));

        enemies.add(new Enemy("Doctor Octopus", 120, 120, 17,
                "Tentacle Slam", "Mechanical Grab", "Overload",
                12, 13, 15, 12, 13, 15, 100));

        enemies.add(new Enemy("Magneto", 120, 120, 18,
                "Magnetic Pulse", "Metal Manipulation", "Force Field Attack",
                12, 13, 15, 12, 13, 15, 100));

        enemies.add(new Enemy("Hela", 120, 120, 19,
                "Necrosword Slash", "Minions Attack", "Asgardian Fury",
                12, 13, 15, 12, 13, 15, 100));

        enemies.add(new Enemy("Kingpin", 120, 120, 19,
                "Heavy Punch", "Ground Slam", "Intimidate",
                12, 13, 15, 12, 13, 15, 100));

        enemies.add(new Enemy("Juggernaut", 120, 120, 20,
                "Unstoppable Charge", "Ground Pound", "Rage Mode",
                12, 13, 15, 12, 13, 15, 100));

        
        enemies.add(new Enemy("Thanos", 130, 130, 20,
                "Power Stone Punch", "Space Stone Snap", "Reality Warp",
                18, 20, 22, 18, 20, 22, 100));

        return enemies.get(rand.nextInt(enemies.size()));
    }
}
