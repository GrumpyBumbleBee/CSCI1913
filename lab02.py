# Alexandra Postolaki (posto022)

def create_game_state(size, token_max):
   """ This function takes two parameters, size and token_max, and returns a newly created
   list of integers representing the number of tokens in each row. The 'size' parameter
   sets the length of the newly created list, and the 'token_max' parameter controls the
   values. """
   state_of_game = []
   if size == 0:
       return state_of_game
   tokens = 1
   size_counter = 1
   if token_max < size:                                                         # In this case, tokens should start from 1 to token_max where it fills the rest of the length of the list with the token_max
       while tokens != token_max:                                                   # Has tokens reach the maximum amount of tokens.
           state_of_game.append(tokens)
           tokens += 1
           size_counter += 1
       while size_counter <= size:                                                  # Tokens is still at the maximum amount, this while loop fills in the rest of the list with the max token amount according to size of list.
           state_of_game.append(tokens)
           size_counter += 1
       return state_of_game
   elif token_max > size:                                                       # Otherwise, it should start from 1 and increase as much as possible according to how many "spaces" are left in the length of the list
       while size_counter <= size:                                                  # Continues to increase token amount which won't ever be the token_max because the size is smaller. Only increases token up to size of list.
           state_of_game.append(tokens)
           tokens += 1
           size_counter += 1
       return state_of_game
   else:                                                                        # Here, if token_max and size are the same number, then token amount should increase as the size of the list increases.
       while tokens <= token_max and size_counter <= size:
           state_of_game.append(tokens)
           tokens += 1
           size_counter += 1
       return state_of_game


def is_valid_move(game_state, row, takes):
   """ This function takes three parameters: 'game_state' is a list of numbers
   representing how many tokens are in each row, 'row' is a string representing which row the user
   has requested to take tokens from (starting from row number 1), and 'takes' is a string representing how many tokens the
   user wished to take. The function returns a boolean value: True (to indicate that user has
   chosen a valid move) or False (to indicate that the move would be invalid). """
   number_of_rows = len(game_state)
   if row.isdigit() and takes.isdigit():
       if int(row) > number_of_rows or int(row) <= 0:
           return False
       elif int(takes) < 1 or int(takes) > 3:
           return False
       if game_state[int(row)-1] == 0 or game_state[int(row)-1] < int(takes):   # Subtracts 1 from the index number because the given 'row' input is 1-indexed while here we want it to be 0-indexed.
            return False
       elif game_state[int(row)-1] >= int(takes):
            return True
   else:
       return False

def update(game_state, row, takes):
   """ This function takes three parameters: 'game_state' is a list of how many tokens there are in each row in a nim game.
   'row' is an integer representing which row the user has requested to take tokens from (rows starts at 1), and 'takes' is an
    integer representing how many tokens the user wishes to take. Returns a newly created list that represents the board after
    the listed number of tokens have been taken from the listed row. """
   game_state_copy = game_state.copy()                                      # Makes separate copy of the original list.
   updated_value =  game_state_copy[row] - takes
   game_state_copy[row] = updated_value                                     # Replaces the original value with the updated value obtained after subtracting the player's takes.
   return game_state_copy

def draw_game_state(game_state):
   """ This function takes one parameter: 'game_state' is a list of numbers which contains only non-negative
    numbers. This function has no return value but prints statements to produce an output directly to the terminal which is
    a depiction of the state of the game. """
   print("====================")
   for index in range(len(game_state)):
       row_number = index + 1
       number_of_pound_signs = game_state[index]
       pound_sign = '#'
       print(row_number, pound_sign * number_of_pound_signs)           # 'pound_sign * number_of_pound_signs' in the print statement makes the '#' string print (number_of_pound_signs) amount of times.
   print("====================")

def is_over(game_state):
    """ This function takes one parameter: 'game_state' which is a list of numbers which contains only non-negative
     numbers. Returns the boolean value 'True' if every value in the input list is 0 (meaning the game is over and 'False' if
     any value is not 0 (meaning the game is not over)."""
    if sum(game_state) == 0:                                                 # Finds the sum of all the numbers in the game_state list which would be 0 only if every number in the list is 0.
        return True
    else:
        return False