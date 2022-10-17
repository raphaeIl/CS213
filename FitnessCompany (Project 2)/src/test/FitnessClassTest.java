package test;

import core.FitnessClass;
import core.entity.Member;
import datatypes.Date;
import datatypes.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Test class for Premium.membershipFee()
 * Note: Our implementation does not do error checks in the FitnessClass class,
 * instead it does the checks a few layers before when the client inputs the command,
 * so the Member that is being passed into these methods will ALWAYS be valid, no need to test
 * the cases of invalid members here.
 * @author Michael Liu, Genfu Liu
 */
class FitnessClassTest {

    /**
     * Testing if the member is actually in the database after checking it in
     */
    @Test
    void database_contains_member_after_checkin() {
        // creating a random member and a random class and checking the member in to that class
        Member member = new Member("Raiden", "Mei", new Date("4/13/1997"), new Date("3/30/2500"), Location.Somerville);
        FitnessClass fitnessClass = new FitnessClass("Pilates", "Jennifer", "morning", "Bridgewater");

        fitnessClass.checkIn(member);

        assertTrue(fitnessClass.containsMember(member));
    }

    /**
     * Testing if the member is out of the database after dropping it
     */
    @Test
    void database_does_not_contain_member_after_drop() {
        // creating a random member and a random class and checking out the member from that class
        Member member = new Member("Raiden", "Mei", new Date("4/13/1997"), new Date("3/30/2500"), Location.Somerville);
        FitnessClass fitnessClass = new FitnessClass("Pilates", "Jennifer", "morning", "Bridgewater");
        fitnessClass.checkIn(member);

        fitnessClass.drop(member);

        assertFalse(fitnessClass.containsMember(member));
    }

    /**
     * Testing if a guest is actually in the database after checking it in
     */
    @Test
    void database_contains_guest_after_checkin() {
        // creating a random member and a random class and checking the member with a guest in to that class
        Member member = new Member("Raiden", "Mei", new Date("4/13/1997"), new Date("3/30/2500"), Location.Somerville);
        FitnessClass fitnessClass = new FitnessClass("Pilates", "Jennifer", "morning", "Bridgewater");

        fitnessClass.checkInGuest(member);

        assertTrue(fitnessClass.containsGuest(member));
    }

    /**
     * Testing if a guest is out of the database after checking it out
     */
    @Test
    void database_does_not_contain_guest_after_drop() {
        // creating a random member and a random class and checking out the member from that class
        Member member = new Member("Raiden", "Mei", new Date("4/13/1997"), new Date("3/30/2500"), Location.Somerville);
        FitnessClass fitnessClass = new FitnessClass("Pilates", "Jennifer", "morning", "Bridgewater");
        fitnessClass.checkInGuest(member);

        fitnessClass.dropGuest(member);

        assertFalse(fitnessClass.containsGuest(member));
    }
}