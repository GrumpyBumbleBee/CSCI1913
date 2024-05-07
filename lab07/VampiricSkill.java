// Alexandra Postolaki (posto022)


public class VampiricSkill extends Skill{
    public VampiricSkill(String name, int strength, int usageLimit){ super(name, strength, usageLimit);}

    /** This skill, when applied, will damage the foe based on the skill's power and also heal the "me" CodeMonster by the skill's power.
     *
     * @param me
     * @param foe
     */
    @Override
    public void applyChanges(CodeMonster me, CodeMonster foe){
        int reduceHealthAmount = 0 - getStrength();          // Should be the 'me' CodeMonster's strength score that's subtracted from 0 (since the negative means we end up removing that much from foe's HP in the adjustHealth() method.
        int increaseHealthAmount = getStrength();            // Will be used to adjust/add on to the health of "me" CodeMonster
        foe.adjustHealth(reduceHealthAmount);               // Adjusts the health of the foe CodeMonster according to the strengthScore of the skill. (Reduces it)
        me.adjustHealth(increaseHealthAmount);              // Adds onto the health of the 'me' CodeMonster
    }
}
