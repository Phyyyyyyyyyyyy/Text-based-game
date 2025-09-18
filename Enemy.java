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
    
}
