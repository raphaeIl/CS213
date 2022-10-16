package core;

import core.entity.Member;
import datatypes.FitnessClass;
import utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Holds the list of fitness classes and also provides functionality
 * such as checking in, dropping, and displaying the schedule
 * @author Michael Liu, Genfu Liu
 */
public class ClassDatabase {

    /**
     * Array that holds all the available classes
     */
    private ClassSchedule classSchedule;

    /**
     * Displays all classes and their schedules
     */
    public void displaySchedule() {
        if (classSchedule == null)
            return;

        classSchedule.displaySchedule();
    }

    public void addFitnessClass(FitnessClass fitnessClass) {
        classSchedule.addFitnessClass(fitnessClass);
    }

    public FitnessClass getFitnessClass(FitnessClass fitnessClass) {
        return classSchedule.getFitnessClass(fitnessClass);
    }

    /**
     * Checking in a member to a class, passes the member along into the FitnessClass
     * @param fitnessClass the class the member is checking in
     * @param target the member that is checking in,
     * @return if the member was successfully checked in or not
     */
    public boolean checkIn(FitnessClass fitnessClass, Member target) {
        FitnessClass currentClass = classSchedule.getFitnessClass(fitnessClass);

        return currentClass.checkIn(target);
    }

    /**
     * Dropping a member from a class, passes the member along into the FitnessClass
     * @param fitnessClassType the class type the member is dropping from
     * @param target the member that is being dropped
     * @return if the member was successfully dropped or not
     */
    public boolean drop(FitnessClass fitnessClass, Member target) {
        FitnessClass currentClass = classSchedule.getFitnessClass(fitnessClass);

        return currentClass.drop(target);
    }

    public boolean checkInGuest(FitnessClass fitnessClass, Member guest) {
        FitnessClass currentClass = classSchedule.getFitnessClass(fitnessClass);

        return currentClass.checkInGuest(guest);
    }

    public boolean dropGuest(FitnessClass fitnessClass, Member guest) {
        FitnessClass currentClass = classSchedule.getFitnessClass(fitnessClass);

        return currentClass.dropGuest(guest);
    }

    public void loadSchedule(String filePath) {
        int classCount = Utils.fileLines(filePath);
        classSchedule = new ClassSchedule(classCount);

        Scanner scanner;

        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found!");
        }

        while (scanner.hasNext()) {
            String[] args = scanner.nextLine().split(" ");

            addFitnessClass(new FitnessClass(args[0], args[1], args[2], args[3]));
        }

        System.out.println("\n-Fitness classes loaded-");

        for (FitnessClass fitnessClass: classSchedule.getAllClasses())
            fitnessClass.displaySchedule();

        System.out.println("-end of class list.\n");
    }

    /**
     * Get the entire class schedule
     * @return an array of FitnessClass representing the schedule
     */
    public FitnessClass[] getClassSchedule() {
        return classSchedule.getAllClasses();
    }

    public boolean containsClass(String className) {
        for (FitnessClass fitnessClass: classSchedule.getAllClasses())
            if (fitnessClass.getClassName().equalsIgnoreCase(className))
                return true;

        return false;
    }

    public boolean containsInstructor(String classInstructor) {
        for (FitnessClass fitnessClass: classSchedule.getAllClasses())
            if (fitnessClass.getClassInstructor().equalsIgnoreCase(classInstructor))
                return true;

        return false;
    }

    public boolean containsLocation(String classLocation) {
        for (FitnessClass fitnessClass: classSchedule.getAllClasses())
            if (fitnessClass.getClassLocation().getCity().equalsIgnoreCase(classLocation))
                return true;

        return false;
    }
}
