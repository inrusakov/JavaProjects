package AddressFinder;

import org.junit.jupiter.api.Test;

import java.lang.ref.Cleaner;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {

    @Test
    public void test_NameFinder(){
        ArrayList<Client> list = new ArrayList<>();
        list.add(new Client(
                "Ivan",
                "Ivanov",
                Date.valueOf("2000-12-11"),
                new Address("Russia","Moscow", "Nikolskaya", "5s3")));

        list.add(new Client(
                "Ivan",
                "Petrov",
                Date.valueOf("2000-12-11"),
                new Address("Russia","Kazan", "Skolskaya", "5s6")));

        list.add(new Client(
                "Oleg",
                "Petrov",
                Date.valueOf("2000-12-11"),
                new Address("Russia","Perm", "Golskaya", "1s3")));

        list.add(new Client(
                "Igor",
                "Andreev",
                Date.valueOf("2000-12-11"),
                new Address("Russia","Moscow", "Kobolskaya", "1s7")));
        
        //System.out.println(Finder.nameSearcher("Ivan", list));
        assertEquals(Finder.nameSearcher("Ivan", list).size(),2);
    }

    @Test
    public void test_AddressFinder(){
        ArrayList<Client> list = new ArrayList<>();
        list.add(new Client(
                "Ivan",
                "Ivanov",
                Date.valueOf("2000-12-11"),
                new Address("Russia","Moscow", "Nikolskaya", "5s3")));

        list.add(new Client(
                "Ivan",
                "Petrov",
                Date.valueOf("2000-12-11"),
                new Address("Russia","Kazan", "Skolskaya", "5s6")));

        list.add(new Client(
                "Oleg",
                "Petrov",
                Date.valueOf("2000-12-11"),
                new Address("Russia","Perm", "Golskaya", "1s3")));

        list.add(new Client(
                "Igor",
                "Andreev",
                Date.valueOf("2000-12-11"),
                new Address("Russia","Moscow", "Kobolskaya", "1s7")));

        //System.out.println(Finder.addressAttributeSearcher("Golskaya", list));
        assertEquals(Finder.addressAttributeSearcher("Golskaya", list).size(),1);
    }

    @Test
    public void test_DateFinder(){
        ArrayList<Client> list = new ArrayList<>();
        list.add(new Client(
                "Ivan",
                "Ivanov",
                Date.valueOf("2001-12-11"),
                new Address("Russia","Moscow", "Nikolskaya", "5s3")));

        list.add(new Client(
                "Ivan",
                "Petrov",
                Date.valueOf("2000-03-15"),
                new Address("Russia","Kazan", "Skolskaya", "5s6")));

        list.add(new Client(
                "Oleg",
                "Petrov",
                Date.valueOf("2002-07-21"),
                new Address("Russia","Perm", "Golskaya", "1s3")));

        list.add(new Client(
                "Igor",
                "Andreev",
                Date.valueOf("2006-09-17"),
                new Address("Russia","Moscow", "Kobolskaya", "1s7")));

        //System.out.println(Finder.dateSearcher(Date.valueOf("2001-01-01"),Date.valueOf("2002-12-31"), list));
        assertEquals(list.size(),4);
    }

    @Test
    public void test_findYoungest(){
        ArrayList<Client> list = new ArrayList<>();
        list.add(new Client(
                "Ivan",
                "Ivanov",
                Date.valueOf("2000-03-15"),
                new Address("Russia","Moscow", "Nikolskaya", "5s3")));

        list.add(new Client(
                "Ivan",
                "Petrov",
                Date.valueOf("2000-03-15"),
                new Address("Russia","Kazan", "Skolskaya", "5s6")));

        list.add(new Client(
                "Oleg",
                "Petrov",
                Date.valueOf("2002-07-21"),
                new Address("Russia","Perm", "Golskaya", "1s3")));

        list.add(new Client(
                "Igor",
                "Andreev",
                Date.valueOf("2006-09-17"),
                new Address("Russia","Moscow", "Kobolskaya", "1s7")));

        //System.out.println(Finder.findYoungest(list));
        assertEquals(Finder.findYoungest(list).size(),1);
        assertEquals(Finder.findYoungest(list).get(0).getSurname(),"Andreev");
    }

    @Test
    public void test_findOldest(){
        ArrayList<Client> list = new ArrayList<>();
        list.add(new Client(
                "Ivan",
                "Ivanov",
                Date.valueOf("2000-03-15"),
                new Address("Russia","Moscow", "Nikolskaya", "5s3")));

        list.add(new Client(
                "Ivan",
                "Petrov",
                Date.valueOf("2000-03-15"),
                new Address("Russia","Kazan", "Skolskaya", "5s6")));

        list.add(new Client(
                "Oleg",
                "Petrov",
                Date.valueOf("2002-07-21"),
                new Address("Russia","Perm", "Golskaya", "1s3")));

        list.add(new Client(
                "Igor",
                "Andreev",
                Date.valueOf("2006-09-17"),
                new Address("Russia","Moscow", "Kobolskaya", "1s7")));

        //System.out.println(Finder.findOldest(list));
        assertEquals(Finder.findOldest(list).size(),2);
    }

    @Test
    public void test_findSameStreetClients(){
        ArrayList<Client> list = new ArrayList<>();
        list.add(new Client(
                "Ivan",
                "Ivanov",
                Date.valueOf("2001-12-11"),
                new Address("Russia","Moscow", "Nikolskaya", "5s3")));

        list.add(new Client(
                "Ivan",
                "Petrov",
                Date.valueOf("2000-03-15"),
                new Address("Russia","Kazan", "Skolskaya", "5s6")));

        list.add(new Client(
                "Oleg",
                "Petrov",
                Date.valueOf("2002-07-21"),
                new Address("Russia","Perm", "Golskaya", "1s3")));

        list.add(new Client(
                "Igor",
                "Andreev",
                Date.valueOf("2006-09-17"),
                new Address("Russia","Moscow", "Kobolskaya", "1s7")));
        list.add(new Client(
                "Ivan",
                "Bobrov",
                Date.valueOf("2003-02-17"),
                new Address("Russia","Moscow", "Golskaya", "1s2")));

        //System.out.println(Finder.findSameStreetClients("Golskaya",list));
        assertEquals(Finder.findSameStreetClients("Golskaya",list).size(),2);
        assertEquals(Finder.findSameStreetClients("Golskaya",list).get(0).getSurname(),"Petrov");
        assertEquals(Finder.findSameStreetClients("Golskaya",list).get(1).getSurname(),"Bobrov");
    }
}