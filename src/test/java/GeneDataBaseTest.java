//package CycleThree.ReleasedCode;
package test.java; //for VSCode fix

import static org.junit.Assert.assertNull;
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
    public void tearDown() throws Exception { 
        sut = null; assertNull(sut);
    }
    
    @Test
    public void testCreatePartnership1() {
        // TODO
    }

    @Test
    public void testCreateParents1() {
        // TODO
    }

    /* TODO test methods for adding new relationships */

    @Test
    public void testCreateNewMarriage1() {
        // TODO
    }

    @Test
    public void testGetMales1() {
        // TODO
    }

    @Test
    public void testGetFemales1() {
        // TODO
    }

    @Test
    public void testGetAllPeople1() {
        // TODO
    }

    @Test
    public void testFindPerson1() {
        // TODO
    }

    @Test
    public void testFindExactNameID1() {
        // TODO
    }

    @Test
    public void testFindExactSurnameID1() {
        // TODO
    }

    @Test
    public void testFindExactFirstNameID1() {
        // TODO
    }

    @Test
    public void testGetChildren1() {
        // TODO
    }

    @Test
    public void testGetParents1() {
        // TODO
    }

    @Test
    public void testGetGrandparents1() {
        // TODO
    }

    @Test
    public void testGetSiblings1() {
        // TODO
    }

    @Test
    public void testEditEntry1() {
        
    }
}