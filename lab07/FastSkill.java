// Alexandra Postolaki (posto022)

public class FastSkill extends Skill{
    public FastSkill(String name, int strength, int usageLimit){super(name, strength, usageLimit);}

    /** This skill, when applied, will damage the foe based on the skill's power while also healing the "me" CodeMonster
     * by the skill's power.
     * @param me
     * @param foe
     */
    @Override
    public void applyChanges(CodeMonster me, CodeMonster foe){
        int reduceHealthAmount = 0 - getStrength();          // Should be the 'me' CodeMonster's strength score that's subtracted from 0 (since the negative means we end up removing that much from foe's HP in the adjustHealth() method.
        double reduceNextTurnTime = me.getNextTurnTime() - me.getSpeedScore();      // Reduces the "me" CodeMonster's nextTurnTime by "me"'s speed score
        foe.adjustHealth(reduceHealthAmount);               // Adjusts the health of the foe CodeMonster according to the strengthScore of the skill.
        me.setNextTurnTime(reduceNextTurnTime);                                             // sets "me" CodeMonster's nextTurnTime to be the reduces / newly calculated nextTurnTime
    }
}
