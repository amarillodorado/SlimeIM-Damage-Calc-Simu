package damagecalculator;

public class Unit{

    private String name;
    private DamageObject damageObject;

    public Unit(String name, DamageObject damageObject){
        this.name = name;
        this.damageObject = damageObject;
    }

    public String getName() {
        return name;
    }

    public DamageObject getDamageObject() {
        return damageObject;
    }
}
