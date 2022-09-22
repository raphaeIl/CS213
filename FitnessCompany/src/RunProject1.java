import client.GymManager;
import core.Member;
import datatypes.Date;
import datatypes.Location;

public class RunProject1 {
    public static void main(String[] args) {
        new GymManager().run();
        Date dob = Date.random();
        Member real = new Member("Az", "Sierra", dob, Date.random(), Location.Franklin);
        Member find = new Member("Az", "Sierra", dob, null, null);

        System.out.println(real);
        System.out.println(find);

        System.out.println(real.equals(find));
    }
}
