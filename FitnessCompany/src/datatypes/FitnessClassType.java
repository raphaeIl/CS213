package datatypes;

public enum FitnessClassType {
    Pilates,
    Spinning,
    Cardio;

    public static FitnessClassType fromString(String location) {
        switch (location.toLowerCase()) {
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
