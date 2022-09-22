package datatypes;

public enum Time { // enum names are based on classname_instructor
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
