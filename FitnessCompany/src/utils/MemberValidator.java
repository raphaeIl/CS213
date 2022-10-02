package utils;

import core.ClassDatabase;
import core.Member;
import core.MemberDatabase;
import datatypes.Date;
import datatypes.FitnessClass;
import datatypes.FitnessClassType;
import datatypes.Location;

/**
 * This class is mainly made to
 * @Author Michael Genfu
 */
public class MemberValidator {

    public static Member validateAndCreateMember(String fname, String lname, String dob, String expire, String location) {
        if (!validateMember(fname, lname, dob, expire, location))
            return null;

        return new Member(fname, lname, new Date(dob), new Date(expire), Location.fromString(location));
    }

    public static boolean validateMember(String fname, String lname, String dob, String expire, String location) { // expire and location are optional params
        Date dobDate = new Date(dob);

        if (!dobDate.isValid()) { // invalid dob
            System.out.printf("DOB %s: invalid calendar date!\n", dobDate);
            return false;
        }

        if (dobDate.equals(new Date()) || dobDate.compareTo(new Date()) > 0) { // today or future dob
            System.out.printf("DOB %s: cannot be today or a future date!\n", dobDate);
            return false;
        }

        if (expire != null) {
            Date expireDate = new Date(expire);

            if (!expireDate.isValid()) { // invalid expiration date
                System.out.printf("Expiration date %s: invalid calendar date!\n", expireDate);
                return false;
            }
        }

        if (Utils.dobToAge(dobDate) < 18) { // You are not 18 years old
            System.out.printf("DOB %s: must be 18 or older to join!\n", dobDate);
            return false;
        }

        if (location != null && Location.fromString(location) == null) { // that location does not exist
            System.out.printf("%s: invalid location!\n", location);
            return false;
        }

        return true;
    }

    public static boolean validateMemberDatabaseInsertion(MemberDatabase memberDatabase, Member member) {
        if (memberDatabase.indexOf(member) != MemberDatabase.NOT_FOUND) { // checking if member is already in database
            System.out.printf("%s %s is already in the database.\n", member.getFname(), member.getLname());
            return false;
        }

        return true;
    }

    public static boolean validateMemberCheckIn(ClassDatabase classDatabase, MemberDatabase memberDatabase, String classType, String fname, String lname, String dob) {
        // member info validation
        FitnessClassType fitnessClassType = FitnessClassType.fromString(classType);
        Member target = memberDatabase.get(fname, lname, new Date(dob));

        if (!MemberValidator.validateMember(fname, lname, dob, null, null))
            return false;

        if (target == null) { // member does not exist
            System.out.printf("%s %s %s is not in the database.\n", fname, lname, dob);
            return false;
        }

        if (fitnessClassType == null) { // class does not exist
            System.out.printf("%s class does not exist.\n", classType);
            return false;
        }

        if (target.getExpire().compareTo(new Date()) < 0) { // membership expired
            System.out.printf("%s %s %s membership expired.\n", target.getFname(), target.getLname(), target.getDob());
            return false;
        }

        FitnessClass currentClass = classDatabase.getFitnessClass(fitnessClassType);

        if (currentClass.containsMember(target)) { // member already checked in
            System.out.printf("%s %s has already checked in %s.\n", target.getFname(), target.getLname(), currentClass.getClassName());
            return false;
        }

        for (FitnessClass fitnessClass: classDatabase.getClassSchedule()) // time conflict with other fitness class
            if (!fitnessClass.getClassName().equals(currentClass.getClassName()) &&
                    fitnessClass.containsMember(target) &&
                    fitnessClass.getClassTime() == currentClass.getClassTime()) {
                System.out.printf("%s time conflict -- %s %s has already checked in %s.\n", currentClass.getClassName(), target.getFname(), target.getLname(), fitnessClass.getClassName());
                return false;
            }

        return true;
    }

    public static boolean validateMemberDrop(ClassDatabase classDatabase, MemberDatabase memberDatabase, String classType, String fname, String lname, String dob) {
        FitnessClassType fitnessClassType = FitnessClassType.fromString(classType);
        Member target = memberDatabase.get(fname, lname, new Date(dob));

        if (!MemberValidator.validateMember(fname, lname, dob, null, null))
            return false;

        if (fitnessClassType == null) { // class does not exist
            System.out.printf("%s class does not exist.\n", classType);
            return false;
        }

        if (!classDatabase.getFitnessClass(fitnessClassType).containsMember(new Member(fname, lname, new Date(dob), null, null))) {
            System.out.printf("%s %s is not a participant in %s.\n", fname, lname, fitnessClassType);
            return false;
        }

        if (target == null) { // member does not exist
            System.out.printf("%s %s %s is not in the database.\n", fname, lname, dob);
            return false;
        }

        return true;
    }
}
