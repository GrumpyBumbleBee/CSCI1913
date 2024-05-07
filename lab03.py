# Alexandra Postolaki (posto022)

def is_sorted(pricebook):
    """This function takes a pricebook (I.E. a list of tuples) and returns either True or False to indicate
    if the list is sorted."""
    if len(pricebook) == 0:  # If list is empty, return True
        return True
    for index_of_pricebook in range(len(pricebook) - 1):  # Loops through the pricebook indices. (Is that even the plural form of index??)
        if pricebook[index_of_pricebook][1] > pricebook[index_of_pricebook + 1][1]:  # Tests to see if the current product's name is alphabetically greater than the next product's name
            return False  # If it is, returns 'False' because list should be sorted in order a-->z instead of z-->a
    return True  # Otherwise it returns 'True' since list is ordered correctly.


def price_average(pricebook):
    """This function takes a pricebook (a list of tuples: sorted or unsorted) and
    returns the average price of all products in the pricebook."""
    if len(pricebook) == 0:  # If list is empty, returns 0.0
        return 0.0
    total_objects = 0  # Used to keep track of how many prices are given.
    sum_of_prices = 0  # Used to keep track of the sum of prices given.
    for index_of_pricebook in range(len(pricebook)):  # Loops through the pricebook indeces.
        total_objects += 1  # Amount of indeces represents each group / how many prices are given
        sum_of_prices += pricebook[index_of_pricebook][0]  # Accesses the price in each price,product group and adds it to the total sum
    average_of_prices = sum_of_prices / total_objects  # Calculating average (sum / # of objects)
    return average_of_prices  # ... Returns the average of the prices.


def unsorted_get(pricebook, name):
    """This function replicates the behavior of the 'dict.get(key)' method. It takes two parameters: an unsorted pricebook and
    a product's name. The function uses a linear search algorithm to check if the named product is in the pricebook. If the product
    is in the pricebook, the function returns its price. Otherwise, the special value None is returned."""
    for index_of_pricebook in range(len(pricebook)):  # Loops through the indeces of the pricebook
        if name == pricebook[index_of_pricebook][1]:  # Checks to see if the given name is found in any of the indeces (groups) in the pricebook
            return pricebook[index_of_pricebook][0]  # If it does, returns the correlating prices.
    return None  # When doesn't pass through 'if' statement in 'for' loop, returns None


def unsorted_put(pricebook, name, price):
    """This function replicated the behavior of the dictionary assignment statement: 'dict[key] = values'. It takes three parameters:
    an unsorted pricebook, the product's name, and the product's price. This function then updates the pricebook to record that
    product's new price (either updating an existing position in the list, or adding a new element to the list)."""
    product_exists = False  # Originally sets product_exists as 'False' because we don't know if it's in the pricebook list
    for index_of_pricebook in range(len(pricebook)):  # Loops through the indeces of the pricebook
        if name == pricebook[index_of_pricebook][1]:  # If the given product name is a product's name in the list,
            pricebook[index_of_pricebook] = (price, name)  # Updates the given product with its new given price.
            product_exists = True  # Given product is found in the pricebook (True)
    if product_exists == False:  # If the given product name wasn't given in the pricebook list, then it doesn't exist in pricebook.
        pricebook.append(tuple((price,name)))  # Adding the tuple (product price given, product name given) to the end of the pricebook list.


def sorted_get(pricebook, name):
    """This function replicated the behavior of the 'dict.get(key)' method. It takes two parameters: a sorted pricebook and a product's name.
    The function then uses a binary search to check if the product is in the pricebook. If the product is in the pricebook, the function
    returns their price. Otherwise, the special value None is returned."""
    lower_half_of_pricebook = 0                                     # Used for the lower half of the pricebook list for binary search
    higher_half_of_pricebook = len(pricebook) - 1                   # Used for the upper/higher half of the pricebook list for binary search
    while lower_half_of_pricebook <= higher_half_of_pricebook:      # Continues while the lower half
        middle_of_pricebook = (lower_half_of_pricebook+higher_half_of_pricebook)//2     # Accessing the middle location of the pricebook list
        if pricebook[middle_of_pricebook][1] < name:                                    # Checks if the middle is less than the given name of product to know which section (upper or lower) of list to look at for alphabetical comparison
            lower_half_of_pricebook = middle_of_pricebook + 1                           # If it is less, then the lower half of the pricebook list will be compared to the given name.
        elif pricebook[middle_of_pricebook][1] > name:                                  # Otherwise if it is more, then the higher half of the pricebook list will be compared to the given name.
            higher_half_of_pricebook = middle_of_pricebook - 1                          #
        else:
            return pricebook[middle_of_pricebook][0]                                    # Else if the product's name in the middle is equivalent to the name passed into the function. Returns the product's price.
    return None                                                                         # Otherwise, None is returned because the givne product name isn't found in the pricebook.
##########MAYBE FIX THIS????############

def sorted_put(pricebook, name, price):
    """This function replicates the behavior of the dictionary assignment statement: 'dict[key] = values'. It takes three parameters: a sorted
    pricebook, the product's name, and the product's price. This function should then update the pricebook to record that product's
    new price while carefully maintaining the sorted order. The sorted order here means sort by the key (the product name) instead of value (the product's
    price)."""
    found = False                           # Sets a variable to boolean 'False' if the product hasn't been found in the pricebook
    current_index = 0
    if len(pricebook) == 0:                 # This means that the pricebook list is emptayyy
        pricebook.append(tuple((price,name)))   # If it's empty, it takes the given product name and price and adds it to the
        found = True                            # Once the product name and price have been inserted, the product is now considered to be found.
    while found != True:                        # A loop that goes through while the product is NOT found in the list
        if name == pricebook[current_index][1]:     # If the given name is found in the beginning of the pricebook
            pricebook[current_index] = (price, name)        # Then the product's price is changed accordingly
            found = True                                    # And the product is considered 'found'
        elif name > pricebook[current_index][1] and current_index + 1 < len(pricebook): # Else if the name is greater than the product at the current index being looked at AND we haven't reached the end of the list
            current_index += 1                                      # Continues to increase index being compared to the given name.
        elif name > pricebook[current_index][1] and current_index + 1 == len(pricebook):    # If the given name is greater than the last product name in the list
            pricebook.append(tuple((price,name)))                                           # Appends the given name and product price to the very end of the pricebook list.
        elif name < pricebook[current_index][1]:                                            # If the given product name is less than the product name at the current index,
            pricebook.insert(current_index, tuple((price, name)))                           # Inserts the given product name and price into the current index and pushes original product name/price pair (found at that index) down the list.
            found = True                                                                    # The product is now found in the pricebook list.