package utils;

import client.GymManager;
import core.ClassDatabase;
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
            GymManager.logf("DOB %s: invalid calendar date!\n", dobDate);
            return false;
        }

        if (dobDate.equals(new Date()) || dobDate.compareTo(new Date()) > 0) { // today or future dob
            GymManager.logf("DOB %s: cannot be today or a future date!\n", dobDate);
            return false;
        }

        if (expire != null) {
            Date expireDate = new Date(expire);

            if (!expireDate.isValid()) { // invalid expiration date
                GymManager.logf("Expiration date %s: invalid calendar date!\n", expireDate);
                return false;
            }
        }

        if (Utils.dobToAge(dobDate) < 18) { // You are not 18 years old
            GymManager.logf("DOB %s: must be 18 or older to join!\n", dobDate);
            return false;
        }

        if (location != null && Location.fromString(location) == null) { // that location does not exist
            GymManager.logf("%s: invalid location!\n", location);
            return false;
        }

        return true;
    }

    /**
     * Validates member info using validateMember() but additionally creates either a Member or any of its subclass if all info are valid
     * @param fname Member first name
     * @param lname Member last name
     * @param dob Member dob in String form
     * @param expire Membership expiration date in String form (optional param)
     * @param location Member location in String form (optional param)
     * @param memberType The type of Member to be created
     * @return the created Member (polymorphism included) or any of its subclasses according to memberType
     */
    public static Member validateAndCreateMember(String fname, String lname, String dob, String expire, String location, MemberType memberType) {
        if (!validateMember(fname, lname, dob, expire, location))
            return null;

        if (memberType == MemberType.Standard) // expiration date left null for the Member classes to set it automatically
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
            GymManager.logf("%s %s is already in the database.\n", member.getFname(), member.getLname());
            return false;
        }

        return true;
    }

    /**
     * Used specifically when checking in a member to check if it's a valid operation
     * @param classDatabase The ClassDatabase we're attempting to check in to
     * @param memberDatabase The MemberDatabase containing all the registered members
     * @param className The name of the class the member is trying to check in
     * @param classInstructor The instructor of the class
     * @param classLocation The location of the class
     * @param fname Member's first name
     * @param lname Member's last name
     * @param dob Member's dob
     * @return if the member can be checked in or not
     */
    public static boolean validateMemberCheckIn(ClassDatabase classDatabase, MemberDatabase memberDatabase, String className, String classInstructor, String classLocation, String fname, String lname, String dob) {
        Member target = memberDatabase.get(new Member(fname, lname, new Date(dob), null, null));

        // member info validation
        if (!MemberValidator.validateMember(fname, lname, dob, null, null))
            return false;

        if (target == null) { // member does not exist
            GymManager.logf("%s %s %s is not in the database.\n", fname, lname, dob);
            return false;
        }

        FitnessClass currentClass = validateAndFindFitnessClass(classDatabase, className, classInstructor, classLocation);

        if (currentClass == null)
            return false;

        if (target.getExpire().compareTo(new Date()) < 0) { // membership expired
            GymManager.logf("%s %s %s membership expired.\n", target.getFname(), target.getLname(), target.getDob());
            return false;
        }

        if (currentClass.containsMember(target)) { // member already checked in
            GymManager.logf("%s %s already checked in.\n", target.getFname(), target.getLname());
            return false;
        }

        for (FitnessClass fitnessClass: classDatabase.getClassSchedule()) // time conflict with other fitness class
            if (!fitnessClass.equals(currentClass) &&
                    fitnessClass.containsMember(target) &&
                        fitnessClass.getClassTime().equals(currentClass.getClassTime())) {
                GymManager.logf("Time conflict - %s - %s, %s, %s\n", currentClass.getClassName().toUpperCase(), currentClass.getClassInstructor().toUpperCase(), currentClass.getClassTime(), currentClass.getClassLocation());
                return false;
            }

        if (!(target instanceof Family) && // location restriction for non family/premium members
                target.getLocation() != currentClass.getClassLocation()) {
            GymManager.logf("%s %s checking in %s - standard membership location restriction.\n", target.getFname(), target.getLname(), currentClass.getClassLocation());
            return false;
        }

        return true;
    }

    /**
     * Used specifically when checking in a guest to check if it's a valid operation
     * @param classDatabase The ClassDatabase we're attempting to check in to
     * @param memberDatabase The MemberDatabase containing all the registered members
     * @param className The name of the class the guest is trying to check in
     * @param classInstructor The instructor of the class
     * @param classLocation The location of the class
     * @param fname Guest's first name
     * @param lname Guest's last name
     * @param dob Guest's dob
     * @return if the guest can be checked in or not
     */
    public static boolean validateGuestCheckIn(ClassDatabase classDatabase, MemberDatabase memberDatabase, String className, String classInstructor, String classLocation, String fname, String lname, String dob) {
        Member target = memberDatabase.get(new Member(fname, lname, new Date(dob), null, null));

        // member info validation
        if (!MemberValidator.validateMember(fname, lname, dob, null, null))
            return false;

        if (target == null) { // member does not exist
            GymManager.logf("%s %s %s is not in the database.\n", fname, lname, dob);
            return false;
        }

        FitnessClass currentClass = validateAndFindFitnessClass(classDatabase, className, classInstructor, classLocation);

        if (currentClass == null)
            return false;

        if (target.getExpire().compareTo(new Date()) < 0) { // membership expired
            GymManager.logf("%s %s %s membership expired.\n", target.getFname(), target.getLname(), target.getDob());
            return false;
        }

        if (!(target instanceof Family)) { // can not check-in guest with standard
            GymManager.log("Standard membership - guest check-in is not allowed.");
            return false;
        }

        if (!classLocation.equalsIgnoreCase(target.getLocation().getCity())) {
            GymManager.logf("%s %s Guest checking in %s - guest location restriction.\n", target.getFname(), target.getLname(), Location.fromString(classLocation));
            return false;
        }

        if (!((Family)target).useGuestPass()) {
            GymManager.logf("%s %s ran out of guest pass.\n", fname, lname);
            return false;
        }

        return true;
    }

    /**
     * Used specifically when dropping a member to check if it's a valid operation
     * @param classDatabase The ClassDatabase we're attempting to drop from
     * @param memberDatabase The MemberDatabase containing all the registered members
     * @param className The name of the class the member is trying to drop from
     * @param classInstructor The instructor of the class
     * @param classLocation The location of the class
     * @param fname Member's first name
     * @param lname Member's last name
     * @param dob Member's dob
     * @return
     */
    public static boolean validateMemberDrop(ClassDatabase classDatabase, MemberDatabase memberDatabase, String className, String classInstructor, String classLocation, String fname, String lname, String dob) {
        Member target = memberDatabase.get(new Member(fname, lname, new Date(dob), null, null));

        // member info validation
        if (!MemberValidator.validateMember(fname, lname, dob, null, null))
            return false;

        FitnessClass currentClass = validateAndFindFitnessClass(classDatabase, className, classInstructor, classLocation);

        if (currentClass == null)
            return false;

        if (target == null) { // member does not exist
            GymManager.logf("%s %s %s is not in the database.\n", fname, lname, dob);
            return false;
        }

        // member is not a participant in that class/ did not check in
        if (!classDatabase.getFitnessClass(currentClass).containsMember(new Member(fname, lname, new Date(dob), null, null))) {
            GymManager.logf("%s %s did not check in.\n", fname, lname);
            return false;
        }

        return true;
    }

    /**
     * Used specifically when dropping a guest to check if it's a valid operation
     * @param classDatabase The ClassDatabase we're attempting to drop from
     * @param memberDatabase The MemberDatabase containing all the registered members
     * @param className The name of the class the guest is trying to drop from
     * @param classInstructor The instructor of the class
     * @param classLocation The location of the class
     * @param fname Guest's first name
     * @param lname Guest's last name
     * @param dob Guest's dob
     * @return
     */
    public static boolean validateGuestDrop(ClassDatabase classDatabase, MemberDatabase memberDatabase, String className, String classInstructor, String classLocation, String fname, String lname, String dob) {
        Member target = memberDatabase.get(new Member(fname, lname, new Date(dob), null, null));

        // member info validation
        if (!MemberValidator.validateMember(fname, lname, dob, null, null))
            return false;

        FitnessClass currentClass = validateAndFindFitnessClass(classDatabase, className, classInstructor, classLocation);

        if (currentClass == null)
            return false;

        if (target == null) { // member does not exist
            GymManager.logf("%s %s %s is not in the database.\n", fname, lname, dob);
            return false;
        }

        // member is not a participant in that class/did not check in
        if (!classDatabase.getFitnessClass(currentClass).containsGuest(new Member(fname, lname, new Date(dob), null, null))) {
            GymManager.logf("%s %s did not check in.\n", fname, lname);
            return false;
        }

        if (!((Family)target).returnGuestPass()) { // somehow can not return guest pass?
            return false;
        }

        return true;
    }

    /**
     * Validates the given fitness class info and attempts to find the class in the database
     * @param classDatabase The ClassDatabase containing all the classes
     * @param className The name of the class
     * @param classInstructor The instructor of the class
     * @param classLocation The location of the class
     * @return The FitnessClass found, null if not found
     */
    public static FitnessClass validateAndFindFitnessClass(ClassDatabase classDatabase, String className, String classInstructor, String classLocation) {
        FitnessClass fitnessClass = classDatabase.getFitnessClass(new FitnessClass(className, classInstructor, "", classLocation));

        if (!classDatabase.containsInstructor(classInstructor)) { // instructor does not exist
            GymManager.logf("%s - instructor does not exist.\n", classInstructor);
            return null;
        }

        if (!classDatabase.containsClass(className)) { // class does not exist
            GymManager.logf("%s - class does not exist.\n", className);
            return null;
        }

        if (!classDatabase.containsLocation(classLocation)) { // invalid location
            GymManager.logf("%s - invalid location.\n", classLocation);
            return null;
        }

        if (fitnessClass == null) { // class/instructor exists but combined doesn't
            GymManager.logf("%s by %s does not exist at %s.\n", className, classInstructor, classLocation);
            return null;
        }

        return fitnessClass;
    }
}
