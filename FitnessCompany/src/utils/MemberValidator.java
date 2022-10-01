package utils;

import core.Member;
import core.MemberDatabase;
import datatypes.Date;
import datatypes.FitnessClass;
import datatypes.FitnessClassType;

/**
 * @Author Michael Genfu
 */
public class MemberValidator {

    public static Member validateAndCreateMember() {
        return null;
    }

    public static boolean validateDatabaseMember(MemberDatabase memberDatabase, Member member, String inputLocation) {
        if (memberDatabase.indexOf(member) != MemberDatabase.NOT_FOUND) { // checking if member is already in database
            System.out.printf("%s %s is already in the database.\n", member.getFname(), member.getLname());
            return false;
        }

        if (!member.getDob().isValid()) { // invalid dob
            System.out.printf("DOB %s: invalid calendar date!\n", member.getDob());
            return false;
        }

        if (member.getDob().equals(new Date()) || member.getDob().compareTo(new Date()) > 0) { // today or future dob
            System.out.printf("DOB %s: cannot be today or a future date!\n", member.getDob());
            return false;
        }

        if (!member.getExpire().isValid()) { // invalid expiration date
            System.out.printf("Expiration date %s: invalid calendar date!\n", member.getExpire());
            return false;
        }

        if (member.getAge() < 18) { // You are not 18 years old
            System.out.printf("DOB %s: must be 18 or older to join!\n", member.getDob());
            return false;
        }

        if (member.getLocation() == null) { // that location does not exist
            System.out.printf("%s: invalid location!\n", inputLocation);
            return false;
        }

        return true;
    }

    public static boolean validateClassDatabase(FitnessClass[] classDatabase, String classType, Member target) {
        // member info validation
        FitnessClassType fitnessClassType = FitnessClassType.fromString(classType);

        if (fitnessClassType == null) { // class does not exist
            System.out.printf("%s class does not exist.\n", classType);
            return false;
        }

        if (target.getExpire().compareTo(new Date()) < 0) { // membership expired
            System.out.printf("%s %s %s membership expired.\n", target.getFname(), target.getLname(), target.getDob());
            return false;
        }

        if (!target.getDob().isValid()) { // invalid dob
            System.out.printf("DOB %s: invalid calendar date!\n", target.getDob());
            return false;
        }

        FitnessClass currentClass = classDatabase[fitnessClassType.ordinal()];

        if (currentClass.containsMember(target)) { // member already checked in
            System.out.printf("%s %s has already checked in %s.\n", target.getFname(), target.getLname(), currentClass.getClassName());
            return false;
        }

        for (FitnessClass fitnessClass: classDatabase) // time conflict with other fitness class
            if (!fitnessClass.getClassName().equals(currentClass.getClassName()) &&
                    fitnessClass.containsMember(target) &&
                    fitnessClass.getClassTime() == currentClass.getClassTime()) {
                System.out.printf("%s time conflict -- %s %s has already checked in %s.\n", currentClass.getClassName(), target.getFname(), target.getLname(), fitnessClass.getClassName());
                return false;
            }

        return true;
    }

}
