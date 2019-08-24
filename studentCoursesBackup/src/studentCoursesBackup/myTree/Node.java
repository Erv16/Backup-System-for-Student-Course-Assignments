package studentCoursesBackup.myTree;

/**
 * @author Erwin Joshua Palani
 */

import java.util.ArrayList;

import studentCoursesBackup.util.MyLogger;
import studentCoursesBackup.util.MyLogger.DebugLevel;

public class Node implements SubjectI, ObserverI{

	/**
	 * The Node class stores a 4 digit B number and a list of courses. It implements the
	 * Subject and Observer interfaces. It allows observers to subscribe or unsubscribe to 
	 * a Subject and helps in updating or removing courses for a particular B number.
	 *
	 * source url 1 {@link https://www.sanfoundry.com/java-program-implement-binary-search-tree/} 
	 * source url 2 {@link https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/}
	 * source url 3 {@link https://www.vogella.com/tutorials/DesignPatternObserver/article.html}
	 */

	Node left, right;
	Integer bNumber;
	ArrayList<String> courseName = null;
	ArrayList<ObserverI> observerReferences = new ArrayList<ObserverI>();

	public Node(){

		/**
		 * Non - parameterized Constructor
		 */

		left = null;
		right = null;
		bNumber = null;
		courseName = new ArrayList<String>();
		MyLogger.writeMessage("Non - parametrized constructor of class Node reached",DebugLevel.CONSTRUCTOR);
	}

	public Node(Integer bNumberIN, String courseNameIN){

		/**
		 * Parameterized Constructor
		 */

		left = null;
		right = null;
		bNumber = bNumberIN;
		courseName = new ArrayList<String>();
		this.courseName.add(courseNameIN);
		MyLogger.writeMessage("Parametrized constructor of class Node reached",DebugLevel.CONSTRUCTOR);
	}

	public void setLeftNode(Node leftIN){

		/**
		 * Sets the Left node for a particular node
		 *
		 * @param leftIN the node to be inserted to the left
		 */

		this.left = leftIN;
	}

	public Node getLeftNode(){

		/**
		 * Returns the left node for a particular node
		 *
		 * @return the left node
		 */

		return this.left;
	}

	public void setRightNode(Node rightIN){

		/**
		 * Sets the right node for a particular node
		 *
		 * @param rightIN the node to be inserted to the right
		 */

		this.right = rightIN;
	}

	public Node getRightNode(){

		/**
		 * Returns the right node for a particular node
		 *
		 * @return the right node
		 */

		return this.right;
	}

	public void setBNumber(Integer bNoIN){

		/**
		 * Sets the B number for a particular node
		 *
		 * @param bNoIN the B number to be set
		 */

		this.bNumber = bNoIN;
	}

	public Integer getBNumber(){

		/**
		 * Returns the B number of a particular node
		 *
		 * @return the B number
		 */

		return bNumber;
	}

	public void setCourseName(String courseIN){

		/**
		 * This method adds the course to the list of courses and triggers the notifyAll method 
		 * for updating the course name in the clones with the insert operation. 
		 *
		 * @param courseIN the course name to be updated/inserted
		 */
		this.courseName.add(courseIN);
		notifyAll(Operation.insert,courseIN);
	}

	public ArrayList<String> getCourse(){

		/**
		 * Returns list of courses
		 *
		 * @return the array list instance containing the list of courses
		 */

		return courseName;
	}

	public void deleteCourse(String course){

		/**
		 * This method removes the course from the list of courses and triggers the notifyAll method 
		 * for removing the course name in the clones with the remove operation. 
		 *
		 * @param course the course name to be removed
		 */

		  if(courseName != null && courseName.contains(course)){
		  	courseName.remove(course);
		  } // end of if
		notifyAll(Operation.remove, course);
	}

	public void notifyAll(Operation op, String course){

		/**
		 * It notifies or ripples down the updates/changes to all of the observers.
		 * <p>
		 * The notifyAll method iterates through all of the observer references and invokes the update
		 * method on each of them to perform the respective updation or removal operation. 
		 *
		 * @param op the operation to be performed
		 * @param course the course to be updated/removed
		 */

		for(ObserverI obs: this.observerReferences){
			obs.update(op,course);
		} // end of for loop
	}

	@Override
	public void subscribe(ObserverI observer){

		/**
		 * Subscribes/registers the observer instances to the Subject
		 *
		 * @param observer the observer instance to be registered
		 */

		this.observerReferences.add(observer);
	}

	@Override
	public void unsubscribe(ObserverI observer){

		/**
		 * Unsubscribes/removes the observer instances registered to the Subject
		 *
		 * @param observer the observer instance to be removed
		 */

		this.observerReferences.remove(observer);
	}

	@Override
	public void update(Operation op, String course){

		/**
		 * Based on the operation, this method either inserts/updates or removes courses
		 * in the clones/observers.
		 *
		 * @param op the operation to be performed
		 * @param the course to be updated/removed
		 */

		switch(op){
			case insert:
				if(this.courseName.contains(course)){
					break;
				} // end of if
				else{
					this.courseName.add(course);
					break;
				} // end of else
			case remove:
				if(this.courseName != null && this.courseName.contains(course)){
					this.courseName.remove(course);
				} // end of if
				break;
		}
	}
}