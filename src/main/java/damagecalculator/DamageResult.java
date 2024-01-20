package damagecalculator;

import java.util.Comparator;
//
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

    // Vergleichsfunktion für die Sortierung nach Namen
    public static Comparator<DamageResult> UnitNameComparator = new Comparator<DamageResult>() {
        @Override
        public int compare(DamageResult r1, DamageResult r2) {
            return r1.getUnitNames().compareTo(r2.getUnitNames());
        }
    };

    public static Comparator<DamageResult> DamageComparatorDescending = new Comparator<DamageResult>() {
        @Override
        public int compare(DamageResult r1, DamageResult r2) {
            // Vergleich der Schadenswerte
            return Double.compare(r2.getDamage(), r1.getDamage());
        }
    };

    public static Comparator<DamageResult> NameAscendingThenDamageDescending = new Comparator<DamageResult>() {
        @Override
        public int compare(DamageResult r1, DamageResult r2) {
            int nameCompare = r1.getUnitNames().compareTo(r2.getUnitNames());
            if (nameCompare != 0) {
                return nameCompare;
            }
            // Bei gleichen Namen wird nach Schaden sortiert (absteigend)
            return Double.compare(r2.getDamage(), r1.getDamage());
        }
    };
}
