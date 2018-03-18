/* CMPT 435
 * Project 1 Milestone 2 -- Maze
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
      //sets up scanner input (not from files -- for now)
      Scanner input = new Scanner(System.in);
      int inputValidLocations;
      int inputRow;
      int inputCol;
      int inputStartRow;
      int inputStartCol;
      int inputEndRow;
      int inputEndCol;
      boolean noSolution = false;
      
      Location startLoc = new Location();
      Location endLoc = new Location();
      
      LocationStack mazeStack = new LocationStack();
      LocationStack solutionStack = new LocationStack();
      
      int count = 0;
      
      //set valid locations
      inputValidLocations = input.nextInt();
     // System.out.println("Valid locs = " + inputValidLocations);
      
      //while loop to set rows and cols
      while(input.hasNextInt() && count < inputValidLocations) {
        Location newLoc = new Location();
        //streamin row and col to location
        newLoc.streamIn(input);
        
        
        //push location onto stack
        mazeStack.push(newLoc);
        
        count++;
        
      }
     // System.out.println("Valid Locations: ");
     // mazeStack.streamOut(mazeStack);
      
      //set start location
      if(input.hasNextInt()) {
        startLoc.streamIn(input);
      // System.out.println("Start location is :");
      // startLoc.streamOut();
      }
      else {
        System.out.println("No start location detected.");
      }
      
      //set end location
      if(input.hasNextInt()) {
        endLoc.streamIn(input);
      //  System.out.println("End location is :");
      // endLoc.streamOut();
      }
      else {
        System.out.println("No end location detected.");
      }
      
      
      //start solutionStack with start location to begin
      solutionStack.push(startLoc);
      
     // solutionStack.streamOut(solutionStack);
      
      
    //find next space
    Location neighborLoc;
    //neighborLoc = solutionStack.getTop().nextNeighbor();
      /*  
    //while loop checks if nextneighbor is in validlocations until all directions were checked or one was found
    while(!solutionStack.getTop().isDone()){
          
      if(mazeStack.isOn(neighborLoc) && !solutionStack.isOn(neighborLoc)){
        System.out.println("Yes");
        solutionStack.push(neighborLoc);
        break;
      }
      else {
        //System.out.println("No");
        neighborLoc = solutionStack.getTop().nextNeighbor();
      }
    }*/
   
    boolean checkIfEnd;
    checkIfEnd = solutionStack.getTop().isEqual(endLoc);
    while(checkIfEnd == false){
      
      neighborLoc = solutionStack.getTop().nextNeighbor();
        
      //while loop checks if nextneighbor is in validlocations until all directions were checked or one was found
      while(!solutionStack.getTop().isDone()){
          
        if(mazeStack.isOn(neighborLoc) && !solutionStack.isOn(neighborLoc)){
          // System.out.println("Yes");
          solutionStack.push(neighborLoc);
          break;
        }
        else {
          //System.out.println("No");
          neighborLoc = solutionStack.getTop().nextNeighbor();
        }
      }
      
      checkIfEnd = solutionStack.getTop().isEqual(endLoc);
      
      if(solutionStack.getTop().isDone() == true && checkIfEnd == false){
        //solutionStack.pop();
        //solutionStack.streamOut(solutionStack);
        
        if(solutionStack.getTop().isEqual(startLoc)){
          noSolution = true;
          break;
        }
        else{
          solutionStack.pop();
        }
      }
      else{
      }
      /*
      if(solutionStack.getTop().isDone() == true && solutionStack.getTop().isEqual(startLoc)){
        noSolution = true;
        break;
      }*/
      
        checkIfEnd = solutionStack.getTop().isEqual(endLoc);
      
      
     /* if(solutionStack.getTop().isDone() == true && checkIfEnd == false){
        checkIfEnd = true;
        noSolution = true;
      }
      else {
      }*/
      //System.out.println("look");
      //solutionStack.streamOut(solutionStack);
      
    }
    
    
    
    //1. check neighbors of top
    //2. add to solution stack
    //if already on stack move back to last spot
    //continue checking from that spot
    //go until endlocation is reached
    
    //solutionStack.pop();
    
    
       
        
      
      
      
      
    //print solution
    if(noSolution == true){
      System.out.println("No solution found");
    }
    else {
     System.out.println("Solution found:");
     solutionStack.streamOut(solutionStack);
    }
    
      
      
      
      
      
  }
  
 }