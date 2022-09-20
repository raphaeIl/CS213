package fitness;

public class GymManager {
    public void run() {
        MemberDatabase mdb = new MemberDatabase();
        mdb.add(new Member("Tyriq", "Turnbull", null, null, null));
        mdb.add(new Member("Aiesha", "Lozano", null, null, null));
        mdb.add(new Member("Shyla", "Ray", null, null, null));
        mdb.add(new Member("Avneet", "Zuniga", null, null, null));
        mdb.add(new Member("Lorena", "Huynh", null, null, null));
        mdb.add(new Member("Zidane", "Norman", null, null, null));
        mdb.add(new Member("Hareem", "Mora", null, null, null));
        mdb.add(new Member("Bob", "Moreno", null, null, null));
        mdb.add(new Member("Kaden", "Black", null, null, null));
        mdb.add(new Member("Tomos", "Sullivan", null, null, null));
        mdb.add(new Member("Jensen", "Perry", null, null, null));
        mdb.add(new Member("Evelyn", "Bevan", null, null, null));
        mdb.add(new Member("Elsie", "Mellor", null, null, null));
        mdb.add(new Member("Dan", "Downes", null, null, null));
        mdb.add(new Member("Kason", "Edwards", null, null, null));
        mdb.add(new Member("Mariam", "Lyons", null, null, null));
        mdb.add(new Member("Chante", "Holcomb", null, null, null));
        mdb.add(new Member("Rosemary", "Fuentes", null, null, null));
        mdb.add(new Member("Buster", "Mcnamara", null, null, null));
        mdb.add(new Member("Louis", "Sierra", null, null, null));



        mdb.printByName();
    }
}
