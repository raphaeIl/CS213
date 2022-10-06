package datatypes;

/**
 * enum class representing the Location of a member, contains info about the location
 * @author Michael Liu, Genfu Liu
 */
public enum Location {
    Bridgewater ("Bridgewater", "08807", "Somerset"),
    Edison      ("Edison",      "08837", "Middlesex"),
    Franklin    ("Franklin",    "08873", "Somerset"),
    Piscataway  ("Piscataway",  "08854", "Middlesex"),
    Somerville  ("Somerville",  "08876", "Somerset");

    private final String city;
    private final String zipCode;
    private final String county;

    /**
     * Constructor to initialize a Location
     * @param city the city of that location
     * @param zipCode the zipcode of that location
     * @param county the county of that location
     */
    Location(String city, String zipCode, String county) {
        this.city = city;
        this.zipCode = zipCode;
        this.county = county;
    }

    /**
     * Getter for city
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter for zip code
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Getter for the county
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * toString to format the location
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s", city.toUpperCase(), zipCode, county.toUpperCase());
    }

    /**
     * Used to convert from the String representation of a location to the enum form
     * @param location the location in String form
     * @return the location in enum form, null if invalid location
     */
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
