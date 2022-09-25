package core;

import datatypes.Date;
import utils.Utils;

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
        if (find(member) != NOT_FOUND) // checking if member is already in database
            return false;

        if (!member.getDob().isValid() || !member.getExpire().isValid() || // invalid dob or expiration date
                member.getDob().equals(new Date()) || member.getDob().compareTo(new Date()) > 0)
            return false;

        if (member.getAge() < 18) // You are not 18 years old
            return false;

        if (member.getLocation() == null) // that location does not exist
            return false;

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

    public int getSize() {
        return size;
    }

    public void print() { //print the array contents as is
        for (int i = 0; i < size; i++)
            System.out.println(mlist[i]);
    }
    public void printByCounty() { //sort by county and then zipcode
        Member.CompareMode = Member.CompareMode.County;
        Utils.insertionSort(mlist, size);

        print();
    }
    public void printByExpirationDate() { //sort by the expiration date TODO: case where expiration dates are the same
        Member.CompareMode = Member.CompareMode.ExpirationDate;
        Utils.insertionSort(mlist, size);

        print();
    }
    public void printByName() { //sort by last name and then first name
        Member.CompareMode = Member.CompareMode.Name;
        Utils.insertionSort(mlist, size);

        print();
    }

    @Override
    public String toString() {
        return Utils.arrayToString(mlist);
    }
}