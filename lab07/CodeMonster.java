// Alexandra Postolaki (posto022)

public class CodeMonster {
    public String name;
    public int currentHP, maxHP;
    public Skill[] moves;
    int moveIndex = 0;
    double speedScore, nextTurnTime;
    /** This constructor updates the maxHP, speedScore, name, and moves array from its parameters.
     * The current hp should be set to maxHP. The nextTurnTime is set to the speed score.
     *
     * @param maxHP
     * @param speedScore
     * @param name
     * @param moves
     */
    public CodeMonster(int maxHP, double speedScore, String name, Skill[] moves){
        this.maxHP = maxHP;
        this.speedScore = speedScore;
        currentHP = maxHP;                          // Current HP set to max HP
        nextTurnTime = speedScore;                  // next turn time set to speed score
        this.name = name;
        this.moves = moves;
    }
    /** prepForBattle() method is called before a new battle. This method resets private variables for a new battle as follows:
     *  current HP is reset to the max.
     *  The next turn time is set to the codeMonster's speed score.
     *  The moveIndex is reset to the first skill in the moves array.
     *  Every skill in the moves array is refreshed using the skill refresh method.
     */
    public void prepForBattle(){
        currentHP = maxHP;
        nextTurnTime = speedScore;
        moveIndex = 0;
        for(int i = 0; i < moves.length; i++){              // Index through the moves array to access each skill object.
            moves[i].refresh();                             // Refreshes the skill object in the moves array.
        }
    }
    /** takeTurn() This function called by battle code when it's this codeMonster's turn to use a skill. This function
     * updates the nextTurnTime by adding the codeMonster's speed to the next turn time. Also chooses which skill to use.
     * Returns the skill that the CodeMonster wishes to use.
     * @return skillChosen
     */
    public Skill takeTurn(){
        nextTurnTime += speedScore;                                 // Updates nextTurnTime by adding the codeMonster's speed to it.
        if(moveIndex >= moves.length){ // For the array to be accessed in the fashion where the takeTurns is called a certain number of times than there are indices of the moves array, we'll restart the moveIndex to 0 until it reaches the length of the moves array.
            moveIndex = 0;
        }
        Skill skillChosen = moves[moveIndex];                      // Assigns the Skill (skill that will be done) to point to the move/skill at a certain index in the moves array
        moveIndex += 1;                                            // Increases moveIndex by 1
        return skillChosen;                                        // Returns the chosen skill to use.
    }
    /** This functions returns true if the current HP is greater than 0, false otherwise
     *
     * @return boolean
     */
    public boolean isAlive(){
        if (currentHP > 0){                 // If the current HP is greater than 0, return true
            return true;
        }
        else{
            return false;                   // false otherwise
        }
    }
    /** adjustHealth() method adjusts the current HP with positive amounts increasing the current HP
     * (representing healing) and negative amounts decreasing the HP (representing damage). This function is in
     * charge of enforcing two limits: the HP should never go over the maximum, and the HP should never go below 0
     * (although it can be reduced to 0). If the hp would ever be reduced below 0 in this function, then the hp is
     * set to 0. If the hp would ever be increased above the maximum, then the HP is set to the max.
     * @param amount
     */
    public void adjustHealth(int amount){
        if((currentHP + amount) < 0) {                                                              // Check if the current HP subtracting the amount is going to be less than 0
            currentHP = 0;                                                                          // If it is, then it sets the current HP to 0
        }
        else if((currentHP + amount) > maxHP) {                                                     // Otherwise, if the current HP adding the amount is going to be over the maximum,
            currentHP = maxHP;                                                                      // Then the current HP is set to the max amount.
        }
        else {                                                                                      // Other-otherwise, the amount (whether negative or positive) is added to the current HP
            currentHP += amount;
        }
    }
    /** setNextTurnTime() takes input (a double representing the next turn time)
     * and updates the private nextTurnTime attribute based on its input parameter value.
     * @param nextTurnTime**/
    public void setNextTurnTime(double nextTurnTime){
        this.nextTurnTime = nextTurnTime;
    }
    public double getNextTurnTime(){
        return nextTurnTime;
    }
    public int getHp(){
        return currentHP;
    }
    public int getMaxHp(){
        return maxHP;
    }
    public Skill[] getMoves(){
        return moves;
    }
    public String getName(){
        return name;
    }
    public double getSpeedScore(){
        return speedScore;
    }
    /** This function returns a string that is the CodeMonster's name, followed by a space,
     * then their current hp, then a '/' then their max hp.
     * @return codeMonster name (String)
     */
    public String toString(){
        String codeMonsterInfo = name + " " + currentHP + "/" + maxHP;          // Formats the string to return
        return codeMonsterInfo;                                                 // Returns the properly formatted string
    }

}
