/* CMPT 435
 * Project 1 Final
 * Filename: Maze.java
 * Student name: Rafael Marmol
 *
 * Program which takes input in form of a maze and outputs solution path from start to endline
 *
 * Class: Maze
 */

import java.util.Scanner;

/* A Maze object contains three things: the starting location, the
 * ending location, and a list of valid locations. It does not contain
 * any logic for actually solving the maze.
 *
 * The Maze constructor initializes all the data members that it can,
 * but the validLocationCount will not yet be known when the Maze is
 * constructed, so use a sensible value here.
 *
 * getStartLocation() returns the starting location of the maze.
 * isEndLocation() returns true if the given Location is the end of
 * the maze, otherwise false.  isValidLocation() returns true if the
 * given Location is in the list of valid Locations, otherwise false.
 *
 * streamIn() provides a means of streaming in a Maze object.  In
 * this version, streamIn() does not do any error checking.  We
 * assume that the entered data is perfect. Consider the following
 * when writing this function: what if we stream in a Maze object with
 * this function, and later call the same function on the same object?
 *
 * validLocationCount keeps the number of valid locations, which will
 * not be known until the object is streamed in. This is the length of
 * the validLocations array.
 *
 * validLocations is an array that contains all the locations that may
 * be used to solve the maze.
 *
 * startLocation holds the starting location of the maze, and
 * endLocation holds the target location of the maze.
 *
 * In this class we make the copy constructor private and illegal to
 * call, because it is not needed in this project, and we don't
 * want the compiler to provide them for us (since this class uses
 * dynamically allocated memory).  If these methods are called, they
 * will intentionally crash the program by the call to assert(false).
 */
class Maze {
  private Maze(Maze m) { assert(false); }

  private int validLocationCount;
  private Location[] validLocations;

  private Location startLocation;
  private Location endLocation;

  /* Maze
   *  parameters:
   *  return value: nothing
   * 
   *  constructor for Maze
   */
  Maze() {
    // -
    validLocationCount = 0;
    validLocations = new Location[validLocationCount];
    startLocation = new Location();
    endLocation = new Location();
  }

  /* getStartLocation
   *  parameters:
   *  return value: Location
   * 
   *  returns the start location of the maze
   */
  Location getStartLocation() {
    // -
    return startLocation;
  }
  
  /* isValidLocation
   *  parameters: loc -- to compare to list of valid locations
   *  return value: boolean
   * 
   *  returns true if entered location is within list of valid locations
   *  returns false if not
   */
  boolean isValidLocation(Location loc) {
    // -
    boolean isIn = false;
    for(int x = 0; x < validLocations.length; x++) {  //for loop runs through each validLocations locations to compare loc
      if (validLocations[x].isEqual(loc)) {
        isIn = true;
      }
      else {
        
      }
    }
    return isIn;
  }
  
  /* isEndLocation
   *  parameters: loc -- to compare to end point
   *  return value: boolean
   * 
   *  checks to see if entered location is the end point
   */
  boolean isEndLocation(Location loc) {
    // -
    if(endLocation.isEqual(loc)){
      return true;
    }
    else {
      return false;
    }
  }

  /* streamIn
   *  parameters: input -- to construct a maze
   *  return value: nothing
   * 
   *  streams in a new maze object
   */
  void streamIn(Scanner input) {
    // -
    
    int count = 0;
      
    //set valid locations
    validLocationCount = input.nextInt();
    validLocations = new Location[validLocationCount];
      
    //while loop to set rows and cols
    while(input.hasNextInt() && count < validLocationCount) {
      Location newLoc = new Location();
      //streamin row and col to location
      newLoc.streamIn(input);
        
      //push location onto stack
      validLocations[count] = newLoc;
      
      count++;
        
    }
      
    //set start location
    if(input.hasNextInt()) {
      startLocation.streamIn(input);
    }
    else {
      System.out.println("No start location detected.");
    }
      
    //set end location
    if(input.hasNextInt()) {
      endLocation.streamIn(input);
    }
    else {
      System.out.println("No end location detected.");
    }
    
  }
}