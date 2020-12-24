# Sorted-Circular-Doubly-Linked-Lists
# The SortedList interface extends the Java Iterable interface and provides the following methods:
#• public boolean add(E obj) – Adds a new element to the list in the right order The method
traverses the list, looking for the right position for obj.
#• public int size() - returns the number of elements in the list.
#• public boolean remove(E obj) – removes the first occurrence of obj from the list. Returns
true if erased, or false otherwise.
#• public boolean remove(int index) – removes the elements at position index. Returns true if
the element is erased, or an IndexOutBoundsException if index is illegal.
#• public int removeAll(E obj) – removes all copies of element obj, and returns the number
of copies erased.
#• public E first() – returns the first (smallest) element in the list, or null if the list is empty.
#• public E last() – returns the last (largest) element in the list, or null if the list is empty.
#• public E get(int index) - returns the elements at position index, or an
IndexOutBoundsException if index is illegal.
#• public void clear() – removes all elements in the list.
#• public boolean contains(E e) – returns true if the element e is in the list or false otherwise.
#• public boolean isEmpty() – returns true if the list is empty, or false otherwise.
#• public Iterator<E> iterator(int index) – Returns a forward iterator from position index, or
an IndexOutBoundsException if index is illegal.
#• public int firstIndex(E e) – returns the index (position) of the first position of element e in
the list or -1 if the element is not present.
#• public int lastIndex(E e) - returns the index (position) of the last position of element e in
the list or -1 if the element is not present.
#• public ReverseIterator<E> reverseIterator() – returns a reverse iterator, starting from the
last element in the list.
#• public ReverseIterator<E> reverseIterator(int index) – returns a reverse iterator, starting
from position index in the list, or an IndexOutBoundsException if index is illegal.
Reverse Iterator
The reverse iterator provides the mechanism to traverse the list backwards, from either the last element, or an element at a given position. The methods in the interface are:
#• public boolean hasPrevious() – returns true if the iterator has a previous element to give, or false otherwise (i.e., we reached the header noder).
#• public E previous() – the return the next previous element, and moves the iterator backward one position.
