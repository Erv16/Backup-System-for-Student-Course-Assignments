package studentCoursesBackup.util;

/**
 * @author Erwin Joshua Palani
 */

public class MyLogger{

    /*
      DEBUG_VALUE=4 [Print to stdout the number of nodes present in a tree. Writes the output to the file]
      DEBUG_VALUE=3 [Print to stdout everytime a constructor is called. Writes the output to the file]
      DEBUG_VALUE=2 [Print to stdout each of the results being written to file. Writes the output to the file.]
      DEBUG_VALUE=1 [Print to stdout updates on FileProcessor and the lines being read from the input file. Writes the output to the file.]
      DEBUG_VALUE=0 [No output should be printed from the application to stdout. It is ok to write to the output file though" ]
    */

    public static enum DebugLevel {RELEASE, FILE_PROCESSOR, IN_RESULTS, CONSTRUCTOR, NODE_COUNT
                                   };

    private static DebugLevel debugLevel;


    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
    case 4: debugLevel = DebugLevel.NODE_COUNT; break;
	  case 3: debugLevel = DebugLevel.CONSTRUCTOR; break;
    case 2: debugLevel = DebugLevel.IN_RESULTS; break;
    case 1: debugLevel = DebugLevel.FILE_PROCESSOR; break;
	  case 0: debugLevel = DebugLevel.RELEASE; break;
	}
    }

    public static void setDebugValue (DebugLevel levelIn) {

      /**
       * The setDebugValue is used for identifying which debug case
       * should be invoked based on the argument value.
       *
       * @param levelIn the debug level value
       */

	debugLevel = levelIn;
    }

    // @return None
    public static void writeMessage (String  message  ,
                                     DebugLevel levelIn ) {

      /**
       * The writeMessage method writes all of the debugging messages
       * associated with a particular debug level onto the console.
       * 
       * @param message the actual message that needs to be written
       * @param levelIn the associated debug level
       */

	if (levelIn == debugLevel)
	    System.out.println(message);
    } // end of if

    /**
	 * @return String
	 */
    public String toString() {

      /**
       * A toString method to print the debug level
       */ 
      
	return "Debug Level is " + debugLevel;
    }
}
