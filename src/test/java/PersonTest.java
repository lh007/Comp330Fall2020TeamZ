//package CycleThree.ReleasedCode;
package test.java; //for VSCode fix

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import main.java.Person;

public class PersonTest {

    // TODO add set up and tear down

    /**
     * tests getDob, getAge, getDod for Person w/ Dob
    */
    @Test
    public void testDateAge1() {
        Person sut = new Person(new String[]{" "," "," "," ","11/18/1998"," "," "," "," "," "});
        assertEquals("11/18/1998", sut.getDob());
        assertEquals(22, sut.getAge()); // test valid until 11/18/2021
        assertEquals("N/a", sut.getDod());
        // TODO add setDob, setAge, setDod tests
    }

    /**
     * tests getDob, getAge, getDod for Person w/ Dod
    */
    @Test
    public void testDateAge2() {
        Person sut = new Person(new String[]{" "," "," "," "," "," ","1/20/2020"," "," "," "});
        assertEquals("N/a", sut.getDob());
        assertEquals(0, sut.getAge()); // age == 0 because they do not have a birth date
        assertEquals("1/20/2020", sut.getDod());
        // TODO add setDob, setAge, setDod tests
    }

    /**
     * tests getDob, getAge, getDod for Person w/ Dob and Dod
    */
    @Test
    public void testDateAge3() {
        Person sut = new Person(new String[]{" "," "," "," ","1/30/1900"," ","1/20/2020"," "," "," "});
        assertEquals("1/30/1900", sut.getDob());
        assertEquals(119, sut.getAge()); // age keeps counting up even if they have died
        assertEquals("1/20/2020", sut.getDod());
        // TODO add setDob, setAge, setDod tests
    }

    /**
     * tests getFamilyName, getGivenName, getSuffix
    */
    @Test
    public void testName1() {
        Person sut = new Person(new String[]{" ","Johnson","Dick","Jr"," "," "," "," "," "," "});
        assertEquals("Johnson", sut.getFamilyName());
        assertEquals("Dick", sut.getGivenName());
        assertEquals("Jr", sut.getSuffix());
        // TODO add setFamilyName, setGivenName, setSuffix tests
    }

    /**
     * tests getID
    */
    @Test
    public void testID1() {
        Person sut = new Person(new String[]{"P0"," "," "," "," ",""," "," "," "," "});
        assertEquals("P0", sut.getID());
        // TODO add setID tests
    }

    /**
     * tests getBirthPlace, getDeathPlace
    */
    @Test
    public void testBirthDeathPlace1() {
        Person sut = new Person(new String[]{" "," "," "," "," ","Chicago"," ","Dallas"," "," "});
        assertEquals("Chicago", sut.getBirthPlace());
        assertEquals("Dallas", sut.getDeathPlace());
        // TODO add setBirthPlace, setDeathPlace tests
    }

    /**
     * tests getMarriageDetails, setMarriageDetails
    */
    @Test
    public void testMarriageDetails1() {
        // TODO
    }

    /**
     * tests getParents, getParentRelationshipID, setParents, setParentRelationshipID
    */
    @Test
    public void testParents1() {
        // TODO
    }

    /**
     * tests getSpouse, getChildren, setSpouse, setChildren
    */
    @Test
    public void testSpouseChildren1() {
        Person sut = new Person(new String[]{"P0"," "," "," "," "," "," "," "," "," "});
        assertEquals("N/a", sut.getSpouse());
        assertTrue(sut.getChildren().isEmpty());
        Person spouse = new Person(new String[]{"P1"," "," "," "," "," "," "," "," "," "});
        sut.setSpouse(spouse.getID());
        assertFalse(sut.getSpouse().isEmpty());
        assertEquals("P1", sut.getSpouse());
        Person child1 = new Person(new String[]{"P2"," "," "," "," "," "," "," "," "," "});
        Person child2 = new Person(new String[]{"P3"," "," "," "," "," "," "," "," "," "});
        sut.setChildren(new ArrayList<String>(Arrays.asList(child1.getID(), child2.getID())));
        assertFalse(sut.getChildren().isEmpty());
        assertEquals("[P2, P3]", sut.getChildren().toString());
    }


}
