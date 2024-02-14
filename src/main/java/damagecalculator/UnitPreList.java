package damagecalculator;

import java.util.ArrayList;
import java.util.List;

public enum UnitPreList {
    VIOLET_WM(new Unit("Violet WM", 80, new DamageObject()
            .setSecretDamageResistanceDown(55)
            .setSecretSkillTrue(true)
            .setCritBuffANDDebuff(40)
            .setCritTrue(true)
            ),"icons/VioletHA2023_5_CharaPartyM.png"),
    AQUA(new Unit("Aqua", 80, new DamageObject()
            .setPierceUPResDown(60)
            .setPierceTrue(true)
            ),"icons/AquaDefault_5_CharaPartyM.png"),
    HINATA_WOM(new Unit("Hinata WoM", 40, new DamageObject()
            .setElementalATKBuff(50)
            ),"icons/Hinata2Anniv_5_CharaPartyM.png"),
    VIOLET_VOC(new Unit("Violet VoC",80, new DamageObject()
            .setMagicPhysicalBuff(100)
            ),"icons/Violet2Anniv_5_CharaPartyM.png"),

    JAUNE_VOC(new Unit("Jaune VoC",80, new DamageObject()
            .setSecretSkillTrue(true)
            .setSecretSkillBuff(100)
            ),"icons/Jaune2Anniv_5_CharaPartyM.png"),

    BLANC_VOC(new Unit("Blanc VoC",80, new DamageObject()
            .setWeaknessStrikeBuff(100)
            .setWeakpointTrue(true)
            ),"icons/Buran2Anniv_5_CharaPartyM.png"),

    Rimuru_FOW(new Unit("Rimuru FoW",80, new DamageObject()
            .setMagicPhysicalBuff(65)
            .setMagicPhysicalDebuff(20)
            ),"icons/RimuruSchool2023_5_CharaPartyM.png"),

    Shinsha_NYB(new Unit("Shinsha NYB",80, new DamageObject()
            .setSecretSkillBuff(80)
            .setCritBuffANDDebuff(40)
            ),"icons/CynthiaArmor_5_CharaPartyM.png"),

    Milim_V2_NYB(new Unit("Milim V2 NYB",40,new DamageObject()
            .setSecretDamageResistanceDown(40)
            .setSecretSkillTrue(true)
            .setSynergyUpDown(20)
            ),"icons/Milim1kuji2024_5_CharaPartyM.png"),

    GUY_WM(new Unit("Guy WM",80, new DamageObject()
            .setSecretSkillBuff(80)
            .setSecretSkillTrue(true)
            .setCritTrue(true)
            ),"icons/GuyHA2023_5_CharaPartyM.png");

    private final Unit unit;
    private final String imagePath;

    UnitPreList(Unit unit, String imagePath) {
        this.unit = unit;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return this.imagePath;
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
}
