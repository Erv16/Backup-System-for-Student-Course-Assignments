package studentCoursesBackup.myTree;

/**
 * @author Erwin Joshua Palani
 */

import java.util.ArrayList;
import java.io.IOException;

import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.MyLogger.DebugLevel;

public class BST{

	/**
	 * The BST class has the primary objective of performing different opeartions on the Tree.
	 * <p>
	 * The BST class is used for inserting or searching for nodes and removing courses that belong
	 * to a particular node. It also prints the nodes that make up the entire Tree.
	 *
	 * source url 1 {@link https://www.sanfoundry.com/java-program-implement-binary-search-tree/} 
	 * source url 2 {@link https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/}
	 */

	private Node root;
	private Node new_Node = null;
	Results res = new Results();

	public BST(){

		/**
		 * Contructor
		 */

		root = null;
		MyLogger.writeMessage("Constructor of class BST reached",DebugLevel.CONSTRUCTOR);
	}

	public void printNodes(Results rsIN){

		/**
		 * A printNodes method that invokes the tree traversal operation and stores the results/nodes
		 * into the Results instance of a particular tree
		 *
		 * @param rsIN the Results instance to which the nodes need to be stored
		 */

		this.res = rsIN;
		traverseBST(this.root);
	}

	public Node insert(Integer bNumberIN, String courseNameIN){

		/**
		 * An insert method that helps in inserting nodes.
		 * <p>
		 * It first checks to see if a node containing the B number exists, if so
		 * it returns the node back. If it doesn't exist, it simply creates/inserts  
		 * a new node with the values passed as arguments.
		 *
		 * @param bNumberIN the B number attribute of a node to check its existance or
		 *                  for insertion
		 * @param courseNameIN the course name attribute of a node
		 * @return the inserted node.
		 */

		Node nodeCheck = search(root,bNumberIN, courseNameIN);
		if(nodeCheck != null){
			return new_Node;
		} // end of if 
		else{
			root = insert(root,bNumberIN,courseNameIN);
			return new_Node;
		} // end of else
	}

	private Node insert(Node node, Integer bNumberIN, String courseNameIN){

		/**
		 * This insert method checks to see if the new node to be inserted should be a root,
		 * or if it should be inserted to the left or to the right of the root node
		 * based on the B number.
		 *
		 * @param node the root node is used as a reference.
		 * @param bNumberIN the B number attribute of the new node.
		 * @param courseNameIN the course name attribute of the new node.
		 * @return the inserted node.
		 */

		if(node == null){
			this.new_Node = node = new Node(bNumberIN, courseNameIN);
		} // end of if
		else{
			if(bNumberIN <= node.getBNumber()){
				node.left = insert(node.left, bNumberIN, courseNameIN);
			} // end of if
			else{
				node.right = insert(node.right, bNumberIN, courseNameIN);
			} // end of else 
		} // end of else
		return node;
	}

	public Boolean isEmpty(){

		/**
		 * A method to check if the tree is empty
		 *
		 * @return a success or failure indicator if the tree is empty or not. 
		 */

		return root == null;
	}

	private void traverseBST(Node node){

		/**
		 * The traverseBST method makes use of a recursive inorder Tree traversal technique
		 * to store the nodes in an ascending order based on the B numbers.
		 *
		 * @param node the root node as the starting reference to begin traversal.
		 */

		if(node != null){
			traverseBST(node.left);

			// %04d is used for formatting the B number in a 4 digit integer format to store

			res.storeNewResult("B-number: "+String.format("%04d",node.getBNumber())+" -> Course(s): "+node.getCourse()+"\r\n");
			traverseBST(node.right);
		}
	}

	private Node search(Node node, Integer bNumberIN, String courseNameIN){

		/**
		 * The search method checks to see if a particular node exists in the Tree. 
		 * 
		 * @param node the root node as a starting point to begin searching.
		 * @param bNumberIN the B number attribute to be searched for its existance.
		 * @param courseNameIN to check if this course name is associated with an
		 *                     existing B number node.
		 *
		 * @return if the node exists, then the node itself. If it doesn't exist, then null.
		 */
		
		while(node != null){

			int val = node.getBNumber();

			if(bNumberIN == val && node.courseName.contains(courseNameIN)){
			 	return node;
			} // end of if
			if(bNumberIN < val){
			 	node = node.getLeftNode();
			} // end of else if
			else if(bNumberIN > val){
			 	node = node.getRightNode();		
			} // end of else if
			else if(bNumberIN == val && !node.courseName.contains(courseNameIN)){
				//node.courseName.add(courseNameIN);
			 	node.setCourseName(courseNameIN);
			 	return node;	
			} // end of else if
			else{
				return null;		
			} // end of else
		}
		return null;
	}

	public void removeCourse(FileProcessor fpIN){

		/**
		 * The removeCourse method removes a course that is assigned to a particular B number.
		 * <p>
		 * It checks to see if a node containing the B number exists. If it does, it proceeds 
		 * to remove the course by invoking the required method. If it doesn't exist, it records
		 * the corresponding error message.
		 *
		 * @param fpIN an instance of FileProcessor to read records from the file for course removal.
		 */

		FileProcessor fpDel = fpIN;
		String delStr = "";

		try{
			while((delStr = fpDel.readLine()) != null){
				String tempDel[] = delStr.split(":");

				if(!tempDel[0].matches("[0-9]{4}")){
					System.err.println("Error: The B-number "+tempDel[0]+" is invalid. It should be a four digit number. Please provide a valid input to proceed");
					MyLogger.writeMessage("The B-number "+tempDel[0]+" is invalid. It should be a four digit number. Please provide a valid input to proceed",DebugLevel.FILE_PROCESSOR);
					System.exit(1);
				} // end of if

				MyLogger.writeMessage("Line read contains B-number "+tempDel[0]+" and course to be deleted "+tempDel[1],DebugLevel.FILE_PROCESSOR);
				Node nodeToBeDeleted = search(root, Integer.parseInt(tempDel[0]),tempDel[1]);
				if(nodeToBeDeleted == null){
					res.storeNewResult("Note: This particular B number "+Integer.parseInt(tempDel[0])+" is invalid. No records exist."+"\r\n");
					System.err.println("Note: This particular B number "+Integer.parseInt(tempDel[0])+" is invalid. No records exist."+"\r\n");
				} // end of if
				else{
					nodeToBeDeleted.deleteCourse(tempDel[1]);
				} // end of else
			}
		} // end of try
		catch(IOException ioe){
			System.err.println("Error: Unable to parse line from the file");
			ioe.printStackTrace();
			System.exit(1);
		}
		catch(Exception e){
			System.err.println("Error: Either B-number or course is not provided in the file. Please provide both the inputs in the format <B-number:Course Name>");
			e.printStackTrace();
			System.exit(1);
		} // end of catch block
	} // end of catch

	public void countNodes(){

		/**
		 * Counts the number of nodes present in the tree. Not a part of the original
		 * specs. Used for debugging purpose.
		 */

		MyLogger.writeMessage("The tree contains "+countNodes(root)+" nodes",DebugLevel.NODE_COUNT);
	}

	private int countNodes(Node root){

		/**
		 * Counts the number of nodes present in the tree. Not a part of the original
		 * specs. Used for debugging purpose.
		 *
		 * @param root the root node as a reference to being counting the nodes.
		 * @return the count of nodes in a tree
		 */

		if(root == null){
			return 0;
		} // end of if
		else{
			int count = 1;
			count += countNodes(root.getLeftNode());
			count += countNodes(root.getRightNode());

			return count;
		} // end of else
	}

}