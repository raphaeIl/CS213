package core.entity;

import datatypes.Date;
import datatypes.Location;

public class Family extends Member {

    private static final int FAMILY_MAX_GUESS_PASS = 1;

    protected static final double FAMILY_ONE_TIME_FEE = 29.99;
    protected static final double FAMILY_MONTHLY_FEE = 59.99;

    protected int guessPassCount;

    /**
     * Construtor to initalize all the member's info
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

    @Override
    public double memberShipFee() {
        return STANDARD_ONE_TIME_FEE + 3 * FAMILY_MONTHLY_FEE;
    }

    public boolean useGuestPass() {
        if (this.guessPassCount <= 0)
            return false;

        this.guessPassCount--;
        return true;
    }

    public boolean returnGuestPass() {
        this.guessPassCount++;
        return true;
    }

    public int getGuessPassCount() {
        return guessPassCount;
    }

    @Override
    public String toString() {
        return String.format("%s %s, DOB: %s, Membership expires %s, Location: %s, (Family) guest-pass remaining: %d", fname, lname, dob, expire, location, this.guessPassCount);
    }

}
