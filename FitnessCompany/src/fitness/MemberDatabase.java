package fitness;

public class MemberDatabase {

    private static final int NOT_FOUND = -1;
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
        if (find(member) != NOT_FOUND) // checking if member is already in database not sure if this is correct
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

    public void print() { //print the array contents as is
        for (Member m: mlist)
            System.out.println(m);
    }
    public void printByCounty() { //sort by county and then zipcode

    }
    public void printByExpirationDate() { //sort by the expiration date

    }
    public void printByName() { //sort by last name and then first name
        Member[] clone = mlist.clone();

        Utils.insertionSort(clone, (m1, m2) -> {

            if (m2 == null)
                return 1;

            int primaryComparison = m1.getLname().compareTo(m2.getLname());
            int secondaryComparison = m1.getFname().compareTo(m2.getFname());

            if (primaryComparison > 0)
                return 1;
            else if (primaryComparison < 0)
                return -1;
            else {
                if (secondaryComparison > 0)
                    return 1;
                else if (secondaryComparison < 0)
                    return -1;
                else return 0;
            }
        });

        for (Member m: clone)
            System.out.println(m);
    }
}