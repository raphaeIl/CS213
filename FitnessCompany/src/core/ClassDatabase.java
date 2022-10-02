package core;

import datatypes.FitnessClass;
import datatypes.FitnessClassType;
import datatypes.Time;
import utils.MemberValidator;
/**
 * @Author Michael Genfu
 */
public class ClassDatabase {

    private static final FitnessClass Pilates = new FitnessClass("Pilates", "Jennifer", Time.Morning);
    private static final FitnessClass Spinning = new FitnessClass("Spinning", "Denise", Time.Afternoon);
    private static final FitnessClass Cardio = new FitnessClass("Cardio", "Kim", Time.Afternoon);

    private FitnessClass[] classSchedule;

    public ClassDatabase() {
        classSchedule = new FitnessClass[] { Pilates, Spinning, Cardio };
    }

    public void displaySchedule() {
        System.out.println("\n-Fitness classes-");

        for (FitnessClass fitnessClass: classSchedule)
            fitnessClass.displaySchedule();

        System.out.println();
    }

    public boolean checkIn(FitnessClassType fitnessClassType, Member target) {
        FitnessClass currentClass = classSchedule[fitnessClassType.ordinal()];

        return currentClass.checkIn(target);
    }

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
