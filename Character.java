
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

    public String getName() {
        return name;
    }

    public String getSkill1() {
        return skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void regenerateMana(int amount) {
        this.mana += amount;
        if (this.mana > 100) {
            this.mana = 100;
        }
    }

    void displayIntro() {
        System.out.println("\n\t\t\t\t\t\t\tYou choose: " + name);
        System.out.println("\t\t\t\t\t\t\tStats - HP: " + hp + " | Attack: " + attack + " | Mana: " + mana);
        if ("Iron Man".equals(name)) {
            System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }

        if ("Captain America".equals(name)) {
           System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }

        if ("Thor".equals(name)) {
            System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }

        if ("Spider-Man".equals(name)) {
          System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }

        if ("Hulk".equals(name)) {
            System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }
        if ("Black Widow".equals(name)) {
            System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }

        if ("Ant-Man".equals(name)) {
            System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }

        if ("The Falcon".equals(name)) {
           System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }
        if("Jan Clark".equals(name)){
            System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }
        if("John Micoh".equals(name)){
            System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }
        if(" Reuben Navarrete".equals(name)){
            System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }
        if("Ethan Manto".equals(name)){
            System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }
        if("Thanos".equals(name)){
            System.out.println("\t\t\t\t\t\t\tSkills:");
            System.out.println("\t\t\t\t\t\t\t" + skill1 + " - " + sk1Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill2 + " - " + sk2Cost + " mana");
            System.out.println("\t\t\t\t\t\t\t" + skill3 + " - " + sk3Cost + " mana");
        }

        System.out.println();
    }

    void displayStats() {
        System.out.println("\t\t\t\t\t" + name + " - HP: " + hp + "/" + maxHp + " | Mana: " + mana);
    }
}



