package damagecalculator;

import java.util.ArrayList;
import java.util.List;

public enum UnitPreList {
    EMPTY(new Unit("empty", new DamageObject())),
    
    VALENTINE_OCTA(new Unit("Valentine Octagram", new DamageObject()
            .setSecretSkillBuff(80)
            .setSecretSkillTrue(true)
    )),
    VIOLET_WM(new Unit("Violet WM", new DamageObject()
            .setSecretDamageResistanceDown(55)
            .setSecretSkillTrue(true)
            .setCritBuffANDDebuff(40)
            .setCritTrue(true)
            )),
    AQUA(new Unit("Aqua", new DamageObject()
            .setPierceUPResDown(60)
            .setPierceTrue(true)
            )),
    HINATA_WOM(new Unit("Hinata WoM", new DamageObject()
            .setElementalATKBuff(50)
            )),
    MASKEDHERO_WOF(new Unit("Masked Hero WoF", new DamageObject()
            .setElementalATKDebuff(50)
            )),
    VIOLET_VOC(new Unit("Violet VoC", new DamageObject()
            .setMagicPhysicalBuff(100)
            )),

    JAUNE_VOC(new Unit("Jaune VoC", new DamageObject()
            .setSecretSkillTrue(true)
            .setSecretSkillBuff(100)
            )),

    BLANC_VOC(new Unit("Blanc VoC", new DamageObject()
            .setWeakpointBuff(100)
            .setWeakpointTrue(true)
            )),

    Rimuru_FOW(new Unit("Rimuru FoW", new DamageObject()
            .setMagicPhysicalBuff(65)
            .setMagicPhysicalDebuff(20)
            )),

    Shinsha_NYB(new Unit("Shinsha NYB", new DamageObject()
            .setSecretSkillBuff(80)
            .setCritBuffANDDebuff(40)
            )),

    Milim_V2_NYB(new Unit("Milim V2 NYB",new DamageObject()
            .setSecretDamageResistanceDown(40)
            .setSecretSkillTrue(true)
            .setSynergyUpDown(20)
            )),

    GUY_WM(new Unit("Guy WM", new DamageObject()
            .setSecretSkillBuff(80)
            .setSecretSkillTrue(true)
            .setCritTrue(true)
            ));

    private final Unit unit;

    UnitPreList(Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public static List<String> getAllEnumNames() {
        List<String> enumNames = new ArrayList<>();
        for (UnitPreList unitPreList : UnitPreList.values()) {
            enumNames.add(unitPreList.name());
        }
        return enumNames;
    }

    public static List<Unit> getSpecificUnitByEnumName(String... enumNames) {
        List<Unit> selectedUnits = new ArrayList<>();
        for (UnitPreList unitPreList : UnitPreList.values()) {
            for (String enumName : enumNames) {
                if (unitPreList.name().equalsIgnoreCase(enumName)) {
                    selectedUnits.add(unitPreList.getUnit());
                    break; // Um Duplikate zu vermeiden, falls der gleiche Enum-Name mehrfach angegeben wird
                }
            }
        }
        return selectedUnits;
    }

    public static List<Unit> getAllUnits() {
        List<Unit> units = new ArrayList<>();
        for (UnitPreList unitPreList : UnitPreList.values()) {
            units.add(unitPreList.getUnit());
        }
        return units;
    }
}
