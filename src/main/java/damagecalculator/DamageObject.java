package damagecalculator;

public class DamageObject {
    double atk_Debuff, atk_Buff, atk_PT, elementalATKBuff, elementalATKDebuff, synergyUpDown,synergyPartnerATK;
    double MagicPhysicalBuff, MagicPhysicalDebuff, attributeResBuff, attributeResDown;
    double attackResBuff, attackResDown, def_ene, def_up, def_down;
    double stunStrikeDamageUPDOWN, enamorStrikeBuff, critBuffANDDebuff, secretSkillfromCharacter;
    double secretSkillBuff, secretDamageResistanceDown, pierceUPResDown, weaknessProtectorBuff, weakpointBuff;
    boolean stunTrue, enamorTrue, critTrue, secretSkillTrue;
    boolean pierceTrue, attributeAdvantageTrue, weakpointTrue, synergyTrue, unit120;
        
    public void addDamageObject(DamageObject damageObject){
            atk_Debuff += damageObject.atk_Debuff;
            atk_Buff += damageObject.atk_Buff;
            atk_PT += damageObject.atk_PT;
            elementalATKBuff += damageObject.elementalATKBuff;
            elementalATKDebuff += damageObject.elementalATKDebuff;
            MagicPhysicalBuff += damageObject.MagicPhysicalBuff;
            MagicPhysicalDebuff += damageObject.MagicPhysicalDebuff;
            attributeResBuff += damageObject.attributeResBuff;
            attributeResDown += damageObject.attributeResDown;
            attackResBuff += damageObject.attackResBuff;
            attackResDown += damageObject.attackResDown;
            def_ene += damageObject.def_ene;
            def_up += damageObject.def_up;
            def_down += damageObject.def_down;
            stunStrikeDamageUPDOWN += damageObject.stunStrikeDamageUPDOWN;
            enamorStrikeBuff += damageObject.enamorStrikeBuff;
            critBuffANDDebuff += damageObject.critBuffANDDebuff;
            secretSkillfromCharacter += damageObject.secretSkillfromCharacter;
            secretSkillBuff += damageObject.secretSkillBuff;
            secretDamageResistanceDown += damageObject.secretDamageResistanceDown;
            pierceUPResDown += damageObject.pierceUPResDown;
            weaknessProtectorBuff += damageObject.weaknessProtectorBuff;
            weakpointBuff += damageObject.weakpointBuff;
            synergyUpDown += damageObject.synergyUpDown;
            synergyPartnerATK += damageObject.synergyPartnerATK;

            // Aggregation der booleschen Werte
            synergyTrue |= damageObject.synergyTrue;
            stunTrue |= damageObject.stunTrue;
            enamorTrue |= damageObject.enamorTrue;
            critTrue |= damageObject.critTrue;
            secretSkillTrue |= damageObject.secretSkillTrue;
            pierceTrue |= damageObject.pierceTrue;
            attributeAdvantageTrue |= damageObject.attributeAdvantageTrue;
            weakpointTrue |= damageObject.weakpointTrue;
            unit120 |= damageObject.unit120;
        }

    public double calculateDamage() {
        // ATK Buff
        double synergyATK = synergyPartnerATK * 0.5 * (1+(synergyUpDown / 100));
        atk_PT = synergyTrue ? atk_PT + synergyATK : atk_PT;
        double atk_plus_buffATK = atk_PT * (atk_Buff) / 100;
        double atk_plus_debuffATK = atk_PT * (atk_Debuff) / 100;
        double ATKBuffs_applied = atk_PT + atk_plus_buffATK + atk_plus_debuffATK;


        // Variante 1 Magic/Physical/Attribute ATK
        double atkBuffs_plus_elementalATKBuff = ATKBuffs_applied * (elementalATKBuff) / 100;
        double atkBuffs_plus_elementalATKdebuff = ATKBuffs_applied * (elementalATKDebuff) / 100;
        double ElementalBuffs_applied = ATKBuffs_applied + atkBuffs_plus_elementalATKdebuff + atkBuffs_plus_elementalATKBuff;

        double atkBuff_elemental_Buff_MaPhyBuff = ElementalBuffs_applied * (MagicPhysicalBuff) / 100;
        double atkBuff_elemental_Buff_MaPhyDebuff = ElementalBuffs_applied * (MagicPhysicalDebuff) / 100;
        double physicalmagiBuffs_applied = ElementalBuffs_applied + atkBuff_elemental_Buff_MaPhyBuff + atkBuff_elemental_Buff_MaPhyDebuff;

        // Variante 2
        double atkBuff_elemental_Buff_MaPhyBuff_basedATKBuff = ATKBuffs_applied * (MagicPhysicalBuff) / 100;
        double atkBuff_elemental_Buff_MaPhyDebuff_basedATKBuff = ATKBuffs_applied * (MagicPhysicalDebuff) / 100;
        double physicalmagiBuffs_applied_baseATKBuff = ATKBuffs_applied + atkBuff_elemental_Buff_MaPhyDebuff_basedATKBuff + atkBuff_elemental_Buff_MaPhyBuff_basedATKBuff;

        double attributeATKPowerUP2 = physicalmagiBuffs_applied_baseATKBuff * (elementalATKBuff) / 100;
        double attributeATKPowerDOWN2 = physicalmagiBuffs_applied_baseATKBuff * (elementalATKDebuff) / 100;
        double attributeATKPowerUP2_applied = physicalmagiBuffs_applied_baseATKBuff + attributeATKPowerDOWN2 + attributeATKPowerUP2;

        double atkBuffs_andOther_applied = Math.max(attributeATKPowerUP2_applied, physicalmagiBuffs_applied);


        // Defence
        double defenceUP = def_ene * (def_up) / 100;
        double defenceDOWN = def_ene * (def_down) / 100;
        double def_AND_down = def_ene + defenceDOWN + defenceUP;


        // Resistances
        // Variante 1
        double attributeResUP = def_AND_down * (attributeResBuff) / 100;
        double attributeResDOWN = def_AND_down * (attributeResDown) / 100;
        double def_plus_elementalRES = def_AND_down - attributeResDOWN + attributeResUP;

        double attackResUP = def_plus_elementalRES * (attackResBuff) / 100;
        double attackResDOWN = def_plus_elementalRES * (attackResDown) / 100;
        double attack_attribute_Res_applied = def_plus_elementalRES + attackResUP + attackResDOWN;

        // Variante 2
        double attackResUP2 = def_AND_down * (attackResBuff) / 100;
        double attackResDOWN2 = def_AND_down * (attackResDown) / 100;
        double attackRes_applied2 = def_AND_down + attackResDOWN2 + attackResUP2;

        double attributeResUP2 = attackRes_applied2 * (attributeResBuff) / 100;
        double attributeResDOWN2 = attackRes_applied2 * (attributeResDown) / 100;
        double attack_attribute_Res_applied2 = attackRes_applied2 + attributeResUP2 - attributeResDOWN2;

        double defUP_allApplied = Math.max(attack_attribute_Res_applied2, attack_attribute_Res_applied);

        double basedamage = (atkBuffs_andOther_applied * 2 - defUP_allApplied) * 0.2;


        // Stun Strike
        double stunStrikeMutliplyer = stunStrikeDamageUPDOWN;
        double stunStrike = stunTrue ? basedamage + basedamage * stunStrikeMutliplyer / 100 : basedamage;


        // Charm Strike
        double charmStrikeUP = enamorStrikeBuff;
        double charmStrike = enamorTrue ? stunStrike + stunStrike * charmStrikeUP / 100 : stunStrike;


        // Critical Damage
        double critUP = critBuffANDDebuff;
        double critDamage = critTrue ? charmStrike + charmStrike * (critUP + 30) / 100 : charmStrike;


        // Secret Skill UP
        double secretSkillUltCharacterUP = critDamage * (1+secretSkillfromCharacter / 100);
        double secretSkillUltSkillUP = secretSkillBuff;
        double secretSkillUltDamage = secretSkillTrue ? secretSkillUltCharacterUP + secretSkillUltCharacterUP * secretSkillUltSkillUP / 100 : critDamage;


        // Don't use, need res up and down
        double secretDamageResDown = secretDamageResistanceDown;//(mysdam_res_up_eizoku + mysdam_res_up_turn) * -1 - mysdam_res_down_kago_sukiru * mysdam_res_down_kago_sukiru_kaisu - mysdam_res_down_sentou_sukiru;
        double secretDamageRes = secretSkillTrue ? secretSkillUltDamage + secretSkillUltDamage * secretDamageResDown / 100 : secretSkillUltDamage;

        double damage1 = secretDamageRes >= 0 ? secretDamageRes : 1;


        // Penetration damage
        double pierceDamage = damage1 + ATKBuffs_applied * 6 / 100;
        double pierceUP = pierceUPResDown;
        double pierce = pierceTrue ? pierceDamage + pierceDamage * pierceUP / 100 : damage1;


        // Attribute Advantage
        double attributeAdvantage = attributeAdvantageTrue ? pierce * 1.5 : pierce;


        // Protector damage up against weakness attribute
        double ForceElementaldamageMultiplyer = weaknessProtectorBuff / 100; // Frey 60% damage increase for WoF to fire enemies
        double damageUP = attributeAdvantageTrue ? attributeAdvantage + attributeAdvantage * ForceElementaldamageMultiplyer : attributeAdvantage;


        // Weakpoint
        double weakpointUP = weakpointBuff / 100;
        double weakpoint = weakpointTrue ? damageUP + pierce * weakpointUP : damageUP;

        double checkvalidValue = weakpoint >= 0 ? weakpoint : 1;

        double unit120Damage = unit120 ? (checkvalidValue * 1.2) : checkvalidValue;

        return unit120Damage;
    }


    public DamageObject setAtk_Debuff(double atk_Debuff) {
        this.atk_Debuff = atk_Debuff;
        return this;
    }

    public DamageObject setAtk_Buff(double atk_Buff) {
        this.atk_Buff = atk_Buff;
        return this;
    }

    public DamageObject setAtk_PT(double atk_PT) {
        this.atk_PT = atk_PT;
        return this;
    }

    public DamageObject setElementalATKBuff(double elementalATKBuff) {
        this.elementalATKBuff = elementalATKBuff;
        return this;
    }

    public DamageObject setElementalATKDebuff(double elementalATKDebuff) {
        this.elementalATKDebuff = elementalATKDebuff;
        return this;
    }

    public DamageObject setSynergyUpDown(double synergyUpDown) {
        this.synergyUpDown = synergyUpDown;
        return this;
    }

    public DamageObject setSynergyPartnerATK(double synergyPartnerATK) {
        this.synergyPartnerATK = synergyPartnerATK;
        return this;
    }

    public DamageObject setMagicPhysicalBuff(double magicPhysicalBuff) {
        MagicPhysicalBuff = magicPhysicalBuff;
        return this;
    }

    public DamageObject setMagicPhysicalDebuff(double magicPhysicalDebuff) {
        MagicPhysicalDebuff = magicPhysicalDebuff;
        return this;
    }

    public DamageObject setAttributeResBuff(double attributeResBuff) {
        this.attributeResBuff = attributeResBuff;
        return this;
    }

    public DamageObject setAttributeResDown(double attributeResDown) {
        this.attributeResDown = attributeResDown;
        return this;
    }

    public DamageObject setAttackResBuff(double attackResBuff) {
        this.attackResBuff = attackResBuff;
        return this;
    }

    public DamageObject setAttackResDown(double attackResDown) {
        this.attackResDown = attackResDown;
        return this;
    }

    public DamageObject setDef_ene(double def_ene) {
        this.def_ene = def_ene;
        return this;
    }

    public DamageObject setDef_up(double def_up) {
        this.def_up = def_up;
        return this;
    }

    public DamageObject setDef_down(double def_down) {
        this.def_down = def_down;
        return this;
    }

    public DamageObject setStunStrikeDamageUPDOWN(double stunStrikeDamageUPDOWN) {
        this.stunStrikeDamageUPDOWN = stunStrikeDamageUPDOWN;
        return this;
    }

    public DamageObject setEnamorStrikeBuff(double enamorStrikeBuff) {
        this.enamorStrikeBuff = enamorStrikeBuff;
        return this;
    }

    public DamageObject setCritBuffANDDebuff(double critBuffANDDebuff) {
        this.critBuffANDDebuff = critBuffANDDebuff;
        return this;
    }

    public DamageObject setSecretSkillfromCharacter(double secretSkillfromCharacter) {
        this.secretSkillfromCharacter = secretSkillfromCharacter;
        return this;
    }

    public DamageObject setSecretSkillBuff(double secretSkillBuff) {
        this.secretSkillBuff = secretSkillBuff;
        return this;
    }

    public DamageObject setSecretDamageResistanceDown(double secretDamageResistanceDown) {
        this.secretDamageResistanceDown = secretDamageResistanceDown;
        return this;
    }

    public DamageObject setPierceUPResDown(double pierceUPResDown) {
        this.pierceUPResDown = pierceUPResDown;
        return this;
    }

    public DamageObject setWeaknessProtectorBuff(double weaknessProtectorBuff) {
        this.weaknessProtectorBuff = weaknessProtectorBuff;
        return this;
    }

    public DamageObject setWeakpointBuff(double weakpointBuff) {
        this.weakpointBuff = weakpointBuff;
        return this;
    }

    public DamageObject setStunTrue(boolean stunTrue) {
        this.stunTrue = stunTrue;
        return this;
    }

    public DamageObject setEnamorTrue(boolean enamorTrue) {
        this.enamorTrue = enamorTrue;
        return this;
    }

    public DamageObject setCritTrue(boolean critTrue) {
        this.critTrue = critTrue;
        return this;
    }

    public DamageObject setSecretSkillTrue(boolean secretSkillTrue) {
        this.secretSkillTrue = secretSkillTrue;
        return this;
    }

    public DamageObject setPierceTrue(boolean pierceTrue) {
        this.pierceTrue = pierceTrue;
        return this;
    }

    public DamageObject setAttributeAdvantageTrue(boolean attributeAdvantageTrue) {
        this.attributeAdvantageTrue = attributeAdvantageTrue;
        return this;
    }

    public DamageObject setWeakpointTrue(boolean weakpointTrue) {
        this.weakpointTrue = weakpointTrue;
        return this;
    }

    public DamageObject setSynergyTrue(boolean synergyTrue) {
        this.synergyTrue = synergyTrue;
        return this;
    }

    public DamageObject setUnit120True(boolean unit120) {
        this.unit120 = unit120;
        return this;
    }

    public double getAtk_Debuff() {
        return atk_Debuff;
    }

    public double getAtk_Buff() {
        return atk_Buff;
    }

    public double getAtk_PT() {
        return atk_PT;
    }

    public double getElementalATKBuff() {
        return elementalATKBuff;
    }

    public double getElementalATKDebuff() {
        return elementalATKDebuff;
    }

    public double getSynergyUpDown() {
        return synergyUpDown;
    }

    public double getSynergyPartnerATK() {
        return synergyPartnerATK;
    }

    public double getMagicPhysicalBuff() {
        return MagicPhysicalBuff;
    }

    public double getMagicPhysicalDebuff() {
        return MagicPhysicalDebuff;
    }

    public double getAttributeResBuff() {
        return attributeResBuff;
    }

    public double getAttributeResDown() {
        return attributeResDown;
    }

    public double getAttackResBuff() {
        return attackResBuff;
    }

    public double getAttackResDown() {
        return attackResDown;
    }

    public double getDef_ene() {
        return def_ene;
    }

    public double getDef_up() {
        return def_up;
    }

    public double getDef_down() {
        return def_down;
    }

    public double getStunStrikeDamageUPDOWN() {
        return stunStrikeDamageUPDOWN;
    }

    public double getEnamorStrikeBuff() {
        return enamorStrikeBuff;
    }

    public double getCritBuffANDDebuff() {
        return critBuffANDDebuff;
    }

    public double getSecretSkillfromCharacter() {
        return secretSkillfromCharacter;
    }

    public double getSecretSkillBuff() {
        return secretSkillBuff;
    }

    public double getSecretDamageResistanceDown() {
        return secretDamageResistanceDown;
    }

    public double getPierceUPResDown() {
        return pierceUPResDown;
    }

    public double getWeaknessProtectorBuff() {
        return weaknessProtectorBuff;
    }

    public double getWeakpointBuff() {
        return weakpointBuff;
    }

    public boolean isStunTrue() {
        return stunTrue;
    }

    public boolean isEnamorTrue() {
        return enamorTrue;
    }

    public boolean isCritTrue() {
        return critTrue;
    }

    public boolean isSecretSkillTrue() {
        return secretSkillTrue;
    }

    public boolean isPierceTrue() {
        return pierceTrue;
    }

    public boolean isAttributeAdvantageTrue() {
        return attributeAdvantageTrue;
    }

    public boolean isWeakpointTrue() {
        return weakpointTrue;
    }

    public boolean isSynergyTrue() {
        return synergyTrue;
    }

    public boolean isUnit120() {
        return unit120;
    }
}


