package damagecalculator;

public class DamageResult{
    private final String unitNames;
    private final int damage;

    public DamageResult(String unitNames, double damage) {
        this.unitNames = unitNames;
        this.damage = (int) damage;
    }

    public String getUnitNames() {
        return unitNames;
    }

    public int getDamage() {
        return damage;
    }
}
