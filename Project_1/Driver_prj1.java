/* CMPT 435
 * Project 1 Final
 * Filename: Driver_prj1.java
 * Student name: Rafael Marmol
 *
 * Program which takes input in form of a maze and outputs solution path from start to endline
 *
 * Class: Driver_prj1
 */

import java.util.Scanner;
public class Driver_prj1 {

  /* main
   *  parameters:
   *      args -- the array of command line argument values
   *  return value: nothing
   * 
   *  runs main method of program or the actual trace
   */
  public static void main(String[] args) {
    //sets up scanner input
    Scanner input = new Scanner(System.in);
    
    //set up location stack to hold solution path
    LocationStack solutionStack = new LocationStack();
    
    //initialize maze object
    Maze maze = new Maze();
      
    //stream in valid location count, validlocations, and starting/ending locations
    maze.streamIn(input);
      
    //checks if no solution is found
    boolean noSolution = false;
      
      
    //start solutionStack with start location to begin
    solutionStack.push(maze.getStartLocation());
      
      
      
    //find next location in solution
    Location neighborLoc;
    boolean checkIfEnd;
    
    checkIfEnd = maze.isEndLocation(solutionStack.getTop());
    
    //while loop to loop through top location in stack's neighbors to see if part
    //of solution until end location is reached
    while(checkIfEnd == false){
      
      neighborLoc = solutionStack.getTop().nextNeighbor();
        
      //while loop checks if nextneighbor is in validlocations until all 
      //directions were checked or one was found
      while(!solutionStack.getTop().isDone()){
          
        //if neighborLoc is a valid location and is not on solution stack,
        //then add it to solution and break from loop to continue search
        //else check the next neighbor
        if(maze.isValidLocation(neighborLoc) && !solutionStack.isOn(neighborLoc)){
          solutionStack.push(neighborLoc);
          break;
        }
        else {
          neighborLoc = solutionStack.getTop().nextNeighbor();
        }
      }
      
      //check if reached end yet
      checkIfEnd = maze.isEndLocation(solutionStack.getTop());
      
      //if we went through all neighbors of top location in solution
      //we must either: check if no solution is reachable
      //or remove that top location to check another path
      if(solutionStack.getTop().isDone() == true && checkIfEnd == false){
        
        if(solutionStack.getTop().isEqual(maze.getStartLocation())){
          noSolution = true;
          break;
        }
        else{
          solutionStack.pop();
        }
        
      }
      else{
      }
      
      //check if end location is reached
      checkIfEnd = maze.isEndLocation(solutionStack.getTop());
      
    }
    
      
    //print solution unless no solution was found
    if(noSolution == true){
      System.out.println("No solution found");
    }
    else {
     System.out.println("Solution found:");
     solutionStack.streamOut(solutionStack);
    }
    
      
  }
  
 }