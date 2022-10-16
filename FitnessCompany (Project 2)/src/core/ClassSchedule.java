package core;

import datatypes.FitnessClass;

public class ClassSchedule {

    /**
     * Array that holds all the available classes,
     * this will be a fixed size array with no growing or shrinking
     * (assuming class schedule does not change after loading)
     */
    private final FitnessClass[] classSchedule;

    private int numClasses;

    public ClassSchedule(int numClasses) {
        this.numClasses = 0;

        classSchedule = new FitnessClass[numClasses];
    }

    public void addFitnessClass(FitnessClass fitnessClass) {
        classSchedule[numClasses++] = fitnessClass;
    }

    /**
     * Gets a specific fitness class from the schedule
     * @param fitnessClassType
     * @return the FitnessClass
     */
    public FitnessClass getFitnessClass(FitnessClass fitnessClass) {
        int index = indexOf(fitnessClass);

        return index == -1 ? null : classSchedule[index];
    }

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
        if (classSchedule == null || classSchedule.length == 0) {
            System.out.println("Fitness class schedule is empty.");
            return;
        }

        System.out.println("\n-Fitness classes-");

        for (FitnessClass fitnessClass: classSchedule)
            fitnessClass.displaySchedule();

        System.out.println("-end of class list.\n");
    }

    public FitnessClass[] getAllClasses() {
        return classSchedule;
    }
}
