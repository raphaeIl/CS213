package core;

import datatypes.Date;
import datatypes.Location;
/**
 * @Author Michael Genfu
 */
public class Member implements Comparable<Member>{
    /**
     * This field is made to mimic java's java.util.comparator class for easier and cleaner sorting code
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

    public enum CompareMode {
        None,
        Name,
        County,
        ExpirationDate,
    }
}