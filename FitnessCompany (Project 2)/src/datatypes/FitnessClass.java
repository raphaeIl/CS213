package datatypes;

import core.entity.Member;
import core.MemberDatabase;

/**
 * Defines a fitness class the members can check in
 * @author Michael Liu, Genfu Liu
 */
public class FitnessClass {

    private String className;
    private String classInstructor;
    private Time classTime;
    private Location classLocation;

    /**
     * All current members that are in this class, (reused MemberDatabase class)
     */
    private MemberDatabase currentMembers;

    private MemberDatabase currentGuests;

    /**
     * Constructor used to initialize all the class info
     * @param className The name of the fitness class
     * @param classInstructor The instructor for this class
     * @param classTime The class time for this class
     */
    public FitnessClass(String className, String classInstructor, Time classTime, Location classLocation) {
        this.className = className;
        this.classInstructor = classInstructor;
        this.classTime = classTime;
        this.classLocation = classLocation;

        this.currentMembers = new MemberDatabase();
        this.currentGuests = new MemberDatabase();
    }

    public FitnessClass(String className, String classInstructor, String classTime, String classLocation) {
        this(className, classInstructor, Time.fromString(classTime), Location.fromString(classLocation));
    }

    /**
     * Used for checking in a member
     * @param member The member to be checked in, has to be a valid member, checks are done on the previous step
     * @return if the member was successfully checked in or not
     */
    public boolean checkIn(Member member) {
        return currentMembers.add(member);
    }

    /**
     * Used for dropping a member
     * @param member The member to be dropped, has to be a valid member
     * @return if the member was successfully dropped in or not
     */
    public boolean drop(Member member) {
        return currentMembers.remove(member);
    }

    public boolean checkInGuest(Member member) {
        return currentGuests.add(member);
    }

    public boolean dropGuest(Member member) {
        return currentGuests.remove(member);
    }

    /**
     * Used to check if this class contains a specific member
     * @param member the member to be checked
     * @return if this class contains the member
     */
    public boolean containsMember(Member member) {
        return currentMembers.get(member) != null;
    }

    public boolean containsGuest(Member guest) {
        return currentGuests.get(guest) != null;
    }

    /**
     * Displays the current class' schedule along with all the participants
     */
    public void displaySchedule() {
        Member[] participants = currentMembers.getMembers();
        Member[] guests = currentGuests.getMembers();

        System.out.println(this);

        if (participants.length > 0)
            System.out.println("- Participants -");

        for (Member member: participants)
            System.out.printf("\t" + member + "\n");

        if (guests.length > 0)
            System.out.println("- Guests -");

        for (Member guest: guests)
            System.out.printf("\t" + guest + "\n");

    }

    /**
     * Getter for class name
     * @return the class name
     */
    public String getClassName() {
        return className;
    }

    /**
     * Getter for the class instructor
     * @return the class instructor
     */
    public String getClassInstructor() {
        return classInstructor;
    }

    /**
     * Getter for the class time
     * @return a Time enum representing the class time
     */
    public Time getClassTime() {
        return classTime;
    }

    public Location getClassLocation() {
        return classLocation;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FitnessClass other &&
                this.className.equalsIgnoreCase(other.className) &&
                    this.classLocation.equals(other.classLocation) &&
                        this.classInstructor.equalsIgnoreCase(other.classInstructor);
//                        &&  this.classTime.equals(other.classTime);
    }

    /**
     * toString used to format the fitness class
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("%s - %s, %s, %s", this.className.toUpperCase(), this.classInstructor.toUpperCase(), this.classTime, this.classLocation.getCity().toUpperCase());
    }
}
