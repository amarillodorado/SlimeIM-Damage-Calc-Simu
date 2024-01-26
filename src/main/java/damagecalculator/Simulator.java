package damagecalculator;

import java.util.ArrayList;
import java.util.List;

public class Simulator {

    public void setDamageObject(DamageObject damageObject) {
        this.damageObject = damageObject;
    }

    DamageObject damageObject;

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.damageObject = new DamageObject()
                .setAtk_Initial(12532)
                        .setAtk_Buff(60)
                                .setElementalATKBuff(5)
                                        .setMagicPhysicalBuff(5)
                                                .setCritTrue(true)
                                                        .setSynergyTrue(true)
                                                                .setSynergyPartnerATK(6044)
                                                                        .setAttributeAdvantageTrue(true)
                                                                                .setWeaknessProtectorBuff(60)
                                                                                        .setWeakpointTrue(true)
                                                                                                .setWeaknessStrikeBuff(60)
                                                                                                        .setSecretSkillTrue(true)
                                                                                                                .setSecretSkillfromCharacter(510)
                                                                                                                        .setSecretSkillBuff(50)
                                                                                                                                .setDef_Initial(11427)
                                                                                                                                        .setAttributeResDown(50);





        System.out.println(simulator.damageObject.calculateDamage());



        //List<Unit> unitList = UnitPreList.getSpecificUnitByEnumName("EMPTY");


        //System.out.println(UnitPreList.getAllEnumNames());

        //simulator.combinationHandler(unitList,1);
        //simulator.printResultsSortedByDamageDescending();
        //simulator.printResultsSortedByName();
        //simulator.printResultsSortedByNameAscendingThenDamageDescending();
    }

    public void combinationHandler(List<Unit> allUnits, int combinationSize) {
        results = new ArrayList<>();
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

    public List<DamageResult> results = new ArrayList<>();
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
