package core.entity;

import datatypes.Date;
import datatypes.Location;

/**
 * Premium class that represents a premium membership,
 * extends the Family class and includes specific data and operations to a premium membership;
 * @author Michael Liu, Genfu Liu
 */
public class Premium extends Family {

    protected static final int INITIAL_PREMIUM_MEMBERSHIP_LENGTH = 12; // in months

    private static final int PREMIUM_MAX_GUESS_PASS = 3;

    /**
     * Constructor to initialize all the member's info
     *
     * @param fname    Member's first name
     * @param lname    Member's last name
     * @param dob      Member's date of birth
     * @param expire   Member's membership expiration date
     * @param location Member's location
     */
    public Premium(String fname, String lname, Date dob, Date expire, Location location) {
        super(fname, lname, dob, expire == null ? new Date().add(1, 0) : expire, location);

        this.guessPassCount = PREMIUM_MAX_GUESS_PASS;
    }

    /**
     * Inherited methods that gets the current premium member's membership fee due for the next billing statement
     * @return the membership fee in a double
     */
    @Override
    public double memberShipFee() {
        return (INITIAL_PREMIUM_MEMBERSHIP_LENGTH - 1) * FAMILY_MONTHLY_FEE;
    }

    /**
     * Inherited toString method to format and display the member
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("%s %s, DOB: %s, Membership expires %s, Location: %s, (Premium) guest-pass remaining: %d", fname, lname, dob, expire, location, this.guessPassCount);
    }
}
