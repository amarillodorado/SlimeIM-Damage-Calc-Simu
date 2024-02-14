package damagecalculator;

public class DamageResult{
    private String unitNames;
    private int damage;

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

    @Override
    public String toString() {
        String formattedUnitNames = String.format("%-55s", unitNames); // Erhöhen Sie die Breite bei Bedarf
        String formattedDamage = String.format("%,.2f", damage); // Formatieren Sie die Schadenszahlen mit zwei Dezimalstellen
        return formattedUnitNames + ": " + formattedDamage;
    }
}
