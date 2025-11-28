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
        System.out.println("\n\t\t\t\t\t\t\tEnemy: " + name);
        System.out.println("\t\t\t\t\t\t\tStats - HP: " + hp + " | Attack: " + attack + " | Mana: " + mana);
        System.out.println("\t\t\t\t\t\t\t1 " + skill1 + " - " +sk1Cost+" mana");
        System.out.println("\t\t\t\t\t\t\t2 " + skill2 + " - " +sk2Cost+" mana");
        System.out.println("\t\t\t\t\t\t\t3 " + skill3 + " - " +sk3Cost+" mana");
        System.out.println();
    }

    @Override
    void displayStats() {
        System.out.println("\t\t\t\t" + name + " - HP: " + hp + "/" + maxHp + " | Mana: " + mana);
    }

    public static Enemy getRandomEnemy() {
        List<Enemy> enemies = new ArrayList<>();
        
        // HP 100: Total damage = 60
        enemies.add(new Enemy("Loki", 100, 100, 15,
                "Illusion Sneak Attack - 15 damage", "Scepter Strike - 20 damage", "Mind Control - 25 damage",
                15, 20, 25, 15, 20, 25, 100));
        
        enemies.add(new Enemy("Mystique", 100, 100, 16,
                "Shape Shift Strike - 15 damage", "Mimic Attack - 20 damage", "Invisible Hit - 25 damage",
                15, 20, 25, 15, 20, 25, 100));
        
        enemies.add(new Enemy("Green Goblin", 100, 100, 16,
                "Pumpkin Bomb - 15 damage", "Glider Attack - 20 damage", "Goblin's Rage - 25 damage",
                15, 20, 25, 15, 20, 25, 100));

        // HP 110: Total damage = 50
        enemies.add(new Enemy("Ultron", 110, 110, 18,
                "Laser Blast - 15 damage", "Metal Punch - 15 damage", "Flight Thrust Attack - 20 damage",
                15, 15, 20, 15, 15, 20, 100));
        
        enemies.add(new Enemy("Red Skull", 110, 110, 16,
                "Cosmic Blast - 15 damage", "Tactical Strike - 15 damage", "Rally Troops - 20 damage",
                15, 15, 20, 15, 15, 20, 100));
        
        enemies.add(new Enemy("Electro", 110, 110, 17,
                "Electric Shock - 15 damage", "Thunderbolt - 15 damage", "Overcharge - 20 damage",
                15, 15, 20, 15, 15, 20, 100));

        // HP 120: Total damage = 40
        enemies.add(new Enemy("Venom", 120, 120, 17,
                "Symbiote Strike - 12 damage", "Web Trap - 13 damage", "Rage - 15 damage",
                12, 13, 15, 12, 13, 15, 100));
        
        enemies.add(new Enemy("Doctor Octopus", 120, 120, 17,
                "Tentacle Slam - 12 damage", "Mechanical Grab - 13 damage", "Overload - 15 damage",
                12, 13, 15, 12, 13, 15, 100));
        
        enemies.add(new Enemy("Magneto", 120, 120, 18,
                "Magnetic Pulse - 12 damage", "Metal Manipulation - 13 damage", "Force Field Attack - 15 damage",
                12, 13, 15, 12, 13, 15, 100));

        // Hela changed to 120 HP: Total damage = 40
        enemies.add(new Enemy("Hela", 120, 120, 19,
                "Necrosword Slash - 12 damage", "Minions Attack - 13 damage", "Asgardian Fury - 15 damage",
                12, 13, 15, 12, 13, 15, 100));
        
        enemies.add(new Enemy("Sabretooth", 110, 110, 18,
                "Ferocious Claw - 15 damage", "Savage Bite - 15 damage", "Howling Vengeance - 20 damage",
                15, 15, 20, 15, 15, 20, 100));
        
        enemies.add(new Enemy("Sandman", 110, 110, 18,
                "Sand Blast - 15 damage", "Morphing Strike - 15 damage", "Sand Smash - 20 damage",
                15, 15, 20, 15, 15, 20, 100));

        // HP 120: Total damage = 40
        enemies.add(new Enemy("Kingpin", 120, 120, 19,
                "Heavy Punch - 12 damage", "Ground Slam - 13 damage", "Intimidate - 15 damage",
                12, 13, 15, 12, 13, 15, 100));
        
        enemies.add(new Enemy("Juggernaut", 120, 120, 20,
                "Unstoppable Charge - 12 damage", "Ground Pound - 13 damage", "Rage Mode - 15 damage",
                12, 13, 15, 12, 13, 15, 100));

        // HP 150: Total damage = 60 (Boss enemy - Thanos)
        enemies.add(new Enemy("Thanos", 130, 130, 20,
                "Power Stone Punch - 18 damage", "Space Stone Snap - 20 damage", "Reality Warp - 22 damage",
                18, 20, 22, 18, 20, 22, 100));

        Random rand = new Random();
        return enemies.get(rand.nextInt(enemies.size()));
    }
}
