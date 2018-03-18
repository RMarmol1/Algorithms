/* CMPT 435
 * Project 2 Final
 * Filename: ArrayQueue.java
 * Student name: Rafael Marmol
 *
 * WordMelt program showing fastest solution from start word to end word using Breadth-First-Search
 *
 * Class: ArrayQueue
 */
 
/* An ArrayQueue is a queue based on an array. The array is a circular array.
 * The queue should grow dynamically if it gets full (it should double in
 * capacity each time it gets full).
 *
 * The default constructor creates an ArrayQueue that is empty but has some
 * capacity > 0. The copy constructor creates a deep copy of the given
 * ArrayQueue object. This means that it gets its own deep copy of the data.
 *
 * The add method adds an element to the back of the queue. The remove method
 * removes one item from the front of the queue. These methods should not move
 * any data already in the queue. The getFront method returns the item at the
 * front of the queue.
 *
 * The getLength function returns the length of the queue. If the length is 0,
 * the queue is considered to be empty.
 *
 * The copyFrom method first checks to see if the queue we are assigning to is
 * the same as this, and if not, makes a deep copy of the given queue.
 *
 * The doubleCapacity method doubles the capacity of the ArrayQueue, and updates
 * the data members so they are now valid for the newly allocated array.
 *
 * Note that even if some methods are not used in your project, you still need
 * to implement them all correctly!
 */

class ArrayQueue {
  private Location[] data;
  private int length, capacity, front;
  
  /* doubleCapacity
   *  parameters: none
   *  return value: nothing
   * 
   *  doubles the capacity of ArrayQueue when more space is needed
   */
  private void doubleCapacity() {
    //creates temp ArrayQueue with data array of twice the size
    //and copies over information to new array
    ArrayQueue temp = new ArrayQueue();
    Location[] tempArray = new Location[2*capacity];
    temp.data = tempArray;
    temp.capacity = tempArray.length;
    temp.copyFrom(this);
    this.copyFrom(temp);
    
  }

  /* ArrayQueue
   *  parameters: none
   *  return value: nothing
   * 
   *  constructor for ArrayQueue starting with capacity of 8
   *  front and length set to 0 and an empty array of length capacity
   */
  ArrayQueue() {
    capacity = 8;
    length = 0;
    front = 0;
    data = new Location[capacity];
  }
  
  /* ArrayQueue
   *  parameters: 
   *      q -- ArrayQueue to be copied from
   *  return value: nothing
   * 
   *  copy constructor for ArrayQueue to create a new ArrayQueue that
   *  is a duplicate of another ArrayQueue
   */
  ArrayQueue(ArrayQueue q) {
    copyFrom(q);
  }

  /* add
   *  parameters:
   *      loc -- Location to be added to array
   *  return value: nothing
   * 
   *  adds a Location to the back of the queue
   */
  void add(Location loc) {
    //index to find where to place Location on queue
    int index = ((length + front) % capacity);
    //check to see if queue is full and needs to be doubled
    //before adding anything
    if((front + length) >= capacity) {
      doubleCapacity();
      index = ((length + front) % capacity);
      data[index] = loc;
      length++;
    }
    else {
      data[index] = loc;
      length++;
    }
  }

  /* remove
   *  parameters: none
   *  return value: nothing
   * 
   *  "removes" front of array by increasing the value of front
   */
  void remove() {
    front++;
    length--;
  }

  /* main
   *  parameters: none
   *  return value: Location
   * 
   *  returns the front of the current state of the ArrayQueue
   */
  Location getFront() {
    return data[front];
  }

  /* getLength
   *  parameters: none
   *  return value: int
   * 
   *  returns the length of the current state of the ArrayQueue
   */
  int getLength()  {
    // -
    return length;
  }

  /* copyFrom
   *  parameters:
   *      q -- ArrayQueue to be copied from
   *  return value: ArrayQueue
   * 
   *  creates a deep copy of one ArrayQueue to another
   */
  ArrayQueue copyFrom(ArrayQueue q) {
    //if they are already equal simply return the original
    if(this == q) {
      return this;
    }
    //if copying from larger array, need to increase size to that array's size
    else if(capacity < q.capacity) {
      capacity = q.capacity;
      data = null;
      data = new Location[capacity];
      for(int x = 0; x < capacity; x++){
        data[x] = q.data[x];
      }
      return this;
    }
    //else if copying from smaller array, no need to change size
    else {
      for(int x = 0; x < q.capacity; x++){
        data[x] = q.data[x];
      }
      return this;
    }
  }
  
  /* printQueue
   *  parameters:
   *      args -- the array of command line argument values
   *  return value: nothing
   * 
   *  runs main method of program or the actual trace
   */
  void printQueue() {
    for(int x = 0; x < data.length; x++) {
      if(data[x] != null){
      System.out.println("Queue[" + x + "] is " + data[x]);
      }
      else {
        System.out.println("Queue[" + x + "] is null");
      }
    }
  }
  
  /* printReverseQueue
   *  parameters: none
   *  return value: nothing
   * 
   *  prints words of ArrayQueue in reverse order
   */
  void printReverseQueue() {
    for(int x = (capacity - 1); x >= 0; x--) {
      if(data[x] != null){
      System.out.println(data[x].word);
      }
    }
  }
  
  /* checkIfInQueue
   *  parameters:
   *      loc -- Location to be checked if it is in the Queue
   *  return value: boolean
   * 
   *  runs through each value of array to check if loc is in it or not
   */
  boolean checkIfInQueue(Location loc){
    boolean check = false;
    for(int x = 0; x < data.length; x++) {
      if(data[x] != null ){
        if(data[x].isEqual(loc) == true){
          check = true;
        }
      }
    }
    return check;
  }
}
