//package CycleThree.ReleasedCode;
package test.java; //for VSCode fix

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import main.java.GeneDataBase;
import main.java.Person;

/**
 * Test methods in GeneDataBaseTest test the functionality of the GeneDataBase
 * class for the actual people read in to our database using a copy of the geneMap.
 * This class tests the functionality independent of any GUIs.
 */
public class GeneDataBaseTest {

    private String file = "/Users/kendall/Desktop/comp330/Comp330Fall2020TeamZ/FamilyTreeInputTextFileV2.txt";
    private GeneDataBase sut = new GeneDataBase(file);

    @After
    public void tearDown() throws Exception { sut = null; assertNull(sut); }

    /**
     * assert the sut != null, then
     * for each person read into the database, verify that the object in memory
     * read in to the HashMap matches the object that is searched for
     */
    @Test
    public void testPlantedPeople() {
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        for (Person person : sut.exportData().values()) {
            assertNotNull(person);
            assertEquals(sut.findPerson(person.getID()), person); // assert that person is contained with their ID
        }
    }

    /**
     * assert the sut != null, then
     * verify that the correct Partnership data was successfully read in from 
     * the file and added to the database
     */
    @Test
    public void testPlantedPartnerships() {
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        Person person;
        person = sut.findPerson("P7"); assertEquals("P6", person.getSpouse()); // single perma marriage
        person = sut.findPerson("P6"); assertEquals("P7", person.getSpouse()); // single perma marriage
        person = sut.findPerson("P10"); assertEquals("P8", person.getSpouse()); // single perma marriage
        person = sut.findPerson("P8"); assertEquals("P10", person.getSpouse()); // remarried
        person = sut.findPerson("P18"); assertEquals("N/a", person.getSpouse()); 
        person = sut.findPerson("P19"); assertEquals("P20", person.getSpouse()); // single perma marriage
        person = sut.findPerson("P20"); assertEquals("P19", person.getSpouse()); // single perma marriage
        person = sut.findPerson("P17"); assertEquals("N/a", person.getSpouse()); 
        person = sut.findPerson("P30"); assertEquals("P9", person.getSpouse()); // single perma marriage
        person = sut.findPerson("P9"); assertEquals("P30", person.getSpouse()); // single perma marriage
    }

    @Test
    public void testPlantedParents() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testCreatePartnership1() {
        // TODO
        fail("not implemented");
        sut.createPartnership(new String[]{" "," "," "," "," "," "," "," "});
    }

    @Test
    public void testCreateParents1() {
        // TODO
        fail("not implemented");
    }

    /* TODO test methods for adding new relationships */

    @Test
    public void testCreateNewMarriage1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testGetMales1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testGetFemales1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testGetAllPeople1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testFindPerson1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testFindExactNameID1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testFindExactSurnameID1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testFindExactFirstNameID1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testGetChildren1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testGetParents1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testGetGrandparents1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testGetSiblings1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testEditEntry1() {
        // TODO
        fail("not implemented");
    }
}