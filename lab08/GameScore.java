/** Created by Alexandra Postolaki
 */
public class GameScore implements Comparable<GameScore>{
    private double score;
    private String name;
    private boolean hardMode;
    /** GameScore() Constructor takes in a String name (representing the name for the record), a double score (the score), and a boolean ('true' if the score was earned on hard mode and 'false' if
     * the score was earned on easy mode).
     * @param name
     * @param score
     * @param hardMode
     */
    public GameScore(String name, double score, boolean hardMode){
        this.name = name;
        this.score = score;
        this.hardMode = hardMode;
    }
    public String getName(){
        return name;
    }
    public double getScore(){
        return score;
    }
    public boolean isHardMode(){
        return hardMode;                        // Returns 'true' if the game was in hard mode, 'false' otherwise.
    }

    /** toString() returns a string of the format: "name score(with no rounding applied)". If the score was made in hard mode,
     * then an asterisk is appended to the end of the toString.
     * @return scoreboardStringOutput
     */
    public String toString(){
        String scoreboardStringOutput = "";
        scoreboardStringOutput = name + " " + score;
        if(hardMode == true){                   // If the score was made in hard mode, then an asterisk is appended to the end of the string.
            scoreboardStringOutput = scoreboardStringOutput + "*";
        }
        return scoreboardStringOutput;
    }

    /** equals() takes in an Object and compares two GameScore objects to see if they are equal. They are considered equal if, and only if,
     * they have equal names, the same score, and the same difficulty. If they are equal, equals() returns boolean 'true', otherwise it returns 'false'.
     * @param o
     * @return isEqual
     */
    public boolean equals(Object o){
        if(this == o) {
            return true;
        }
        else if(o == null) {
            return false;
        }
        else if(o instanceof GameScore) {
            GameScore gs = (GameScore) o;
            return this.name.equals(gs.name) && this.score == gs.score && this.hardMode == gs.hardMode;                     // Should return boolean 'true' if the names, scores, and difficulty mode are the exact same.
        }
        return false;
    }

    /** compareTo() compares the current GameScore object (this) to the parameter
     * object (other). Returns int 0 if 'this' object has the same score and difficulty mode as the 'other' object.
     *                 Returns int -1 if 'this' object has the same score as the 'other' object, but is in easy mode while the 'other' object is in hard mode.
     *                 Returns int 1 if 'this' object and 'other' object have the same difficulty mode, but 'this' object has a higher score. Also returns int 1 if 'this' object is in hard mode while 'other' object is in easy mode
     *                      even if the 'other' object score is greater than 'this' object's score. (Difficulty has greater priority.)
     * @param other
     * @return int (if this > other, returns 1. If this < other, returns -1. If this == other, returns 0)
     */
    public int compareTo(GameScore other){
        if(this.score == other.score && this.hardMode == other.hardMode){
            return 0;
        }
        else if(this.score == other.score && this.hardMode != other.hardMode){              // Looks at the difficulty if the scores are the same.
            if(this.hardMode == false && other.hardMode == true){                               // If the other is in hard mode, but (this) is not, then negative int returned.
                return -1;
            }
            else if(this.hardMode == true && other.hardMode == false) {                         // If (this) is hard mode, but other is not, 1 returned
                return 1;
            }
        }
        else if(this.score != other.score){                                                 // Checks if scores are different
            if(this.hardMode == true && other.hardMode == false){                                   // Checks if (this) is hard mode, but other is not, returns 1 (DIFFICULTY HAS PRIORITY OVER SCORE)
                return 1;
            }
            else if(this.hardMode == false && other.hardMode == true){                                   // If (this) is easy mode, but other is not, returns -1
                return -1;
            }
            else if(this.score > other.score){                                                   // If (this) score is higher than other score, returns 1.
                return 1;
            }
            else{
                return -1;
            }
        }
        return -1;
    }
}
