package core.entity;

import datatypes.Date;
import datatypes.Location;

/**
 * Member class that stores all member info
 * @author Michael Liu, Genfu Liu
 */
public class Member implements Comparable<Member>{

    /**
     * This field is made to mimic java's java.util.comparator class (since we can't use :( ) for easier and cleaner sorting code
     * can be switched to different sorting modes and compareTo will compare members according to that mode
     */
    public static CompareMode CompareMode = Member.CompareMode.None;

    protected static final int INITIAL_STANDARD_MEMBERSHIP_LENGTH = 3; // in months
    protected static final double STANDARD_ONE_TIME_FEE = 29.99;
    protected static final double STANDARD_MONTHLY_FEE = 39.99;

    /**
     * Member's first name
     */
    protected String fname;

    /**
     * Member's last name
     */
    protected String lname;

    /**
     * Member's date of birth
     */
    protected Date dob;

    /**
     * Member's membership expiration date
     */
    protected Date expire;

    /**
     * Member's location
     */
    protected Location location;

    /**
     * Construtor to initalize all the member's info
     * @param fname Member's first name
     * @param lname Member's last name
     * @param dob Member's date of birth
     * @param expire Member's membership expiration date
     * @param location Member's location
     */
    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;

        if (this.expire == null)
            this.expire = new Date().add(0, INITIAL_STANDARD_MEMBERSHIP_LENGTH);

        this.location = location;
    }

    /**
     * Gets the current member's membership fee due for the next billing statement
     * @return the membership fee in a double
     */
    public double memberShipFee() {
        return STANDARD_ONE_TIME_FEE + INITIAL_STANDARD_MEMBERSHIP_LENGTH * STANDARD_MONTHLY_FEE;
    }

    /**
     * Member first name getter
     * @return Member's first name
     */
    public String getFname() {
        return fname;
    }

    /**
     * Member last name getter
     * @return Member's last name
     */
    public String getLname() {
        return lname;
    }

    /**
     * Member dob getter
     * @return Member's date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Member membership expiration date getter
     * @return Member's membership expiration date
     */
    public Date getExpire() {
        return expire;
    }

    /**
     * Member location getter
     * @return Member's location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Inherited toString method to convert class to a formatted string
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("%s %s, DOB: %s, Membership expires %s, Location: %s", fname, lname, dob, expire, location);
    }

    /**
     * Inherited equals method that compares two member objects,
     * equal if they have the same first, last name and dob
     * @param obj the other object (most likely a member) to compare for equality
     * @return true if equal false if not
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Member other &&
                this.fname.equalsIgnoreCase(other.fname) &&
                this.lname.equalsIgnoreCase(other.lname) &&
                this.dob.equals(other.dob);
    }

    /**
     * Will compare this member to another based on the current Member.CompareMode,
     * there is a primary and secondary comparison corresponding to the project requirements
     * @param otherMember the object to be compared.
     * @return 0 if the members are equal, 1 if this member is greater than the other, -1 if less
     */
    @Override
    public int compareTo(Member otherMember) {
        if (otherMember == null)
            return 1;

        int primaryComparison = 0;
        int secondaryComparison = 0;

        switch (Member.CompareMode) {
            case Name:
                primaryComparison = this.getLname().compareTo(otherMember.getLname());
                secondaryComparison = this.getFname().compareTo(otherMember.getFname());
                break;
            case County:
                primaryComparison = this.location.getCounty().compareTo(otherMember.location.getCounty());
                secondaryComparison = this.location.getZipCode().compareTo(otherMember.location.getZipCode());
                break;
            case ExpirationDate:
                primaryComparison = this.expire.compareTo(otherMember.expire);
                secondaryComparison = -1;
                break;
            default:
                break;
        }

        if (primaryComparison > 0)
            return 1;
        else if (primaryComparison < 0)
            return -1;
        else {
            if (secondaryComparison > 0)
                return 1;
            else if (secondaryComparison < 0)
                return -1;
            else
                return 0;
        }
    }

    /**
     * Used to toggle between different sorting modes
     */
    public enum CompareMode {
        None,
        Name,
        County,
        ExpirationDate,
    }
}