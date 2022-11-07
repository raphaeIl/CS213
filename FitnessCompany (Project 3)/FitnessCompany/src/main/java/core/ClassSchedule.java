package core;

import javafx.GymManagerController;

/**
 * An array-based linear data structure that hold the list of fitnessclasses,
 * also provide the methods for managing classes as well as displaying them
 * @author Michael Liu, Genfu Liu
 */
public class ClassSchedule {

    /**
     * Array that holds all the available classes,
     * this will be a fixed size array with no growing or shrinking
     * (assuming class schedule does not change after loading)
     */
    private final FitnessClass[] classSchedule;

    /**
     * The current total number of classes
     */
    private int numClasses;

    /**
     * Constructor to initialize the class schedule with a fixed size
     * @param numClasses the fixed number of classes in this schedule
     */
    public ClassSchedule(int numClasses) {
        this.numClasses = 0;

        classSchedule = new FitnessClass[numClasses];
    }

    /**
     * Adds a fitness class to the class schedule
     * @param fitnessClass
     */
    public void addFitnessClass(FitnessClass fitnessClass) {
        classSchedule[numClasses++] = fitnessClass;
    }

    /**
     * Gets a specific fitness class from the schedule that matches the param's fitness class
     * @param fitnessClass the fitness class to be queried
     * @return the queried fitnessClass, null if not found
     */
    public FitnessClass getFitnessClass(FitnessClass fitnessClass) {
        int index = indexOf(fitnessClass);

        return index == -1 ? null : classSchedule[index];
    }

    /**
     * Gets the index of a specific fitness class
     * @param fitnessClass the fitness class to be queried
     * @return the index of the fitness class, -1 if not found
     */
    public int indexOf(FitnessClass fitnessClass) {
        for (int i = 0; i < classSchedule.length; i++)
            if (classSchedule[i].equals(fitnessClass))
                return i;

        return -1;
    }

    /**
     * Displays all classes and their schedules
     */
    public void displaySchedule() {
        GymManagerController.log("\n-Fitness classes-");

        for (FitnessClass fitnessClass: classSchedule)
            fitnessClass.displaySchedule();

        GymManagerController.log("-end of class list.\n");
    }

    /**
     * Getter to get the number of classes that is in the schedule
     * @return the number of classes
     */
    public int getNumClasses() {
        return numClasses;
    }

    /**
     * Gets all the fitness classes that is in the class schedule
     * @return the fitnessclasses as an array
     */
    public FitnessClass[] getAllClasses() {
        return classSchedule;
    }
}
