package core;

import datatypes.Date;
import datatypes.Location;

/**
 * Member class that stores all member info
 * @Author Michael Liu, Genfu Liu
 */
public class Member implements Comparable<Member>{

    /**
     * This field is made to mimic java's java.util.comparator class (since we can't use :( ) for easier and cleaner sorting code
     * can be switched to different sorting modes and compareTo will compare members according to that mode
     */
    public static CompareMode CompareMode = Member.CompareMode.None;

    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Date getDob() {
        return dob;
    }

    public Date getExpire() {
        return expire;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("%s %s, DOB: %s, Membership expires %s, Location: %s", fname, lname, dob, expire, location);
    }

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