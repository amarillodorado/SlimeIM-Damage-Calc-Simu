package damagecalculator;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
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
    IntegerField enamorStrikeBuff_IntegerField;
    Checkbox enamorTrue_Checkbox;
    IntegerField critBuffANDDebuff_IntegerField;
    Checkbox critTrue_Checkbox;
    IntegerField pierceUPResDown_IntegerField;
    Checkbox pierceTrue_Checkbox;
    IntegerField weaknessProtectorBuff_IntegerField;
    IntegerField weakpointBuff_IntegerField;
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
    Checkbox unit120_Checkbox;

    public void initializeDamageDialog(VerticalLayout layout) {
        //ATK
        VerticalLayout layout_atk_v = new VerticalLayout();
        Accordion atk_accordion = new Accordion();
        atk_Debuff_IntegerField = new IntegerField("ATK Debuff");
        atk_Buff_IntegerField = new IntegerField("ATK Buff");
        atk_PT_IntegerField = new IntegerField("ATK Initial");
        addToolTip(atk_PT_IntegerField, "Select the ATK from the Team Building screen (not in fight ATK)");
        layout_atk_v.add(atk_PT_IntegerField,atk_Buff_IntegerField,atk_Debuff_IntegerField);
        atk_accordion.add("Attack",layout_atk_v);
        atk_accordion.close();

        Accordion accordionTypeAdvantage = new Accordion();
        VerticalLayout layout_typeAdvantage_v = new VerticalLayout();
        elementalATKBuff_IntegerField = new IntegerField("Elemental ATK Buff");
        elementalATKDebuff_IntegerField = new IntegerField("Elemental ATK Debuff");
        magicPhysicalBuff_IntegerField = new IntegerField("Magic / Physical Buff");
        magicPhysicalDebuff_IntegerField = new IntegerField("Magic / Physical Debuff");
        layout_typeAdvantage_v.add(elementalATKBuff_IntegerField,elementalATKDebuff_IntegerField,magicPhysicalBuff_IntegerField,magicPhysicalDebuff_IntegerField);
        accordionTypeAdvantage.add("Magic / Physical & Elemental",layout_typeAdvantage_v);
        accordionTypeAdvantage.close();

        //Synergy
        VerticalLayout layout_synergy_v = new VerticalLayout();
        synergyUpDown_IntegerField = new IntegerField("Synergy Buff / Debuff");
        synergyPartnerATK_IntegerField = new IntegerField("Synergy Partner ATK");
        synergyTrue_Checkbox = new Checkbox("Synergy");
        synergyPartnerATK_IntegerField.setVisible(false);
        synergyUpDown_IntegerField.setVisible(false);
        layout_synergy_v.add(synergyTrue_Checkbox,synergyPartnerATK_IntegerField,synergyUpDown_IntegerField);
        synergyTrue_Checkbox.addValueChangeListener(event -> {
            boolean isChecked = event.getValue();
            synergyPartnerATK_IntegerField.setVisible(isChecked);
            synergyUpDown_IntegerField.setVisible(isChecked);
        });


        Accordion accordion_stun_enamor = new Accordion();
        VerticalLayout layout_accordian_stun_enamor = new VerticalLayout();
        layout_accordian_stun_enamor.setPadding(false);
        VerticalLayout layout_stunStrike_v = new VerticalLayout();
        stunTrue_Checkbox = new Checkbox("Stun Strike");
        stundStrikeMulti_IntegerField = new IntegerField("Stun Strike Buff / Debuff");
        stundStrikeMulti_IntegerField.setVisible(false);
        layout_stunStrike_v.add(stunTrue_Checkbox,stundStrikeMulti_IntegerField);
        stunTrue_Checkbox.addValueChangeListener(event -> stundStrikeMulti_IntegerField.setVisible(event.getValue()));

        VerticalLayout layout_charmStrike_v = new VerticalLayout();
        enamorTrue_Checkbox = new Checkbox("Enamor Strike");
        enamorStrikeBuff_IntegerField = new IntegerField("Enamor Strike Buff / Debuff");
        enamorStrikeBuff_IntegerField.setVisible(false);
        layout_charmStrike_v.add(enamorTrue_Checkbox, enamorStrikeBuff_IntegerField);
        enamorTrue_Checkbox.addValueChangeListener(event -> enamorStrikeBuff_IntegerField.setVisible(event.getValue()));
        layout_accordian_stun_enamor.add(layout_stunStrike_v,layout_charmStrike_v);
        accordion_stun_enamor.add("Enamor & Stun Strike",layout_accordian_stun_enamor);
        accordion_stun_enamor.close();

        VerticalLayout layout_crit_v = new VerticalLayout();
        critTrue_Checkbox = new Checkbox("Critical");
        critBuffANDDebuff_IntegerField = new IntegerField("Critical Damage Buff / Debuff");
        critBuffANDDebuff_IntegerField.setVisible(false);
        layout_crit_v.add(critTrue_Checkbox,critBuffANDDebuff_IntegerField);
        critTrue_Checkbox.addValueChangeListener(event -> critBuffANDDebuff_IntegerField.setVisible(event.getValue()));

        VerticalLayout layout_penetration_v = new VerticalLayout();
        pierceTrue_Checkbox = new Checkbox("Pierce");
        pierceUPResDown_IntegerField = new IntegerField("Pierce Power Buff / Debuff");
        pierceUPResDown_IntegerField.setVisible(false);
        layout_penetration_v.add(pierceTrue_Checkbox, pierceUPResDown_IntegerField);
        pierceTrue_Checkbox.addValueChangeListener(event-> pierceUPResDown_IntegerField.setVisible(event.getValue()));

        VerticalLayout layout_weakness_v = new VerticalLayout();
        weaknessProtectorBuff_IntegerField = new IntegerField("Weakness Protector Buff");
        addToolTip(weaknessProtectorBuff_IntegerField, "Use this if you have ANTI Element from your protector, usually 60");

        weakpointTrue_Checkbox = new Checkbox("Weakpoint");
        weakpointBuff_IntegerField = new IntegerField("Weakpoint Buff / Debuff");
        weakpointBuff_IntegerField.setVisible(false);
        layout_weakness_v.add(weaknessProtectorBuff_IntegerField,weakpointTrue_Checkbox,weakpointBuff_IntegerField);
        weakpointTrue_Checkbox.addValueChangeListener(event ->weakpointBuff_IntegerField.setVisible(event.getValue()));

        VerticalLayout layout_secretSkill_v = new VerticalLayout();
        secretSkillfromCharacter_IntegerField = new IntegerField("Soul of Combos Damage from Unit");
        addToolTip(secretSkillfromCharacter_IntegerField, "EX Soul of Combos Damage would be normally 735.");
        secretSkillBuff_IntegerField = new IntegerField("Soul of Combos Buff / Debuff");
        secretSkillTrue_Checkbox = new Checkbox("Secret Skill");
        secretDamageResistanceDown_IntegerField = new IntegerField("Secret Skill Damage Resistance down");
        secretDamageResistanceDown_IntegerField.setVisible(false);
        secretSkillfromCharacter_IntegerField.setVisible(false);
        secretSkillBuff_IntegerField.setVisible(false);
        layout_secretSkill_v.add(secretSkillTrue_Checkbox,secretSkillfromCharacter_IntegerField, secretSkillBuff_IntegerField, secretDamageResistanceDown_IntegerField);
        secretSkillTrue_Checkbox.addValueChangeListener(event ->{
            boolean isChecked = event.getValue();
            secretSkillfromCharacter_IntegerField.setVisible(isChecked);
            secretSkillBuff_IntegerField.setVisible(isChecked);
            secretDamageResistanceDown_IntegerField.setVisible(isChecked);
        });

        attributeResBuff_IntegerField = new IntegerField("Attribute Res Buff");
        attributeResDown_IntegerField = new IntegerField("Attribute Res Down");
        attackResBuff_IntegerField = new IntegerField("Attack Res Buff");
        attackResDown_IntegerField = new IntegerField("Attack Res Down");

        Accordion accordionDef = new Accordion();
        VerticalLayout layout_def_v = new VerticalLayout();
        def_ene_IntegerField = new IntegerField("Defence Initial");
        def_up_IntegerField = new IntegerField("Defence UP");
        def_down_IntegerField = new IntegerField("Defence DOWN");
        addToolTip(def_ene_IntegerField,"Select the DEF from the Team Building screen (not in fight DEF)");

        layout_def_v.add(def_ene_IntegerField,def_down_IntegerField);
        accordionDef.add("Defence Enemy", layout_def_v);
        accordionDef.close();

        VerticalLayout layout_attributeAdvantage_v = new VerticalLayout();
        attributeAdvantageTrue_Checkbox = new Checkbox("Do you have Attribute Advantage?");
        layout_attributeAdvantage_v.add(attributeAdvantageTrue_Checkbox);

        VerticalLayout layout_unit120_v = new VerticalLayout();
        unit120_Checkbox = new Checkbox("Is your Unit Level 120?");
        layout_unit120_v.add(unit120_Checkbox);

        layout_atk_v.setPadding(false);
        layout_synergy_v.setPadding(false);
        layout_stunStrike_v.setPadding(false);
        layout_charmStrike_v.setPadding(false);
        layout_crit_v.setPadding(false);
        layout_penetration_v.setPadding(false);
        layout_weakness_v.setPadding(false);
        layout_secretSkill_v.setPadding(false);
        layout_typeAdvantage_v.setPadding(false);
        layout_def_v.setPadding(false);
        layout_attributeAdvantage_v.setPadding(false);
        layout_unit120_v.setPadding(false);

        setValue();

        layout.add(atk_accordion,accordionTypeAdvantage, layout_synergy_v,layout_crit_v,layout_penetration_v,
                layout_weakness_v,layout_secretSkill_v,accordionDef,accordion_stun_enamor,layout_attributeAdvantage_v,layout_unit120_v
                );

    }

    private void addToolTip(IntegerField component, String text){
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE,
                ButtonVariant.LUMO_ICON);
        component.setSuffixComponent(button);
        component.setTooltipText(text);
        Tooltip tooltip = component.getTooltip().withManual(true);
        button.addClickListener(event -> {
            tooltip.setOpened(!tooltip.isOpened());
        });
    }

    public void setValue(){
        //Set default value
        atk_Debuff_IntegerField.setValue(0); 
        atk_Buff_IntegerField.setValue(0); 
        atk_PT_IntegerField.setValue(0); 

        synergyUpDown_IntegerField.setValue(0); 
        synergyPartnerATK_IntegerField.setValue(0); 

        stundStrikeMulti_IntegerField.setValue(0); 

        enamorStrikeBuff_IntegerField.setValue(0); 

        critBuffANDDebuff_IntegerField.setValue(0); 

        pierceUPResDown_IntegerField.setValue(0); 

        weaknessProtectorBuff_IntegerField.setValue(0); 
        weakpointBuff_IntegerField.setValue(0); 

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

        stundStrikeMulti_IntegerField.setMin(0);

        enamorStrikeBuff_IntegerField.setMin(0);

        critBuffANDDebuff_IntegerField.setMin(0);

        pierceUPResDown_IntegerField.setMin(0);

        weaknessProtectorBuff_IntegerField.setMin(0);
        weakpointBuff_IntegerField.setMin(0);

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
                .setStunStrikeDamageUPDOWN(stundStrikeMulti_IntegerField.getValue())
                .setEnamorStrikeBuff(enamorStrikeBuff_IntegerField.getValue())
                .setCritBuffANDDebuff(critBuffANDDebuff_IntegerField.getValue())
                .setSecretSkillfromCharacter(secretSkillfromCharacter_IntegerField.getValue())
                .setSecretSkillBuff(secretSkillBuff_IntegerField.getValue())
                .setSecretDamageResistanceDown(secretDamageResistanceDown_IntegerField.getValue())
                .setPierceUPResDown(pierceUPResDown_IntegerField.getValue())
                .setWeaknessProtectorBuff(weaknessProtectorBuff_IntegerField.getValue())
                .setWeakpointBuff(weakpointBuff_IntegerField.getValue())
                // set booleans
                .setStunTrue(stunTrue_Checkbox.getValue())
                .setEnamorTrue(enamorTrue_Checkbox.getValue())
                .setCritTrue(critTrue_Checkbox.getValue())
                .setSecretSkillTrue(secretSkillTrue_Checkbox.getValue())
                .setPierceTrue(pierceTrue_Checkbox.getValue())
                .setAttributeAdvantageTrue(attributeAdvantageTrue_Checkbox.getValue())
                .setWeakpointTrue(weakpointTrue_Checkbox.getValue())
                .setSynergyTrue(synergyTrue_Checkbox.getValue())
                .setUnit120True(unit120_Checkbox.getValue());
        return damageObject;
    }

}
