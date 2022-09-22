package main.client;

import main.core.Member;
import main.core.MemberDatabase;
import main.datatypes.Date;
import main.datatypes.Location;

public class GymManager {
    public void run() {
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


        mdb.printByExpirationDate();
    }
}
