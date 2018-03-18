/* CMPT 435
 * Project 2 Final
 * Filename: Driver_prj2.java
 * Student name: Rafael Marmol
 *
 * WordMelt program showing fastest solution from start word to end word using Breadth-First-Search
 *
 * Class: Driver_prj2
 */
 
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class Driver_prj2 {
  
  /* main
   *  parameters:
   *      args -- the array of command line argument values
   *  return value: nothing
   * 
   *  runs main method of program or the actual trace
   */
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    
    Maze maze = new Maze();
    ArrayQueue queue = new ArrayQueue();
    ArrayQueue solutionQueue = new ArrayQueue();
    TreeMap<Location, Location> map = new TreeMap<Location, Location>();
    Location endLocation = new Location();
    boolean noSolution = true;
    
    //int count = 0;
    
    //stream in words into maze
    maze.streamIn(input);
    
    //if start is equal to end then no need to do so much work
    if(maze.isEndLocation(maze.getStartLocation()) == true){
      //noSolution = false;
      System.out.println("Solution found:");
      maze.getStartLocation().streamOut();
    }
    else{
    
      //begin with startLocation
      Location next = maze.getStartLocation();
    
      //start with starting location on the queue
      queue.add(next);
      
      map.put(next, next);
     
      //while the queue is not empty and the ending location has not been found
      
      
      while(queue.getLength() > 0 && maze.isEndLocation(next) == false) {
      
        //pull one location off the queue
        next = queue.getFront();
      
        //remove front from queue
        queue.remove();
      
      
        //look at all valid neighbor locations
        
        while(next.isDone() == false){
          Location neighbor = next.nextNeighbor();
        
          //if neighbor is a valid location and not already in queue then add neighbor to queue
          if(maze.isValidLocation(neighbor) == true && queue.checkIfInQueue(neighbor) == false){
            //make sure not equal to current word
            if(next.isEqual(neighbor) == false){
              
              queue.add(neighbor);
              //if not in map then add relation to map
              if(map.containsKey(neighbor) == false){
                map.put(neighbor, next);
              }
              //if end location is found set it as endLocation
              if(maze.isEndLocation(neighbor) == true){
                endLocation = neighbor;
                noSolution = false;
                
              }
              
            }
            //else do nothing
            else {
              
            }
         
          }
        
        }
      
      }
      
    
    
    
      //if endLocation was never reached then no solution exists
      if(noSolution == true){
        System.out.println("No solution found");
      }
      //print solution
      else{
        //need to traverse map backwards to find shortest path
        System.out.println("Solution found:");
        Location solution = map.get(endLocation);
        while (solution != maze.getStartLocation() && solution != null){
          solutionQueue.add(solution);
          if(map.get(solution) != null){
            solution = map.get(solution);
          }
        }
        maze.getStartLocation().streamOut();
        //need to reverse since its backwards
        solutionQueue.printReverseQueue();
        endLocation.streamOut();
      }
    }
  }
}