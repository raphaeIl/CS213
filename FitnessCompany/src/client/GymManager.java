package client;

import core.ClassDatabase;
import core.Member;
import core.MemberDatabase;
import datatypes.Date;
import datatypes.Location;
import utils.MemberValidator;

import java.util.Scanner;

/**
 * @Author Michael
 */
public class GymManager {

    private static final int EXECUTE_SUCCESS = 0;
    private static final int EXECUTE_EXIT = 1;
    private static final int EXECUTE_ERROR = -1;

    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;

    public GymManager() {
        memberDatabase = new MemberDatabase();
        classDatabase = new ClassDatabase();
    }

    public int executeCommand(String[] args) {
        if (args[0].isEmpty() || args[0].isBlank())
            return EXECUTE_ERROR;

        switch (args[0]) {
            case "A":
                Member newMember = new Member(args[1], args[2], new Date(args[3]), new Date(args[4]), Location.fromString(args[5]));

                if (!MemberValidator.validateMemberDatabase(memberDatabase, newMember, args[5]))
                    break;

                if (memberDatabase.add(newMember))
                    System.out.printf("%s %s added.\n", newMember.getFname(), newMember.getLname());
                break;
            case "R":
                Member target = new Member(args[1], args[2], new Date(args[3]), null, null);

                if (memberDatabase.remove(target))
                    System.out.printf("%s %s removed.\n", target.getFname(), target.getLname());
                else
                    System.out.printf("%s %s is not in the database.\n", target.getFname(), target.getLname());

                break;
            case "P":
                if (memberDatabase.getSize() <= 0) {
                    System.out.println("Member database is empty!");
                    break;
                }

                System.out.println("\n-list of members-");
                memberDatabase.print();
                System.out.println("-end of list-\n");

                break;
            case "PC":
                if (memberDatabase.getSize() <= 0) {
                    System.out.println("Member database is empty!");
                    break;
                }

                System.out.println("\n-list of members sorted by county and zipcode-");
                memberDatabase.printByCounty();
                System.out.println("-end of list-\n");

                break;
            case "PN":
                if (memberDatabase.getSize() <= 0) {
                    System.out.println("Member database is empty!");
                    break;
                }

                System.out.println("\n-list of members sorted by last name, and first name-");
                memberDatabase.printByName();
                System.out.println("-end of list-\n");

                break;
            case "PD":
                if (memberDatabase.getSize() <= 0) {
                    System.out.println("Member database is empty!");
                    break;
                }

                System.out.println("\n-list of members sorted by membership expiration date-");
                memberDatabase.printByExpirationDate();
                System.out.println("-end of list-\n");

                break;
            case "S":
                classDatabase.displaySchedule();
                break;
            case "C":
                int targetIndex = memberDatabase.indexOf(new Member(args[2], args[3], new Date(args[4]), null, null));
                target = memberDatabase.get(targetIndex);

                if (!new Date(args[4]).isValid()) { // invalid dob
                    System.out.printf("DOB %s: invalid calendar date!\n", args[4]);
                    break;
                }

                if (target == null) { // member does not exist
                    System.out.printf("%s %s %s is not in the database.\n", args[2], args[3], args[4]);
                    break;
                }

                classDatabase.checkIn(args[1], target);
                break;
            case "D":
                targetIndex = memberDatabase.indexOf(new Member(args[2], args[3], new Date(args[4]), null, null));
                target = memberDatabase.get(targetIndex);

                if (!new Date(args[4]).isValid()) { // invalid dob
                    System.out.printf("DOB %s: invalid calendar date!\n", args[4]);
                    break;
                }

                if (target == null) { // member does not exist
                    System.out.printf("%s %s %s is not in the database.\n", args[2], args[3], args[4]);
                    break;
                }

                classDatabase.drop(args[1], target);
                break;
            case "Q":
                return EXECUTE_EXIT;
            default:
                System.out.println(args[0] + " is an invalid command!");
                break;
        }

        return EXECUTE_SUCCESS;
    }

    public void run() {
        System.out.println("Gym Manager running...");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int result = executeCommand(scanner.nextLine().split(" "));

            if (result == EXECUTE_EXIT)
                break;
        }

        System.out.println("Gym Manager terminated.");
    }
}
