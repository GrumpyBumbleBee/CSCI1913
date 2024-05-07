// Alexandra Postolaki (posto022)

public class Skill {
    private String name;
    private int strengthScore;
    private int usageLimit, usageLeft;
    /** Takes a String and two ints to assign the
     * private name, strength, and usage limits. Additionally, it sets
     * up variables so that getUsageLeft would return the usage Limit (I.E a newly created skill that
     * has all its uses left.)
     * @param name
     * @param strength
     * @param usageLimit
     */
    public Skill(String name, int strength, int usageLimit){
        this.name = name;
        this.strengthScore = strength;
        this.usageLimit = usageLimit;
        this.usageLeft = usageLimit;
    }
    public String getName(){
        return name;
    }
    /** refresh() resets private variables so that getUsageLeft
     * will be the usageLimit. This function called at the beginning of a battle
     * to reset the use-tracking variables for a new battle.
     */
    public void refresh(){
        this.name = name;
        this.strengthScore = strengthScore;
        usageLeft = usageLimit;
    }
    public int getStrength(){
        return strengthScore;
    }
    public int getUsageLimit(){
        return usageLimit;
    }
    public int getUsageLeft(){
        return usageLeft;
    }
    /** useSkill() takes in two CodeMonsters, me and foe, and is the main entry point to
     * using a skill that the battle code will use. In our code, this function ensures usage limit for a skill
     * is enforced. This function ensures that there are usages left for the current skill. If there
     * are, reduces the remaining usages by one and then calls applyChanges() to actually effect the battle. If the move
     * does not have any uses left this battle, this function does nothing.
     * @param me
     * @param foe
     */
    public void useSkill(CodeMonster me, CodeMonster foe){
        if(usageLeft > 0){                          // Checks if the usages left are greater than 0 (meaning we can reduce the remaining usages by one).
            usageLeft -= 1;                         // If it is, then usageLeft is reduced by 1
            applyChanges(me, foe);                  // applyChanges() is called to actually effect the battle
        }
    }
    /** applyChanges() takes in two CodeMonsters, me and foe, and updates that state of the
     * CodeMonsters to reflect the change made by using this skill.
     * @param me
     * @param foe
     */
    public void applyChanges(CodeMonster me, CodeMonster foe){
        int reduceHealthAmount = 0 - strengthScore;         // Should be the 'me' CodeMonster's strength score that's subtracted from 0 (since the negative means we end up removing that much from foe's HP in the adjustHealth() method.
        foe.adjustHealth(reduceHealthAmount);               // Adjusts the health of the foe CodeMonster according to the strengthScore of the skill.
    }
    /** toString() returns a string formed of the name, a space, the number of remaining
     * usages, a "/" and the usage limit.
     * @return nameUsagesAndUsageLimit
     */
    public String toString(){
        String nameUsagesAndUsageLimit = name + " " + usageLeft + "/" + usageLimit;                                             // Forms the string
        return nameUsagesAndUsageLimit;                                                                                         // Returns the formed string
    }
}
