package core;

import datatypes.Date;
import utils.Utils;

/**
 * An array-based linear data structure that hold the list of members,
 * also provide the methods for managing the list of members
 * @Author Michael Liu, Genfu Liu
 */
public class MemberDatabase {

    public static final int NOT_FOUND = -1;
    private static final int EXPANSION_SIZE = 4;

    private Member[] mlist;
    private int size;

    public MemberDatabase() {
        mlist = new Member[EXPANSION_SIZE];
    }

    private int find(Member member) {
        for (int i = 0; i < size; i++)
            if (member.equals(mlist[i]))
                return i;

        return NOT_FOUND;
    }

    private void grow() {
        Member[] grew = new Member[size + EXPANSION_SIZE];

        for (int i = 0; i < size; i++)
            grew[i] = mlist[i];

        mlist = grew;
    }

    public boolean add(Member member) {
        if (size == mlist.length)
            grow();

        mlist[size++] = member;

        return true;
    }

    public boolean remove(Member member) {
        int targetIndex = find(member);

        if (targetIndex == NOT_FOUND)
            return false;

        for (int i = targetIndex; i < size - 1; i++)
            mlist[i] = mlist[i + 1];

        size--;

        return true;
    }

    public int indexOf(Member member) { // why is find even private
        return find(member);
    }

    public Member get(int index) {
        if (index == -1)
            return null;

        return mlist[index];
    }

    public Member get(String fname, String lname, Date dob) {
        int index = find(new Member(fname, lname, dob, null, null));

        return index == -1 ? null : mlist[index];
    }

    public int getSize() {
        return size;
    }

    public Member[] getMembers() {
        Member[] members = new Member[size];

        for (int i = 0, j = 0; i < size; i++) {
            if (mlist[i] != null)
                members[j++] = mlist[i];
        }

        return members;
    }

    public void print() { // print the array contents as is
        printDatabase("");
    }

    public void printByCounty() { // sort by county and then zipcode
        Member.CompareMode = Member.CompareMode.County;
        Utils.insertionSort(mlist, size);

        printDatabase(" sorted by county and zipcode");
    }
    public void printByExpirationDate() { //sort by the expiration date
        Member.CompareMode = Member.CompareMode.ExpirationDate;
        Utils.insertionSort(mlist, size);

        printDatabase(" sorted by membership expiration date");
    }
    public void printByName() { //sort by last name and then first name
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

    @Override
    public String toString() {
        return Utils.arrayToString(mlist);
    }
}