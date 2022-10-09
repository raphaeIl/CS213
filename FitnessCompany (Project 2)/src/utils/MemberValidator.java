package utils;

import core.ClassSchedule;
import core.entity.Family;
import core.entity.Member;
import core.MemberDatabase;
import core.entity.Premium;
import datatypes.*;

/**
 * This class is mainly responsible for validating member info from client input,
 * also displays error messages when there is an invalid input.
 * @author Michael Liu, Genfu Liu
 */
public class MemberValidator {

    /**
     * Validates all the member info that the user inputs
     * @param fname Member first name
     * @param lname Member last name
     * @param dob Member dob in String form
     * @param expire Membership expiration date in String form (optional param)
     * @param location Member location in String form (optional param)
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
    public static Member validateAndCreateMember(String fname, String lname, String dob, String expire, String location, MemberType memberType) {
        if (!validateMember(fname, lname, dob, expire, location))
            return null;

        if (memberType == MemberType.Standard)
            return new Member(fname, lname, new Date(dob), null, Location.fromString(location));
        else if (memberType == MemberType.Family)
            return new Family(fname, lname, new Date(dob), null, Location.fromString(location));
        else
            return new Premium(fname, lname, new Date(dob), null, Location.fromString(location));
    }

    /**
     * Used specifically when adding members to the MemberDatabase to check if it's a valid operation
     * @param memberDatabase The MemberDatabase we're attempting to insert to
     * @param member The member we're trying to insert
     * @return if the member can be inserted or not
     */
    public static boolean validateMemberDatabaseInsertion(MemberDatabase memberDatabase, Member member) {
        if (memberDatabase.get(member) != null) { // checking if member is already in database
            System.out.printf("%s %s is already in the database.\n", member.getFname(), member.getLname());
            return false;
        }

        return true;
    }

    /**
     * Used specifically when checking in a member to check if it's a valid operation
     * @param classSchedule The ClassDatabase we're attempting to check in to
     * @param memberDatabase The MemberDatabase containing all the registered members
     * @param classType The class the member is trying to check in
     * @params all other params are the Member's info
     * @return if the member can be checked in or not
     */
    public static boolean validateMemberCheckIn(ClassSchedule classSchedule, MemberDatabase memberDatabase, String className, String classInstructor, String classLocation, String fname, String lname, String dob) {
        Member target = memberDatabase.get(new Member(fname, lname, new Date(dob), null, null));

        // member info validation
        if (!MemberValidator.validateMember(fname, lname, dob, null, null))
            return false;

        if (target == null) { // member does not exist
            System.out.printf("%s %s %s is not in the database.\n", fname, lname, dob);
            return false;
        }

        FitnessClass currentClass = validateAndFindFitnessClass(classSchedule, className, classInstructor, classLocation);

        if (currentClass == null)
            return false;

        if (target.getExpire().compareTo(new Date()) < 0) { // membership expired
            System.out.printf("%s %s %s membership expired.\n", target.getFname(), target.getLname(), target.getDob());
            return false;
        }

        if (currentClass.containsMember(target)) { // member already checked in
            System.out.printf("%s %s has already checked in.\n", target.getFname(), target.getLname());
            return false;
        }

        for (FitnessClass fitnessClass: classSchedule.getClassSchedule()) // time conflict with other fitness class
            if (!fitnessClass.equals(currentClass) &&
                    fitnessClass.containsMember(target) &&
                        fitnessClass.getClassTime().equals(currentClass.getClassTime())) {
                System.out.printf("Time Conflict - %s, %s %s %s\n", currentClass.getClassName().toUpperCase(), currentClass.getClassInstructor().toUpperCase(), currentClass.getClassTime(), currentClass.getClassLocation());
                return false;
            }

        if (!(target instanceof Family) && // location restriction for non family/premium members
                target.getLocation() != currentClass.getClassLocation()) {
            System.out.printf("%s %s checking in %s - standard membership location restriction.\n", target.getFname(), target.getLname(), currentClass.getClassLocation());
            return false;
        }

        return true;
    }


    public static boolean validateGuestCheckIn(ClassSchedule classSchedule, MemberDatabase memberDatabase, String className, String classInstructor, String classLocation, String fname, String lname, String dob) {
        Member target = memberDatabase.get(new Member(fname, lname, new Date(dob), null, null));

        // member info validation
        if (!MemberValidator.validateMember(fname, lname, dob, null, null))
            return false;

        if (target == null) { // member does not exist
            System.out.printf("%s %s %s is not in the database.\n", fname, lname, dob);
            return false;
        }

        FitnessClass currentClass = validateAndFindFitnessClass(classSchedule, className, classInstructor, classLocation);

        if (currentClass == null)
            return false;

        if (target.getExpire().compareTo(new Date()) < 0) { // membership expired
            System.out.printf("%s %s %s membership expired.\n", target.getFname(), target.getLname(), target.getDob());
            return false;
        }

        if (!(target instanceof Family)) { // can not check-in guest with standard
            System.out.println("Standard membership - guest check-in is not allowed.");
            return false;
        }

        if (!classLocation.equalsIgnoreCase(target.getLocation().getCity())) {
            System.out.printf("%s %s Guest checking in %s - guest location restriction.\n", target.getFname(), target.getLname(), Location.fromString(classLocation));
            return false;
        }

        if (!((Family)target).useGuestPass()) {
            System.out.printf("%s %s ran out of guest pass.\n", fname, lname);
            return false;
        }

        return true;
    }



    /**
     * Used specifically when dropping a member from the ClassDatabase to check if it's a valid operation
     * @param classSchedule The ClassDatabase we're attempting to drop from
     * @param memberDatabase The MemberDatabase containing all the registered members
     * @param classType The class the member is trying to drop
     * @params all other params are the Member's info
     * @return if the member can be dropped
     */
    public static boolean validateMemberDrop(ClassSchedule classSchedule, MemberDatabase memberDatabase, String className, String classInstructor, String classLocation, String fname, String lname, String dob) {
        Member target = memberDatabase.get(new Member(fname, lname, new Date(dob), null, null));

        // member info validation
        if (!MemberValidator.validateMember(fname, lname, dob, null, null))
            return false;

        FitnessClass currentClass = validateAndFindFitnessClass(classSchedule, className, classInstructor, classLocation);

        if (currentClass == null)
            return false;

        if (target == null) { // member does not exist
            System.out.printf("%s %s %s is not in the database.\n", fname, lname, dob);
            return false;
        }

        // member is not a participant in that class/ did not check in
        if (!classSchedule.getFitnessClass(currentClass).containsMember(new Member(fname, lname, new Date(dob), null, null))) {
            System.out.printf("%s %s did not check in.\n", fname, lname);
            return false;
        }

        return true;
    }

    public static boolean validateGuestDrop(ClassSchedule classSchedule, MemberDatabase memberDatabase, String className, String classInstructor, String classLocation, String fname, String lname, String dob) {
        Member target = memberDatabase.get(new Member(fname, lname, new Date(dob), null, null));

        // member info validation
        if (!MemberValidator.validateMember(fname, lname, dob, null, null))
            return false;

        FitnessClass currentClass = validateAndFindFitnessClass(classSchedule, className, classInstructor, classLocation);

        if (currentClass == null)
            return false;

        if (target == null) { // member does not exist
            System.out.printf("%s %s %s is not in the database.\n", fname, lname, dob);
            return false;
        }

        // member is not a participant in that class/did not check in
        if (!classSchedule.getFitnessClass(currentClass).containsGuest(new Member(fname, lname, new Date(dob), null, null))) {
            System.out.printf("%s %s did not check in.\n", fname, lname);
            return false;
        }

        if (!((Family)target).returnGuestPass()) { // somehow can not return guest pass?
            return false;
        }

        return true;
    }

    public static FitnessClass validateAndFindFitnessClass(ClassSchedule classSchedule, String className, String classInstructor, String classLocation) {
        FitnessClass fitnessClass = classSchedule.getFitnessClass(new FitnessClass(className, classInstructor, "", classLocation));

        if (!classSchedule.containsInstructor(classInstructor)) { // instructor does not exist
            System.out.printf("%s - instructor does not exist.\n", classInstructor);
            return null;
        }

        if (!classSchedule.containsClass(className)) { // class does not exist
            System.out.printf("%s - class does not exist.\n", className);
            return null;
        }

        if (!classSchedule.containsLocation(classLocation)) { // invalid location
            System.out.printf("%s - invalid location.\n", classLocation);
            return null;
        }

        if (fitnessClass == null) { // class/instructor exists but combined doesn't
            System.out.printf("%s by %s does not exist at %s.\n", className, classInstructor, classLocation);
            return null;
        }

        return fitnessClass;
    }
}
