// Alexandra Postolaki (posto022)
public class Battle {
    /** doOneTurn(CodeMonster one, CodeMonster two) handles exactly one turn of battle. This function first figures out which codeMonster
     * (one or two) is taking this turn. Faster codeMonsters act more frequently than slower ones. The codeMonster with a smaller nextTurnTime
     * should go, if the codeMonsters are ties for nextTurnTime then codeMonster one goes. After deciding which codeMonster is going,
     * its takeTurn method is called to decide which skill is used, and the skill is used. Finally, a message is printed using the toString outputs from the Skill
     * and CodeMonster class.
     * @param one
     * @param two
     */
    public static void doOneTurn(CodeMonster one, CodeMonster two){
        double monsterOneNextTurnTime = one.getNextTurnTime();
        double monsterTwoNextTurnTime = two.getNextTurnTime();
        if(monsterOneNextTurnTime <= monsterTwoNextTurnTime) {                                                            // If they are the same, make CodeMonster one go first (or if CodeMonster one's nextTurnTime is smaller than CodeMonster two's)
            Skill skillChosen = one.takeTurn();                                                                           // Determines which skill will be used by CodeMonster one
            String stringReturningOutcome = one.toString() + " uses " + skillChosen.toString() + " on " + two.toString(); // Forms the appropriate string to print out
            skillChosen.useSkill(one, two);                                                                               // CodeMonster one uses the skill on CodeMonster two
            System.out.println(stringReturningOutcome);                                                                   // Prints out the string outcome
        }
        else{                                                                                                             // Otherwise, if CodeMonster one is larger than CodeMonster two, make CodeMonster two take a turn first.
            Skill skillChosen = two.takeTurn();                                                                           // Determines which skill used by CodeMonster two
            String stringReturningOutcome = two.toString() + " uses " + skillChosen.toString() + " on " + one.toString(); // Forms the appropriate string to print out
            skillChosen.useSkill(two, one);                                                                               // CodeMonster two uses the skill on CodeMonster one
            System.out.println(stringReturningOutcome);                                                                   // Prints out the string outcome
        }
    }

    /** This function carries out an entire battle: Takes in two CodeMonsters, one and two, and prepares each for battle. Then a message is printed out indicating the match up.
     * After that, one turn is taken at a time until one CodeMonster is no longer alive (it... unfortunately passed away in battle). This function then prints out a note declaring the
     * victorious CodeMonster that won the battle and returns the winner to indicate the victory.
     * @param one
     * @param two
     * @return codeMonsterWinner
     */
    public static CodeMonster battle(CodeMonster one, CodeMonster two) {
        one.prepForBattle();                                                            // Prep each CodeMonster for battle
        two.prepForBattle();
        String introString = one.toString() + " vs. " + two.toString();                 // Creates appropriate string to indicate the match up
        System.out.println(introString);                                                // Prints out the string
        while (one.isAlive() == true && two.isAlive() == true) {                        // Continues until one perishes (unfortunately)
            doOneTurn(one, two);
        }
        if (one.isAlive() == false) {                                                   // If CodeMonster one is no longer with us... (one's dead/not alive/isAlive is false)
            String winnerString = two.toString() + " wins!";                            // Then CodeMonster two wins!
            System.out.println(winnerString);                                           // Prints out winner string
            return two;
        }
        else{                                                                           // Otherwise (CodeMonster two is dead), CodeMonster one is declared the victor.
            String winnerString = one.toString() + " wins!";
            System.out.println(winnerString);                                           // Prints out winner string
        }
        return one;                                                                     // Returns CodeMonster one to indicate it as the winner!
    }
}
