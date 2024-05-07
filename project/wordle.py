from words import words
from display_utility import green, yellow, grey
import random

# By: posto022
"""secret - a string, the secret word. Assume all uppercase and 5 letters long
   guess - a string, the guessed word. Assume all uppercase and 5 letters long
   clues - a list of tuples, with each tuple being a guess (string) and the clues returned (a list of strings, see check_word). EXAMPLE: [(guess, clue), (guess2, clue2), (guess3, clue3),...]"""
def check_word(secret, guess):
    """This functions takes a word and generates the output clue. First input is the secret word for the Wordle game
    and second input is the guess input by the user. Output will be a length-5 list containing the strings "grey", "yellow", and "green". """
    hint_list = ['grey'] * 5                                      # Create list that will be returned
    secret = secret.lower()                             # Turn secret word to all lowercase
    guess = guess.lower()                               # Turn guessed word to all lowercase (just in case although it wasn't specified in the project outline)
    marked_letter = ''                                  # Used to prevent situation where guessed words has 2+ duplicate letters while secret only has one of that letter.

    for letterIndex in range(0,5):                      # Goes through the range of the word from index 0 to 4 (length of word must be 5)
        how_many_of_same_letter_in_guess_count = guess.count(guess[letterIndex])                # Used to check if there are multiples of the same letter in the guessed word
        how_many_of_same_letter_in_secret_count = secret.count(guess[letterIndex])              # Used to check if there are multiples of the same letter in the secret word
        if guess[letterIndex] == secret[letterIndex]:   # If the guess letter (at a given index) is equivalent to the placement of the same letter in the secret word
            hint_list[letterIndex] = 'green'                      # Appends 'green' to the hint_list\
            if how_many_of_same_letter_in_guess_count > how_many_of_same_letter_in_secret_count:            # Used to prevent situation where there are more duplicated letters in the guessed word than there are in the secret word.
                marked_letter += guess[letterIndex]                                                 # WAS marked_letter = guess[letterIndex]           # Marks the letter because green takes priority marking.
    for letterIndex in range(0,5):                                      # Goes through the range of the guessed word ('index by index' style)
        if guess[letterIndex] in secret and hint_list[letterIndex] == 'grey' and guess[letterIndex] not in marked_letter:       # WAS guess[letterIndex] != markedLetter       # Otherwise if the letter at the given index in guess list is simply in the secret word,
            hint_list[letterIndex] = 'yellow'                                                                                       # Appends 'yellow'
            if guess.count(guess[letterIndex]) > 1:                             # if guess.count(guess[letterIndex]) > 1, then there aren't any repeats of the current letter being looked at.
                marked_letter += guess[letterIndex]     #WAS marked_letter = guess[letterIndex]                              # marks letter to prevent situation where there are more duplicated letters in the guessed word than there are in the secret word.
    return hint_list


def update_known(current_known, guess_and_clue):
    """ Helper function for known_word(). This function takes in the current_known string and a tuple from given clues and
    adds to the string if the tuple contains another letter that is green in a certain location. The string, after being updated, is then
    returned."""
    updated_known_string = ""
    updated_known_list = []
    updated_known_list[:0] = current_known                  # Creates a list from the current known string. (Each letter/key in the string is added as a separate item in the list).
    for index, color in enumerate(guess_and_clue[1]):
        if color == 'green':
            updated_known_list[index] = guess_and_clue[0][index]            # Updates the list at the index correlating to where 'green' is found in the tuple's list's index.
    for item in updated_known_list:
        updated_known_string += item                            # Readds all of the items in the list to the updated string to be returned.
    return updated_known_string

def known_word(clues):
    """ This function takes in a list containing a record of guesses taken and clues received so far. It outputs a string indicating
    what we know about the secret word according to green hints seen so far."""
    known_string = "_____"
    if clues == []:
        return known_string
    else:
        for tup_group in clues:
            if 'green' in tup_group[1]:
                known_string = update_known(known_string, tup_group)
    return known_string
def no_letter_assembly_line(current_known, guess_and_clue):
    """ Helper function for the no_letters() function. Takes in the current known string and the tuple groups from clues passed in. Returns an updated string representing
     the letters that were not in the secret word given the guess and the current known clues."""
    updated_known_string = ""
    updated_known_list = []
    updated_known_list[:0] = current_known
    marked_equivalent_letter_and_place = ""
    for index, color in enumerate(guess_and_clue[1]):
        if color == 'green' or color == 'yellow':
            marked_equivalent_letter_and_place += guess_and_clue[0][index]
    for index, color in enumerate(guess_and_clue[1]):
        if color == 'grey' and guess_and_clue[0][index] not in updated_known_list and guess_and_clue[0][index] not in marked_equivalent_letter_and_place:
            updated_known_list.append(guess_and_clue[0][index])
    updated_known_list_sorted = sorted(updated_known_list)
    for item in updated_known_list_sorted:
        updated_known_string += item
    return updated_known_string
def no_letters(clues):
    """ This function takes in a list containing a record of guesses taken and clues received so far. The output of this function is a string
    indicating which letters we know are not in the word according to grey hints seen so far. """
    known_string = ""
    if clues == []:
        return known_string
    else:
        for tup_groups in clues:
            if 'grey' in tup_groups[1]:
                known_string = no_letter_assembly_line(known_string, tup_groups)
    return known_string

def yes_letters(clues):
    """ This funciton takes in a list containing record of guesses taken and clues received so far. It outputs a string indicating what letters are
    in the word based on yellow and green hints we've seen so far."""
    # The output should only show each letter once no matter how many times it has been guessed.
    # The output should be in alphabetical order.
    # The output should be all caps.
    known_string = ""
    known_letter_list = []
    if clues == []:
        return known_string
    else:
        for tup_group in clues:                                             # Goes through each 'group'/tup_group  in clues
            if 'green' in tup_group[1] or 'yellow' in tup_group[1]:
                for index, color in enumerate(tup_group[1]):
                    if color == 'green' and tup_group[0][index] not in known_letter_list:       # Adds letters found in the matching location and in both secret and guessed word based off of the clues.
                        known_letter_list.append(tup_group[0][index])
                    elif color == 'yellow' and tup_group[0][index] not in known_letter_list:        # Adds letters found in both secret and guessed word based off of the clues.
                        known_letter_list.append(tup_group[0][index])
        known_letter_list_sorted = sorted(known_letter_list)
        for item in known_letter_list_sorted:       # Here the list is sorted (line above) and adds each item from the sorted list of letters in both the secret word and guessed word (according to the clues given)
            known_string += item
        return known_string

if __name__ == "__main__":
    """ Implements a playable Wordle game. Starts by randomly selecting a secret word from a provided word list. Asks user to input
    a valid word to guess the secret word. User gets 6 guesses until the game ends. Once the game has ended (either the user's guesses are used up or the user has
    guessed the correct word), the answer will be revealed. After each guess, a complete record of the guesses and clues seen so far will be printed. """
    chosen_random_secret_word = random.choice(words)                                                    # Starts by randomly selecting a word from provided word list in secret.
    clues = []
    words_guessed_list = []
    word_is_guessed = False                                                                            # Used to see if the word is guessed before all 6 guesses are used
    while len(words_guessed_list) < 6 or word_is_guessed == True:
        print("Known: ", known_word(clues))                                                         # Uses known_word() function to print out the string of known letters and their placement based on the clues passed in.
        print("Green/Yellow Letters: ", yes_letters(clues))                                         # Uses yes_letters() function to print out the letters that are green or yellow (letters found both in the guessed and secret word) according to the clues passed in.
        print("Grey Letters: ", no_letters(clues))                                                  # Uses no_letter() funciton to print out the letters that are grey (letters that are found in the guessed word, but not in the secret word) according to the clues passed in.
        player_guess = input("> ")          # Takes in the user's input/guesses
        player_guess = player_guess.lower()     # Turns user's guess to lowercase
        while player_guess not in words or len(player_guess) != 5:                  # Checks for invalid guesses (user input is not found in the words list or is not a 5 lettered word
            player_guess = input("> ")                                                  # If invalid input, continues to ask for another input
        if player_guess in words and len(player_guess) == 5 and player_guess not in words_guessed_list:    # And check if the word is not already in words_guessed list
            words_guessed_list.append(player_guess)                         # Adds each guessed word to a list if the guess is valid and the guessed word hasn't already been guessed.
        for guessed_word in words_guessed_list:                             # Goes through the list of words already guessed
            guessed_word = guessed_word.upper()                             # Uppercases the current guessed word being looked at
            guessed_word_and_clue_tuple = ()                                # creates empty tuple
            clue_of_guessed_word = check_word(chosen_random_secret_word, guessed_word)     # clue_of_guessed_word now contains the clue list between the guessed word and the secret word
            guessed_word_and_clue_tuple = (guessed_word, clue_of_guessed_word)      # Creates a new tuple containing the guessed word and the clue list between the guessed word and the secret word
            if guessed_word_and_clue_tuple not in clues:        # If the tuple is not already in the clues list (the list containing all the tuples of guessed words and corresponding clue list)
                clues.append(guessed_word_and_clue_tuple)       # Then the tuple is added to the list containing all clues from all the guessed words.
            for letter in guessed_word:                                                 # Goes through each letter in the guessed_word
                if letter in yes_letters(clues) and letter in known_word(clues):        # If in both, then the letter should be green
                    green(letter)                                                               # prints the letter in green
                elif letter in yes_letters(clues) and letter not in known_word(clues):      # If only in the yes_letters, but it isn't a "known" letter then it is yellow
                    yellow(letter)                                                              # prints the letter in yellow
                else:                                                                       # Otherwise the letter is printed in grey
                    grey(letter)
            print()
            if player_guess == chosen_random_secret_word:
                word_is_guessed = True                                      # Causes While loop to end (and end game) since the word has been guessed before using all 6 guesses.
    print("Answer: ", chosen_random_secret_word)                            # After player guesses 6 times, the secret word is revealed