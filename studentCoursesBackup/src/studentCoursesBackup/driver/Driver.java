
package studentCoursesBackup.driver;
    
/**
 * @author Erwin Joshua Palani
 */

import studentCoursesBackup.myTree.BST;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.MyLogger;
    
    public class Driver {

    	/**
    	 * The Driver class is responsible for forwarding/invoking all the necessary required operations or methods.
    	 */

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
	    	}
	    	catch(NumberFormatException nfe){
	    		return null;
	    	}
	    }
	
	public static void main(String[] args) {
	    
	    /*
	     * As the build.xml specifies the arguments as argX, in case the
	     * argument value is not given java takes the default value specified in
	     * build.xml. To avoid that, below condition is used.
	     */

	    try{
		    if ( (args.length != 6) || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
		    	 || args[3].equals("${arg3}") || args[4].equals("${arg4}") || args[5].equals("${arg5}")) {
			    
			    System.err.println("Error: Incorrect number of arguments. Program accepts 6 arguments."+"\n"+"Try the following command:\n\t" +
						"ant -buildfile src/build.xml run -Darg0=~/<inputFile> " +
						"-Darg1=~/<deleteFile> -Darg2=~/<output1> -Darg3=~/<output2> " +
						"-Darg4=~/<output3> -Darg5=~/<debug value>\n");
			    System.exit(1);
		    } // end of if

		    String inputFile = args[0];
		    String deleteFile = args[1];
		    String output1File = args[2];
		    String output2File = args[3];
		    String output3File = args[4];
		    Integer debugValue = tryParseInt(args[5]);

		    if(debugValue > 4 || debugValue < 0){
		    	System.err.println("Error: The debug value is out of scope. Please provide a value between the range of 0 to 4"+"\n"+
		    						"4 -> Print to stdout the number of nodes present in a tree. Writes the output to the file"+"\n"+
      								"3 -> Print to stdout everytime a constructor is called. Writes the output to the file"+"\n"+
      								"2 -> Print to stdout each of the results being written to file. Writes the output to the file"+"\n"+
      								"1 -> Print to stdout updates on FileProcessor and the lines being read from the input file. Writes the output to the file"+"\n"+
      								"0 -> No output is printed to stdout. Writes the output to the file");
		    	System.exit(1);
		    } // end of if

		    MyLogger.setDebugValue(debugValue);
		    FileProcessor fp = new FileProcessor(inputFile);
		    TreeBuilder tb = new TreeBuilder();

		    //Inserting B-numbers and courses into the trees from the input file.
		    BST tree = tb.buildTree(fp);

		    if(debugValue == 4){
		    	tree.countNodes();
		    	tb.treeClone1.countNodes();
		    	tb.treeClone2.countNodes();
		    } // end of if

		    Results res_orig = new Results();
		    Results res_clone_1 = new Results();
		    Results res_clone_2 = new Results();

		    res_orig.storeNewResult("The Original Tree contains the following B-numbers and corresponding courses opted: \r\n");
		    res_clone_1.storeNewResult("The First Backup Tree after insertion contains the following records: \r\n");
		    res_clone_2.storeNewResult("The Second Backup Tree after insertion contains the following records: \r\n");

		    //Storing the content read from the input file into Results
		    tree.printNodes(res_orig);
		    tb.treeClone1.printNodes(res_clone_1);
		    tb.treeClone2.printNodes(res_clone_2);

		    //Writing the content stored in Results to the respective output files.
		    res_orig.writeToFile(output1File);
		    res_clone_1.writeToFile(output2File);
		    res_clone_2.writeToFile(output3File);

		    fp.closeFile();


		    FileProcessor fpD = new FileProcessor(deleteFile);

		    //Removing courses associated to B-numbers based on the data read in from the delete file.
		    tree.removeCourse(fpD);

		    res_orig.storeNewResult("The Original Tree after deletion contains the following B-numbers and their updated courses: \r\n");
		    res_clone_1.storeNewResult("The First Backup Tree after deletion contains the following records: \r\n");
		    res_clone_2.storeNewResult("The Second Backup Tree after deletion contains the following records: \r\n");

		    //Storing the content read from the delete file into Results
		    tree.printNodes(res_orig);
		    tb.treeClone1.printNodes(res_clone_1);
		    tb.treeClone2.printNodes(res_clone_2);

		   	//Writing the content stored in Results after deletion to the respective output files.
		    res_orig.writeToFile(output1File);
		    res_clone_1.writeToFile(output2File);
		    res_clone_2.writeToFile(output3File);
	    } // end of try
	    catch(Exception e){
	    	e.printStackTrace();
	    	System.exit(1);
	    } //end of catch
		}  // end public static void main
    }  // end public class Driver
