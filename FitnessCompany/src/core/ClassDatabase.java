package core;

import datatypes.FitnessClass;
import datatypes.FitnessClassType;
import datatypes.Time;

/**
 * Holds the list of fitness classes and also provides functionality
 * such as checking in, dropping, and displaying the schedule
 * @Author Michael Liu, Genfu Liu
 */
public class ClassDatabase {

    /**
     * Array that holds all the available classes
     */
    private final FitnessClass[] classSchedule;

    public ClassDatabase() {
        FitnessClass Pilates = new FitnessClass("Pilates", "Jennifer", Time.Morning);
        FitnessClass Spinning = new FitnessClass("Spinning", "Denise", Time.Afternoon);
        FitnessClass Cardio = new FitnessClass("Cardio", "Kim", Time.Afternoon);

        classSchedule = new FitnessClass[] { Pilates, Spinning, Cardio };
    }

    /**
     * Displays all classes and their schedules
     */
    public void displaySchedule() {
        System.out.println("\n-Fitness classes-");

        for (FitnessClass fitnessClass: classSchedule)
            fitnessClass.displaySchedule();

        System.out.println();
    }

    /**
     * Checking in a member to a class, passes the member along into the FitnessClass
     * @param fitnessClassType the class type the member is checking in
     * @param target the member that is checking in,
     * @return if the member was successfully checked in or not
     */
    public boolean checkIn(FitnessClassType fitnessClassType, Member target) {
        FitnessClass currentClass = classSchedule[fitnessClassType.ordinal()];

        return currentClass.checkIn(target);
    }

    /**
     * Dropping a member from a class, passes the member along into the FitnessClass
     * @param fitnessClassType the class type the member is dropping from
     * @param target the member that is being dropped
     * @return if the member was successfully dropped or not
     */
    public boolean drop(FitnessClassType fitnessClassType, Member target) {
        FitnessClass currentClass = classSchedule[fitnessClassType.ordinal()];

        return currentClass.drop(target);
    }

    public FitnessClass getFitnessClass(FitnessClassType fitnessClassType) {
        return classSchedule[fitnessClassType.ordinal()];
    }

    public FitnessClass[] getClassSchedule() {
        return classSchedule;
    }
}
