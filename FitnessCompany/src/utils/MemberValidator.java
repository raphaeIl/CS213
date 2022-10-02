package utils;

import core.ClassDatabase;
import core.Member;
import core.MemberDatabase;
import datatypes.Date;
import datatypes.FitnessClass;
import datatypes.FitnessClassType;
import datatypes.Location;

/**
 * This class is mainly responsible for validating member info from client input,
 * also displays error messages when there is an invalid input.
 * @Author Michael Liu, Genfu Liu
 */
public class MemberValidator {

    /**
     * Validates all the member info that the user inputs
     * @param fname Member first name
     * @param lname Member last name
     * @param dob Member dob in String form
     * @param expire Membership expiration date in String form
     * @param location Member location in String form
     * @return true if all the member info are valid, false and also prints out an error message if invalid
     */
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

    /**
     * Validates member info using validateMember() but additionally creates a Member if all info are valid
     * @params all params are the Member's info
     * @return a created Member object if all the info are valid, null if invalid
     */
    public static Member validateAndCreateMember(String fname, String lname, String dob, String expire, String location) {
        if (!validateMember(fname, lname, dob, expire, location))
            return null;

        return new Member(fname, lname, new Date(dob), new Date(expire), Location.fromString(location));
    }

    /**
     * Used specifically when adding members to the MemberDatabase to check if it's a valid operation
     * @param memberDatabase The MemberDatabase we're attempting to insert to
     * @param member The member we're trying to insert
     * @return if the member can be inserted or not
     */
    public static boolean validateMemberDatabaseInsertion(MemberDatabase memberDatabase, Member member) {
        if (memberDatabase.indexOf(member) != MemberDatabase.NOT_FOUND) { // checking if member is already in database
            System.out.printf("%s %s is already in the database.\n", member.getFname(), member.getLname());
            return false;
        }

        return true;
    }

    /**
     * Used specifically when checking in a member from the ClassDatabase to check if it's a valid operation
     * @param classDatabase The ClassDatabase we're attempting to check in to
     * @param memberDatabase The MemberDatabase containing all the registered members
     * @param classType The class the member is trying to check in
     * @params all other params are the Member's info
     * @return if the member can be checked in or not
     */
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

    /**
     * Used specifically when dropping a member from the ClassDatabase to check if it's a valid operation
     * @param classDatabase The ClassDatabase we're attempting to drop from
     * @param memberDatabase The MemberDatabase containing all the registered members
     * @param classType The class the member is trying to drop
     * @params all other params are the Member's info
     * @return if the member can be dropped
     */
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
