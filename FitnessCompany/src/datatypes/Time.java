package datatypes;

/**
 * Represents a Time in a day
 * @Author Michael Liu, Genfu Liu
 */
public enum Time {
    Morning(9, 30),
    Afternoon(14, 0);

    private final int hour;
    private final int minute;

    Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
