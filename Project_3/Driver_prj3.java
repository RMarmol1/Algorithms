/* CMPT 435
 * Project 3 Final
 * Filename: Driver_prj3.java
 * Student name: Rafael Marmol
 *
 * Encryption/Decryption through means of creating a codebook with a Binary
*  Standard Tree
 *
 * Class: Driver_prj3
 */

import java.util.Scanner;
public class Driver_prj3 {
  /* main
   *  parameters:
   *      args -- the array of command line argument values
   *  return value: nothing
   * 
   *  runs main method of program
   */
  public static void main(String[] args){
   
    //sets up codebook through an encryptiontree
    EncryptionTree codeBook = new EncryptionTree();
    
    Scanner input = new Scanner(System.in);
    
    //scans each line to check for actions and input
    while(input.hasNextLine()){
      
      //formats line
      String line = input.nextLine();
      
      //check if line is empty
      if(line.replaceAll(" ", "").equals("")){
      }
      //if not empty
      else{
      
        line = line.replaceAll("'", "");
        //line = line.replaceAll(" ","");
        String[] inputA = line.split(" ");
        //for(String s: inputA){
          //System.out.println("s: " + s);
        //}
        String[] inputB = new String[inputA.length - 1];
        for(int i = 0; i < inputA.length-1; i++){
          inputB[i] = inputA[i+1];
        }
        //System.out.println("a : " + inputA[0]);
        for(int i = 0; i < inputA.length-1; i++){
          if(inputA[i].equals("")){
           inputA[0] = inputA[i+1];
          }
          if(!inputA[i].equals("")){
            i = inputA.length;
          }
        }
        String action = inputA[0].substring(0,1);      
      
        //runs through each action
        //insert
        if(action.equals("i")){
          if(inputB.length == 0 || inputB[inputB.length-1].equals("i")){ 
          
            if(line.substring(1).replaceAll(" ", "").equals("") || line.substring(1).replaceAll(" ", "").equals("i") ){
              line = line.substring(1).replaceAll(" ", "");
              while(input.hasNextLine()){
                line = input.nextLine().replaceAll(" ", "");
                if(!line.equals("")){
                  codeBook.insert(line); 
                  
                  break;
                }
              }
           
            }            
            else{
            codeBook.insert(line.substring(1));
            
            }
          }
          else{
            codeBook.insert(inputB[inputB.length-1]);
          }
        
        }
        //remove
        else if(action.equals("r")){
          if(inputB.length == 0 || inputB[inputB.length-1].equals("r")){
          
            if(line.substring(1).replaceAll(" ", "").equals("")){
              line = line.substring(1).replaceAll(" ", "");
              while(input.hasNextLine()){
                line = input.nextLine().replaceAll(" ", "");
                if(!line.equals("")){
                  codeBook.remove(line); 
                  break;
                }
              }
           
            }   
            else{
              codeBook.remove(line.substring(1));
            }
          }
          else{
            codeBook.remove(inputB[inputB.length-1]);
          }
        }
        //encrypt
        else if(action.equals("e")){
          for(String s : inputB){
           System.out.print(codeBook.encrypt(s) + " ");
          }
          System.out.println();
        }
        //decrypt
        else if(action.equals("d")){
          for(String s : inputB){
           System.out.print(codeBook.decrypt(s) + " ");
          }
          System.out.println();
        }
        //printPreorder
        else if(action.equals("p")){
          codeBook.printPreorder();
        }
        //quit
        else if(action.equals("q")){
          System.exit(0);
        }
        //else do nothing
        else{
        }
      }
    }
   
  }
  
}
