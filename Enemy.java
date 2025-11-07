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
                "Illusion Sneak Attack - 20 damage", "Scepter Strike - 20 damage", "Mind Control - 30 damage",
                20, 20, 30, 20, 20, 30, 100));
        enemies.add(new Enemy("Ultron", 120, 120, 18,
                "Laser Blast - 25 damage", "Metal Punch - 15 damage", "Flight Thrust Attack - 30 damage",
                25, 15, 30, 25, 15, 30, 100));
        enemies.add(new Enemy("Red Skull", 110, 110, 16,
                "Cosmic Blast - 30 damage", "Tactical Strike - 20 damage", "Rally Troops - 20 damage",
                30, 20, 20, 30, 20, 20, 100));
        enemies.add(new Enemy("Venom", 130, 130, 17,
                "Symbiote Strike - 25 damage", "Web Trap - 15 damage", "Rage - 30 damage",
                25, 15, 30, 25, 15, 30, 100));
        enemies.add(new Enemy("Thanos", 150, 150, 20,
                "Power Stone Punch - 30 damage", "Space Stone Snap - 40 damage", "Reality Warp - 45 damage",
                30, 40, 45, 30, 40, 45, 100));
        enemies.add(new Enemy("Hela", 140, 140, 19,
                "Necrosword Slash - 25 damage", "Minions Attack - 20 damage", "Asgardian Fury - 35 damage",
                25, 20, 35, 25, 20, 35, 100));
        enemies.add(new Enemy("Green Goblin", 115, 115, 16,
                "Pumpkin Bomb - 20 damage", "Glider Attack - 15 damage", "Goblin's Rage - 25 damage",
                20, 15, 25, 20, 15, 25, 100));
        enemies.add(new Enemy("Doctor Octopus", 125, 125, 17,
                "Tentacle Slam - 25 damage", "Mechanical Grab - 20 damage", "Overload - 30 damage",
                25, 20, 30, 25, 20, 30, 100));
        enemies.add(new Enemy("Magneto", 130, 130, 18,
                "Magnetic Pulse - 30 damage", "Metal Manipulation - 20 damage", "Force Field Attack - 25 damage",
                30, 20, 25, 30, 20, 25, 100));
        enemies.add(new Enemy("Kingpin", 140, 140, 19,
                "Heavy Punch - 25 damage", "Ground Slam - 30 damage", "Intimidate - 20 damage",
                25, 30, 20, 25, 30, 20, 100));
        enemies.add(new Enemy("Sandman", 135, 135, 18,
                "Sand Blast - 20 damage", "Morphing Strike - 25 damage", "Sand Smash - 35 damage",
                20, 25, 35, 20, 25, 35, 100));
        enemies.add(new Enemy("Electro", 120, 120, 17,
                "Electric Shock - 25 damage", "Thunderbolt - 30 damage", "Overcharge - 30 damage",
                25, 30, 30, 25, 30, 30, 100));
        enemies.add(new Enemy("Mystique", 110, 110, 16,
                "Shape Shift Strike - 20 damage", "Mimic Attack - 25 damage", "Invisible Hit - 30 damage",
                20, 25, 30, 20, 25, 30, 100));
        enemies.add(new Enemy("Juggernaut", 140, 150, 20,
                "Unstoppable Charge - 30 damage", "Ground Pound - 25 damage", "Rage Mode - 30 damage",
                30, 25, 30, 30, 25, 30, 100));
        enemies.add(new Enemy("Sabretooth", 130, 130, 18,
                "Ferocious Claw - 25 damage", "Savage Bite - 20 damage", "Howling Vengeance - 35 damage",
                25, 20, 35, 25, 20, 35, 100));
        

        Random rand = new Random();
        return enemies.get(rand.nextInt(enemies.size()));
    }
}

