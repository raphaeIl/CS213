package main.datatypes;

public class FitnessClass {

    public static final FitnessClass Pilates = new FitnessClass("Pilates", "Jennifer", Time.Pilates_Jennifer);
    public static final FitnessClass Spinning = new FitnessClass("Pilates", "Jennifer", Time.Spinning_Denise);
    public static final FitnessClass Cardio = new FitnessClass("Pilates", "Jennifer", Time.Cardio_Kim);

    private String className;
    private String classInstructor;
    private Time classTime;

    public FitnessClass(String className, String classInstructor, Time classTime) {
        this.className = className;
        this.classInstructor = classInstructor;
        this.classTime = classTime;
    }

    @Override
    public String toString() {
        return String.format("%s class taught by %s at %s", className, classInstructor, classTime);
    }
}
