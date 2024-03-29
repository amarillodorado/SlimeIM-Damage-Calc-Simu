package damagecalculator;

import java.util.ArrayList;
import java.util.List;

public class Simulator {
    DamageObject damageObject;
    public List<DamageResult> results = new ArrayList<>();
    int maxSkillCost;

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        /*
        simulator.damageObject = new DamageObject().setAtk_Initial(23418)
                        .setElementalATKBuff(85)
                                .setMagicPhysicalBuff(75)
                                        .setCritTrue(true)
                                                .setAttributeAdvantageTrue(true)
                                                        .setWeaknessProtectorBuff(60)
                                                                .setWeakpointTrue(true)
                                                                        .setWeaknessStrikeBuff(220)
                                                                                .setSecretSkillBuff(80)
                                                                                        .setSecretSkillfromCharacter(735)
                                                                                                .setSecretDamageResistanceDown(185)
                                                                                                        .setDef_Initial(1352)
                .setSecretSkillTrue(true)
                .setAttackResDown(65)
                .setCritBuffANDDebuff(8);

         */
        System.out.println(Math.round(simulator.damageObject.calculateDamage()));
    }


    public void combinationHandler(List<Unit> allUnits, int combinationSize) {
        results = new ArrayList<>();
        if (combinationSize > allUnits.size()) {
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

    private void simulateDamage(Unit... units) {
        DamageObject tempDamageObject = new DamageObject();
        tempDamageObject.addDamageObject(damageObject);
        int tempAggregationOfSkillCost = 0;

        // Aggregation der Werte für jede Unit in der Liste
        for (Unit unit : units) {
            tempAggregationOfSkillCost += unit.getSkillCost();
            if (maxSkillCost != 0 && tempAggregationOfSkillCost > maxSkillCost) return;
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
        results.add(new DamageResult("[" + unitNames + "]", damage));
    }

    public void setMaxSkillCost(int maxSkillCost) {
        this.maxSkillCost = maxSkillCost;
    }

    public void setDamageObject(DamageObject damageObject) {
        this.damageObject = damageObject;
    }
}
