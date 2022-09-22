package core;

import datatypes.FitnessClass;
import datatypes.FitnessClassType;
import datatypes.Time;

public class ClassDatabase {

    private static final FitnessClass Pilates = new FitnessClass("Pilates", "Jennifer", Time.Pilates_Jennifer);
    private static final FitnessClass Spinning = new FitnessClass("Spinning", "Denise", Time.Spinning_Denise);
    private static final FitnessClass Cardio = new FitnessClass("Cardio", "Kim", Time.Cardio_Kim);

    public static FitnessClass[] classSchedule;

    public ClassDatabase() {
        classSchedule = new FitnessClass[] { Pilates, Spinning, Cardio };
    }

    public void displaySchedule() {
        for (FitnessClass fitnessClass: classSchedule)
            System.out.println(fitnessClass);
    }

    public void checkIn(FitnessClassType classType, Member target) {
        classSchedule[classType.ordinal()].checkIn(target);
    }

    public void drop(FitnessClassType classType, Member target) {
        classSchedule[classType.ordinal()].drop(target);
    }
}
