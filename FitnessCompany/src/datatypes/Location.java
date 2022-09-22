package datatypes;

public enum Location {
    Bridgewater ("Bridgewater", "08807", "Somerset County"),
    Edison      ("Edison",      "08837", "Middlexsex County"),
    Franklin    ("Franklin",    "08873", "Somerset County"),
    Piscataway  ("Piscataway",  "08854", "Middlesex County"),
    Somerville  ("Somerville",  "08876", "Somerset County");

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
        return String.format("%s, %s, %s", city, county, zipCode);
    }
}
