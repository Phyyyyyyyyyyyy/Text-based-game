public class Hero extends Character {

    public Hero(String name, int hp, int maxHp, int attack, String skill1, String skill2, String skill3,
                int sk1Cost, int sk2Cost, int sk3Cost, int sk1Damage, int sk2Damage, int sk3Damage, int mana) {
        super(name, hp, maxHp, attack, skill1, skill2, skill3, 
              sk1Cost, sk2Cost, sk3Cost, sk1Damage, sk2Damage, sk3Damage, mana);
    }

    @Override
    public void displayIntro() {
        System.out.println("\n\t\t\t\t\t\t\tYou choose: " + getName());
        System.out.println("\t\t\t\t\t\t\tStats - HP: " + getHp() + " | Attack: " + getAttack() + " | Mana: " + getMana());
        System.out.println("\t\t\t\t\t\t\tSkills:");
        System.out.println("\t\t\t\t\t\t\t" + getSkill1() + " - " + getSk1Cost() + " mana");
        System.out.println("\t\t\t\t\t\t\t" + getSkill2() + " - " + getSk2Cost() + " mana");
        System.out.println("\t\t\t\t\t\t\t" + getSkill3() + " - " + getSk3Cost() + " mana");
        System.out.println();
    }

    @Override
    public void displayStats() {
        System.out.println("\t\t\t\t\t" + getName() + " - HP: " + getHp() + "/" + getMaxHp() + " | Mana: " + getMana());
    }
}
