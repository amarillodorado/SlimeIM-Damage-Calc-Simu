package damagecalculator;

import java.util.ArrayList;
import java.util.List;

public class Simulator {

    static DamageObject damageObject;


/*
    private void setValues(){

        atk_Debuff = 0;
        atk_Buff = 141;
        atk_PT = 12850;
        elementalATKBuff = 15;
        elementalATKDebuff = 50;
        MagicPhysicalBuff = 105;
        MagicPhysicalDebuff = 0;
        attributeResBuff = 0;
        attributeResDown = 0;
        attackResBuff = 0;
        attackResDown = 0;
        synergyTrue = true;
        synergyUpDown = 80;
        synergyPartnerATK = 3100;
        def_ene = 11983;
        def_up = 0;
        def_down = 0;
        stundStrikeMulti = 0;
        stunTrue = false;
        charmStrikeBuff = 0;
        charmTrue = false;
        critBuffANDDebuff = 0;
        critTrue = false;
        secretSkillfromCharacter = 765;
        secretSkillUPResDown = 170;
        secretSkillTrue = true;
        secretDamageResUPvar = 55;
        secretDamageResUPTrue = true;
        penetrationUPResDown = 75;
        penetrationTrue = true;
        attributeAdvantageTrue = true;
        weaknessProtectorBuff = 60;
        weakpointBuff = 100;
        weakpointTrue = true;
        unit120 = true; //If the unit is 120 multiply ult with 1.2

    }

 */

    public static void main(String[] args) {
        damageObject = new DamageObject().setAtk_Buff(141)
                .setAtk_PT(12850)
                .setElementalATKBuff(15)
                .setElementalATKDebuff(50)
                .setMagicPhysicalBuff(105)
                .setSynergyTrue()
                .setSynergyUpDown(80)
                .setSynergyPartnerATK(3100)
                .setDef_ene(11983)
                .setSecretSkillfromCharacter(765)
                .setSecretSkillTrue()
                .setSecretSkillUPResDown(170)
                .setSecretDamageResUPvar(55)
                .setSecretDamageResUPTrue()
                .setPenetrationUPResDown(75)
                .setPenetrationTrue()
                .setAttributeAdvantageTrue()
                .setWeaknessProtectorBuff(60)
                .setWeakpointBuff(100)
                .setWeakpointTrue()
                .setUnit120True();


        Simulator simulator = new Simulator();
        List<Unit> unitList = UnitPreList.getSpecificUnitByEnumName("EMPTY");


        //System.out.println(UnitPreList.getAllEnumNames());

        simulator.combinationHandler(unitList,1);
        simulator.printResultsSortedByDamageDescending();
        //simulator.printResultsSortedByName();
        //simulator.printResultsSortedByNameAscendingThenDamageDescending();
    }

    // Methode zum Erzeugen von Kombinationen und Aufrufen von simulateDamage
    public void combinationHandler(List<Unit> allUnits, int combinationSize) {
        if(combinationSize > allUnits.size()) {
            System.out.println("Fehler: Die Anzahl der ausgewählten Units (" + combinationSize +
                    ") ist größer als d" +
                    "ie verfügbaren Units (" + allUnits.size() + ").");
            return;
        }

        List<Unit> selectedUnits = new ArrayList<>();
        combine(allUnits, selectedUnits, combinationSize, 0);
    }

    private void combine(List<Unit> allUnits, List<Unit> selectedUnits, int combinationSize, int startIndex) {
        if (selectedUnits.size() == combinationSize) {
            // Konvertiere die Liste in ein Array und rufe simulateDamage auf
            simulateDamage(selectedUnits.toArray(new Unit[0]));
            return;
        }

        for (int i = startIndex; i < allUnits.size(); i++) {
            selectedUnits.add(allUnits.get(i));
            combine(allUnits, selectedUnits, combinationSize, i + 1);
            selectedUnits.remove(selectedUnits.size() - 1); // entferne letztes Element für nächsten Schritt
        }
    }

    private List<DamageResult> results = new ArrayList<>();
    private void simulateDamage(Unit... units) {
        DamageObject tempDamageObject = new DamageObject();
        tempDamageObject.addDamageObject(damageObject);

        // Aggregation der Werte für jede Unit in der Liste
        for (Unit unit : units) {
            tempDamageObject.addDamageObject(unit.getDamageObject());
        }

        double damage = tempDamageObject.calculateDamage();

        StringBuilder unitNames = new StringBuilder();
        for (Unit unit : units) {
            if (unitNames.length() > 0) {
                unitNames.append(", ");
            }
            unitNames.append(unit.getName());
        }
        results.add(new DamageResult("[" + unitNames.toString() + "]", damage));


    }

    public void printResultsSortedByName() {
        results.sort(DamageResult.UnitNameComparator);
        for (DamageResult result : results) {
            System.out.println(result);
        }
    }
    public void printResultsSortedByDamageDescending() {
        results.sort(DamageResult.DamageComparatorDescending);
        for (DamageResult result : results) {
            System.out.println(result);
        }
    }

    public void printResultsSortedByNameAscendingThenDamageDescending() {
        results.sort(DamageResult.NameAscendingThenDamageDescending);
        for (DamageResult result : results) {
            System.out.println(result);
        }
    }


}
