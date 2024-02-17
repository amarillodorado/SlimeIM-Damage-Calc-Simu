package damagecalculator;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.IntegerField;

public class DamageInput implements GuiHandler{
    private MainView mainView;
    public DamageInput(MainView mainview){
        this.mainView = mainview;
    }
    IntegerField atk_Debuff_IntegerField;
    IntegerField atk_Buff_IntegerField;
    IntegerField atk_PT_IntegerField;
    IntegerField synergyUpDown_IntegerField;
    IntegerField synergyPartnerATK_IntegerField;
    Checkbox synergyTrue_Checkbox;
    IntegerField stundStrikeMulti_IntegerField;
    Checkbox stunTrue_Checkbox;
    IntegerField enamorStrikeBuff_IntegerField;
    Checkbox enamorTrue_Checkbox;
    IntegerField critBuffANDDebuff_IntegerField;
    Checkbox critTrue_Checkbox;
    IntegerField pierceUPResDown_IntegerField;
    Checkbox pierceTrue_Checkbox;
    IntegerField weaknessProtectorBuff_IntegerField;
    IntegerField weaknessStrikeBuff_IntegerField;
    Checkbox weakpointTrue_Checkbox;
    IntegerField secretSkillfromCharacter_IntegerField;
    IntegerField secretSkillBuff_IntegerField;
    Checkbox secretSkillTrue_Checkbox;
    IntegerField secretDamageResistanceDown_IntegerField;
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

    IntegerField critResDown;
    IntegerField pierceResDown_IntegerField;
    IntegerField synergyResDown_IntegerField;

    public void initializeDamageDialog(VerticalLayout layout) {
        //ATK
        VerticalLayout layout_atk_v = new VerticalLayout();
        Accordion accordion_atk = new Accordion();
        atk_Debuff_IntegerField = new IntegerField("ATK Debuff");
        atk_Buff_IntegerField = new IntegerField("ATK Buff");
        atk_PT_IntegerField = new IntegerField("ATK Initial");
        addToolTip(atk_PT_IntegerField, "Select the ATK from the Team Building screen (not in fight ATK)");
        layout_atk_v.add(atk_PT_IntegerField,atk_Buff_IntegerField,atk_Debuff_IntegerField);
        accordion_atk.add("Attack",layout_atk_v);
        accordion_atk.close();

        Accordion accordion_TypeAdvantage = new Accordion();
        VerticalLayout layout_typeAdvantage_v = new VerticalLayout();
        elementalATKBuff_IntegerField = new IntegerField("Elemental ATK Buff");
        elementalATKDebuff_IntegerField = new IntegerField("Elemental ATK Debuff");
        magicPhysicalBuff_IntegerField = new IntegerField("Magic / Physical Buff");
        magicPhysicalDebuff_IntegerField = new IntegerField("Magic / Physical Debuff");
        layout_typeAdvantage_v.add(elementalATKBuff_IntegerField,elementalATKDebuff_IntegerField,magicPhysicalBuff_IntegerField,magicPhysicalDebuff_IntegerField);
        accordion_TypeAdvantage.add("Magic / Physical & Elemental",layout_typeAdvantage_v);
        accordion_TypeAdvantage.close();

        Accordion accordion_stun_enamor = new Accordion();
        VerticalLayout layout_accordian_stun_enamor = new VerticalLayout();
        VerticalLayout layout_stunStrike_v = new VerticalLayout();
        stunTrue_Checkbox = new Checkbox("Stun Strike");
        stundStrikeMulti_IntegerField = new IntegerField("Stun Strike Buff / Debuff");
        stundStrikeMulti_IntegerField.setVisible(false);
        layout_stunStrike_v.add(stunTrue_Checkbox,stundStrikeMulti_IntegerField);
        stunTrue_Checkbox.addValueChangeListener(event -> {
            stundStrikeMulti_IntegerField.setVisible(event.getValue());
            stundStrikeMulti_IntegerField.setValue(0);
        });

        VerticalLayout layout_charmStrike_v = new VerticalLayout();
        enamorTrue_Checkbox = new Checkbox("Enamor Strike");
        enamorStrikeBuff_IntegerField = new IntegerField("Enamor Strike Buff / Debuff");
        enamorStrikeBuff_IntegerField.setVisible(false);
        layout_charmStrike_v.add(enamorTrue_Checkbox, enamorStrikeBuff_IntegerField);
        enamorTrue_Checkbox.addValueChangeListener(event -> {
            enamorStrikeBuff_IntegerField.setVisible(event.getValue());
            enamorStrikeBuff_IntegerField.setValue(0);
        });
        layout_accordian_stun_enamor.add(layout_stunStrike_v,layout_charmStrike_v);
        accordion_stun_enamor.add("Enamor & Stun Strike",layout_accordian_stun_enamor);
        accordion_stun_enamor.close();


        Accordion accordion_crit_pierce_synergy = new Accordion();
        VerticalLayout layout_crit_pierce_synergy_v = new VerticalLayout();
        VerticalLayout layout_crit_v = new VerticalLayout();
        critTrue_Checkbox = new Checkbox("Critical");
        critBuffANDDebuff_IntegerField = new IntegerField("Critical Damage Buff / Debuff");
        critResDown = new IntegerField("Crit Res down");
        critBuffANDDebuff_IntegerField.setVisible(false);
        critResDown.setVisible(false);
        layout_crit_v.add(critTrue_Checkbox,critBuffANDDebuff_IntegerField, critResDown);
        critTrue_Checkbox.addValueChangeListener(event -> {
            critBuffANDDebuff_IntegerField.setVisible(event.getValue());
            critResDown.setVisible(event.getValue());
            critResDown.setValue(0);
            critBuffANDDebuff_IntegerField.setValue(0);
        });

        VerticalLayout layout_pierce_v = new VerticalLayout();
        pierceTrue_Checkbox = new Checkbox("Pierce");
        pierceUPResDown_IntegerField = new IntegerField("Pierce Power Buff / Debuff");
        pierceResDown_IntegerField = new IntegerField("Pierce Res down");
        pierceUPResDown_IntegerField.setVisible(false);
        pierceResDown_IntegerField.setVisible(false);
        layout_pierce_v.add(pierceTrue_Checkbox, pierceUPResDown_IntegerField, pierceResDown_IntegerField);
        pierceTrue_Checkbox.addValueChangeListener(event-> {
            pierceUPResDown_IntegerField.setVisible(event.getValue());
            pierceResDown_IntegerField.setVisible(event.getValue());
            pierceResDown_IntegerField.setValue(0);
            pierceUPResDown_IntegerField.setValue(0);
        });

        //Synergy
        VerticalLayout layout_synergy_v = new VerticalLayout();
        synergyUpDown_IntegerField = new IntegerField("Synergy Buff / Debuff");
        synergyResDown_IntegerField = new IntegerField("Synergy Res down");
        synergyPartnerATK_IntegerField = new IntegerField("Synergy Partner ATK");
        synergyTrue_Checkbox = new Checkbox("Synergy");
        synergyPartnerATK_IntegerField.setVisible(false);
        synergyUpDown_IntegerField.setVisible(false);
        synergyResDown_IntegerField.setVisible(false);
        layout_synergy_v.add(synergyTrue_Checkbox,synergyPartnerATK_IntegerField,synergyUpDown_IntegerField, synergyResDown_IntegerField);
        synergyTrue_Checkbox.addValueChangeListener(event -> {
            boolean isChecked = event.getValue();
            synergyPartnerATK_IntegerField.setVisible(isChecked);
            synergyUpDown_IntegerField.setVisible(isChecked);
            synergyResDown_IntegerField.setVisible(isChecked);
            synergyResDown_IntegerField.setValue(0);
            synergyUpDown_IntegerField.setValue(0);
            synergyPartnerATK_IntegerField.setValue(0);
        });
        layout_crit_pierce_synergy_v.add(layout_crit_v,layout_pierce_v,layout_synergy_v);
        accordion_crit_pierce_synergy.add("Critical & Pierce & Synergy Damage",layout_crit_pierce_synergy_v);
        accordion_crit_pierce_synergy.close();

        Accordion accordion_weakness_attributeadvantage_v = new Accordion();
        VerticalLayout layout_weakness_attributeadvantage_v = new VerticalLayout();
        VerticalLayout layout_attributeAdvantage_v = new VerticalLayout();
        attributeAdvantageTrue_Checkbox = new Checkbox("Do you have Attribute Advantage?");
        layout_attributeAdvantage_v.add(attributeAdvantageTrue_Checkbox);

        VerticalLayout layout_weakness_v = new VerticalLayout();
        weaknessProtectorBuff_IntegerField = new IntegerField("Weakness Protector Buff");
        addToolTip(weaknessProtectorBuff_IntegerField, "Use this if you have ANTI Element from your protector, usually 60");

        weakpointTrue_Checkbox = new Checkbox("Weakness Strike");
        weaknessStrikeBuff_IntegerField = new IntegerField("Weakness Strike Buff");
        weaknessStrikeBuff_IntegerField.setVisible(false);
        layout_weakness_v.add(weaknessProtectorBuff_IntegerField,weakpointTrue_Checkbox, weaknessStrikeBuff_IntegerField);
        weakpointTrue_Checkbox.addValueChangeListener(event -> {
            weaknessStrikeBuff_IntegerField.setVisible(event.getValue());
            weaknessStrikeBuff_IntegerField.setValue(0);
        });
        layout_weakness_attributeadvantage_v.add(layout_attributeAdvantage_v,layout_weakness_v);
        accordion_weakness_attributeadvantage_v.add("Weakness & Attribute Advantage",layout_weakness_attributeadvantage_v);
        accordion_weakness_attributeadvantage_v.close();

        Accordion accordion_secretskill = new Accordion();
        VerticalLayout layout_secretSkill_v = new VerticalLayout();
        secretSkillfromCharacter_IntegerField = new IntegerField("Soul of Combos Damage from Unit");
        addToolTip(secretSkillfromCharacter_IntegerField, "EX Soul of Combos Damage would be normally 735/765.");
        secretSkillBuff_IntegerField = new IntegerField("Damage Buff / Debuff");
        secretSkillTrue_Checkbox = new Checkbox("Secret Skill");
        secretDamageResistanceDown_IntegerField = new IntegerField("Damage Resistance down");
        secretDamageResistanceDown_IntegerField.setVisible(false);
        secretSkillfromCharacter_IntegerField.setVisible(false);
        secretSkillBuff_IntegerField.setVisible(false);
        layout_secretSkill_v.add(secretSkillTrue_Checkbox,secretSkillfromCharacter_IntegerField, secretSkillBuff_IntegerField, secretDamageResistanceDown_IntegerField);
        secretSkillTrue_Checkbox.addValueChangeListener(event ->{
            boolean isChecked = event.getValue();
            secretSkillfromCharacter_IntegerField.setVisible(isChecked);
            secretSkillBuff_IntegerField.setVisible(isChecked);
            secretDamageResistanceDown_IntegerField.setVisible(isChecked);
            secretSkillfromCharacter_IntegerField.setValue(0);
            secretSkillBuff_IntegerField.setValue(0);
            secretDamageResistanceDown_IntegerField.setValue(0);
        });
        accordion_secretskill.add("Secret Skill", layout_secretSkill_v);
        accordion_secretskill.close();


        Accordion accordionDef = new Accordion();
        VerticalLayout layout_def_v = new VerticalLayout();
        def_ene_IntegerField = new IntegerField("Defence Initial");
        def_up_IntegerField = new IntegerField("Defence UP");
        def_down_IntegerField = new IntegerField("Defence DOWN");
        addToolTip(def_ene_IntegerField,"Select the DEF from the Team Building screen (not in fight DEF)");
        attributeResBuff_IntegerField = new IntegerField("Attribute Resistance Buff");
        attributeResDown_IntegerField = new IntegerField("Attribute Resistance Debuff");
        attackResBuff_IntegerField = new IntegerField("Magic / Physical Res Buff");
        attackResDown_IntegerField = new IntegerField("Magic / Physical Res Debuff");

        layout_def_v.add(def_ene_IntegerField,def_down_IntegerField, attributeResBuff_IntegerField,attributeResDown_IntegerField,attackResBuff_IntegerField,attackResDown_IntegerField);
        accordionDef.add("Defence / Resistance Enemy", layout_def_v);
        accordionDef.close();


        layout_atk_v.setPadding(false);
        layout_synergy_v.setPadding(false);
        layout_stunStrike_v.setPadding(false);
        layout_charmStrike_v.setPadding(false);
        layout_crit_v.setPadding(false);
        layout_pierce_v.setPadding(false);
        layout_weakness_v.setPadding(false);
        layout_secretSkill_v.setPadding(false);
        layout_typeAdvantage_v.setPadding(false);
        layout_def_v.setPadding(false);
        layout_attributeAdvantage_v.setPadding(false);
        layout_weakness_attributeadvantage_v.setPadding(false);
        layout_crit_pierce_synergy_v.setPadding(false);
        layout_accordian_stun_enamor.setPadding(false);

        layout_atk_v.setSpacing(false);
        layout_synergy_v.setSpacing(false);
        layout_stunStrike_v.setSpacing(false);
        layout_charmStrike_v.setSpacing(false);
        layout_crit_v.setSpacing(false);
        layout_pierce_v.setSpacing(false);
        layout_weakness_v.setSpacing(false);
        layout_secretSkill_v.setSpacing(false);
        layout_typeAdvantage_v.setSpacing(false);
        layout_def_v.setSpacing(false);
        layout_attributeAdvantage_v.setSpacing(false);
        layout_accordian_stun_enamor.setSpacing(false);
        layout_weakness_attributeadvantage_v.setSpacing(false);
        layout_crit_pierce_synergy_v.setSpacing(false);

        setValue();

        layout.add(accordion_atk,accordion_TypeAdvantage, accordion_crit_pierce_synergy,
                accordion_weakness_attributeadvantage_v,accordion_secretskill,accordionDef,accordion_stun_enamor
                );

    }

    public void setValue(){
        //Set default value
        atk_Debuff_IntegerField.setValue(0); 
        atk_Buff_IntegerField.setValue(0); 
        atk_PT_IntegerField.setValue(0); 

        synergyUpDown_IntegerField.setValue(0); 
        synergyPartnerATK_IntegerField.setValue(0);
        synergyResDown_IntegerField.setValue(0);

        stundStrikeMulti_IntegerField.setValue(0); 

        enamorStrikeBuff_IntegerField.setValue(0); 

        critBuffANDDebuff_IntegerField.setValue(0);
        critResDown.setValue(0);

        pierceUPResDown_IntegerField.setValue(0);
        pierceResDown_IntegerField.setValue(0);

        weaknessProtectorBuff_IntegerField.setValue(0); 
        weaknessStrikeBuff_IntegerField.setValue(0);

        secretSkillfromCharacter_IntegerField.setValue(0); 
        secretSkillBuff_IntegerField.setValue(0);

        secretDamageResistanceDown_IntegerField.setValue(0);

        elementalATKBuff_IntegerField.setValue(0);
        elementalATKDebuff_IntegerField.setValue(0);
        magicPhysicalBuff_IntegerField.setValue(0); 
        magicPhysicalDebuff_IntegerField.setValue(0);

        attributeResBuff_IntegerField.setValue(0);
        attributeResDown_IntegerField.setValue(0);

        attackResBuff_IntegerField.setValue(0);
        attackResDown_IntegerField.setValue(0);

        def_ene_IntegerField.setValue(0); 
        def_up_IntegerField.setValue(0);
        def_down_IntegerField.setValue(0);

        //Set Minimum
        atk_Debuff_IntegerField.setMin(0);
        atk_Buff_IntegerField.setMin(0);
        atk_PT_IntegerField.setMin(0);

        synergyUpDown_IntegerField.setMin(0);
        synergyPartnerATK_IntegerField.setMin(0);
        synergyResDown_IntegerField.setMin(0);

        stundStrikeMulti_IntegerField.setMin(0);

        enamorStrikeBuff_IntegerField.setMin(0);

        critBuffANDDebuff_IntegerField.setMin(0);
        critResDown.setMin(0);

        pierceUPResDown_IntegerField.setMin(0);
        pierceResDown_IntegerField.setMin(0);

        weaknessProtectorBuff_IntegerField.setMin(0);
        weaknessStrikeBuff_IntegerField.setMin(0);

        secretSkillfromCharacter_IntegerField.setMin(0);
        secretSkillBuff_IntegerField.setMin(0);

        secretDamageResistanceDown_IntegerField.setMin(0);

        elementalATKBuff_IntegerField.setMin(0);
        elementalATKDebuff_IntegerField.setMin(0);
        magicPhysicalBuff_IntegerField.setMin(0);
        magicPhysicalDebuff_IntegerField.setMin(0);

        attributeResBuff_IntegerField.setMin(0);
        attributeResDown_IntegerField.setMin(0);

        attackResBuff_IntegerField.setMin(0);
        attackResDown_IntegerField.setMin(0);

        def_ene_IntegerField.setMin(0);
        def_up_IntegerField.setMin(0);
        def_down_IntegerField.setMin(0);

    }

    public void setValuesFromDamageObject(DamageObject damageObject) {
        atk_Debuff_IntegerField.setValue((int) damageObject.getAtk_Debuff());
        atk_Buff_IntegerField.setValue((int) damageObject.getAtk_Buff());
        atk_PT_IntegerField.setValue((int) damageObject.getAtk_Initial());
        synergyUpDown_IntegerField.setValue((int)damageObject.getSynergyUpDown());
        synergyPartnerATK_IntegerField.setValue((int)damageObject.getSynergyPartnerATK());
        stundStrikeMulti_IntegerField.setValue((int)damageObject.getStunStrikeDamageUPDOWN());
        enamorStrikeBuff_IntegerField.setValue((int)damageObject.getEnamorStrikeBuff());
        critBuffANDDebuff_IntegerField.setValue((int)damageObject.getCritBuffANDDebuff());
        pierceUPResDown_IntegerField.setValue((int)damageObject.getPierceUPResDown());
        weaknessProtectorBuff_IntegerField.setValue((int)damageObject.getWeaknessProtectorBuff());
        weaknessStrikeBuff_IntegerField.setValue((int)damageObject.getWeaknessStrikeBuff());
        secretSkillfromCharacter_IntegerField.setValue((int)damageObject.getSecretSkillfromCharacter());
        secretSkillBuff_IntegerField.setValue((int)damageObject.getSecretSkillBuff());
        secretDamageResistanceDown_IntegerField.setValue((int)damageObject.getSecretDamageResistanceDown());
        elementalATKBuff_IntegerField.setValue((int)damageObject.getElementalATKBuff());
        elementalATKDebuff_IntegerField.setValue((int)damageObject.getElementalATKDebuff());
        magicPhysicalBuff_IntegerField.setValue((int)damageObject.getMagicPhysicalBuff());
        magicPhysicalDebuff_IntegerField.setValue((int)damageObject.getMagicPhysicalDebuff());
        attributeResBuff_IntegerField.setValue((int)damageObject.getAttributeResBuff());
        attributeResDown_IntegerField.setValue((int)damageObject.getAttributeResDown());
        attackResBuff_IntegerField.setValue((int)damageObject.getAttackResBuff());
        attackResDown_IntegerField.setValue((int)damageObject.getAttackResDown());
        def_ene_IntegerField.setValue((int)damageObject.getDef_Initial());
        def_up_IntegerField.setValue((int)damageObject.getDef_up());
        def_down_IntegerField.setValue((int)damageObject.getDef_down());

        // Setzen der Checkbox-Werte
        synergyTrue_Checkbox.setValue(damageObject.isSynergyTrue());
        stunTrue_Checkbox.setValue(damageObject.isStunTrue());
        enamorTrue_Checkbox.setValue(damageObject.isEnamorTrue());
        critTrue_Checkbox.setValue(damageObject.isCritTrue());
        secretSkillTrue_Checkbox.setValue(damageObject.isSecretSkillTrue());
        pierceTrue_Checkbox.setValue(damageObject.isPierceTrue());
        weakpointTrue_Checkbox.setValue(damageObject.isWeakpointTrue());
        attributeAdvantageTrue_Checkbox.setValue(damageObject.isAttributeAdvantageTrue());
    }

    public DamageObject createObject(){
        DamageObject damageObject = new DamageObject();
        try{
            damageObject.setAtk_Debuff(atk_Debuff_IntegerField.getValue());
            damageObject.setAtk_Buff(atk_Buff_IntegerField.getValue());
            damageObject.setAtk_Initial(atk_PT_IntegerField.getValue());
            damageObject.setElementalATKBuff(elementalATKBuff_IntegerField.getValue());
            damageObject.setElementalATKDebuff(elementalATKDebuff_IntegerField.getValue());
            damageObject.setSynergyUpDown(synergyUpDown_IntegerField.getValue()+synergyResDown_IntegerField.getValue());
            damageObject.setSynergyPartnerATK(synergyPartnerATK_IntegerField.getValue());
            damageObject.setMagicPhysicalBuff(magicPhysicalBuff_IntegerField.getValue());
            damageObject.setMagicPhysicalDebuff(magicPhysicalDebuff_IntegerField.getValue());
            damageObject.setAttributeResBuff(attributeResBuff_IntegerField.getValue());
            damageObject.setAttributeResDown(attributeResDown_IntegerField.getValue());
            damageObject.setAttackResBuff(attackResBuff_IntegerField.getValue());
            damageObject.setAttackResDown(attackResDown_IntegerField.getValue());
            damageObject.setDef_Initial(def_ene_IntegerField.getValue());
            damageObject.setDef_up(def_up_IntegerField.getValue());
            damageObject.setDef_down(def_down_IntegerField.getValue());
            damageObject.setStunStrikeDamageUPDOWN(stundStrikeMulti_IntegerField.getValue());
            damageObject.setEnamorStrikeBuff(enamorStrikeBuff_IntegerField.getValue());
            damageObject.setCritBuffANDDebuff(critBuffANDDebuff_IntegerField.getValue()+critResDown.getValue());
            damageObject.setSecretSkillfromCharacter(secretSkillfromCharacter_IntegerField.getValue());
            damageObject.setSecretSkillBuff(secretSkillBuff_IntegerField.getValue());
            damageObject.setSecretDamageResistanceDown(secretDamageResistanceDown_IntegerField.getValue());
            damageObject.setPierceUPResDown(pierceUPResDown_IntegerField.getValue()+pierceResDown_IntegerField.getValue());
            damageObject.setWeaknessProtectorBuff(weaknessProtectorBuff_IntegerField.getValue());
            damageObject.setWeaknessStrikeBuff(weaknessStrikeBuff_IntegerField.getValue());
            //boolean
            damageObject.setStunTrue(stunTrue_Checkbox.getValue());
            damageObject.setEnamorTrue(enamorTrue_Checkbox.getValue());
            damageObject.setCritTrue(critTrue_Checkbox.getValue());
            damageObject.setSecretSkillTrue(secretSkillTrue_Checkbox.getValue());
            damageObject.setPierceTrue(pierceTrue_Checkbox.getValue());
            damageObject.setAttributeAdvantageTrue(attributeAdvantageTrue_Checkbox.getValue());
            damageObject.setWeakpointTrue(weakpointTrue_Checkbox.getValue());
            damageObject.setSynergyTrue(synergyTrue_Checkbox.getValue());// set booleans
        }catch (Exception e){
            mainView.exceptionObjectCreationPopUp();
        }
        finally {
            return damageObject;
        }

    }

}
