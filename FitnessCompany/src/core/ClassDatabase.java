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
        System.out.println("-Fitness classes-");

        for (FitnessClass fitnessClass: classSchedule)
            fitnessClass.displaySchedule();
    }

    public void checkIn(String classType, Member target) {
        FitnessClassType fitnessClassType = FitnessClassType.fromString(classType);

        if (!MemberValidator.validateClassDatabase(classSchedule, classType, target))
            return;

        FitnessClass currentClass = classSchedule[fitnessClassType.ordinal()];

        currentClass.checkIn(target);
        System.out.printf("%s %s checked in %s.\n", target.getFname(), target.getLname(), currentClass.getClassName());
    }

    public void drop(FitnessClassType classType, Member target) {
        if (classType == null || target == null)
            return;

        classSchedule[classType.ordinal()].drop(target);
    }
}
