package core;

import datatypes.FitnessClass;
import datatypes.FitnessClassType;
import datatypes.Time;

public class ClassDatabase {

    private static final FitnessClass Pilates = new FitnessClass("Pilates", "Jennifer", Time.Morning);
    private static final FitnessClass Spinning = new FitnessClass("Spinning", "Denise", Time.Afternoon);
    private static final FitnessClass Cardio = new FitnessClass("Cardio", "Kim", Time.Afternoon);

    public static FitnessClass[] classSchedule;

    public ClassDatabase() {
        classSchedule = new FitnessClass[] { Pilates, Spinning, Cardio };

        displaySchedule();
    }

    public void displaySchedule() {
        for (FitnessClass fitnessClass: classSchedule)
            System.out.println(fitnessClass);
    }

    public void checkIn(FitnessClassType classType, Member target) {
        if (classType == null || target == null)
            return;

        classSchedule[classType.ordinal()].checkIn(target);
    }

    public void drop(FitnessClassType classType, Member target) {
        if (classType == null || target == null)
            return;

        classSchedule[classType.ordinal()].drop(target);
    }
}
