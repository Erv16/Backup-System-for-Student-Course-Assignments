package studentCoursesBackup.util;

/**
 * @author Erwin Joshua Palani
 */

import studentCoursesBackup.myTree.BST;
import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.myTree.ObserverI;
import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.MyLogger.DebugLevel;

import java.io.IOException;

public class TreeBuilder{

	/**
	 * The TreeBuilder class helps in creating clones of the original node. It helps in subscribing
	 * the observers to the subject and in validating the B number and Course attribute.
	 */

	FileProcessor fp = null;
	public BST treeClone1 = null, treeClone2 = null;

	public TreeBuilder(){

		/**
		 * Constructor
		 */

		MyLogger.writeMessage("Constructor of TreeBuilder Class reached",DebugLevel.CONSTRUCTOR);
	}

	/**
    	 * Returns a parsed Integer value. 
    	 * <p>
    	 * Parsing a String to an Integer value directly raises a NumberFormatException and terminates
		 * the execution of the program. To avoid this default exception from being raised, have created 
		 * the tryParseInt method that parses the String to an Integer value and returns the Integer value 
		 * everytime it reads an input from the file.
		 * <p>
		 * Source url {@link https://codereview.stackexchange.com/questions/19773/convert-string-to-integer-with-default-value}
		 *
    	 * @param str the string value that needs to be parsed 
		 *            into an Integer value
		 * @return the parsed Integer value
    	 */

	public static Integer tryParseInt(String str){
	    	try{
	    		return Integer.parseInt(str);
	    	} // end of try block
	    	catch(NumberFormatException nfe){
	    		return null;
	    	} // end of catch block
	    }

	public BST buildTree(FileProcessor fpIN){

		/**
		 * The buildTree method helps in creating a tree and cloning it. It also performs basic 
		 * validation such as checking if the B number is a valid 4 digit integer and if the course
		 * name is in the range A - K on reading the input.
		 *
		 * @param fpIN an instance of FileProcessor to help in parsing the input
		 * @return the main/original tree
		 */

		this.fp = fpIN;

		BST origTree = new BST();

		this.treeClone1 = new BST();
		this.treeClone2 = new BST();

		String inputStr = "";

		try{
			while((inputStr = fp.readLine()) != null){
				
				String temp[] = inputStr.split(":");

				// Validation to check if the B number is a valid 4 digit integer

				if(!temp[0].matches("[0-9]{4}")){
					System.err.println("The B-number "+temp[0]+" is invalid. It should be a four digit number. Please provide a valid input to proceed");
					MyLogger.writeMessage("The B-number "+temp[0]+" is invalid. It should be a four digit number. Please provide a valid input to proceed",DebugLevel.FILE_PROCESSOR);
					System.exit(1);
				} // end of if

				//Validation to check if the course name is within the A - K range

				char c = temp[1].charAt(0);
				if(c >= 'L' && c <= 'Z'){
					System.err.println("The course "+temp[1]+" is invalid. The valid course names are in the range from A to K. Please provide a valid input to proceed");
					MyLogger.writeMessage("The course "+temp[1]+" is invalid. The valid course names are in the range from A to K. Please provide a valid input to proceed",DebugLevel.FILE_PROCESSOR);
					System.exit(1);
				} // end of if
					MyLogger.writeMessage("The line read contains B-number "+temp[0]+" and course "+temp[1],DebugLevel.FILE_PROCESSOR);
					Node node_orig = origTree.insert(tryParseInt(temp[0]),temp[1]);
					Node backup_Node_1 = treeClone1.insert(tryParseInt(temp[0]),temp[1]);
					Node backup_Node_2 = treeClone2.insert(tryParseInt(temp[0]),temp[1]);
					node_orig.subscribe((ObserverI) backup_Node_1);
					node_orig.subscribe((ObserverI) backup_Node_2);
			}

		} // end of try block
		catch(IOException ioe){
			System.err.println("Error: Unable to parse line from the file");
			ioe.printStackTrace();
			System.exit(1);
		} // end of catch block
		catch(Exception e){
			System.err.println("Error: Either B-number or course is not provided in the file. Please provide both the inputs in the format <B-number:Course Name>");
			e.printStackTrace();
			System.exit(1);
		} // end of catch block

		return origTree;
	}
}