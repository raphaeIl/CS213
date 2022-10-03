package core;

import utils.Utils;

/**
 * An array-based linear data structure that hold the list of members,
 * also provide the methods for managing the list of members
 * @author Michael Liu, Genfu Liu
 */
public class MemberDatabase {

    public static final int NOT_FOUND = -1;
    private static final int EXPANSION_SIZE = 4;

    /**
     * An array of members that are currently in the database,
     * grows automatically by EXPANSION_SIZE, can contain null elements
     */
    private Member[] mlist;

    /**
     * Actual size of the database, since the array contains null elements
     */
    private int size;

    /**
     * Constructor to initialize the database array
     */
    public MemberDatabase() {
        mlist = new Member[EXPANSION_SIZE];
    }

    /**
     * Finds a specific member
     * @param member The member that is being queried
     * @return the index of the member, NOT_FOUND (-1) if not found
     */
    private int find(Member member) {
        for (int i = 0; i < size; i++)
            if (member.equals(mlist[i]))
                return i;

        return NOT_FOUND;
    }

    /**
     * Automatically grows the array by EXPANSION_SIZE (4) everytime it's full
     */
    private void grow() {
        Member[] grew = new Member[size + EXPANSION_SIZE];

        for (int i = 0; i < size; i++)
            grew[i] = mlist[i];

        mlist = grew;
    }

    /**
     * Adds a member to the database
     * @param member The member to be added
     * @return if the member was added successfully
     */
    public boolean add(Member member) {
        if (size == mlist.length)
            grow();

        mlist[size++] = member;

        return true;
    }

    /**
     * Removes a member from the database
     * @param member The membner to be removed
     * @return if the member was removed successfully
     */
    public boolean remove(Member member) {
        int targetIndex = find(member);

        if (targetIndex == NOT_FOUND)
            return false;

        for (int i = targetIndex; i < size - 1; i++)
            mlist[i] = mlist[i + 1];

        size--;

        return true;
    }

    /**
     * Finds a member from it's first name, last name and dob which are stored inside the member parameter
     * (would've preferred just passing the info through 3 separate params instead of a temp member param)
     * @param member the member that is being queried
     * @return the member that matches the info in the member param, null if not found
     */
    public Member get(Member member) { // why is find even private
        return find(member) == -1 ? null : mlist[find(member)];
    }

    /**
     * Gets the size of the database
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets all the members that is in the database in an array with no null elements (unlike the original auto growing one)
     * @return the array of all the members in the database
     */
    public Member[] getMembers() {
        Member[] members = new Member[size];

        for (int i = 0, j = 0; i < size; i++) {
            if (mlist[i] != null)
                members[j++] = mlist[i];
        }

        return members;
    }

    /**
     * print the array contents as is
     */
    public void print() {
        printDatabase("");
    }

    /**
     * print the array sorted by county and then zipcode
     */
    public void printByCounty() { //
        Member.CompareMode = Member.CompareMode.County;
        Utils.insertionSort(mlist, size);

        printDatabase(" sorted by county and zipcode");
    }

    /**
     * print the arrat sort by the expiration date
     */
    public void printByExpirationDate() { //
        Member.CompareMode = Member.CompareMode.ExpirationDate;
        Utils.insertionSort(mlist, size);

        printDatabase(" sorted by membership expiration date");
    }

    /**
     * print the array sorted by last name and then first name
     */
    public void printByName() {
        Member.CompareMode = Member.CompareMode.Name;
        Utils.insertionSort(mlist, size);

        printDatabase(" sorted by last name, and first name");
    }

    /**
     * This is a helper method we added to reduce code duplication such as repeatedly checking if the database is empty in the other print methods
     * @param displayMessage displays this message along with the list of members currently in the database
     */
    private void printDatabase(String displayMessage) {
        if (size <= 0) {
            System.out.println("Member database is empty!");
            return;
        }

        System.out.printf("\n-list of members%s-\n", displayMessage);

        for (int i = 0; i < size; i++)
            System.out.println(mlist[i].toString());

        System.out.println("-end of list-\n");
    }

    /**
     * Inherited toString method to format the database array
     * @return
     */
    @Override
    public String toString() {
        return Utils.arrayToString(mlist);
    }
}