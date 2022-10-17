package core;

import client.GymManager;
import core.entity.Member;
import utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Holds the fitness class schedule and also provides functionality
 * such as checking in, dropping, and displaying the schedule
 * @author Michael Liu, Genfu Liu
 */
public class ClassDatabase {

    /**
     * The ClassSchedule that holds all the available classes
     */
    private ClassSchedule classSchedule;

    /**
     * Displays all classes and their schedules
     */
    public void displaySchedule() {
        if (classSchedule == null || classSchedule.getNumClasses() == 0) {
            GymManager.log("Fitness class schedule is empty.");
            return;
        }

        classSchedule.displaySchedule();
    }

    /**
     * Adds a fitness class to the class schedule, passes it to the ClassSchedule object
     * @param fitnessClass the fitness class to be added
     */
    public void addFitnessClass(FitnessClass fitnessClass) {
        classSchedule.addFitnessClass(fitnessClass);
    }

    /**
     * Finds and returns a fitness class from the schedule
     * @param fitnessClass the fitness class to be queried
     * @return the queried FitnessClass
     */
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
     * Dropping/Checking out a member from a class, passes the member along into the FitnessClass
     * @param fitnessClass the class the member is dropping from
     * @param target the member that is being dropped
     * @return if the member was successfully dropped or not
     */
    public boolean drop(FitnessClass fitnessClass, Member target) {
        FitnessClass currentClass = classSchedule.getFitnessClass(fitnessClass);

        return currentClass.drop(target);
    }

    /**
     * Checking in a guest to a class, passes the guest along into the FitnessClass
     * @param fitnessClass the class the guest is checking in
     * @param guest the guest that is checking in,
     * @return if the guest was successfully checked in or not
     */
    public boolean checkInGuest(FitnessClass fitnessClass, Member guest) {
        FitnessClass currentClass = classSchedule.getFitnessClass(fitnessClass);

        return currentClass.checkInGuest(guest);
    }

    /**
     * Dropping/Checking out a guest from a class, passes the guest along into the FitnessClass
     * @param fitnessClass the class the guest is dropping from
     * @param guest the guest that is being dropped
     * @return if the guest was successfully dropped or not
     */
    public boolean dropGuest(FitnessClass fitnessClass, Member guest) {
        FitnessClass currentClass = classSchedule.getFitnessClass(fitnessClass);

        return currentClass.dropGuest(guest);
    }

    /**
     * This is used to load all the fitness classes from a file to the current class database
     * @param filePath the file's path
     * @throws RuntimeException if the filepath is invalid (file not found)
     */
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

    /**
     * Checks if the class schedule contains a certain class
     * @param className the class that is being checked
     * @return if the class exists in the schedule
     */
    public boolean containsClass(String className) {
        for (FitnessClass fitnessClass: classSchedule.getAllClasses())
            if (fitnessClass.getClassName().equalsIgnoreCase(className))
                return true;

        return false;
    }

    /**
     * Checks if the class schedule contains a certain class with the specified instructor
     * @param classInstructor the instructor of the class that is being checked
     * @return if the class with this instructor exists
     */
    public boolean containsInstructor(String classInstructor) {
        for (FitnessClass fitnessClass: classSchedule.getAllClasses())
            if (fitnessClass.getClassInstructor().equalsIgnoreCase(classInstructor))
                return true;

        return false;
    }

    /**
     * Checks if the class schedule contains a certain class with the specified class location
     * @param classLocation the location of the class that is being checked
     * @return if the class with this location exists
     */
    public boolean containsLocation(String classLocation) {
        for (FitnessClass fitnessClass: classSchedule.getAllClasses())
            if (fitnessClass.getClassLocation().getCity().equalsIgnoreCase(classLocation))
                return true;

        return false;
    }
}
