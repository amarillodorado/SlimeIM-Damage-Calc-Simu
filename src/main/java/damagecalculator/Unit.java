package damagecalculator;

public class Unit{

    private String name;
    private int skillCost;
    private DamageObject damageObject;

    public Unit(String name, int skillCost, DamageObject damageObject){
        this.name = name;
        this.skillCost = skillCost;
        this.damageObject = damageObject;
    }

    public String getName() {
        return name;
    }

    public DamageObject getDamageObject() {
        return damageObject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamageObject(DamageObject damageObject) {
        this.damageObject = damageObject;
    }

    public int getSkillCost() {
        return skillCost;
    }

    public void setSkillCost(int skillCost) {
        this.skillCost = skillCost;
    }
}
