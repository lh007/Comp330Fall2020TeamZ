//package CycleThree.ReleasedCode;
package test.java; //for VSCode fix

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import main.java.Person;

/**
 * Test methods in PersonTest test the functionality of 
 * the Person class independent of a database.
 */
public class PersonTest {

    Person sut; // the main Person object we are creating and testing
    Person spouse; Person spouse2;// sut's spouse, if applicable for the test
    Person child1; Person child2; Person child3; // sut's children, if applicable for the test

    @After
    public void tearDown() throws Exception { 
        sut = null;
        assertNull(sut);
        spouse = null;
        assertNull(spouse);
        spouse2 = null;
        assertNull(spouse2);
        child1 = null; child2 = null; child3 = null;
        assertNull(child1); assertNull(child2); assertNull(child3);
    }

    /**
     * tests getDob, getAge, getDod and setDob, setDod
     * for living Person w/ Dob w/o Dod
     */
    @Test
    public void testDateAge1() {
        sut = new Person(new String[]{" "," "," "," ","11/18/1998"," "," "," "," "," "});
        assertEquals("11/18/1998", sut.getDob());
        assertEquals(22, sut.getAge()); // test valid until 11/18/2021
        assertEquals("N/a", sut.getDod());
        sut.setDob("11/18/2001"); // setting new date of birth
        assertEquals("11/18/2001", sut.getDob());
        assertEquals(19, sut.getAge()); // test valid until 11/18/2021
        sut.setDod("05/06/2020");
        assertEquals("05/06/2020", sut.getDod());
        assertEquals(18, sut.calculateDeathAge(sut.getDob(), sut.getDod())); // 18, because they died before their 19th birthday
        assertEquals(19, sut.getAge()); // should still show 19, because their age keeps counting after death
                                        // test valid until 11/18/2021
    }

    /**
     * tests getDob, getAge, getDod and setDob, setDod
     * for dead Person w/ Dod w/o Dob
     */
    @Test
    public void testDateAge2() {
        sut = new Person(new String[]{" "," "," "," "," "," ","1/20/2020"," "," "," "});
        assertEquals("N/a", sut.getDob());
        assertEquals(0, sut.getAge()); // age == 0 because they do not have a birth date
        assertEquals("1/20/2020", sut.getDod());
        assertEquals(-1, sut.calculateDeathAge(sut.getDob(), sut.getDod())); // returns -1 because they do not have a date of birth
        sut.setDob("05/20/2018"); // setting date of birth
        assertEquals("05/20/2018", sut.getDob());
        assertEquals(2, sut.getAge()); // age == 2 now because they were given a birth date (even though they are dead)
                                       // test valid until 5/20/2021
        assertEquals(1, sut.calculateDeathAge(sut.getDob(), sut.getDod())); // returns 1 because they now
                                                                            // have a birth date
        sut.setDod("1/20/2019");
        assertEquals("1/20/2019", sut.getDod());
        assertEquals(0, sut.calculateDeathAge(sut.getDob(), sut.getDod())); // 0 because their date of death was reset
        assertEquals(2, sut.getAge()); // their overall age is still 2, test valid until 5/20/2021
    }

    /**
     * tests getDob, getAge, getDod for Person w/ Dob w/ Dod
     */
    @Test
    public void testDateAge3() {
        sut = new Person(new String[]{" "," "," "," ","1/30/1900"," ","1/20/2020"," "," "," "});
        assertEquals("1/30/1900", sut.getDob());
        assertEquals(119, sut.getAge()); // age keeps counting up even if they have died
        assertEquals("1/20/2020", sut.getDod());
        assertEquals(119, sut.calculateDeathAge(sut.getDob(), sut.getDod()));
    }

    /**
     * tests getDob, getAge, getDod for Person w/o Dob and Dod
     */
    @Test
    public void testDateAge4() {
        sut = new Person(new String[]{" "," "," "," "," "," "," "," "," "," "});
        assertEquals("N/a", sut.getDob());
        assertEquals(0, sut.getAge());
        assertEquals("N/a", sut.getDod());
        assertEquals(-1, sut.calculateDeathAge(sut.getDob(), sut.getDod()));
    }

    /**
     * tests getFamilyName, getGivenName, getSuffix, 
     * setFamilyName, setGivenName, and setSuffix
     */
    @Test
    public void testName1() {
        sut = new Person(new String[]{" ","Johnson","Dick","Jr"," "," "," "," "," "," "});
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
        sut = new Person(new String[]{"P0"," "," "," "," ",""," "," "," "," "});
        assertEquals("P0", sut.getID());
        // TODO add setID tests
    }

    /**
     * tests getBirthPlace, getDeathPlace, setBirthPlace, setDeathPlace
     */
    @Test
    public void testBirthDeathPlace1() {
        sut = new Person(new String[]{" "," "," "," "," ","Chicago"," ","Dallas"," "," "});
        assertEquals("Chicago", sut.getBirthPlace());
        assertEquals("Dallas", sut.getDeathPlace());
        sut.setBirthPlace("Las Vegas");
        assertEquals("Las Vegas", sut.getBirthPlace());
        sut.setDeathPlace("San Francisco");
        assertEquals("San Francisco", sut.getDeathPlace());
    }

    /**
     * tests newPartnership, getSpouse, getMarriageDetails
     */
    @Test
    public void testMarriageDetails1() {
        sut = new Person(new String[]{"P1"," "," "," "," "," "," "," "," "," "});
        spouse = new Person(new String[]{"P2"," "," "," "," "," "," "," "," "," "});
        child1 = new Person (new String[]{"P3"," "," "," "," "," "," "," "," "," "});
        child2 = new Person (new String[]{"P4"," "," "," "," "," "," "," "," "," "});
        sut.newPartnership(new String[]{"R1","P1","P2","01/01/2020","01/02/2020",
        "Las Vegas","Chicago"," "," "}, spouse);
        assertEquals("P2", sut.getSpouse());
        assertEquals("P1", spouse.getSpouse());
        assertEquals("P2", sut.getMarriageDetails()[2]);
        assertEquals("P1", spouse.getMarriageDetails()[1]);
    }

    /**
     * tests newPartnership, setNewMarriageDetails, getSpouse, getMarriageDetails
     */
    @Test
    public void testMarriageDetails2() {
        // the same marriage as before
        sut = new Person(new String[]{"P1"," "," "," "," "," "," "," "," "," "});
        spouse = new Person(new String[]{"P2"," "," "," "," "," "," "," "," "," "});
        sut.newPartnership(new String[]{"R1","P1","P2","01/01/2020","01/02/2020",
        "Las Vegas","Chicago"," "," "}, spouse);
        assertEquals("P2", sut.getSpouse());
        assertEquals("P1", spouse.getSpouse());
        assertEquals("P2", sut.getMarriageDetails()[2]);

        // this time, set new marriage details for SUT
        spouse2 = new Person(new String[]{"P3"," "," "," "," "," "," "," "," "," "});
        sut.newPartnership(new String[]{"R2","P1","P3","01/03/2020","01/04/2020",
        "Chicago","New York"," "," "}, spouse2);
        assertEquals("P3", sut.getSpouse());
        assertEquals("P3", sut.getMarriageDetails()[2]);
        assertEquals("P1", spouse2.getSpouse());
        assertEquals("P1", spouse2.getMarriageDetails()[1]);
        assertEquals("P1", spouse.getSpouse()); // the old spouse is still technically married to P1, but this is on purpose
                                                // so we can find all of a person's children across marriages
    }

    /**
     * tests getParents, getParentRelationshipID
     */
    @Test
    public void testParents1() {
        // TODO
    }

    /**
     * tests getSpouse, getChildren, setSpouse
     */
    @Test
    public void testSpouseChildren1() {
        sut = new Person(new String[]{"P0"," "," "," "," "," "," "," "," "," "});
        assertEquals("N/a", sut.getSpouse());
        assertTrue(sut.getChildren().isEmpty());
        spouse = new Person(new String[]{"P1"," "," "," "," "," "," "," "," "," "});
        sut.setSpouse(spouse.getID());
        assertFalse(sut.getSpouse().isEmpty());
        assertEquals("P1", sut.getSpouse());
        //Person child1 = new Person(new String[]{"P2"," "," "," "," "," "," "," "," "," "});
        //Person child2 = new Person(new String[]{"P3"," "," "," "," "," "," "," "," "," "});
        //sut.setChildren(new ArrayList<String>(Arrays.asList(child1.getID(), child2.getID())));
        //assertFalse(sut.getChildren().isEmpty());
        //assertEquals("[P2, P3]", sut.getChildren().toString());
    }

    @Test
    public void testGender1() {
        // TODO
    }

    @Test
    public void testGender2() {
        // TODO
    }

    @Test
    public void testGender3() {
        // TODO
    }


}
