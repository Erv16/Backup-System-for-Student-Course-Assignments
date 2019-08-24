package studentCoursesBackup.myTree;

/**
 * @author Erwin Joshua Palani
 */

import studentCoursesBackup.myTree.ObserverI.Operation;

public interface SubjectI{

	/**
	 * The Subject interface that conatins method declarations for subscribing, unsubscribing
	 * and notifing all of the Observers.
	 */

	public void subscribe(ObserverI observer);

	public void unsubscribe(ObserverI observer);

	public void notifyAll(Operation op, String course);
}