package damagecalculator;

public class Unit{

    private String name;

    private int skillcost;
    private DamageObject damageObject;

    public Unit(String name, int skillcost, DamageObject damageObject){
        this.name = name;
        this.skillcost = skillcost;
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

    public int getSkillcost() {
        return skillcost;
    }

    public void setSkillcost(int skillcost) {
        this.skillcost = skillcost;
    }
}
