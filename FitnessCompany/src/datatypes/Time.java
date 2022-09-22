package datatypes;

public enum Time { // enum names are based on classname_in
    Pilates_Jennifer(9, 30),
    Spinning_Denise(14, 0),
    Cardio_Kim(14, 0);

    private int hour;
    private int minute;

    Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
