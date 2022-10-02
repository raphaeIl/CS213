package client;

import core.ClassDatabase;
import core.Member;
import core.MemberDatabase;
import datatypes.Date;
import datatypes.FitnessClassType;
import datatypes.Location;
import utils.MemberValidator;

import java.util.Scanner;

/**
 * Main User Interface class that process command lines entered to the IDE console and display
 * the results on the console.
 * @Author Michael Genfu
 */
public class GymManager {

    private static final int EXECUTE_SUCCESS = 0;
    private static final int EXECUTE_EXIT = 1;
    private static final int EXECUTE_ERROR = -1;

    /**
     * Database used to store all members, includes operations such as  adding, removing, searching and displaying.
     */
    private MemberDatabase memberDatabase;

    /**
     * Database used to store all classes along with their current members, includes operations such as checking in, dropping, and displaying.
     */
    private ClassDatabase classDatabase;

    public GymManager() {
        memberDatabase = new MemberDatabase();
        classDatabase = new ClassDatabase();
    }

    /**
     * Handles all client commands
     * @param args an array of command arguments
     * @return the result of executing the command, either  EXECUTE_SUCCESS if the command was sucessfully executed,
     *                                                      EXECUTE_EXIT if the client requested to exit,
     *                                                      EXECUTE_ERROR if there was an error.
     */
    public int executeCommand(String[] args) {
        if (args.length <= 0 || args[0].isEmpty() || args[0].isBlank())
            return EXECUTE_ERROR;

        switch (args[0]) {
            case "A":
                Member newMember = MemberValidator.validateAndCreateMember(args[1], args[2], args[3], args[4], args[5]);

                if (newMember == null || !MemberValidator.validateMemberDatabaseInsertion(memberDatabase, newMember))
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
                memberDatabase.print();

                break;
            case "PC":
                memberDatabase.printByCounty();

                break;
            case "PN":
                memberDatabase.printByName();

                break;
            case "PD":
                memberDatabase.printByExpirationDate();

                break;
            case "S":
                classDatabase.displaySchedule();

                break;
            case "C":
                target = memberDatabase.get(args[2], args[3], new Date(args[4]));

                if (!MemberValidator.validateMemberCheckIn(classDatabase, memberDatabase, args[1], args[2], args[3], args[4]))
                    break;

                if (classDatabase.checkIn(FitnessClassType.fromString(args[1]), target))
                    System.out.printf("%s %s checked in %s.\n", target.getFname(), target.getLname(), FitnessClassType.fromString(args[1]));

                break;
            case "D":
                target = memberDatabase.get(args[2], args[3], new Date(args[4]));

                if (!MemberValidator.validateMemberDrop(classDatabase, memberDatabase, args[1], args[2], args[3], args[4]))
                    break;

                if (classDatabase.drop(FitnessClassType.fromString(args[1]), target))
                    System.out.printf("%s %s dropped %s.\n", target.getFname(), target.getLname(), FitnessClassType.fromString(args[1]));

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
