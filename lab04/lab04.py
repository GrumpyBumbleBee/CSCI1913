# Lab 5
# Alexandra Postolaki (posto022)

# LAB5 has a substantial written Q&A component as well. You answer these questions by updating the
# multi-line strings below to indicate your answer. It's a bit hokey, but it works.

# Question 1: Which image file you submitted covers which analysis case?
Answer1 = '''
Image names are clear
'''

# Question 2: For each algorithm, explain how you see it behaving in your images.
# If the algorithm's behavior differed case-by-case say this and explain how it behaved in each case.

Answer2_insertion = '''
Random - insertion sort was second highest in increasing as the list length increased ("medium runtime" compared to the two).
near_sorted - insertion sort and merge sort are overlapping in the image and both lines increased almost by the 
            same amount relative to the increasing list length meaning that these two had a smaller runtime.
backwards - selection sort and insertion sort appeared to be overlapping in the image (approximately
            the same rate of increase relative to the increase of list length) (meaning it took more time to sort as list increased).
sorted_list - insertion sort and merge both increased slowly (faster runtime) and although the images dont show this,the
            data shows that the merge actually increased faster (took more time) than insertion sort. However, these two appear
            to be overlapping in the image as selection sort was very quick to increase / had a longer runtime.
'''

Answer2_selection = '''
Random - selection sort appeared to increase the most as the list length increased (higher runtime).
near_sorted - selection sort once again appeared to increase the most (higher runtime) as list length increased.
backwards - selection sort and insertion sort appeared to be overlapping in the image (approximately
            the same rate of increase in runtime relative to the increase of list length) (meaning it took more time to sort as list increased).
sorted_list - selection sort increased the most (higher runtime) as the list length increased.
'''

Answer2_merge = '''
Random - merge sort was very slow to increase (faster runtime). It stayed almost horizontal.
near_sorted - insertion sort and merge sort are overlapping in the image and both increased in runtime almost by the 
            same amount relative to the increasing list length.
backwards - merge sort has a small runtime (faster) compared to the other two as the list length increased.
sorted_list - insertion sort and merge both increased slowly (faster runtime) and although the images dont show this,the
            data shows that the merge actually increased faster (took more time) than insertion sort. However, these two appear
            to be overlapping in the image as selection sort was very quick to increase / had a longer runtime.
'''


# Question 3: For each algorithm, What is the theoretical expectation. I.E. what is the expected big-O runtime behavior.
# If the algorithm's expected behavior differs case-by-case say this and explain how it is
# expected to behave case-by-case. (You should be able to find this information in the textbook.
# If not we will discuss this in class)

Answer3_insertion = '''
O(n^2) in backwards order (worst case) and O(n) in best case (almost / already in order)
'''

Answer3_selection = '''
O(n^2) since it will always check every item whether or not it is already in order.
'''

Answer3_merge = '''
O(nLogn) for best and worse cases. 
'''


# Question 4: For each algorithm, How did the observed behavior match the theoretical behavior? Again, if your answer
# differs case by case, say that here and explain your findings for each case.

Answer4_insertion = '''
Insertion sort was not very fast as seen theoretically. As seen in the images, it was proven to be one of the 
algorithms that did end up taking longer time as the list length increased. When the list was sorted, it
was seen to reflect more O(n) and was relatively faster.
'''

Answer4_selection = '''
Selection sort was almost always the same for all four images (fairly slow) which is the same as the theoretical
behavior stated that it is O(n^2) pretty much all the time because selection sort will always check every
item whether or not it is already in order.
'''

Answer4_merge = '''
O(nlogn) is fast compared to O(n^2). Merge sort was seen to have the fasted runtime compared to the other sort
algorithms in each of the given cases. 
'''


# Question 5: Merge sort is theoretically the fastest algorithm, are there
# cases where another algorithm might be faster?

Answer5 = '''
As stated before, in the best case scenario insertion sort is fasted when the list is already sorted. In
these cases, it's at O(n) which is theoretically faster than merge's O(nlogn). 
'''


# Question 6: If you didn't know the order of data you might want to sort,
# what algorithm might you use to sort it, and why?

Answer6 = '''
Merge sort because even in the worst/best case, it is seen to run faster because the O(nlogn) stays the
same regardless of whether or not the list is sorted or backwards. Even seen in the four images, merge
was still seen to have a shorter runtime relative to the other algorithms as the list length increased.
'''


# Selection, Insertion, and Merge sorts -- taken from ZyBooks.
# Not too different, its still the same algorithm, they just did it using less memory than I did
# (Which leads to a slightly harder to understand piece of code)

def selection_sort(numbers):
    """Sort the list numbers in-place. (Note, this doesn't have to be numbers)"""
    comparison_count = 0
    for i in range(len(numbers) - 1):
        # Find index of smallest remaining element
        index_smallest = i
        for j in range(i + 1, len(numbers)):
            comparison_count += 1
            if numbers[j] < numbers[index_smallest]:
                index_smallest = j
        # Swap numbers[i] and numbers[index_smallest]
        temp = numbers[i]
        numbers[i] = numbers[index_smallest]
        numbers[index_smallest] = temp
    return comparison_count
def insertion_sort(numbers):
    """Sort the list numbers in-place. (Note, this doesn't have to be numbers)"""
    comparison_count = 0
    for i in range(1, len(numbers)):
        j = i
        # Insert numbers[i] into sorted part
        # stopping once numbers[i] in correct position
        # KLUVER NOTE - PLEASE READ - so this line is a bit tricky. Technically, if j > 0 then numbers would not be compared
        #               to make things easier you can assume that every time the list condition is checked one array element
        #               comparison occurs. That is -- you can ignore the short-circuit evaluation of the logical and in this
        #               counting problem.

        while j > 0 and numbers[j] < numbers[j - 1]:
            comparison_count += 1
            # Swap numbers[j] and numbers[j - 1]
            temp = numbers[j]
            numbers[j] = numbers[j - 1]
            numbers[j - 1] = temp
            j = j - 1
        comparison_count += 1
    return comparison_count
def merge(numbers, i, j, k):
    """ Given two sorted sub-lists create one sorted list. This is done in-place, meaning we are given one list
    and expected to modify the list to be sorted. Furthermore, this operates on "sub-lists" (a specific range of the list)
    The list named numbers may contain other types of data than just numbers
    the variables i, j, and k are all indices into the numbers list
    So the part of the list to be sorted is from position i to k (inclusive) with i to j being one sorted list, and j+1 to k being another."""
    merged_size = k - i + 1   #  Size of merged partition
    merged_numbers = []        #  Temporary list for merged numbers
    comparison_counter = 0
    for l in range(merged_size):
        merged_numbers.append(0)
    merge_pos = 0      #  Position to insert merged number

    left_pos = i       #  Initialize left partition position
    right_pos = j + 1  #  Initialize right partition position

    #  Add smallest element from left or right partition to merged numbers
    while left_pos <= j and right_pos <= k:
        comparison_counter += 1
        if numbers[left_pos] < numbers[right_pos]:
            merged_numbers[merge_pos] = numbers[left_pos]
            left_pos = left_pos + 1
        else:
            merged_numbers[merge_pos] = numbers[right_pos]
            right_pos = right_pos + 1
        merge_pos = merge_pos + 1

    #  If left partition is not empty, add remaining elements to merged numbers
    while left_pos <= j:
        merged_numbers[merge_pos] = numbers[left_pos]
        left_pos = left_pos + 1
        merge_pos = merge_pos + 1

    #  If right partition is not empty, add remaining elements to merged numbers
    while right_pos <= k:
        merged_numbers[merge_pos] = numbers[right_pos]
        right_pos = right_pos + 1
        merge_pos = merge_pos + 1

    #  Copy merge number back to numbers
    merge_pos = 0
    while merge_pos < merged_size:
        numbers[i + merge_pos] = merged_numbers[merge_pos]
        merge_pos = merge_pos + 1
    return comparison_counter


def merge_sort_recursive(numbers, i, k):
    """ Sort the sub-list in numbers from position i to k (inclusive)"""
    comparison_counter = 0
    if i < k:
        j = (i + k) // 2  #  Find the midpoint in the partition

        #  Recursively sort left and right partitions
        # KLUVER NOTE - PLEASE READ - you should think about these two function calls as returning a count of
        #     comparisons. Naturally the comparisons done by those function-calls will count against this function-call.
        #     make sure you're not ignoring the return values on the following two lines.
        comparison_counter += merge_sort_recursive(numbers, i, j)
        comparison_counter += merge_sort_recursive(numbers, j + 1, k)

        #  Merge left and right partition in sorted order
        comparison_counter += merge(numbers, i, j, k)
    return comparison_counter

def merge_sort(numbers):
    """ Sort a list of numbers

    This function is added on-top of the textbook's code to simply start the recursive process with the
    appropriate parameters. This also gives us a consistent sorting interface over the three sorts."""
    comparison_counter = 0
    comparison_counter += merge_sort_recursive(numbers, 0, len(numbers)-1)
    return comparison_counter
    #return merge_sort_recursive(numbers, 0, len(numbers)-1)

