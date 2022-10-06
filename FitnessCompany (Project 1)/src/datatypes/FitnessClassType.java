package datatypes;

/**
 * Represents a specific fitness class type
 * @author Michael Liu, Genfu Liu
 */
public enum FitnessClassType {
    Pilates,
    Spinning,
    Cardio;

    /**
     * Used to convert from the String representation of a fitness class to the enum form
     * @param fitnessClass the fitness class in String form
     * @return the fitness class in FitnessClassType (enum) form, null if invalid class
     */
    public static FitnessClassType fromString(String fitnessClass) {
        switch (fitnessClass.toLowerCase()) {
            case "pilates":
                return Pilates;
            case "spinning":
                return Spinning;
            case "cardio":
                return Cardio;
            default:
                return null;
        }
    }
}
