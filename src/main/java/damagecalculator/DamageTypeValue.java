package damagecalculator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DamageTypeValue {
    private String damageType;
    private Map<String, Object> unitValues; // Einheitenname zu Wert (kann int oder boolean sein)

    public DamageTypeValue(String damageType) {
        this.damageType = damageType;
        this.unitValues = new LinkedHashMap<>(); // Beibehaltung der Reihenfolge
    }

    public String getDamageType() {
        return damageType;
    }

    public void addUnitValue(String unitName, Object value) {
        this.unitValues.put(unitName, value);
    }

    // Gibt den Wert als Object zurück, um sowohl Integer als auch Boolean zu unterstützen
    public Object getUnitValue(String unitName) {
        return this.unitValues.getOrDefault(unitName, 0); // 0 als Standardwert für int, false für boolean
    }
}
