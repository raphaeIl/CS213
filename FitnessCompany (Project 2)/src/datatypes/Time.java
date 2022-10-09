package datatypes;

/**
 * Represents a Time in a day
 * @author Michael Liu, Genfu Liu
 */
public enum Time {
    Morning(9, 30),
    Afternoon(14, 0),
    Evening(18, 30);

    private final int hour;
    private final int minute;

    /**
     * Constructor to create a time in a day
     * @param hour the current hour
     * @param minute the current minute
     */
    Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * toString to format the string into hh:mm form
     * @return
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }

    public static Time fromString(String time) {
        switch (time.toLowerCase()) {
            case "morning":
                return Time.Morning;
            case "afternoon":
                return Time.Afternoon;
            case "evening":
                return Time.Evening;
            default:
                return null;
        }
    }
}
