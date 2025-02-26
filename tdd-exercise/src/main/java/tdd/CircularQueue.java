package tdd;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a **fixed** capacity.
 *  When full, new elements overwrite the oldest ones.
 *  
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 *  
 *  For the exercise: 
 *   - Think about the test cases you need to write.
 *   - Introduce methods in the interface in order to make the tests pass.
 *   - Refactor
 */
public interface CircularQueue {

    /**
     * Gets the capacity of the CircularQueue.
     *
     * @return The capacity of the CircularQueue.
     */
    int capacity();

    /**
     * Checks if the CircularQueue is full.
     *
     * @return true if the CircularQueue is full, false otherwise.
     */
    boolean isFull();

    /**
     * Checks if the CircularQueue is empty.
     *
     * @return true if the CircularQueue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Enqueue an integer, if the CircularQueue is full it overwrites the oldest one.
     *
     * @param value The integer to enqueue.
     */
    void enqueue(int value);

    /**
     * Retrieves, but does not remove, the oldest element of the CircularQueue.
     *
     * @return The oldest element of the CircularQueue.
     * @throws IllegalStateException if the CircularQueue is empty.
     */
    int peek();

    /**
     * Dequeue and returns the oldest element of the CircularQueue.
     *
     * @return The dequeued element.
     * @throws IllegalStateException if the CircularQueue is empty.
     */
    int dequeue();

    /**
     * Gets the actual number of elements inside the CircularQueue.
     *
     * @return The actual number of elements inside the CircularQueue.
     */
    int size();

}