package core;

import datatypes.Date;
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
    }

    public void displaySchedule() {
        for (FitnessClass fitnessClass: classSchedule)
            System.out.println(fitnessClass);
    }

    public void checkIn(FitnessClassType classType, Member target) {
        // member info validation
        if (classType == null || target == null) // class/member does not exist
            return;

        if (target.getExpire().compareTo(new Date()) < 0 || // membership expired
                !target.getDob().isValid()) // invalid dob
            return;

        FitnessClass currentClass = classSchedule[classType.ordinal()];

        if (currentClass.containsMember(target)) // member already checked in
            return;

        for (FitnessClass fitnessClass: classSchedule) // time conflict with other fitness class
            if (!fitnessClass.getClassName().equals(currentClass.getClassName()) &&
                    fitnessClass.containsMember(target) &&
                        fitnessClass.getClassTime() == currentClass.getClassTime())
                return;

        currentClass.checkIn(target);
        System.out.println("successfully checked in: " + target);
    }

    public void drop(FitnessClassType classType, Member target) {
        if (classType == null || target == null)
            return;

        classSchedule[classType.ordinal()].drop(target);
    }
}
