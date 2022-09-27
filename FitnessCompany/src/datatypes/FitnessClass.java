package datatypes;

import core.Member;
import core.MemberDatabase;

public class FitnessClass {

    private String className;
    private String classInstructor;
    private Time classTime;

    private MemberDatabase currentMembers;

    public FitnessClass(String className, String classInstructor, Time classTime) {
        this.className = className;
        this.classInstructor = classInstructor;
        this.classTime = classTime;
        this.currentMembers = new MemberDatabase();
    }

    public boolean checkIn(Member member) {
        return currentMembers.add(member);
    }

    public boolean drop(Member member) {
        return currentMembers.remove(member);
    }

    public boolean containsMember(Member member) {
        return currentMembers.indexOf(member) != -1;
    }

    public String getClassName() {
        return className;
    }

    public String getClassInstructor() {
        return classInstructor;
    }

    public Time getClassTime() {
        return classTime;
    }

    @Override
    public String toString() {
        return String.format("%s class taught by %s at %s with members:\n%s", className, classInstructor, classTime, currentMembers);
    }

    public void displaySchedule() {
        System.out.printf("%s - %s, %s\n", this.className, this.classInstructor.toUpperCase(), this.classTime);
        System.out.printf("\t" + "** participants **\n");

        for (Member member: currentMembers.getMembers())
            System.out.printf("\t\t" + member + "\n");
    }
}
