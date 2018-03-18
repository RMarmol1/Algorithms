/* CMPT 435
 * Project 2 Final
 * Filename: Maze.java
 * Student name: Rafael Marmol
 *
 * WordMelt program showing fastest solution from start word to end word using Breadth-First-Search
 *
 * Class: Maze
 */

import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Scanner;

/* Changes from Project 1:
 *
 * Data member validLocations was changed from a Location array to a
 * Set<Location>, but it still represents the group of locations that may be
 * visited in the maze.
 *
 * Data member validLocationCount was eliminated (because validLocations is no
 * longer an array).
 *
 * We let the compiler deal with the assignment operator, copy constructor, and 
 * for this version of the Maze, you should use the default constructor for the
 * _usual_ and to initally allocate memory for the Set of validLocations using
 * the TreeSet implementation of a Set.
 */

class Maze {
  private Set<Location> validLocations;

  private Location startLocation;
  private Location endLocation;
  
  private int validLocationCount;

  /* ArrayQueue
   *  parameters: none
   *  return value: nothing
   * 
   *  constructor for Maze to initialize variables
   */
  Maze() {
    // -
    validLocations = new TreeSet<Location>();
    startLocation = new Location();
    endLocation = new Location();
    validLocationCount = 0;
  }

  /* getStartLocation
   *  parameters: none
   *  return value: Location
   * 
   *  returns the startLocation or first word of the puzzle
   */
  Location getStartLocation() {
    // -
    return startLocation;
  }
  
  /* isValidLocation
   *  parameters: 
   *      loc -- Location to be checked
   *  return value: boolean
   * 
   *  traverses validLocations set to check if loc is in it or not
   */
  boolean isValidLocation(Location loc) {
    //uses iterator to traverse through the set
    return validLocations.contains(loc);
  }
  
  /* isEndLocation
   *  parameters: 
   *      loc -- Location to check if endLocation
   *  return value: boolean
   * 
   *  checks to see if loc is endLocation
   */
  boolean isEndLocation(Location loc) {
    // -
    if(loc.isEqual(endLocation)){
      return true;
    }
    else {
      return false;
    }
  }

  /* streamIn
   *  parameters: 
   *      input -- Scanner (in form of txt file for project)
   *  return value: nothing
   * 
   *  streams in information containing validLocationCount, validLocations,
   *  startLocation, and endLocation
   */
  void streamIn(Scanner input) {
    //count to be used in while loop
    int count = 0;
    
    //set valid locations
    validLocationCount = input.nextInt();
    
    //while loop to set valid locations
    while(input.hasNext() && count < validLocationCount)  {
      Location newLoc = new Location();
      newLoc.streamIn(input);
      //push location onto set
      validLocations.add(newLoc);
      count++;
    }
      
    //set start location
    if(input.hasNext()) {
      startLocation.streamIn(input);
    }
    else {
      System.out.println("No start location detected.");
    }
      
    //set end location
    if(input.hasNext()) {
      endLocation.streamIn(input);
    }
    else {
      System.out.println("No end location detected.");
    }
  }
}