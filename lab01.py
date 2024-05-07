# CSCI 1913, Spring 2023, Lab 1
# Student Names:
# Alexandra Postolaki (posto022)

#TODO: INSTRUCTIONS:
# 1. Fill in your name and your partners name at the top
# 2. Look over the general structure of the file. You'll want to know this structure for future labs.
#     * At the top you will always be required to list your name, and the name of the other student you work with in
#       the lab and any other resources you wish to disclose using.
#     * Then python functions. Each of which has a "docstring" (a """string""" on it's first line(s))
#       documenting the function, often this should list at least what the function does, it's inputs, and it's outputs.
#     * Then at the end the "main" if statement -- which is used for any personal testing you want -- and can often be
#       deleted before submission.
# 3. Look over the provided main code provided at the bottom You'll want to understand what this code uses and how you
#    could use it. Don't be afraid to edit it and "play around" with it.
# 4. Fill in the two provided functions
# 5. Write a new function for kluver_cat_name You will find Kluver's cat name on slack (Don't just ask other people. The
#      whole point of this is to make sure you know your way around the important resource that is slack!)


def digits(number):
    """ This function is meant to be used in a loop like so:
      for digit in digits(1031):
        print(digit)
      # prints 1 then 3 then 0 then 1
      The code is implemented with some features we won't cover in lecture. Don't worry about how it works.
      Just focus on how to use this function.
    """

    # The above is called a "docstring" -- it's a special string always placed on the first line of a function
    # Docstrings are used to document how a function works or is meant to be used. This (documentation) is an important
    # part of programming. The format and location is very important as it allows tools to automatically extract this
    # info and show it to you as you program. You can even ask python about these, see "main" for an example.

    while number > 0:
        yield number % 10
        number = number // 10


def check_isbn13(isbn):
    """ This function takes a number and returns True if the number is a valid ISBN13 number.
        If given a number that is not a valid ISBN 13 number it will return False."""
    sum_even = 0
    sum_odd = 0
    if isbn < 0 or len(str(isbn)) > 13:                                                            # Checks and returns 'False' if given number is negative or contains more than 13 digits
        return False
    elif len(str(isbn)) == 13:                                                                     # (If given number is 13 digits long)
        for i in range(len(str(isbn))):                                                                 # Loops through the digits of the given number.
            if i % 2 == 0:                       # NOTE: if index ('i') of the number is even, it correlates to an odd position since index starts at 0, but first digit found at position 1
                sum_odd = sum_odd + int(str(isbn)[i])                                                       # Adds all digits in odd positions.
            else:
                sum_even = sum_even + int(str(isbn)[i])                                                     # Adds all digits in even positions.
        total = sum_odd + (3 * sum_even)                                                           # Calculates total
        if total % 10 == 0:
            return True                                                                            # Returns boolean 'True' if total is divisible by 10 (is a valid isbn number)
        else:
            return False                                                                           # Returns boolean 'False' if total is not divisible by 10.
    else:                                                                                          # The length of given isbn number is < 13 but > 0:
        max_length_of_given_isbn = 0                                                                    # Used to find the length of given number.
        if len(str(isbn)) > 1:                                                                           # Checks if the isbn given is greater than 1.
            for i in range(len(str(isbn))):                                                                  # Loops through the digits of the given number.
                max_length_of_given_isbn += 1
            for j in range(len(str(isbn)[max_length_of_given_isbn]), len(str(isbn)[0]), -1):       # Loops / reads each digits in the given number from right to left for numbers less than 13 digits long.
                if j % 2 == 0:
                    sum_odd = sum_odd + int(str(isbn)[j])                                               # Adds all digits in odd positions of the number (from right-to-left)
                else:
                    sum_even = sum_even + int(str(isbn)[j])                                             # Adds the digits in even positions of the number (from right-to-left)
        else:                                                                                      # Here, only one-digit numbers pass through.

            sum_odd = int(str(isbn)[0])                                                            # sum_odd would just be the one digit given (since it's in the tens place)
        total = sum_odd + (3 * sum_even)                                                           # Calculates total
        if total % 10 == 0:
            return True                                                                            # Returns boolean 'True' if total is divisible by 10 (is a valid isbn number)
        else:
            return False                                                                           # Returns boolean 'False' if total is not divisible by 10.

def make_isbn13(book_number):
    """ Takes a 12 (or less) digit number and returns a valid isbn13 integer starting with those 12 digits.
        This can be used to take a book number and create an ISBN13 to store that number. Returns -1 if the
        given number cannot be converted into a valid ISBN13 number (if number is negative or contains 13 or more digits."""
    sum_even = 0
    sum_odd = 0
    last_digit_to_add_to_make_valid_isbn = 0
    if len(str(book_number)) >= 13 or book_number < 0:                                             # Checks and returns -1 if given number is negative or contains 13 or more digits
        return -1
    elif len(str(book_number)) % 2 == 0:                                                           # Checks if the book_number length is even.
        for i in range(len(str(book_number))):                                                     # Goes through indeces of each digit in the given book_number.
            if i % 2 == 0:                      # NOTE: if index ('i') of the number is even, it correlates to an odd position since index starts at 0, but first digit found at position 1
                sum_odd = sum_odd + int(str(book_number)[i])                                            # Adds all digits in odd positions of the book_number
            else:
                sum_even = sum_even + int(str(book_number)[i])                                          # Adds all digits in even positions of the book_number
        while((sum_odd + last_digit_to_add_to_make_valid_isbn) + (3 * sum_even)) % 10 != 0:        # Loops through until tester variable has the value that satisfied the formula for a valid ISBN
            last_digit_to_add_to_make_valid_isbn = last_digit_to_add_to_make_valid_isbn + 1
        valid_isbn = str(book_number) + str(last_digit_to_add_to_make_valid_isbn)                  # Concatenates given book_number first with the digit found that forms a valid ISBN13
        return int(valid_isbn)                                                                     # Returns an integer of the valid ISBN13 created
    else:                                                                                          # From previous if's and elif's, all book_numbers that are odd in length pass through here.
        if len(str(book_number)) == 1:
            sum_even = int(str(book_number)[0])                                                    # sum_even is instantly made to be the given digit if boom_number is just one digit long.
            while ((sum_odd + last_digit_to_add_to_make_valid_isbn) + (3 * sum_even)) % 10 != 0:   # Loops through until tester variable has the value that satisfied the formula for a valid ISBN
                last_digit_to_add_to_make_valid_isbn += 1
            valid_isbn = str(book_number) + str(last_digit_to_add_to_make_valid_isbn)              # Concatenates the given book_number first with the found digit that converts the number to a valid ISBN
            return int(valid_isbn)                                                                 # Returns an integer of the valid ISBN13 created
        for i in range(len(str(book_number))):                                                     # Loops through indeces of each digit in the given book_number.
            if i % 2 == 0:
                sum_even = sum_even + int(str(book_number)[i])                                              # Adds all digits in even positions
            else:
                sum_odd = sum_odd + int(str(book_number)[i])                                                # Adds all digits in odd positions
        while (sum_odd + (3 * (sum_even + last_digit_to_add_to_make_valid_isbn))) % 10 != 0:       # Loops through until tester variable has the value that satisfied the formula for a valid ISBN
            last_digit_to_add_to_make_valid_isbn += 1
        valid_isbn = str(book_number) + str(last_digit_to_add_to_make_valid_isbn)                  # Concatenates the given book_number first with the found digit that converts the number to a valid ISBN
        return int(valid_isbn)                                                                     # Returns an integer of the valid ISBN13 created.

def kluver_cat_name():
    """ Takes no input and returns the name of Professor Kluver's amazingly beautiful cat named Willow
        (case-sensitive as a name should be) """
    return "Willow"                                                                                 # Returns "Willow"

if __name__ == "__main__":
    # The above if statement is a weird piece of python magic.
    # It's meaning translates roughly as "if we're running this file as a program" instead of "if the file is being run
    # by other files, like the autograder or test code.
    # This code _won't_ run when we test your file. So it's a good place to put any tests or scratch code of your own.

    # In this case, you will want to start by playing around with the following loop, and making sure you understand it,
    #  and how it can be used in the program you'll need to write.
    for digit in digits(10314):
        print(digit)
    # TODO: Think about what loop this creates, what values digit takes, and in which order.
            # As stated in the docstring for the digits() function above, this should print 4 then 1 then 3 then 0 then 1 which could be used in the code (although I chose to use range and
            # step by -1 for practice and funsies) when reading numbers that are less than 13 digits from right-to-left.

    # And as an extra: an example of why docstring formatting is important!
    help(digits) # print the docstring (if it's correctly formatted) for the digits function
    # You can use the help function with any function, but it will only be useful if the author added a good docstring!

