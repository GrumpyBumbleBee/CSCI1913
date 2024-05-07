/** EDITED BY ALEXANDRA POSTOLAKI
 * An object that tracks the top-N values of a larger collection.
 * This data structure stores N elements of any sortable type.
 * (NOTE -- while we initially envisioned this in relation to GameScores -- we're building this with generics
 *          and interfaces so it can work with ANY sortable data type. This is a common way to build software
 *          you use an example to determine a need -- but you design to be as flexible as possible.)
 * @param <T> the type of data stored in this LeaderBoard object -- must be a sortable type with defines the compareTo method.
 */
public class LeaderBoard<T extends Comparable<T>> {

    // do not change.
    private T[] scores;

    /**
     * Create a new leaderboard
     * @param size the size of the leaderboard
     * @param dflt the default value for each position in the leaderboard.
     */
    @SuppressWarnings("unchecked")
    public LeaderBoard(int size, T dflt) {
        // do not change.

        // we don't want to deal with small-size boards.
        if (size < 2) {
            size = 2;
        }
        // This line will always have a warning on it (if we hadn't suppressed that on line 17)
        // quite frankly it's a bit messy to begin with -- but it turns out generics and arrays don't play well together.
        // and this is generally understood to be the best we can do for making a generic array.
        scores = (T[]) new Comparable[size];

        for (int i = 0; i < size; i++) {
            scores[i] = dflt;
        }
    }

    public int getSize(){
        return scores.length;
    }

    public T highScore() {
        return scores[0];
    }

    public T lowScore() {
        return scores[scores.length - 1];
    }

    /** MODIFIED BY ALEXANDRA POSTOLAKI
     * add() method is used to attempt to add a new score to the leaderboard. This method only updates the leaderboard if given a new
     * item bigger than the current small item. If the leaderboard is modified, this method does so in a way that keeps the
     * scores array sorted big-to-small.
     * @param newScore
     */
    public void add(T newScore) {
        if(newScore.compareTo(scores[scores.length - 1]) > 0) { // First, check if the element is larger than the current smallest element.
            int newScoreCurrentPosition = scores.length - 1;            // Used to keep track of current position of the new item.
            scores[newScoreCurrentPosition] = newScore;             // If it is, replace the smallest element with this one
            while(newScoreCurrentPosition > 0 && newScore.compareTo(scores[newScoreCurrentPosition - 1]) > 0) {                // Swaps the last element to the left until the sorting is correct.
                T temporaryVariableForSwapping = scores[newScoreCurrentPosition];
                scores[newScoreCurrentPosition] = scores[newScoreCurrentPosition - 1];
                scores[newScoreCurrentPosition - 1] = temporaryVariableForSwapping;
                newScoreCurrentPosition = newScoreCurrentPosition - 1;
            }
        }
    }
    @Override
    public String toString() {
        // do not change.
        String retVal = "";
        for (int i = 0; i < scores.length; i++) {
            retVal += (i+1) + ". " + scores[i].toString() + "\n";
        }
        return retVal;
    }

}
