public class Enemy {
    String name;
    int hp;
    int maxHp = hp;
    int attack;
    String skill1;
    String skill2;
    String skill3;
    int sk1Cost;
    int sk2Cost;
    int sk3Cost;
    int mana;

    public Enemy(String name, int hp, int maxHp, int attack, String skill1, String skill2, String skill3, int sk1Cost, int sk2Cost, int sk3Cost, int mana) {
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.sk1Cost = sk1Cost;
        this.sk2Cost = sk2Cost;
        this.sk3Cost = sk3Cost;
        this.mana = mana;
    }

    void displayStats() {
        System.out.println("\nEnemy: " + name);
        System.out.println("Stats - HP: " + hp + " | Attack: " + attack + " | Mana: " + mana);
        System.out.println("Skill 1: " + skill1);
        System.out.println("Skill 2: " + skill2);
        System.out.println("Skill 3: " + skill3);
    }

    public static Enemy getRandomEnemy() {
        List<Enemy> enemies = new ArrayList<>();        
        enemies.add(new Enemy("Loki", 100, 100, 15, "Illusion - avoids damage", "Scepter Strike - 20 damage", "Mind Control - 30 damage", 20, 20, 30, 100));
        enemies.add(new Enemy("Ultron", 120, 120, 18, "Laser Blast - 25 damage", "Metal Punch - 15 damage", "Self-Repair - heals 20 HP", 25, 15, 20, 100));
        enemies.add(new Enemy("Red Skull", 110, 110, 16, "Cosmic Blast - 30 damage", "Tactical Strike - 20 damage", "Rally Troops - doubles attack", 30, 20, 20, 100));
        enemies.add(new Enemy("Venom", 130, 130, 17, "Symbiote Strike - 25 damage", "Web Trap - 15 damage", "Rage - doubles attack", 25, 15, 20, 100));         //arraylist of enemy characters for cpu/ai/bot

        Random rand = new Random();       //to select random enem
        return enemies.get(rand.nextInt(enemies.size()));           //return enemy object for main
    }
    
}

