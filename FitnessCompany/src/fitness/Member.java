package fitness;

// S s
public class Member implements Comparable<Member>{
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
    public String toString() { // TODO: implement toString for Date and Location
        return String.format("%s %s, DOB: %s, Membership expires %s, Location: %s", fname, lname, dob, expire, location);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Member other &&
                this.fname.equals(other.fname) &&
                this.lname.equals(other.lname) &&
                this.dob.equals(other.dob); // TODO: implement equals for Date
    }

    @Override
    public int compareTo(Member o) {
        return 0;
    }
}












