public class Character {
    String name;
    int hp;
    int maxHp;
    int attack;
    String skill1;
    String skill2;
    String skill3;
    int sk1Cost;
    int sk2Cost;
    int sk3Cost;
    int sk1Damage;
    int sk2Damage;
    int sk3Damage;  
    int mana;

    public Character(String name, int hp, int maxHp, int attack, String skill1, String skill2, String skill3,
                     int sk1Cost, int sk2Cost, int sk3Cost, int sk1Damage, int sk2Damage, int sk3Damage, int mana) {
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
        this.sk1Damage = sk1Damage;
        this.sk2Damage = sk2Damage;
        this.sk3Damage = sk3Damage;
        this.mana = mana;
    }

    public String getName() { return name; }
    public String getSkill1() { return skill1; }
    public String getSkill2() { return skill2; }
    public String getSkill3() { return skill3; }

    public void regenerateMana(int amount) {
        this.mana += amount;
        if (this.mana > 100) {
            this.mana = 100;
        }
    }

    void displayIntro() {
        System.out.println("\nYou chose: " + name);
        System.out.println("Stats - HP: " + hp + " | Attack: " + attack + " | Mana: " + mana +
                "\n Skill 1: " + skill1 + 
                "\n Skill 2: " + skill2 + 
                "\n Skill 3: " + skill3);
        System.out.println();
    }

    void displayStats() {
        System.out.println(name + " - HP: " + hp + "/" + maxHp + " | Mana: " + mana);
    }
}
