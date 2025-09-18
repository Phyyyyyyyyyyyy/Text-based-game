public class Character {
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

        public Character(String name, int hp, int maxHp, int attack, String skill1, String skill2, String skill3, int sk1Cost, int sk2Cost, int sk3Cost, int mana) {
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.skill1 = skill1;
        this.skill2 = skill2;                                                           ///////////////////////////////////forda construct ang this
        this.skill3 = skill3;
        this.sk1Cost = sk1Cost;
        this.sk2Cost = sk2Cost;
        this.sk3Cost = sk3Cost;
        this.mana = mana;
    
    }

    void displayStats() {
        System.out.println("\nYou chose: " + name);               //display stats mehtod after picking para isa ra tanan para way kapoy, kay tapulan kong dako.
        System.out.println("Stats - HP: " + hp + " | Attack: " + attack + " | Mana: +  " + mana + " \n Skill 1: " + skill1 + " \n Skill 2: " + skill2 + " \n Skill 3: " + skill3  + " ");
    }
}
