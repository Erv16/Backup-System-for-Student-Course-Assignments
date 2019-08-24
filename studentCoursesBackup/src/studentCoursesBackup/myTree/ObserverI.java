package studentCoursesBackup.myTree;

/**
 * @author Erwin Joshua Palani
 */

public interface ObserverI{

	/**
	 * The Observer interface containing the enum and the update method declaration.
	 */

	enum Operation {insert,remove};

	void update(Operation op, String course);
}