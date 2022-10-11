package client;

import core.ClassSchedule;
import core.entity.Family;
import core.entity.Member;
import core.MemberDatabase;
import datatypes.Date;
import datatypes.FitnessClass;
import datatypes.FitnessClassType;
import datatypes.MemberType;
import utils.MemberValidator;

import java.util.Scanner;

/**
 * Main User Interface class that process command lines entered to the IDE console and display
 * the results on the console.
 * @author Michael Liu, Genfu Liu
 */
public class GymManager {

    private static final int EXECUTE_SUCCESS = 0;
    private static final int EXECUTE_EXIT = 1;
    private static final int EXECUTE_ERROR = -1;

    /**
     * Database used to store all members
     */
    private final MemberDatabase memberDatabase;

    /**
     * Database used to store all classes along with their current members
     */
    private final ClassSchedule classSchedule;

    /**
     * Constructor of GymManager to initialize both databases
     */
    public GymManager() {
        memberDatabase = new MemberDatabase();
        classSchedule = new ClassSchedule();
    }

    /**
     * Handles all client commands
     * @param args an array of command arguments
     * @return the result of executing the command, either  EXECUTE_SUCCESS if the command was successfully executed,
     *                                                      EXECUTE_EXIT if the client requested to exit,
     *                                                      EXECUTE_ERROR if there was an error.
     */
    public int executeCommand(String[] args) {
        if (args.length <= 0 || args[0].isEmpty() || args[0].isBlank())
            return EXECUTE_ERROR;

        switch (args[0]) {
            case "A":
            case "AF":
            case "AP":
                MemberType memberType = null;

                switch (args[0]) {
                    case "A": memberType = MemberType.Standard; break;
                    case "AF": memberType = MemberType.Family; break;
                    case "AP": memberType = MemberType.Premium; break;
                }

                Member newMember = MemberValidator.validateAndCreateMember(args[1], args[2], args[3], null, args[4], memberType);

                if (newMember == null || !MemberValidator.validateMemberDatabaseInsertion(memberDatabase, newMember))
                    break;

                if (memberDatabase.add(newMember))
                    System.out.printf("%s %s added.\n", newMember.getFname(), newMember.getLname());

                break;
            case "R":
                Member target = memberDatabase.get(new Member(args[1], args[2], new Date(args[3]), null, null));

                if (memberDatabase.remove(target))
                    System.out.printf("%s %s removed.\n", target.getFname(), target.getLname());
                else
                    System.out.printf("%s %s is not in the database.\n", args[1], args[2]);

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
            case "PF":
                memberDatabase.printWithMembershipFees();

                break;
            case "S":
                classSchedule.displaySchedule();

                break;
            case "C":
                target = memberDatabase.get(new Member(args[4], args[5], new Date(args[6]), null, null));

                if (!MemberValidator.validateMemberCheckIn(classSchedule, memberDatabase, args[1], args[2], args[3], args[4], args[5], args[6]))
                    break;

                FitnessClass fitnessClass = classSchedule.getFitnessClass(new FitnessClass(args[1], args[2], "", args[3]));

                if (classSchedule.checkIn(fitnessClass, target))
                    System.out.printf("%s %s checked in ", target.getFname(), target.getLname(), fitnessClass);

                fitnessClass.displaySchedule();
                break;
            case "CG":
                target = memberDatabase.get(new Member(args[4], args[5], new Date(args[6]), null, null));

                if (!MemberValidator.validateGuestCheckIn(classSchedule, memberDatabase, args[1], args[2], args[3], args[4], args[5], args[6]))
                    break;

                fitnessClass = classSchedule.getFitnessClass(new FitnessClass(args[1], args[2], "", args[3]));

                if (classSchedule.checkInGuest(new FitnessClass(args[1], args[2], "", args[3]), target))
                    System.out.printf("%s %s (guest) checked in ", target.getFname(), target.getLname(), fitnessClass);

                fitnessClass.displaySchedule();
                System.out.println();
                break;
            case "D":
                target = memberDatabase.get(new Member(args[4], args[5], new Date(args[6]), null, null));

                if (!MemberValidator.validateMemberDrop(classSchedule, memberDatabase,  args[1], args[2], args[3], args[4], args[5], args[6]))
                    break;

                if (classSchedule.drop(new FitnessClass(args[1], args[2], "", args[3]), target))
                    System.out.printf("%s %s done with the class.\n", target.getFname(), target.getLname());

                break;

            case "DG":
                target = memberDatabase.get(new Member(args[4], args[5], new Date(args[6]), null, null));

                if (!MemberValidator.validateGuestDrop(classSchedule, memberDatabase, args[1], args[2], args[3], args[4], args[5], args[6]))
                    break;

                if (classSchedule.dropGuest(new FitnessClass(args[1], args[2], "", args[3]), target))
                    System.out.printf("%s %s Guest done with the class.\n", target.getFname(), target.getLname());

                break;
            case "LS":
                classSchedule.loadSchedule("FitnessCompany (Project 2)/classSchedule.txt");
                break;

            case "LM":
                memberDatabase.loadMembers("FitnessCompany (Project 2)/memberList.txt");
                break;

            case "Q":
                return EXECUTE_EXIT;

            default:
                System.out.println(args[0] + " is an invalid command!");
                break;
        }

        return EXECUTE_SUCCESS;
    }

    /**
     * Starts the user interface, continuously reads user input command lines
     */
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
