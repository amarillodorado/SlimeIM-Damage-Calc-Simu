package damagecalculator;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;

public class DamageInput {
    IntegerField atk_Debuff_IntegerField;
    IntegerField atk_Buff_IntegerField;
    IntegerField atk_PT_IntegerField;
    IntegerField synergyUpDown_IntegerField;
    IntegerField synergyPartnerATK_IntegerField;
    Checkbox synergyTrue_Checkbox;
    IntegerField stundStrikeMulti_IntegerField;
    Checkbox stunTrue_Checkbox;
    IntegerField charmStrikeBuff_IntegerField;
    Checkbox charmTrue_Checkbox;
    IntegerField critBuffANDDebuff_IntegerField;
    Checkbox critTrue_Checkbox;
    IntegerField penetrationUPResDown_IntegerField;
    Checkbox penetrationTrue_Checkbox;
    IntegerField weaknessProtectorBuff_IntegerField;
    IntegerField weakpointBuff_IntegerField;
    Checkbox weakpointTrue_Checkbox;
    IntegerField secretSkillfromCharacter_IntegerField;
    IntegerField secretSkillUPResDown_IntegerField;
    Checkbox secretSkillTrue_Checkbox;
    IntegerField secretDamageResUPvar_IntegerField;
    Checkbox secretDamageResUPTrue_Checkbox;
    IntegerField elementalATKBuff_IntegerField;
    IntegerField elementalATKDebuff_IntegerField;
    IntegerField magicPhysicalBuff_IntegerField;
    IntegerField magicPhysicalDebuff_IntegerField;
    IntegerField attributeResBuff_IntegerField;
    IntegerField attributeResDown_IntegerField;
    IntegerField attackResBuff_IntegerField;
    IntegerField attackResDown_IntegerField;
    IntegerField def_ene_IntegerField;
    IntegerField def_up_IntegerField;
    IntegerField def_down_IntegerField;
    Checkbox attributeAdvantageTrue_Checkbox;
    Checkbox unit120_Checkbox;

    public void initializeDamageDialog(VerticalLayout layout) {
        //ATK
        VerticalLayout layout_atk_v = new VerticalLayout();
        Accordion atk_accordion = new Accordion();
        atk_Debuff_IntegerField = new IntegerField("ATK Debuff");
        atk_Buff_IntegerField = new IntegerField("ATK Buff");
        atk_PT_IntegerField = new IntegerField("ATK PT");
        layout_atk_v.add(atk_PT_IntegerField,atk_Buff_IntegerField,atk_Debuff_IntegerField);
        atk_accordion.add("ATK",layout_atk_v);


        //Synergy
        VerticalLayout layout_synergy_v = new VerticalLayout();
        synergyUpDown_IntegerField = new IntegerField("Synergy Up Down");
        synergyPartnerATK_IntegerField = new IntegerField("Synergy Partner ATK");
        synergyTrue_Checkbox = new Checkbox("Synergy True");
        synergyPartnerATK_IntegerField.setVisible(false);
        synergyUpDown_IntegerField.setVisible(false);
        layout_synergy_v.add(synergyTrue_Checkbox,synergyPartnerATK_IntegerField,synergyUpDown_IntegerField);
        synergyTrue_Checkbox.addValueChangeListener(event -> {
            boolean isChecked = event.getValue();
            synergyPartnerATK_IntegerField.setVisible(isChecked);
            synergyUpDown_IntegerField.setVisible(isChecked);
        });

        VerticalLayout layout_stunStrike_v = new VerticalLayout();
        stunTrue_Checkbox = new Checkbox("Stun True");
        stundStrikeMulti_IntegerField = new IntegerField("Stun Strike Multi");
        stundStrikeMulti_IntegerField.setVisible(false);
        layout_stunStrike_v.add(stunTrue_Checkbox,stundStrikeMulti_IntegerField);
        stunTrue_Checkbox.addValueChangeListener(event -> stundStrikeMulti_IntegerField.setVisible(event.getValue()));

        VerticalLayout layout_charmStrike_v = new VerticalLayout();
        charmTrue_Checkbox = new Checkbox("Charm True");
        charmStrikeBuff_IntegerField = new IntegerField("Charm Strike Buff");
        charmStrikeBuff_IntegerField.setVisible(false);
        layout_charmStrike_v.add(charmTrue_Checkbox,charmStrikeBuff_IntegerField);
        charmTrue_Checkbox.addValueChangeListener(event -> charmStrikeBuff_IntegerField.setVisible(event.getValue()));

        VerticalLayout layout_crit_v = new VerticalLayout();
        critTrue_Checkbox = new Checkbox("Crit True");
        critBuffANDDebuff_IntegerField = new IntegerField("Crit Buff AND Debuff");
        critBuffANDDebuff_IntegerField.setVisible(false);
        layout_crit_v.add(critTrue_Checkbox,critBuffANDDebuff_IntegerField);
        critTrue_Checkbox.addValueChangeListener(event -> critBuffANDDebuff_IntegerField.setVisible(event.getValue()));

        VerticalLayout layout_penetration_v = new VerticalLayout();
        penetrationTrue_Checkbox = new Checkbox("Penetration True");
        penetrationUPResDown_IntegerField = new IntegerField("Penetration UP Res Down");
        penetrationUPResDown_IntegerField.setVisible(false);
        layout_penetration_v.add(penetrationTrue_Checkbox,penetrationUPResDown_IntegerField);
        penetrationTrue_Checkbox.addValueChangeListener(event->penetrationUPResDown_IntegerField.setVisible(event.getValue()));

        VerticalLayout layout_weakness_v = new VerticalLayout();
        weaknessProtectorBuff_IntegerField = new IntegerField("Weakness Protector Buff");
        weakpointTrue_Checkbox = new Checkbox("Weakpoint True");
        weakpointBuff_IntegerField = new IntegerField("Weakpoint Buff");
        weakpointBuff_IntegerField.setVisible(false);
        layout_weakness_v.add(weaknessProtectorBuff_IntegerField,weakpointTrue_Checkbox,weakpointBuff_IntegerField);
        weakpointTrue_Checkbox.addValueChangeListener(event ->weakpointBuff_IntegerField.setVisible(event.getValue()));

        VerticalLayout layout_secretSkill_v = new VerticalLayout();
        secretSkillfromCharacter_IntegerField = new IntegerField("Secret Skill from Character");
        secretSkillUPResDown_IntegerField = new IntegerField("Secret Skill UP Res Down");
        secretSkillTrue_Checkbox = new Checkbox("Secret Skill True");
        secretSkillfromCharacter_IntegerField.setVisible(false);
        secretSkillUPResDown_IntegerField.setVisible(false);
        layout_secretSkill_v.add(secretSkillTrue_Checkbox,secretSkillfromCharacter_IntegerField,secretSkillUPResDown_IntegerField);
        secretSkillTrue_Checkbox.addValueChangeListener(event ->{
            boolean isChecked = event.getValue();
            secretSkillfromCharacter_IntegerField.setVisible(isChecked);
            secretSkillUPResDown_IntegerField.setVisible(isChecked);
        });

        VerticalLayout layout_secretDamageDown_v = new VerticalLayout();
        secretDamageResUPvar_IntegerField = new IntegerField("Secret Damage Res UP Var");
        secretDamageResUPTrue_Checkbox = new Checkbox("Secret Damage Res UP True");
        secretDamageResUPvar_IntegerField.setVisible(false);
        layout_secretDamageDown_v.add(secretDamageResUPTrue_Checkbox, secretDamageResUPvar_IntegerField);
        secretDamageResUPTrue_Checkbox.addValueChangeListener(event->secretDamageResUPvar_IntegerField.setVisible(event.getValue()));

        Accordion accordionTypeAdvantage = new Accordion();
        VerticalLayout layout_typeAdvantage_v = new VerticalLayout();
        elementalATKBuff_IntegerField = new IntegerField("Elemental ATK Buff");
        elementalATKDebuff_IntegerField = new IntegerField("Elemental ATK Debuff");
        magicPhysicalBuff_IntegerField = new IntegerField("Magic Physical Buff");
        magicPhysicalDebuff_IntegerField = new IntegerField("Magic Physical Debuff");
        layout_typeAdvantage_v.add(elementalATKBuff_IntegerField,elementalATKDebuff_IntegerField,magicPhysicalBuff_IntegerField,magicPhysicalDebuff_IntegerField);
        accordionTypeAdvantage.add("Magic / Physical & Elemental",layout_typeAdvantage_v);

        attributeResBuff_IntegerField = new IntegerField("Attribute Res Buff");
        attributeResDown_IntegerField = new IntegerField("Attribute Res Down");
        attackResBuff_IntegerField = new IntegerField("Attack Res Buff");
        attackResDown_IntegerField = new IntegerField("Attack Res Down");

        Accordion accordionDef = new Accordion();
        VerticalLayout layout_def_v = new VerticalLayout();
        def_ene_IntegerField = new IntegerField("DEF ENE");
        def_up_IntegerField = new IntegerField("DEF UP");
        def_down_IntegerField = new IntegerField("DEF DOWN");
        layout_def_v.add(def_ene_IntegerField,def_down_IntegerField);
        accordionDef.add("Defence enemy", layout_def_v);

        VerticalLayout layout_attributeAdvantage_v = new VerticalLayout();
        attributeAdvantageTrue_Checkbox = new Checkbox("Attribute Advantage True");
        layout_attributeAdvantage_v.add(attributeAdvantageTrue_Checkbox);

        VerticalLayout layout_unit120_v = new VerticalLayout();
        unit120_Checkbox = new Checkbox("Unit 120");
        layout_unit120_v.add(unit120_Checkbox);

        layout_atk_v.setPadding(false);
        layout_synergy_v.setPadding(false);
        layout_stunStrike_v.setPadding(false);
        layout_charmStrike_v.setPadding(false);
        layout_crit_v.setPadding(false);
        layout_penetration_v.setPadding(false);
        layout_weakness_v.setPadding(false);
        layout_secretSkill_v.setPadding(false);
        layout_secretDamageDown_v.setPadding(false);
        layout_typeAdvantage_v.setPadding(false);
        layout_def_v.setPadding(false);
        layout_attributeAdvantage_v.setPadding(false);
        layout_unit120_v.setPadding(false);

        setValue();

        layout.add(atk_accordion,layout_synergy_v, layout_stunStrike_v,layout_charmStrike_v,layout_crit_v,layout_penetration_v,
                layout_weakness_v,layout_secretSkill_v,layout_secretDamageDown_v,accordionTypeAdvantage,accordionDef,layout_attributeAdvantage_v,layout_unit120_v);

    }

    public void setValue(){
        // ATK
        atk_Debuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        atk_Buff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        atk_PT_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Synergy
        synergyUpDown_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        synergyPartnerATK_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Stun Strike
        stundStrikeMulti_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Charm Strike
        charmStrikeBuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Crit
        critBuffANDDebuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Penetration
        penetrationUPResDown_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Weakness
        weaknessProtectorBuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        weakpointBuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Secret Skill
        secretSkillfromCharacter_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        secretSkillUPResDown_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Secret Damage Res UP Var
        secretDamageResUPvar_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Elemental/Type Advantage
        elementalATKBuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        elementalATKDebuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        magicPhysicalBuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        magicPhysicalDebuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Attribute Res
        attributeResBuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        attributeResDown_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Attack Res
        attackResBuff_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        attackResDown_IntegerField.setValue(0); // Setzen des Standardwerts auf 0

// Defense
        def_ene_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        def_up_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
        def_down_IntegerField.setValue(0); // Setzen des Standardwerts auf 0
    }

    public DamageObject createObject(){
        DamageObject damageObject = new DamageObject();
        damageObject.setAtk_Debuff(atk_Debuff_IntegerField.getValue())
                .setAtk_Buff(atk_Buff_IntegerField.getValue())
                .setAtk_PT(atk_PT_IntegerField.getValue())
                .setElementalATKBuff(elementalATKBuff_IntegerField.getValue())
                .setElementalATKDebuff(elementalATKDebuff_IntegerField.getValue())
                .setSynergyUpDown(synergyUpDown_IntegerField.getValue())
                .setSynergyPartnerATK(synergyPartnerATK_IntegerField.getValue())
                .setMagicPhysicalBuff(magicPhysicalBuff_IntegerField.getValue())
                .setMagicPhysicalDebuff(magicPhysicalDebuff_IntegerField.getValue())
                .setAttributeResBuff(attributeResBuff_IntegerField.getValue())
                .setAttributeResDown(attributeResDown_IntegerField.getValue())
                .setAttackResBuff(attackResBuff_IntegerField.getValue())
                .setAttackResDown(attackResDown_IntegerField.getValue())
                .setDef_ene(def_ene_IntegerField.getValue())
                .setDef_up(def_up_IntegerField.getValue())
                .setDef_down(def_down_IntegerField.getValue())
                .setStundStrikeMulti(stundStrikeMulti_IntegerField.getValue())
                .setCharmStrikeBuff(charmStrikeBuff_IntegerField.getValue())
                .setCritBuffANDDebuff(critBuffANDDebuff_IntegerField.getValue())
                .setSecretSkillfromCharacter(secretSkillfromCharacter_IntegerField.getValue())
                .setSecretSkillUPResDown(secretSkillUPResDown_IntegerField.getValue())
                .setSecretDamageResUPvar(secretDamageResUPvar_IntegerField.getValue())
                .setPenetrationUPResDown(penetrationUPResDown_IntegerField.getValue())
                .setWeaknessProtectorBuff(weaknessProtectorBuff_IntegerField.getValue())
                .setWeakpointBuff(weakpointBuff_IntegerField.getValue())
                // set booleans
                .setStunTrue(stunTrue_Checkbox.getValue())
                .setCharmTrue(charmTrue_Checkbox.getValue())
                .setCritTrue(critTrue_Checkbox.getValue())
                .setSecretSkillTrue(secretSkillTrue_Checkbox.getValue())
                .setSecretDamageResUPTrue(secretDamageResUPTrue_Checkbox.getValue())
                .setPenetrationTrue(penetrationTrue_Checkbox.getValue())
                .setAttributeAdvantageTrue(attributeAdvantageTrue_Checkbox.getValue())
                .setWeakpointTrue(weakpointTrue_Checkbox.getValue())
                .setSynergyTrue(synergyTrue_Checkbox.getValue())
                .setUnit120True(unit120_Checkbox.getValue());
        return damageObject;
    }
}
