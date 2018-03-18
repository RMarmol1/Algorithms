/* CMPT 435
 * Project 3 Final
 * Filename: BST.java
 * Student name: Rafael Marmol
 *
 * Encryption/Decryption through means of creating a codebook with a Binary
*  Standard Tree
 *
 * Class: BSTNode, BST, EncryptionTree
 */

import java.util.Scanner;
import java.util.Stack;

/* Class: BSTNode
 *
 * Each BSTnode represents a node in a Binary Search Tree
 * Each BSTNode contains its own data and pointers to left and right nodes
 *
 * Methods: BSTNode, printPreorder, minNode, maxNode, findToRemove, 
 * findParent, printPreorder, verifySearchOrder
 */
class BSTNode {
  protected  BSTNode(BSTNode t) { assert(false); }

  protected  String data;
  protected  BSTNode left;
  protected  BSTNode right;

  /* BSTNode
   *  parameters:
   *      d -- String value for data of BSTNode
   *      l -- BSTNode on left
   *      r -- BSTNode on right 
   *  return value: nothing
   * 
   *  constructor for BSTNode
   */
  public BSTNode(String d, BSTNode l, BSTNode r) {
    data = d; left = l; right = r;
  }

  /* getLeft
   *  parameters: none
   *  return value: BSTNode
   * 
   *  returns left node
   */
  public BSTNode getLeft()  { return left;  }
  
  /* getRight
   *  parameters: none
   *  return value: BSTNode
   * 
   *  returns right node
   */
  public BSTNode getRight()  { return right; }
  
  /* getData
   *  parameters: none
   *  return value: String
   * 
   *  returns data of node
   */
  public String getData()    { return data;  }
  
  /* printPreorder
   *  parameters: indent -- String of spaces to put before printing node data
   *  return value: none
   * 
   *  prints the BinarySearchTree in preorder mode
   */
  //can add parameter String ind if makes easier; could be recursion
  public void printPreorder() {

    //prints self, left child, right child recursively
    //print self
    /*if(this != null){
      System.out.println(indent + getData());
      indent += "  " ;
      //print left
      if(getLeft() != null){
        getLeft().printPreorder(indent);
      }
      else {
        System.out.println(indent + "NULL");
      }
      //print right
      if(getRight() != null){
        getRight().printPreorder(indent);
      }
      else {
        System.out.println(indent + "NULL");
      }
    }
    else{
      System.out.println(indent + "NULL");
      indent += "  " ;
    }*/
    
    
    //iterative
    String ind = "";
	  BSTNode EndNode = minNode();
	  BSTNode current = this;
    int level = 0;
    //keeps track of next node
    Stack<BSTNode> nodeStack = new Stack<BSTNode>();
    //keeps track of levels or indents to print
    Stack<Integer> levelStack = new Stack<Integer>();
    
    nodeStack.push(current);
    levelStack.push(level);
    
    //traverse nodes on tree by setting up a stack
    while(!nodeStack.empty()) {
      //print current
      if(nodeStack.peek() == null){
        level = levelStack.peek();
        ind = "";
        for(int i = 0; i < level; i++){
          ind += "  ";
        }
        System.out.println(ind + "NULL");
      }
      else{
        level = levelStack.peek();
        ind = "";
        for(int i = 0; i < level; i++){
          ind += "  ";
        }
        System.out.println(ind + nodeStack.peek().getData());
      }
      nodeStack.pop();
      levelStack.pop();
      //add children to stack
      if(current == null){
      }
      else{
        nodeStack.push(current.right);
        nodeStack.push(current.left);
        level++;
        levelStack.push(level);
        levelStack.push(level);
      }
    
      if(!nodeStack.empty()){
        current = nodeStack.peek();
      }
    
    }
    
  }

  
  /* minNode
   *  parameters: none
   *  return value: BSTNode
   * 
   *  finds leftMost node
   */
  public BSTNode minNode() { 
    //-
    BSTNode temp;
    temp = this;
    while (temp.getLeft() != null){
      temp = temp.getLeft();
    }
    return temp;
    
  }
  
  /* maxNode
   *  parameters: none
   *  return value: BSTNode
   * 
   *  finds rightMost node
   */
  public BSTNode maxNode() { 
    //- 
    BSTNode max;
    max = this;
    while(max.getRight() != null){
      max = max.getRight();
    }
    return max;
  }


  /* professor's implementation of verifySearchOrder() */
  public void verifySearchOrder() {
    if (left != null) {
      assert(left.maxNode().data.compareTo(data) == -1);
      left.verifySearchOrder();
    }
    if (right != null) {
      assert(data.compareTo(right.minNode().data) == -1);
      right.verifySearchOrder();
    }
  }
}

/* Class: BST 
 *
 * A BST is a String-based class that represents a binary search tree. 
 *
 * Methods: BST, isEqual, insert, remove
 *
 */
class BST {
  protected BST(BST t) { assert(false); }
  protected BST isEqual(BST t) { assert(false); return this; }
  
  protected BSTNode root;


  /* BST
   *  parameters: none
   *  return value: nothing
   * 
   *  constructor for BST
   */
  public BST() {
    root = null; 
  }

  /* insert
   *  parameters:
   *      item -- String to be added into the BST as a BSTNode
   *  return value: nothing
   * 
   *  inserts new item into the BST
   */
  public void insert(String item) { 
  
    
  
    BSTNode newNode = new BSTNode(item, null, null);
    BSTNode current;
    current = root;
    //if tree is empty then set root
    if (root == null){
      root = newNode;
    }
    //otherwise add node below root
    else{
      //check if already in tree
      if(findToRemove(item).getData().equals("NULL")){
      //find correct position to insert
        while(current != null){
          if (newNode.getData().compareTo(current.getData()) < 0){
            if(current.getLeft() == null){
              current.left = newNode;
              break;
            }
            else{
              current = current.getLeft();
            }
          }
          else if (newNode.getData().compareTo(current.getData()) > 0){
            if(current.getRight() == null){
              current.right = newNode;
              break;
            }
            else{
              current = current.getRight();
            }
          }
        }
      }
      else{
        //do nothing
      }
    }
  }
  
  /* remove
   *  parameters:
   *      items -- finds node containing String of item to remove it
   *  return value: nothing
   * 
   *  removes node from BST
   */
  public void remove(String item) { 

    BSTNode parent;
    BSTNode grandchild;
    BSTNode toRemove;
    toRemove = findToRemove(item);
    parent = findParent(item);
    //if removing root
    if(toRemove == root){
      //root has no children
      if(toRemove.getLeft() == null && toRemove.getRight() == null){
        root = null;
        toRemove = null;
      }
      
      //root has 1 child
      else if(toRemove.getLeft() != null || toRemove.getRight() != null){
        if(toRemove.getLeft() != null && toRemove.getRight() == null){
          root = toRemove.getLeft();
          toRemove = null;
        }
        else{
          if(toRemove.getRight().getLeft() == null){
            root = toRemove.getRight();
          }
          else{
            BSTNode leftMost = toRemove.right;
            BSTNode leftMostParent = toRemove;
    
            if(leftMost.left != null){
              while(leftMost.left != null){
                leftMostParent = leftMost;
                leftMost = leftMost.left;
              }
              
              if(leftMost.right != null){
                leftMostParent.left = leftMost.right;
              }
              
              leftMostParent.left = null;
              leftMost.right = toRemove.right;
            }
    
            leftMost.left = toRemove.left;
           
            root = leftMost;
            
            toRemove.left = toRemove.right = null;
            toRemove = null;
          }
        }
      }
      
      //root has 2 children
      else if(toRemove.getLeft() != null && toRemove.getRight() != null){
        BSTNode leftMost = toRemove.right;
            BSTNode leftMostParent = toRemove;
    
            if(leftMost.left != null){
              while(leftMost.left != null){
                leftMostParent = leftMost;
                leftMost = leftMost.left;
              }
              
              if(leftMost.right != null){
                leftMostParent.left = leftMost.right;
              }
              
              leftMostParent.left = null;
              leftMost.right = toRemove.right;
            }
    
            leftMost.left = toRemove.left;
           
            root = leftMost;
            
            toRemove.left = toRemove.right = null;
            toRemove = null;
      }
      
      //else do nothing
      else{
      }
    }
    //not root
    else{
    //removing a leaf node
    if(toRemove.getLeft() == null && toRemove.getRight() == null){
      if(parent.left != null && parent.left.getData().equals
      (toRemove.getData())){
        parent.left = null;
      }
      else {
        parent.right = null;
      }
      toRemove = null;
    }
    
    //removing a node with one child
    else if(toRemove.getLeft() != null && toRemove.getRight() == null 
    || toRemove.getLeft() == null && toRemove.getRight() != null){
      if(toRemove.left != null){
        grandchild = toRemove.left;
        toRemove.left = null;
      }
      else {
        grandchild = toRemove.right;
        toRemove.right = null;
      }
    
      if(parent.left == toRemove) {
        parent.left = grandchild;
      }
      else {
        parent.right = grandchild;
      }
    
      toRemove = null;
    }
    
    //removing a node with 2 children
    else if(toRemove.getLeft() != null && toRemove.getRight() != null){
      BSTNode leftMost = toRemove.right;
      BSTNode leftMostParent = toRemove;
    
      if(leftMost.left != null){
        while(leftMost.left != null){
          leftMostParent = leftMost;
          leftMost = leftMost.left;
        }
      
        leftMostParent.left = leftMost.right;
        leftMost.right = toRemove.right;
      }
    
      leftMost.left = toRemove.left;
    
      if(parent.left == toRemove){
        parent.left = leftMost;
      }
      else {
        parent.right = leftMost;
      }
    
      toRemove.left = toRemove.right = null;
      toRemove = null;
    }
    
    else{
      System.out.println("ERROR: Could not remove node");
    }
    }
    
    
  }
  

  /* findToRemove
   *  parameters:
   *      s -- String to be removed
   *      current -- BSTNode in path to find toRemove
   *  return value: BSTNode
   * 
   *  finds the node to be removed
   */
  public BSTNode findToRemove(String s){
 
   BSTNode toRemove = new BSTNode ("NULL",null,null);
   /*
    if(current.getData().equals(s)){
      toRemove = current;
    }
    else {
      
      if (current.getLeft() != null 
      && findToRemove(s, current.getLeft()).getData().equals(s)){
        toRemove = findToRemove(s, current.getLeft());
      }
      if (current.getRight() != null 
      && findToRemove(s, current.getRight()).getData().equals(s)){
        toRemove = findToRemove(s, current.getRight());
      }
    }
    return toRemove;*/
    
    
    //iterative
	  BSTNode EndNode = root.minNode();
	  BSTNode current = root;
    Stack<BSTNode> nodeStack = new Stack<BSTNode>();
    
    nodeStack.push(current);
    
    //traverse tree by putting nodes onto a stack
    while(!nodeStack.empty()) {
      //if found then set toremove
      if(nodeStack.peek() != null && nodeStack.peek().getData().equals(s)){
        toRemove = nodeStack.peek();
      }
      else{
        //System.out.println(ind + nodeStack.peek().getData());
      }
      nodeStack.pop();
    
      //add children to stack
      if(current == null){
      }
      else{
        nodeStack.push(current.right);
        nodeStack.push(current.left);
      }
    
      if(!nodeStack.empty()){
        current = nodeStack.peek();
      }
    }
    
    return toRemove;
    
  }
  
  /* findParent
   *  parameters:
   *      s -- String to be removed
   *      current -- BSTNode path
   *  return value: BSTNode
   * 
   *  finds parent of node to be removed
   */
  public BSTNode findParent(String s){
 
    //recursive
     /*
    if ((current.getLeft() != null && current.getLeft().getData().equals(s))
    || (current.getRight() != null && 
    current.getRight().getData().equals(s))){
      parent = current;
    }
    else {
      if (current.getLeft() != null && parent.getData() == "NULL"){
        parent = findParent(s, current.getLeft());
      }
      if (current.getRight() != null && parent.getData() == "NULL"){
        parent = findParent(s, current.getRight());
      }
    }
    
    return parent;*/
    
    //iterative
    BSTNode parent = new BSTNode ("NULL",null,null);
	  BSTNode EndNode = root.minNode();
	  BSTNode current = root;
    Stack<BSTNode> nodeStack = new Stack<BSTNode>();
    
    nodeStack.push(current);
    
    //traverse tree by putting each node onto a stack
    while(!nodeStack.empty()) {
      //if one of node's children are s then it is parent
      if(nodeStack.peek() != null && (nodeStack.peek().getLeft() != null 
      || nodeStack.peek().getRight() != null)){
        if (nodeStack.peek().getLeft() != null 
        && nodeStack.peek().getLeft().getData().equals(s)){
          parent = current;
        }
        else if (nodeStack.peek().getRight() != null 
        && nodeStack.peek().getRight().getData().equals(s)){
          parent = current;
        }
        else{
        }
      }
      else{
        //do nothing
      }
      nodeStack.pop();
    
      if(current == null){
      }
      else{
        nodeStack.push(current.right);
        nodeStack.push(current.left);
      }
    
      if(!nodeStack.empty()){
        current = nodeStack.peek();
      }
    }
    
    return parent;
    
    
    
  }

    

  
  /* printPreorder
   *  parameters: none
   *  return value: nothing
   * 
   *  prints tree in preorder starting at root
   */
  public void printPreorder() 
  { if (root != null) root.printPreorder(); }
  
  /* verifySearchOrder
   *  parameters:
   *  return value: nothing
   * 
   *  checks to see if search order is correct
   */
  public void verifySearchOrder() 
  { if (root != null) root.verifySearchOrder(); }

}

/* Class: EncryptionTree 
 *
 * An EncryptionTree is a special type of BST which knows how to encrypt a
 * String object (e.g. word) into a string that represents the path to the 
 * object in the tree, and decrypt a path into the String object (e.g. word)
 * it leads to.
 *
 * Methods: EncryptionTree, encrypt, decrypt
 */
class EncryptionTree extends BST {
  /* EncryptionTree
   *  parameters: none
   *  return value: nothing
   * 
   *  constructor for EncryptionTree
   */
  public EncryptionTree() {}
  
  /* encrypt
   *  parameters:
   *      item -- String of values to be encrypted
   *  return value: String
   * 
   *  uses codebook tree to encrypt a given string of words into the form
   *   r0/1 where r = root, 0 = left, 1 = right
   */
  public String encrypt(String item) {

    String encrypt = "";
    StringBuilder sb = new StringBuilder(encrypt);
    //if empty
    if(root == null){
      return "?";
    }
    //if can't find item, then return ?
    else if(findToRemove(item).getData().equals("NULL")){
      return "?";
    }
    //if found
    else{
      BSTNode find = findToRemove(item);
      BSTNode parent = findParent(item);
      
      while (find.getData() != root.getData()){
        //if on left set 0
        if(parent.getLeft() != null 
        && parent.getLeft().getData().equals(find.getData())){
          sb.insert(0, "0");
        }
        //if right set 1
        else {
          sb.insert(0, "1");
        }
        find = parent;
        parent = findParent(find.getData());
      }
    
      return "r" + sb;
    }
    
    
    
    
  }
  
  /* decrypt
   *  parameters:
   *      path -- String of path to follow in codebook
   *  return value: String
   * 
   *  decrypts a path into words from the encryptiontree
   */
  public String decrypt(String path) { 
  
    String plaintext = "";
    
    //if empty
    if(root == null){
      plaintext = "";
    }
    //if root
    else if(path.equals("r")){
      plaintext = root.getData();
    }
    //if not root
    else {
    
      StringBuilder sb = new StringBuilder(path);
    
      BSTNode start = root;
      char pathNum = sb.charAt(1);
      int count = 1; //keeps track of length of path
      
      while(start != null && count < path.length()){
        //if 0 go left
        if(pathNum == '0'){
          if(start.getLeft() != null){
            plaintext = start.getLeft().getData();
            start = start.getLeft();
          }
          else{
            plaintext = "";
            
          }
          
          
        }
        //if 1 go right
        else {
          if(start.getRight() != null){
          plaintext = start.getRight().getData();
          start = start.getRight();
          }
          else{
            plaintext = "";
          }
        }
        count++;
        if((count) < path.length()){
          pathNum = sb.charAt(count);
        }
      
      }
    }
    
    //if nothing found return ?
    if(plaintext.equals("")){
      plaintext = "?";
    }
    
    
    return plaintext;
  }
}
