/*
 * CSCI 1913
 * Lab 06
 * Author: Alexandra Postolaki (posto022)
 * */


public class NIM {

    /**
     * cerate a game state array
     * @param size -- the number of rows
     * @param tokenMax -- the largest value
     * @return an array representing a token array. The array in the first position will be one, each following will be
     *     one larger up to the max.
     */
    public static int[] createGameState(int size, int tokenMax){
        /**This function takes two parameters, size and tokenMax, and returns a newly created
         list of integers representing the number of tokens in each row. The 'size' parameter
         sets the length of the newly created list, and the 'tokenMax' parameter controls the
         values.**/
        int[] stateOfGame = new int[size];              // Creates an int array for stateOfGame which is the length of 'size' (given as input)
        if(size == 0){                                  // If the size given is 0
            return stateOfGame;                         // Returns an array (stateOfGame) of length 0
        }
        int tokens = 1;                                 // sets 'tokens' to 1 (will be used to compare to tokenMax input)
        int sizeCounter = 0;                            // Used to count the size of list (where it's at - like index- so it doesn't go beyond the given size).
        if(tokenMax < size){                            // If tokenMax is less than the size of the array
            while(tokens != tokenMax) {                 // while the tokens aren't equal to the tokenMax amount,
                stateOfGame[sizeCounter] = tokens;      // then the stateOfGame array "appends" the tokens at the index equivalent to sizeCounter (where ever sizeCounter is currently at)
                tokens += 1;                            // Increment tokens
                sizeCounter += 1;                       // Increment sizeCounter (up one)
            }
            while(sizeCounter+1 <= size) {              // While the sizeCounter less than or equal to size (plus 1 since we started at 0 and size is the length starting at 1)
                stateOfGame[sizeCounter] = tokens;      // continues to append 'tokens' to the stateOfGame array at given sizeCounter indeces
                sizeCounter += 1;                       // increments sizeCounter
            }
            return stateOfGame;
        }
        else if(tokenMax > size){                       // Otherwise, if tokenMax is greater than the size of the stateOfGame array
            while(sizeCounter+1 <= size){               // While the sizeCounter less than or equal to size (plus 1 since we started at 0 and size is the length starting at 1)
                stateOfGame[sizeCounter] = tokens;      // 'appends' tokens at the given sizeCounter index of stateOfGame array
                tokens += 1;                            // Increments tokens
                sizeCounter += 1;                       // Increments sizeCounter (index)
            }
            return stateOfGame;
        }
        else{                                                                   // Else,
            while(tokens <= tokenMax && sizeCounter + 1 <= size){               // While the tokens is less than and/or equal to tokenMax AND the sizeCounter (+1) is less than or equal to the given size,
                stateOfGame[sizeCounter] = tokens;                              // 'append' the tokens to the stateOfGame array at index represented by sizeCounter.
                tokens += 1;                            // Increment tokens by 1 until reaches tokenMax after each loop
                sizeCounter += 1;                       // Increment sizeCounter by 1 until reaches size after each loop
            }
            return stateOfGame;
        }
    }

    /**
     * This provided function operators similarly to the python isDigit method. You give it a string and it will tell
     * you if the string contains only digits. Give it a read -- the actual design isn't hard, basically a linear search.
     * @param str any string
     * @return true if all letters in the string are digits.
     */
    private static boolean isDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a given move (as represented by two input strings) is valid. This should check if the inputs are numbers
     * if those numbers are in the valid range.
     * @param gameState an array representing the number of tokens in each row.
     * @param row a string representing which row the user wants to take from. 1-indexed.
     * @param takes a string representing how many tokens the user wants to take.
     * @return true if and only if the move would be valid
     */
    public static boolean isValidMove(int[] gameState, String row, String takes) {
        /**This function takes three parameters: 'gameState' is a list of numbers
         representing how many tokens are in each row, 'row' is a string representing which row the user
         has requested to take tokens from (starting from row number 1), and 'takes' is a string representing how many tokens the
         user wished to take. The function returns a boolean value: True (to indicate that user has
         chosen a valid move) or False (to indicate that the move would be invalid).**/
        int numberOfRows = gameState.length;                    // Finds gameState length and has numberOfRows variable point to it.
        if(isDigit(row) && isDigit(takes)) {                    // Tests if the given row and takes are digits.
            int intRow = Integer.parseInt(row);                 // Changes the given row to an int and has variable intRow point to it.
            int intTakes = Integer.parseInt(takes);             // ^ Does the same for takes but has intTakes point to the int takes.
            if (intRow > numberOfRows | intRow <= 0) {          // If the (int) row is greater than the numberOfRows (gameState length) OR the (int) row is less than or equal to 0
                return false;                                   // Returns boolean false.
            } else if (intTakes < 1 | intTakes > 3) {           // Otherwise if the (int) takes is less than 1 OR greater than 3
                return false;                                   // Returns boolean false.
            }
            if (gameState[intRow - 1] == 0 | gameState[intRow - 1] < intTakes) {        // Subtracts 1 from the index number because the given 'row' input is 1-indexed while here we want it to be 0-indexed.
                return false;                                                           // returns false
            }
            else if (gameState[intRow - 1] >= intTakes) {                               // Also subtracts 1 from the index number because the given 'row' input is 1-indexed while here we want it to be 0-indexed.
                return true;                                                            // Returns true because it's a valid move to have a number at a given row be subtracted from if the number being subtracted from it is less or equivalent to it.
            }
        }
        return false;                                                           // Otherwise returns boolean false.
    }
    /**
     * User System.out.println to represent a token grid to the program user.
     * @param gameState an array representing the number of tokens in each row.
     */
    public static void drawGameState(int[] gameState){
        /**This function takes one parameter: 'gameState' is a list of numbers which contains only non-negative
         numbers. This function has no return value but prints statements to produce an output directly to the terminal which is
         a depiction of the state of the game. **/
        System.out.println("====================");
        for(int index = 0; index < gameState.length; index++){                      // Uses for loop to go through the gameState array length by indeces.
            int rowNumber = index + 1;                                              // Has variable rowNumber point to an int represented by the index + 1
            int numberOfPoundSigns = gameState[index];                              // Has variable numberOfPoundSigns point to an int represented by gameState[index] which gives amount of pound signs needed to print for the tokens
            String poundSign = "#";
            System.out.println(rowNumber+ " " + poundSign.repeat(numberOfPoundSigns));  // Uses .repeat method to repeatedly print a certain amount of pound signs for each token to draw the state of the game
        }
        System.out.println("====================");
    }
    /**
     * Create an updated version of the game state. You can assume the row and takes are valid for the gameState array provided.
     * @param gameState an array representing the number of tokens in each row.
     * @param row the row the user wants to take from (0-indexed)
     * @param takes the number of tokens the user wants to take
     * @return a new array representing the state number of tokens in each row after the given numbers were removed.
     */
    public static int[] update(int[] gameState, int row, int takes){
        /** This function takes three parameters: 'gameState' is a list of how many tokens there are in each row in a nim game.
         'row' is an integer representing which row the user has requested to take tokens from (rows starts at 1), and 'takes' is an
         integer representing how many tokens the user wishes to take. Returns a newly created list that represents the board after
         the listed number of tokens have been taken from the listed row. **/

        int gameStateCopy[] = new int[gameState.length];        // Make a "copy" of gameState (really just makes a new array called gameStateCopy that's the same size as the gameState array
        for(int i = 0; i < gameState.length; i++){              // Adding in all numbers from gameState to the copy using for loop
            gameStateCopy[i] = gameState[i];
        }
        int updatedValue = gameStateCopy[row] - takes;          // updatedValue is used to point to the int where tokens at specified row in gameStateCopy are removed
        gameStateCopy[row] = updatedValue;                      // Updates the gameStateCopy array at specified row (input row given by user) with the updatedValue
        return gameStateCopy;                                   // returns the updated version of gameStateCopy
    }

    /**
     *
     * @param gameState an array representing the number of tokens in each row.
     * @return true if and only if every element of gameState is false.
     */
    public static boolean isOver(int[] gameState) {
       /** This function takes one parameter: 'gameState' which is a list of numbers which contains only non-negative
     numbers. Returns the boolean value 'True' if every value in the input list is 0 (meaning the game is over and 'False' if
     any value is not 0 (meaning the game is not over). **/
        int sum = 0;
        for(int i = 0; i < gameState.length; i++){          // Goes through the length of gameState array starting at index 0
            sum += gameState[i];                            // Calculates the sum of each tokens in each row (index represented by i)
        }
        if(sum == 0){                                       // If the sum of all the rows is 0,
            return true;                                    // returns boolean true meaning the game is, in fact, over
        }
        else{
            return false;                                   // Otherwise it returns false meaning the game is,in fact, not over... bet you didn't see that coming!
        }
    }
}