package datatypes;

import core.Member;
import core.MemberDatabase;

import java.util.ArrayList;
import java.util.List;

public class FitnessClass {

    private String className;
    private String classInstructor;
    private Time classTime;

    private List<Member> currentMembers;

    public FitnessClass(String className, String classInstructor, Time classTime) {
        this.className = className;
        this.classInstructor = classInstructor;
        this.classTime = classTime;
        this.currentMembers = new ArrayList<>();
    }

    public boolean checkIn(Member member) {
        return currentMembers.add(member);
    }

    public boolean drop(Member member) {
        return currentMembers.remove(member);
    }

    public boolean containsMember(Member member) {
        return currentMembers.contains(member);
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

    public List<Member> getCurrentMembers() {
        return currentMembers;
    }

    @Override
    public String toString() {
        String toString = "-Fitness classes-\n";

        toString += String.format("%s - %s, %s\n", this.className, this.classInstructor.toUpperCase(), this.classTime);
        toString += "\t" + "** participants **\n";

        for (Member member: currentMembers)
            toString += "\t" + member + "\n";

//        return String.format("%s class taught by %s at %s with members:\n%s", className, classInstructor, classTime, currentMembers);
        return toString;
    }
}
