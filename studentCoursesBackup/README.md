# CSX42: Assignment 3
## Name: Erwin Joshua Palani

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in studentCoursesBackup/src folder.

-----------------------------------------------------------------------
#### Note: Assuming you are in the erwin_palani_assign_3/studentCoursesBackup folder

## Instruction to clean:

####Command: 
ant -buildfile src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: 
ant -buildfile src/build.xml run -Darg0=<inputFile.txt> -Darg1=<deleteFile.txt> -Darg2=<output1File.txt> -Darg3=<output2File.txt> -Darg4=<output3File.txt> -Darg5=<Logger-Value> 

Example:

The last argument is the Logger value. 
ant -buildfile studentCoursesBackup/src/build.xml run -Darg0=inputFile.txt -Darg1=deleteFile.txt -Darg2=output1.txt -Darg3=output2.txt -Darg4=output3.txt -Darg5=2



-----------------------------------------------------------------------
## Description:

This objective of the assignment is to backup courses opted by students (their B numbers are used for identification) using the Observer Pattern. 

Each Student/B number is allocated courses which can either be updated or removed. 

Binary Search Tree:
The type of tree chosen for use in this assignment is the Binary Search Tree. A Binary Search Tree stores items in a sorted sequence whilst offering efficient lookup, addition and removal of items. Binary Search Tree was the choice to go with as primarily it stores items in a sorted order, as items in the left subtree are always smaller than the root of the tree and the items in the right subtree are always greater. Inorder traversal helps visit the nodes within the tree in sorted order and helps in displaying the nodes as per the necessary requirements.

The worst case Time complexities for carrying out different operations using a Binary Search Tree are as follows:

Searching: Need to traverse all elements, hence O(n)
Insertion: Need to traverse all elements, hence O(n)
Deletion: Need to traverse all elements, hence O(n)

The Average case time complexity of a Binary Search Tree for all of the above operations is O(log n)

The Space complexity of a Binary Search Tree is O(n)

The other data structure used is an Array List which has similar Time complexity as the Binary Search Tree.

Observer Pattern:

The Observer Pattern is a Design Pattern wherein a Subject, has a number of Observers subscribed to it and are notified on any changes being made.

The Tree Builder creates three trees, the main tree and the two backup trees. Nodes for all three trees are created and data is inserted into them. The nodes of the backup/cloned trees are registered as Observers using the subscribe(), with the main tree being the subject.The following approach has been used in this assignment for implementing the Observer pattern. There are two instances where the observers listening are updated. 

The first is when a course needs to be updated/added for an existing node with a B number. In this case, the node with the B number whose course needs to be updated is searched and on finding it a setCourseName() method is called which updates the course for the node in the main tree. It internally also invokes the notifyAll() method which in turn runs through all the observers registered and using the update() method updates their respective copies with course.

The second instance is when a course needs to be removed. The process as above is followed except now the deleteCourse() method is invoked for removing the course in the main tree's copy and then internally invoking the notifyAll() method for removing the courses from the observers copies as well.

Sources or references used:

1. https://www.sanfoundry.com/java-program-implement-binary-search-tree/

2. https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/

The two links mentioned above are the references used to understand and implement a Binary Search Tree

3. https://codereview.stackexchange.com/questions/19773/convert-string-to-integer-with-default-value
To understand how to avoid the NumberFormatException while parsing a String to an Integer value.

4.https://www.vogella.com/tutorials/DesignPatternObserver/article.html
Used as a reference to understand the Observer Pattern in general.

5. https://www.journaldev.com/709/java-read-file-line-by-line
Reference used for reading a file line by line.

6. https://www.geeksforgeeks.org/singleton-class-java/
Reference on how to create a Singleton Class and instance

7. https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied it, nor have I given my solution to anyone else. I understand that if I am involved in plagiarism or cheating I will have to sign an official form that I have cheated and that this form will be stored in my official university record. I also understand that I will receive a grade of 0 for the involved assignment for my first offense and that I will receive a grade of F for the course for any additional offense.""

Date: 07/09/2019
Name: Erwin Joshua Palani 


