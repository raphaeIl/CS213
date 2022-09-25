package datatypes;

public enum Location {
    Bridgewater ("Bridgewater", "08807", "Somerset"),
    Edison      ("Edison",      "08837", "Middlexsex"),
    Franklin    ("Franklin",    "08873", "Somerset"),
    Piscataway  ("Piscataway",  "08854", "Middlesex"),
    Somerville  ("Somerville",  "08876", "Somerset");

    private final String city;
    private final String zipCode;
    private final String county;

    Location(String city, String zipCode, String county) {
        this.city = city;
        this.zipCode = zipCode;
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCounty() {
        return county;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", city.toUpperCase(), zipCode, county.toUpperCase());
    }

    public static Location fromString(String location) {

        switch (location.toLowerCase()) {
            case "bridgewater":
                return Bridgewater;
            case "edison":
                return Edison;
            case "franklin":
                return Franklin;
            case "piscataway":
                return Piscataway;
            case "somerville":
                return Somerville;
            default:
                return null;
        }
    }
}
