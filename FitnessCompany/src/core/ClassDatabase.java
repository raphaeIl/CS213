package core;

import datatypes.Date;
import datatypes.FitnessClass;
import datatypes.FitnessClassType;
import datatypes.Time;
import utils.MemberValidator;

public class ClassDatabase {

    private static final FitnessClass Pilates = new FitnessClass("Pilates", "Jennifer", Time.Morning);
    private static final FitnessClass Spinning = new FitnessClass("Spinning", "Denise", Time.Afternoon);
    private static final FitnessClass Cardio = new FitnessClass("Cardio", "Kim", Time.Afternoon);

    public static FitnessClass[] classSchedule;

    public ClassDatabase() {
        classSchedule = new FitnessClass[] { Pilates, Spinning, Cardio };
    }

    public void displaySchedule() {
        System.out.println("\n-Fitness classes-");

        for (FitnessClass fitnessClass: classSchedule)
            fitnessClass.displaySchedule();

        System.out.println();
    }

    public void checkIn(String classType, Member target) {
        FitnessClassType fitnessClassType = FitnessClassType.fromString(classType);

        if (!MemberValidator.validateClassDatabase(classSchedule, classType, target))
            return;

        FitnessClass currentClass = classSchedule[fitnessClassType.ordinal()];

        if (currentClass.checkIn(target))
            System.out.printf("%s %s checked in %s.\n", target.getFname(), target.getLname(), currentClass.getClassName());
    }

    public void drop(String classType, Member target) {
        FitnessClassType fitnessClassType = FitnessClassType.fromString(classType);

        if (fitnessClassType == null) { // class does not exist
            System.out.printf("%s class does not exist.\n", classType);
            return;
        }

        FitnessClass currentClass = classSchedule[fitnessClassType.ordinal()];

        if (!currentClass.containsMember(target)) {
            System.out.printf("%s %s is not a participant in %s.\n", target.getFname(), target.getLname(), fitnessClassType);
            return;
        }

        if (currentClass.drop(target))
            System.out.printf("%s %s dropped %s.\n", target.getFname(), target.getLname(), currentClass.getClassName());
    }
}
