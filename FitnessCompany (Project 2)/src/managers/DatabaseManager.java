package managers;

import core.ClassDatabase;
import core.MemberDatabase;

public class DatabaseManager {

    // Singleton
    private static DatabaseManager instance = null;

    public static DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();

        return instance;
    }

    /**
     * Database used to store all members
     */
    private final MemberDatabase memberDatabase;

    /**
     * Database used to store all classes along with their current members
     */
    private final ClassDatabase classDatabase;

    /**
     * Constructor of GymManager to initialize both databases
     */
    private DatabaseManager() {
        memberDatabase = new MemberDatabase();
        classDatabase = new ClassDatabase();
    }

    public MemberDatabase getMemberDatabase() {
        return memberDatabase;
    }

    public ClassDatabase getClassDatabase() {
        return classDatabase;
    }
}
