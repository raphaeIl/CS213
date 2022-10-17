package test;

import core.entity.Premium;
import datatypes.Date;
import datatypes.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit Test class for Premium.membershipFee()
 * @author Michael Liu, Genfu Liu
 */
class PremiumTest {

    /**
     * Testing to see if the method calculates the correct fee for a premium member
     */
    @Test
    public void premium_memberShipFee_returns_correct_fee() {
        // a premium member with random info
        Premium premiumMember = new Premium("Kiana", "Kaslana", new Date("12/7/1998"), new Date("3/30/2717"), Location.Bridgewater);

        // all info according to chart in project instructions
        final double PREMIUM_ONETIME_FEE = 0; // premium one-time fee is waived
        final double PREMIUM_MONTHLY_FEE = 59.99; // premium monthly fee is the same as family
        final int MEMBERSHIP_LENGTH = Date.MONTHS_IN_YEAR - 1; // paid annually but 1 month free of charge

        double expected_premium_membership_fee = PREMIUM_ONETIME_FEE + PREMIUM_MONTHLY_FEE * MEMBERSHIP_LENGTH;
        double actual_membership_fee = premiumMember.memberShipFee();

        assertEquals(expected_premium_membership_fee, actual_membership_fee);
    }
}