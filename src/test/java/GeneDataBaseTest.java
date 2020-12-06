//package CycleThree.ReleasedCode;
package test.java; //for VSCode fix

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import main.java.GeneDataBase;
import main.java.Person;

/**
 * Test methods in GeneDataBaseTest test the functionality of the GeneDataBase
 * class for the actual people read in to our database using a copy of the geneMap.
 * This class tests the functionality independent of any GUIs.
 */
public class GeneDataBaseTest {

    public GeneDataBase sut;
    public HashMap<String, Person> copiedMap;
    private String file;
    private ArrayList<String> males;
    private ArrayList<String> females;
    private ArrayList<String> allPeople;

    @Before
    public void setUp() throws Exception {
        copiedMap = new HashMap<>();
        file = "FamilyTreeInputTextFileV2.txt";
        males = new ArrayList<String>();
        females = new ArrayList<String>();
        allPeople = new ArrayList<String>();
        sut = new GeneDataBase(copiedMap, file, males, females, allPeople);
        sut.plantTree();
    }

    @After
    public void tearDown() throws Exception { sut = null; assertNull(sut); }

    /**
     * tests that the correct Partnership data was successfully read in from 
     * the file and added to the SUT (database)
     */
    @Test
    public void testPlantedPartnerships() {
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