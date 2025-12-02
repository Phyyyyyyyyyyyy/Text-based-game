// Character.java
public abstract class Character {
    
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private String skill1;
    private String skill2;
    private String skill3;
    private int sk1Cost;
    private int sk2Cost;
    private int sk3Cost;
    private int sk1Damage;
    private int sk2Damage;
    private int sk3Damage;
    private int mana;

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

    // Getters
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public String getSkill1() { return skill1; }
    public String getSkill2() { return skill2; }
    public String getSkill3() { return skill3; }
    public int getSk1Cost() { return sk1Cost; }
    public int getSk2Cost() { return sk2Cost; }
    public int getSk3Cost() { return sk3Cost; }
    public int getSk1Damage() { return sk1Damage; }
    public int getSk2Damage() { return sk2Damage; }
    public int getSk3Damage() { return sk3Damage; }
    public int getMana() { return mana; }

    // Setters
    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(hp, maxHp));
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, Math.min(mana, 100));
    }

    public void regenerateMana(int amount) {
        setMana(this.mana + amount); // Use setter for safety
    }

    // Abstract methods
    public abstract void displayIntro();
    public abstract void displayStats();

    // Common concrete method — now uses getters
    protected String getBaseStats() {
        return getName() + " - HP: " + getHp() + "/" + getMaxHp() +
               " | Attack: " + getAttack() + " | Mana: " + getMana();
    }
}
