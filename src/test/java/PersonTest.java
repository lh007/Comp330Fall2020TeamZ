package test.java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import main.java.Person;

public class PersonTest {

    // for living person
    @Test
    public void testDateAge1() {
        Person sut = new Person(new String[]{" "," "," "," ","11/18/1998"," "," "," "," "});
        assertEquals("11/18/1998", sut.getDob());
        assertEquals(21, sut.getAge());
        assertEquals("N/a", sut.getDod());
    }

    // for dead person w/o dob
    @Test
    public void testDateAge2() {
        Person sut = new Person(new String[]{" "," "," "," "," "," ","1/20/2020"," "," "});
        assertEquals("N/a", sut.getDob());
        assertEquals(0, sut.getAge()); // age == 0 because they do not have a birth date
        assertEquals("1/20/2020", sut.getDod());
    }

    // for dead person w/ dob
    @Test
    public void testDateAge3() {
        Person sut = new Person(new String[]{" "," "," "," ","1/30/1900"," ","1/20/2020"," "," "});
        assertEquals("1/30/1900", sut.getDob());
        assertEquals(119, sut.getAge()); // age keeps counting up even if they have died
        assertEquals("1/20/2020", sut.getDod());
    }

    @Test
    public void testName1() {
        Person sut = new Person(new String[]{" ","Johnson","Dick","Jr"," "," "," "," "," "});
        assertEquals("Johnson", sut.getFamilyName());
        assertEquals("Dick", sut.getGivenName());
        assertEquals("Jr", sut.getSuffix());
    }

    @Test
    public void testID1() {
        Person sut = new Person(new String[]{"P0"," "," "," "," ",""," "," "," "});
        assertEquals("P0", sut.getID());
    }

    @Test
    public void testBirthDeathPlace1() {
        Person sut = new Person(new String[]{" "," "," "," "," ","Chicago"," ","Dallas"," "});
        assertEquals("Chicago", sut.getBirthPlace());
        assertEquals("Dallas", sut.getDeathPlace());
    }

    @Test
    public void testSpouseChildren1() {
        
    }


}