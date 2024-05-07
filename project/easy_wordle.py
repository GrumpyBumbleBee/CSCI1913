from wordle import check_word, known_word, no_letters, yes_letters
from words import words
from display_utility import green, yellow, grey
import random

# By: posto022 Alexandra Postolaki
def filter_word_list(words, clues):
    """ This function takes a list of words and the clue list format and returns a new word list
    containing only the words in the input word list which could be the secret word."""
    all_secret_words = []
    approved_possible_secret_word_list = []
    number_of_guess_words = 0
    for group in clues:                                                           # Counts how many guess words are present.
        number_of_guess_words += 1
    if clues == []:                                                                     # If clues list is empty, return a word list with all the possible secret words in it.
        for word in words:
            all_secret_words.append(word)
        return all_secret_words
    for word in words:                                                                      # Appends all the words that could be the possible secret word according to each of the 'guess' words passed in in the clues list
        word = word.upper()                                                                 # Upper cases the word used to see if it's a possible secret word
        for group in clues:                                                                     # Loops through each group (guess word and it's corresponding clues) in clues
            clues_from_check_word = check_word(word, group[0])                                  # Uses check_word function to get the clue list created between the possible secret word and the guess word passed in
            if clues_from_check_word == group[1]:                                               # If the clue list between the possible secret word and the guess word's clue list are the same,
                all_secret_words.append(word.lower())                                    # The possible secret word IS a possible secret word for the current guess word being looked at and is appended to the all_secret_words (and is made to be all lowercase)
    for possible_secret_words in all_secret_words:
        how_many_times_possible_secret_word_appears_in_returning_word_list = all_secret_words.count(possible_secret_words)       # If a possible secret word appears in the list the same number of times as there are guessed words (meaning that word was approved as a ppossible secret
                                                                                                                                    # word by all guessed words, then that possible secret word gets appended to the returning approved_possible_secret_word_list if it hasn't been already.
        if how_many_times_possible_secret_word_appears_in_returning_word_list == number_of_guess_words and possible_secret_words not in approved_possible_secret_word_list:
            approved_possible_secret_word_list.append(possible_secret_words)
    return approved_possible_secret_word_list                                                              # Returns the returning word list which now should contain only the words that passed as the possible secret words for all of the guessed words
if __name__ == "__main__":
    """ Implements a playable Wordle game. After each accepted guess (and its clues), the number of possible words is shown
    and up to 5 random possible words. If fewer than 5 words are possible, all words are shown. If more than 5, only 5 are shown. These
    possible words are chosen at random. """
    chosen_random_secret_word = random.choice(words)                                                    # Starts by randomly selecting a word from provided word list in secret.
    clues = []
    words_guessed_list = []
    word_is_guessed = False                                                                            # Used to see if the word is guessed before all 6 guesses are used
    while len(words_guessed_list) < 6 or word_is_guessed == False:
        player_guess = input("> ")                              # Gets user's input
        player_guess = player_guess.lower()                     # Makes user guess all lowercase
        while player_guess not in words or len(player_guess) != 5:          # Checks for invalid inputs
            player_guess = input("> ")                                          # while user's input is invalid, keeps asking for input
        if player_guess in words and len(player_guess) == 5 and player_guess not in words_guessed_list:    # And check if the word is not already in words_guessed list
            words_guessed_list.append(player_guess)
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
        if len(words_guessed_list) <= 6:
            result = filter_word_list(words, clues)             # results is the resulting possible word choices available
            number_words_possible = len(result)
            print(number_words_possible, "words possible:")
            if number_words_possible < 5:                       # If the result list of possible words is less than 5
                for possible_words in result:                   # prints all of the possible words in the list
                    print(possible_words)
            else:
                for i in range(0,5):                            # Otherwise, prints out only the first 5 words of the possible words list
                    print(result[i])
        if player_guess == chosen_random_secret_word:       # If the player's guess is the chosen word,
            word_is_guessed = True                                      # Causes While loop to end (and end game) since the word has been guessed before using all 6 guesses.
    print("Answer: ", chosen_random_secret_word)                            # After player guesses 6 times, the secret word is revealed
