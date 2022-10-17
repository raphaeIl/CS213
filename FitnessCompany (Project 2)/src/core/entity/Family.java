package core.entity;

import datatypes.Date;
import datatypes.Location;

/**
 * Family class that represents a family membership,
 * extends the Member class and includes specific data and operations to a family membership;
 * @author Michael Liu, Genfu Liu
 */
public class Family extends Member {

    private static final int FAMILY_MAX_GUESS_PASS = 1;

    protected static final int INITIAL_FAMILY_MEMBERSHIP_LENGTH = INITIAL_STANDARD_MEMBERSHIP_LENGTH; // in months
    protected static final double FAMILY_ONE_TIME_FEE = STANDARD_ONE_TIME_FEE;
    protected static final double FAMILY_MONTHLY_FEE = 59.99;

    /**
     * The amount of guess passes the current member has left
     */
    protected int guessPassCount;

    /**
     * Constructor to initialize all the member's info
     *
     * @param fname    Member's first name
     * @param lname    Member's last name
     * @param dob      Member's date of birth
     * @param expire   Member's membership expiration date
     * @param location Member's location
     */
    public Family(String fname, String lname, Date dob, Date expire, Location location) {
        super(fname, lname, dob, expire, location);

        guessPassCount = FAMILY_MAX_GUESS_PASS;
    }

    /**
     * Inherited methods that gets the current family member's membership fee due for the next billing statement
     * @return the membership fee in a double
     */
    @Override
    public double memberShipFee() {
        return FAMILY_ONE_TIME_FEE + INITIAL_FAMILY_MEMBERSHIP_LENGTH * FAMILY_MONTHLY_FEE;
    }

    /**
     * Use a guest pass when a guest is checking in
     * @return if the guest pass has been successfully used or not
     */
    public boolean useGuestPass() {
        if (this.guessPassCount <= 0)
            return false;

        this.guessPassCount--;
        return true;
    }

    /**
     * Returns a guest past when a guest is checking out
     * @return if the guest pass was successfully returned or not
     */
    public boolean returnGuestPass() {
        this.guessPassCount++;
        return true;
    }

    /**
     * Inherited toString method to format and display the member
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("%s %s, DOB: %s, Membership expires %s, Location: %s, (Family) guest-pass remaining: %d", fname, lname, dob, expire, location, this.guessPassCount);
    }

}
