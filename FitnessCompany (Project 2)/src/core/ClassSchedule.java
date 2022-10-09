package core;

import core.entity.Member;
import datatypes.FitnessClass;
import datatypes.FitnessClassType;
import utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Holds the list of fitness classes and also provides functionality
 * such as checking in, dropping, and displaying the schedule
 * @author Michael Liu, Genfu Liu
 */
public class ClassSchedule {

    /**
     * Array that holds all the available classes
     */
    private FitnessClass[] classSchedule;

    private int numClasses;

    /**
     * ClassDatabase constructor used to initialize all the fitness classes
     */
    public ClassSchedule() {
        numClasses = 0;
    }

    public void loadSchedule(String filePath) {
        int classCount = Utils.fileLines(filePath);
        classSchedule = new FitnessClass[classCount];

        Scanner scanner;

        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!");
        }

        while (scanner.hasNext()) {
            String[] args = scanner.nextLine().split(" ");

            classSchedule[numClasses++] = new FitnessClass(args[0], args[1], args[2], args[3]);
        }

        System.out.println("\n-Fitness classes loaded-");

        for (FitnessClass fitnessClass: classSchedule)
            fitnessClass.displaySchedule();

        System.out.println("-end of class list.\n");
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

        System.out.println("-end of class list.");
    }

    /**
     * Checking in a member to a class, passes the member along into the FitnessClass
     * @param fitnessClassType the class type the member is checking in
     * @param target the member that is checking in,
     * @return if the member was successfully checked in or not
     */
    public boolean checkIn(FitnessClass fitnessClass, Member target) {
        FitnessClass currentClass = classSchedule[indexOf(fitnessClass)];

        return currentClass.checkIn(target);
    }

    /**
     * Dropping a member from a class, passes the member along into the FitnessClass
     * @param fitnessClassType the class type the member is dropping from
     * @param target the member that is being dropped
     * @return if the member was successfully dropped or not
     */
    public boolean drop(FitnessClass fitnessClass, Member target) {
        FitnessClass currentClass = classSchedule[indexOf(fitnessClass)];

        return currentClass.drop(target);
    }

    public boolean checkInGuest(FitnessClass fitnessClass, Member guest) {
        FitnessClass currentClass = classSchedule[indexOf(fitnessClass)];

        return currentClass.checkInGuest(guest);
    }

    public boolean dropGuest(FitnessClass fitnessClass, Member guest) {
        FitnessClass currentClass = classSchedule[indexOf(fitnessClass)];

        return currentClass.dropGuest(guest);
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
     * Get the entire class schedule
     * @return an array of FitnessClass representing the schedule
     */
    public FitnessClass[] getClassSchedule() {
        return classSchedule;
    }

    public boolean containsClass(String className) {
        for (FitnessClass fitnessClass: classSchedule)
            if (fitnessClass.getClassName().equalsIgnoreCase(className))
                return true;

        return false;
    }

    public boolean containsInstructor(String classInstructor) {
        for (FitnessClass fitnessClass: classSchedule)
            if (fitnessClass.getClassInstructor().equalsIgnoreCase(classInstructor))
                return true;

        return false;
    }

    public boolean containsLocation(String classLocation) {
        for (FitnessClass fitnessClass: classSchedule)
            if (fitnessClass.getClassLocation().getCity().equalsIgnoreCase(classLocation))
                return true;

        return false;
    }
}
