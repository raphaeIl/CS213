package datatypes;

import core.Member;
import core.MemberDatabase;

/**
 * Defines a fitness class the members can check in
 * @author Michael Liu, Genfu Liu
 */
public class FitnessClass {

    private String className;
    private String classInstructor;
    private Time classTime;

    /**
     * All current members that are in this class, (reused MemberDatabase class)
     */
    private MemberDatabase currentMembers;

    /**
     * Constructor used to initialize all the class info
     * @param className The name of the fitness class
     * @param classInstructor The instructor for this class
     * @param classTime The class time for this class
     */
    public FitnessClass(String className, String classInstructor, Time classTime) {
        this.className = className;
        this.classInstructor = classInstructor;
        this.classTime = classTime;
        this.currentMembers = new MemberDatabase();
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

    /**
     * Used to check if this class contains a specific member
     * @param member the member to be checked
     * @return if this class contains the member
     */
    public boolean containsMember(Member member) {
        return currentMembers.get(member) != null;
    }

    /**
     * Displays the current class' schedule along with all the participants
     */
    public void displaySchedule() {
        Member[] participants = currentMembers.getMembers();

        System.out.printf("%s - %s %s\n", this.className, this.classInstructor.toUpperCase(), this.classTime);

        if (participants.length > 0)
            System.out.printf("\t" + "** participants **\n");

        for (Member member: participants)
            System.out.printf("\t\t" + member + "\n");
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

    /**
     * toString used to format the fitness class
     * @return the formatted string
     */
    @Override
    public String toString() {
        return String.format("%s class taught by %s at %s with members:\n%s", className, classInstructor, classTime, currentMembers);
    }
}
