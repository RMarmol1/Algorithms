/* CMPT 435
 * Project 1 Final
 * Filename: LocationStack.java
 * Student name: Rafael Marmol
 *
 * Program which takes input in form of a maze and outputs solution path from start to endline
 *
 * Class: LocationStack, LocationStackNode
 */

/* Class declaration for a simple stack of Location objects. It is not
 * a difficult class; it can contain only Location objects. It can
 * grow and shrink because it is a linked structure. The class
 * LocationStackNode (below) encapsulates the nodes of the stack.
 *
 * Methods push(), pop(), and getTop() provide standard stack methods.
 * Using assert() to check for problems in these three methods could
 * be useful (hint).  isEmpty() returns true if the stack is empty,
 * false otherwise. isOn() returns true if the given Location is on
 * the stack, otherwise returns false.
 *
 * streamOut() streams out the stack from bottom to top. This method
 * should NOT make a copy of the stack. Instead, it should require two
 * passes over the stack to print the stack. The first pass will
 * traverse the stack to top->bottom, reversing the links of the nodes
 * as it goes. The second pass will traverse from bottom->top,
 * printing each Location as it is visited, and undoing the reversing
 * of the node links.
 *
 * The default constructor initializes the data members as
 * appropriate. The copy constructor is not usable in this class;
 * therefore it is private and will fail on an assert() if called.
 *
 * The data member top is a reference to the top node.
 */

class LocationStack {
  private LocationStack(LocationStack s) { assert(false); }
  private LocationStackNode top;

  /* LocationStack
   *  parameters:
   *  return value: nothing
   * 
   *  constructor for LocationStack
   */
  LocationStack() {
    // -
    top = null;
    
  }

  /* push
   *  parameters: loc -- location to be added to stack
   *  return value: nothing
   * 
   *  Mimics a stack push in which entered loc will be added to
   *  top and sets is next node to old top
   */
  void push(Location loc) {
    // -
    
    //creates new locationstacknode with entered loc as location
    //and top as its nextNode
    LocationStackNode newNode = new LocationStackNode(loc, top);
    
    //if the stack is not empty then create temp node
    //holding top's value set new top to newNode
    //set old top as nextNode
    if(!isEmpty()) {
      LocationStackNode temp;
      temp = top;
      top = newNode;
      top.setNextNode(temp);
    }
    else {
      top = newNode;
    }
    
    
    
  }
  
  /* pop
   *  parameters:
   *  return value: nothing
   * 
   *  mimics stack pop in which top element is removed
   */
  void pop() {
    // -
    //if stack is not empty, then top is set to
    //second location, leading to old top to be
    //garbage collected
    if(this.isEmpty() == false){
      
      top = top.getNextNode();
      
    }
    else {
      System.out.println("woops");
    }
    
    
  }
  
  /* getTop
   *  parameters:
   *  return value: Location
   * 
   *  returns the location in the top node
   */
  Location getTop() {
    // -
    return top.getLocation();
    
  }

  /* isEmpty
   *  parameters:
   *  return value: boolean
   * 
   *  checks if stack is empty
   */
  boolean isEmpty() {
    // -
    //if there is no top in the stack then it must be empty
    if(top == null){
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /* isOn
   *  parameters: loc -- to check in stack of locations
   *  return value: boolean
   * 
   *  checks through stack to see if loc is present or not
   */
  boolean isOn(Location loc) {
    // -
    //while loop to follow nextnode and add counter for size of stack
    LocationStackNode temp;
    temp = top.getNextNode();
    int size = 1;
    while(temp != null) {
      temp = temp.getNextNode();
      size++;
    }
    
    //checks stack for instance of loc
    boolean isItIn = false;
    int count = 0;
    temp = top;
    while(count < size) {
      if(temp.getLocation().isEqual(loc)) {
        isItIn = true;
        count = size;
      }
      else {
        temp = temp.getNextNode();
        count++;
      }
    }
    
    return isItIn;
  }

  /* streamOut
   *  parameters: s -- LocationStack to print
   *  return value: nothing
   * 
   *  traverses stack and reorders it in reverse to print contents forward
   */
  void streamOut(LocationStack s) {
    // -
    //reverse the stack by setting temporary nodes to hold values of top
    //and nextnode
    LocationStackNode tempTop;
    LocationStackNode tempNext;
    tempTop = top;
    tempNext = top.getNextNode();
    tempTop.setNextNode(null);
    top.setNextNode(null);
    top = tempTop;
    
    //while loop continues through rest of stack
    while(tempNext != null) {
      tempTop = tempNext.getNextNode();
      this.push(tempNext.getLocation());
      tempNext = tempTop;
      
    }
    
    
    //go through and print contents of stack
    LocationStackNode temp;
    temp = top;
    while(temp != null) {
      temp.getLocation().streamOut();
      System.out.println("");
      temp = temp.getNextNode();
      
    }
    
    
    //reverse stack again back to normal
    tempTop = top;
    tempNext = top.getNextNode();
    tempTop.setNextNode(null);
    top.setNextNode(null);
    top = tempTop;
    
    while(tempNext != null) {
      tempTop = tempNext.getNextNode();
      this.push(tempNext.getLocation());
      tempNext = tempTop;
      
    }
    
  }
}

/* Class declaration for a Node on a LocationStack. Each node contains
 * a Location and a link to the next LocationStackNode (the one below
 * it on the stack). 
 *
 * The only constructor that may be used for this class is the one
 * which takes values to initialize its data members. Other
 * constructors may not be called as they are not necessary. These
 * restrictions help prevent us from accidentally making multiple
 * nodes that all point to the same next node.
 *
 * If we set a LocationStack object to null, this should invoke the garbage
 * collector and delete the nextNode in LocationStackNode, so that deleting
 * the top of the stack has a chaining effect that deletes every node
 * on the stack.
 *
 * The get/set methods for this class are self-explanatory, and are
 * the interface by which you should modify a node as necessary.
 */
class LocationStackNode {
  private LocationStackNode() { assert(false); }
  private LocationStackNode(LocationStackNode s) { assert(false); }

  private Location location;
  private LocationStackNode nextNode;

  /* LocationStackNode
   *  parameters: loc -- a Locations to be set as the location for the node
   *              next -- a node to be set as next node
   *
   *  return value: nothing
   * 
   *  constructor for LocationStackNode
   */
  LocationStackNode(Location loc, LocationStackNode next) {
    // -
    location = loc;
    nextNode = next;
  }

  /* getLocation
   *  parameters:
   *  return value: Location
   * 
   *  returns location of node
   */
  Location getLocation() {
    // -
    return location;
  }
  
  /* getNextNode
   *  parameters:
   *  return value: LocationStackNode
   * 
   *  returns the next node in stack
   */
  LocationStackNode getNextNode() {
    // -
    return nextNode;
  }
  
  /* setNextNode
   *  parameters: next -- LocationStackNode to be set for nextNode
   *  return value: nothing
   * 
   *  sets nextNode to next node
   */
  void setNextNode(LocationStackNode next) {
    // -
    nextNode = next;
  }
}