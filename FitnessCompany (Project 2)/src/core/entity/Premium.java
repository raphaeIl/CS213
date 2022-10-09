package core.entity;

import datatypes.Date;
import datatypes.Location;

public class Premium extends Family {

    private static final int PREMIUM_MAX_GUESS_PASS = 3;

    /**
     * Construtor to initalize all the member's info
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

    @Override
    public double memberShipFee() {
        return 11 * FAMILY_MONTHLY_FEE;
    }

    @Override
    public String toString() {
        return String.format("%s %s, DOB: %s, Membership expires %s, Location: %s, (Premium) guest-pass remaining: %d", fname, lname, dob, expire, location, this.guessPassCount);
    }
}
