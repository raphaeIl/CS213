package managers;

import core.ClassDatabase;
import core.MemberDatabase;

/**
 * This singleton class stores and manages all the databases in this application,
 * current the member and the class database.
 * @author Michael Liu, Genfu Liu
 */
public class DatabaseManager {

    // region Singleton
    /**
     * This class implements the Singleton pattern for easier access of this class
     * throughout the entire program as well as limiting multiple instance of it to be
     * created since this application will most likely only need 1 database manager.
     */
    private static DatabaseManager instance = null;

    /**
     * Static method to get the single instance of this class
     * @return the single instance of this class
     */
    public static DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();

        return instance;
    }

    // endregion

    /**
     * Database used to store all members
     */
    private final MemberDatabase memberDatabase;

    /**
     * Database used to store all classes along with the schedule
     */
    private final ClassDatabase classDatabase;

    /**
     * Constructor of DatabaseManager to initialize both databases
     */
    private DatabaseManager() {
        memberDatabase = new MemberDatabase();
        classDatabase = new ClassDatabase();
    }

    /**
     * Gets the member database containing all the members
     * @return the MemberDatabase
     */
    public MemberDatabase getMemberDatabase() {
        return memberDatabase;
    }

    /**
     * Gets the class database containing all the fitness classes
     * @return the ClassDatabase
     */
    public ClassDatabase getClassDatabase() {
        return classDatabase;
    }
}
