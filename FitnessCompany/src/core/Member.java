package core;

import datatypes.Date;
import datatypes.Location;
import test.Testcase;

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

    /**
     * Testbed main for Member
     * all test cases and their requirements and description are in the test design document,
     * sorry for the really messy code (would've preferred to use a separate json file to store the test cases)
     */
    public static void main(String[] args) {
        Testcase[] compareToTestcases = {
                new Testcase("Create two instance of Member with random info, CompareMode = None", 0, new Object[] { CompareMode.None }, new Member[] { new Member("Kiana", "Kaslana", new Date("12/7/1998"), new Date("3/30/2717"), Location.Bridgewater), new Member("Raiden", "Mei", new Date("4/13/1997"), new Date("3/30/2500"), Location.Edison) }),

                new Testcase("Create one instance of Member with a random info, another one but with the same last name and a lexicographically greater first name than the first one, CompareMode = Name", -1, new Object[] { CompareMode.Name }, new Member[] { new Member("Kiana", "Kaslana", new Date("12/7/1998"), new Date("3/30/2717"), Location.Bridgewater), new Member("Siegfried", "Kaslana", new Date("11/25/1972"), new Date("7/19/2212"), Location.Piscataway) }),
                new Testcase("Create one instance of Member with a random info, another one but with the same last name and a lexicographically smaller first name than the first one, CompareMode = Name", 1, new Object[] { CompareMode.Name }, new Member[] { new Member("Kiana", "Kaslana", new Date("12/7/1998"), new Date("3/30/2717"), Location.Bridgewater), new Member("Kallen", "Kaslana", new Date("11/7/1998"), new Date("2/30/2717"), Location.Franklin) }),

                new Testcase("Create one instance of Member with a random info, another one but with the same county and a zip code greater than the first one, CompareMode = County", -1, new Object[] { CompareMode.County }, new Member[] { new Member("Kiana", "Kaslana", new Date("12/7/1998"), new Date("3/30/2717"), Location.Franklin), new Member("Raiden", "Mei", new Date("4/13/1997"), new Date("3/30/2500"), Location.Somerville) }),
                new Testcase("Create one instance of Member with a random info, another one but with the same county and a zip code less than the first one, CompareMode = County", 1, new Object[] { CompareMode.County }, new Member[] { new Member("Kiana", "Kaslana", new Date("12/7/1998"), new Date("3/30/2717"), Location.Franklin), new Member("Raiden", "Mei", new Date("4/13/1997"), new Date("3/30/2500"), Location.Bridgewater) }),

                new Testcase("Create one instance of Member with a random info, another one but an expiration date after the first one, CompareMode = ExpirationDate", -1, new Object[] { CompareMode.ExpirationDate }, new Member[] { new Member("Kiana", "Kaslana", new Date("12/7/1998"), new Date("3/30/2717"), Location.Franklin), new Member("Raiden", "Mei", new Date("4/13/1997"), new Date("4/1/2727"), Location.Somerville) }),
                new Testcase("Create one instance of Member with a random info, another one but an expiration date before the first one, CompareMode = ExpirationDate", 1, new Object[] { CompareMode.ExpirationDate }, new Member[] { new Member("Kiana", "Kaslana", new Date("12/7/1998"), new Date("3/30/2717"), Location.Franklin), new Member("Raiden", "Mei", new Date("4/13/1997"), new Date("3/21/2707"), Location.Somerville) }),
        };

        Testcase[] equalsTestcases = {
                new Testcase("Create an instance of Member with random info, and another one with the same first, last name, and date of birth.", true, null, new Member[] { new Member("Kiana", "Kaslana", new Date("12/7/1998"), new Date("3/30/2717"), Location.Franklin), new Member("kiana", "kaslana", new Date("12/7/1998"), new Date("3/30/2717"), Location.Franklin) }),
        };

        System.out.println("\n\n-- Now testing: Member.compareTo(other) --");
        for (int i = 0; i < compareToTestcases.length; i++) {
            Testcase current = compareToTestcases[i];
            System.out.printf("\nTest case #%d: %s:\n", i + 1, current.getDescription());

            Member.CompareMode = (Member.CompareMode) current.getOptionalParams()[0];
            Member currentMember = (Member) current.getTestClasses()[0];
            Member otherMember = (Member) current.getTestClasses()[1];

            int actualOutput = currentMember.compareTo(otherMember);
            System.out.printf("\tCase comparing - \n\t\t%s\n\t\t%s:\n\tcompareTo(other) returns %-5s: %s\n", currentMember, otherMember, actualOutput, (current.getExpectedOutput()).equals(actualOutput) ? "PASS" : "FAIL");
        }

        Member.CompareMode = Member.CompareMode.None;

        System.out.println("\n\n-- Now testing: Member.equals(other) --");
        for (int i = 0; i < equalsTestcases.length; i++) {
            Testcase current = equalsTestcases[i];
            System.out.printf("\nTest case #%d: %s:\n", i + 1, current.getDescription());

            Member currentMember = (Member) current.getTestClasses()[0];
            Member otherMember = (Member) current.getTestClasses()[1];

            boolean actualOutput = currentMember.equals(otherMember);
            System.out.printf("\tCase comparing - \n\t\t%s\n\t\t%s:\n\tequals(other) returns %-5s: %s\n", currentMember, otherMember, actualOutput, (current.getExpectedOutput()).equals(actualOutput) ? "PASS" : "FAIL");
        }
    }
}