package client;

import core.ClassDatabase;
import core.Member;
import core.MemberDatabase;
import datatypes.Date;
import datatypes.FitnessClassType;
import datatypes.Location;
import utils.Utils;

import java.util.Scanner;

public class GymManager {

    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;

    public GymManager() {
        memberDatabase = new MemberDatabase();
        classDatabase = new ClassDatabase();
    }

    public int executeCommand(String[] args) {
        switch (args[0]) {
            case "A":
                Member newMember = new Member(args[1], args[2], new Date(args[3]), new Date(args[4]), Location.fromString(args[5]));

                memberDatabase.add(newMember);
            case "R":
                Member target = new Member(args[1], args[2], new Date(args[3]), null, null);

                memberDatabase.remove(target);
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
                target = new Member(args[1], args[2], new Date(args[3]), null, null);

                classDatabase.checkIn(FitnessClassType.fromString(args[0]), target);
                break;
            case "D":
                target = new Member(args[1], args[2], new Date(args[3]), null, null);

                classDatabase.drop(FitnessClassType.fromString(args[0]), target);
            case "Q":
                return -1;
            default:
                break;
        }

        return 0;
    }

    public void dbInitTest() { // <- test method delete later
        MemberDatabase mdb = new MemberDatabase();
        mdb.add(new Member("Tyriq", "Turnbull", Date.random(), Date.random(), Location.Bridgewater));
        mdb.add(new Member("Aiesha", "Lozano", Date.random(), Date.random(), Location.Bridgewater));
        mdb.add(new Member("Shyla", "Ray", Date.random(), Date.random(), Location.Edison));
        mdb.add(new Member("Avneet", "Zuniga", Date.random(), Date.random(), Location.Franklin));
        mdb.add(new Member("Lorena", "Huynh", Date.random(), Date.random(), Location.Piscataway));
        mdb.add(new Member("Zidane", "Norman", Date.random(), Date.random(), Location.Somerville));
        mdb.add(new Member("Hareem", "Mora", Date.random(), Date.random(), Location.Edison));
        mdb.add(new Member("Bob", "Moreno", Date.random(), Date.random(), Location.Edison));
        mdb.add(new Member("Kaden", "Black", Date.random(), Date.random(), Location.Piscataway));
        mdb.add(new Member("Tomos", "Sullivan", Date.random(), Date.random(), Location.Somerville));
        mdb.add(new Member("Jensen", "Perry", Date.random(), Date.random(), Location.Somerville));
        mdb.add(new Member("Evelyn", "Bevan", Date.random(), Date.random(), Location.Bridgewater));
        mdb.add(new Member("Elsie", "Mellor", Date.random(), Date.random(), Location.Edison));
        mdb.add(new Member("Dan", "Downes", Date.random(), Date.random(), Location.Piscataway));
        mdb.add(new Member("Kason", "Edwards", Date.random(), Date.random(), Location.Franklin));
        mdb.add(new Member("Mariam", "Lyons", Date.random(), Date.random(), Location.Piscataway));
        mdb.add(new Member("Chante", "Holcomb", Date.random(), Date.random(), Location.Somerville));
        mdb.add(new Member("Rosemary", "Fuentes", Date.random(), Date.random(), Location.Bridgewater));
        mdb.add(new Member("Buster", "Mcnamara", Date.random(), Date.random(), Location.Bridgewater));
        mdb.add(new Member("Louis", "Sierra", Date.random(), Date.random(), Location.Franklin));

        mdb.add(new Member("Aaaaaaa", "Sierra", Date.random(), Date.random(), Location.Franklin));
        mdb.add(new Member("B", "Sierra", Date.random(), Date.random(), Location.Franklin));
        mdb.add(new Member("CDE", "Sierra", Date.random(), Date.random(), Location.Franklin));
        mdb.add(new Member("Ab", "Sierra", Date.random(), Date.random(), Location.Franklin));
        mdb.add(new Member("Az", "Sierra", Date.random(), Date.random(), Location.Franklin));


//        mdb.printByExpirationDate();
        System.out.println(mdb);
    }

    public void run() {
        System.out.println("Gym Manager running...");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int result = executeCommand(scanner.nextLine().split(" "));

            if (result != 0)
                break;
        }

        System.out.println("Gym Manager terminated.");
    }
}
