
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyHierarchy {

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    private static final List<Enemy> easyEnemies = new ArrayList<>();
    private static final List<Enemy> mediumEnemies = new ArrayList<>();
    private static final List<Enemy> hardEnemies = new ArrayList<>();
    private static final Random rand = new Random();

    static {
        initializeEnemies();
    }

    private static void initializeEnemies() {
        // EASY ENEMIES (5) - Low HP and damage
        easyEnemies.add(new Enemy("Loki", 70, 70, 12,
                "Illusion Sneak Attack - 5 damage", "Scepter Strike - 10 damage", "Mind Control - 10 damage",
                5, 10, 10, 5, 10, 10, 80));

        easyEnemies.add(new Enemy("Mystique", 70, 70, 11,
                "Shape Shift Strike - 10 damage", "Mimic Attack - 15 damage", "Invisible Hit - 18 damage",
                10, 15, 18, 10, 15, 18, 80));

        easyEnemies.add(new Enemy("Green Goblin", 75, 70, 12,
                "Pumpkin Bomb - 10 damage", "Glider Attack - 15 damage", "Goblin's Rage - 18 damage",
                10, 15, 18, 10, 15, 18, 85));

        easyEnemies.add(new Enemy("Electro", 70, 70, 11,
                "Electric Shock - 10 damage", "Thunderbolt - 12 damage", "Overcharge - 15 damage",
                10, 12, 15, 10, 12, 15, 75));

        easyEnemies.add(new Enemy("Sandman", 70, 70, 11,
                "Sand Blast - 10 damage", "Morphing Strike - 12 damage", "Sand Smash - 15 damage",
                10, 12, 15, 10, 12, 15, 90));

        // MEDIUM ENEMIES (5) - Medium HP and damage
        mediumEnemies.add(new Enemy("Ultron", 100, 100, 15,
                "Laser Blast - 13 damage", "Metal Punch - 13 damage", "Flight Thrust Attack - 15 damage",
                13, 13, 15, 13, 13, 15, 100));

        mediumEnemies.add(new Enemy("Red Skull", 100, 100, 15,
                "Cosmic Blast - 10 damage", "Tactical Strike - 10 damage", "Rally Troops - 15 damage",
                10, 10, 15, 10, 10, 15, 100));

        mediumEnemies.add(new Enemy("Venom", 100, 100, 15,
                "Symbiote Strike - 10 damage", "Web Trap - 15 damage", "Rage - 18 damage",
                10, 15, 18, 10, 15, 18, 100));

        mediumEnemies.add(new Enemy("Doctor Octopus", 110, 110, 15,
                "Tentacle Slam - 10 damage", "Mechanical Grab - 15 damage", "Overload - 18 damage",
                10, 15, 18, 10, 15, 18, 100));

        mediumEnemies.add(new Enemy("Sabretooth", 104, 104, 15,
                "Ferocious Claw - 13 damage", "Savage Bite - 15 damage", "Howling Vengeance - 18 damage",
                10, 13, 16, 10, 13, 16, 100));

        // HARD ENEMIES (5) - High HP and damage
        hardEnemies.add(new Enemy("Magneto", 120, 120, 15,
                "Magnetic Pulse - 15 damage", "Metal Manipulation - 15 damage", "Force Field Attack - 20 damage",
                12, 14, 16, 12, 14, 16, 120));

        hardEnemies.add(new Enemy("Hela", 120, 120, 15,
                "Necrosword Slash - 16 damage", "Minions Attack - 18 damage", "Asgardian Fury - 20 damage",
                12, 14, 16, 12, 14, 16, 120));

        hardEnemies.add(new Enemy("Kingpin", 120, 120, 15,
                "Heavy Punch - 14 damage", "Ground Slam - 16 damage", "Intimidate - 18 damage",
                12, 14, 16, 12, 14, 16, 120));

        hardEnemies.add(new Enemy("Juggernaut", 125, 125, 15,
                "Unstoppable Charge - 14 damage", "Ground Pound - 16 damage", "Rage Mode - 18 damage",
                14, 16, 18, 14, 16, 18, 120));

        //////finals bows
        hardEnemies.add(new Enemy("Thanos", 135, 135, 22,
                "Power Stone Punch - 10 damage", "Space Stone Snap - 15 damage", "Reality Warp - 20 damage",
                10, 15, 20, 10, 15, 20, 130));
    }

    public static Enemy getEnemyByDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return easyEnemies.get(rand.nextInt(easyEnemies.size()));
            case MEDIUM:
                return mediumEnemies.get(rand.nextInt(mediumEnemies.size()));
            case HARD:
                return hardEnemies.get(rand.nextInt(hardEnemies.size()));
            default:
                return easyEnemies.get(rand.nextInt(easyEnemies.size()));
        }
    }

    public static List<Enemy> getEnemiesByDifficulty(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return new ArrayList<>(easyEnemies);
            case MEDIUM:
                return new ArrayList<>(mediumEnemies);
            case HARD:
                return new ArrayList<>(hardEnemies);
            default:
                return new ArrayList<>(easyEnemies);
        }
    }

    public static Enemy getRandomEnemy() {
        int randomDifficulty = rand.nextInt(3);
        if (randomDifficulty == 0) {
            return getEnemyByDifficulty(Difficulty.EASY);
        }
        if (randomDifficulty == 1) {
            return getEnemyByDifficulty(Difficulty.MEDIUM);
        }
        return getEnemyByDifficulty(Difficulty.HARD);
    }

    public static void displayAllEnemies() {

        System.out.println();
    }
}
