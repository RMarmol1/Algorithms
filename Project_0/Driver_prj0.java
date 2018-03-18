/* CMPT 435
 * Project 0 -- Program trace verification
 * Filename: Driver_prj0.java0
 * Student name: Rafael Marmol
 *
 * Program which traces through call and return  functions in a stack and verifies if it works or not
 */

import java.util.Scanner;
import java.util.Stack;

public class Driver_prj0 {

  /* main
   *  parameters:
   *      args -- the array of command line argument values
   *  return value: nothing
   * 
   *  runs main method of program or the actual trace
   */
  public static void main(String[] args) {
    // Here we initialize the scanner variable to read lines of input
    // Also initialize strings for line which later takes next line of input,
    // callString will check if beginning of line has 'call'
    // returnString will check if beginning of line has 'return'
    // callFunctionString will hold function being called
    // returnFunctionString will hold function being returned
    Scanner input = new Scanner(System.in);
    String line;
    String callString = "";;
    String returnString = "";
    String callFunctionString = "";
    String returnFunctionString = "";

    // the callStack is used for storing the names of functions that have been
    // called and not yet returned
    Stack<String> callStack = new Stack<String>();

    // Each time we go through this while loop, we read a line of input.
    // The function hasNext() returns a boolean, which is checked by the while 
    // condition. If System.in has reached the end of the file, it will return 
    // false and the loop will exit.  Otherwise, it will return true and the 
    // loop will continue. The boolean error will show if an error has been
    // detected in the trace. If error has been detected, the loop will exit
    int lineNumber = 0;
    int maximum_depth = 0;
    boolean error = false;
    while (input.hasNext() && error == false) {
      line = input.nextLine();
      //sets call and return function strings
      if(line.substring(0,4).equals("call")){
        callString = line.substring(0,4);
        callFunctionString = (line.substring(5)).trim();
        returnString = "";
        returnFunctionString = "";
      }
      
      if(line.substring(0,6).equals("return")){
        returnString = line.substring(0,6);
        returnFunctionString = (line.substring(7)).trim();
        callString = "";
        callFunctionString = "";
      }
      
      
      lineNumber++;
      //System.out.println(line);
      
      //puts function into stack
      if (callString.equals("call")){
        callStack.push(callFunctionString);
        maximum_depth = checkDepth(callStack, maximum_depth);
      }
      
      //check return function
      else if (returnString.equals("return")){

         
        //checks if function is in stack to begin with
        if(checkIfInStack(callStack, returnFunctionString) == false && callStack.size() == 0){
          System.out.println("Invalid trace at line " + lineNumber);
          System.out.println("Returning from " + returnFunctionString + " which was not called");
          //print stack
          System.out.println("Stack trace:");
          for(int x = callStack.size()-1; x >= 0; x--){
            System.out.println(callStack.get(x));
          }
          error = true;
        }

        
        //if correct function returned then popped from stack
        else if (returnFunctionString.equals(callStack.peek())){
          callStack.pop();
        }
        
        //wrong function called
        else{
        System.out.println("Invalid trace at line " + lineNumber);
          System.out.println("Returning from " + returnFunctionString + " instead of " + callStack.peek());
          //print stack
          System.out.println("Stack trace:");
          for(int x = callStack.size()-1; x >= 0; x--){
            System.out.println(callStack.get(x));
          }
          error = true;
        }
          
        
      }
      
      //else do nothing
      else {
        
      }
      
      
    }
    
    
    //if stack still has functions inside then returns error
    if(input.hasNext() == false && callStack.size() >= 1 && error == false){
      System.out.println("Invalid trace at line " + lineNumber);
      System.out.println("Not all functions returned");
      //print stack
      System.out.println("Stack trace:");
      for(int x = callStack.size()-1; x >= 0; x--){
        System.out.println(callStack.get(x));
      }
          
    }
    
    //else if stack is empty and no errors than end on valid trace and show max depth
    else if (error == false){
      System.out.println("Valid trace");
      System.out.println("Maximum call depth was " + maximum_depth);
    }
    
    //else do nothing
    else {
      
    }
      
  }
  
  /* checkDepth
   *  parameters:
   *      stack1 - takes in a stack to check its size
   *      max -- takes in a max number to be compared to
   *  return value: int
   * 
   *  after a new function is added to the stack the checkDepth method will compare
   *  the new depth of the stack to the max depth and adjust accordingly
   */
  public static int checkDepth(Stack stack1, int max) {
    if (stack1.size() > max){
      max = stack1.size();
      return max;
    } 
      
    else {
      return max;
    }
  }
  
  /* checkIfInStack
   *  parameters:
   *      stack - takes in a stack to check contents
   *      function1 -- takes in string to check functions in stack
   *  return value: boolean
   * 
   *  checks if function is in stack
   */
  public static boolean checkIfInStack(Stack stack, String function1) {
    boolean inIt = false;
      
    for (int x = 0; x < stack.size(); x++){
      if(function1.equals(stack.get(x))) {
        inIt = true;      
      }
      else {
        
      }
    }
      
    return inIt;
  }
}
