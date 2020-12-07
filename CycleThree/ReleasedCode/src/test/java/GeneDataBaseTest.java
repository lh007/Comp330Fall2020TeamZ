package CycleThree.ReleasedCode.src.test.java;
//package test.java; //for VSCode fix

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.After;
import org.junit.jupiter.api.Test;

import CycleThree.ReleasedCode.src.main.java.GeneDataBase;
import CycleThree.ReleasedCode.src.main.java.Person;

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
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        Person person;
        person = sut.findPerson("P1"); assertEquals("[P6, P7]", person.getParents().toString());
        person = sut.findPerson("P2"); assertEquals("[P6, P7]", person.getParents().toString()); 
        person = sut.findPerson("P3"); assertEquals("[P6, P7]", person.getParents().toString()); 
        person = sut.findPerson("P7"); assertEquals("[P10]", person.getParents().toString());
        person = sut.findPerson("P13"); assertEquals("[P8]", person.getParents().toString()); 
        person = sut.findPerson("P9"); assertEquals("[P1]", person.getParents().toString()); 
        person = sut.findPerson("P8"); assertEquals("[]", person.getParents().toString());
        person = sut.findPerson("P15"); assertEquals("[]", person.getParents().toString()); 
        person = sut.findPerson("P16"); assertEquals("[P18]", person.getParents().toString()); 
        person = sut.findPerson("P17"); assertEquals("[P18]", person.getParents().toString()); 
        person = sut.findPerson("P18"); assertEquals("[P10]", person.getParents().toString()); 
        person = sut.findPerson("P23"); assertEquals("[P10]", person.getParents().toString()); 
        person = sut.findPerson("P6"); assertEquals("[P20, P19]", person.getParents().toString()); 
        person = sut.findPerson("P21"); assertEquals("[P20, P19]", person.getParents().toString()); 
        person = sut.findPerson("P25"); assertEquals("[P1]", person.getParents().toString()); 
        person = sut.findPerson("P9"); assertEquals("[P1]", person.getParents().toString()); 
        person = sut.findPerson("P30"); assertEquals("[P17]", person.getParents().toString()); 
        person = sut.findPerson("P31"); assertEquals("[P17]", person.getParents().toString()); 
        person = sut.findPerson("P26"); assertEquals("[P9, P30]", person.getParents().toString()); 
        person = sut.findPerson("P27"); assertEquals("[P9, P30]", person.getParents().toString());  
    }

    @Test
    public void testPlantedChildren() {
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        Person person;
        person = sut.findPerson("P1"); assertEquals("[P9, P25]", person.getChildren().toString());
        person = sut.findPerson("P8"); assertEquals("[P13]", person.getChildren().toString());
        person = sut.findPerson("P19"); assertEquals("[P6, P21]", person.getChildren().toString());
        person = sut.findPerson("P20"); assertEquals("[P6, P21]", person.getChildren().toString());
        person = sut.findPerson("P6"); assertEquals("[P1, P2, P3]", person.getChildren().toString());
        person = sut.findPerson("P7"); assertEquals("[P1, P2, P3]", person.getChildren().toString());
        person = sut.findPerson("P10"); assertEquals("[P7, P18, P23]", person.getChildren().toString());
        person = sut.findPerson("P18"); assertEquals("[P16, P17]", person.getChildren().toString());
        person = sut.findPerson("P19"); assertEquals("[P6, P21]", person.getChildren().toString());
        person = sut.findPerson("P20"); assertEquals("[P6, P21]", person.getChildren().toString());
        person = sut.findPerson("P17"); assertEquals("[P30, P31]", person.getChildren().toString());
        person = sut.findPerson("P9"); assertEquals("[P26, P27]", person.getChildren().toString());
        person = sut.findPerson("P30"); assertEquals("[P26, P27]", person.getChildren().toString());
    }

    @Test
    public void testPlantedSiblings() {
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        assertEquals("[P2, P3]", sut.getSiblings("P1").toString());
        assertEquals("[P1, P3]", sut.getSiblings("P2").toString());
        assertEquals("[P1, P2]", sut.getSiblings("P3").toString());
        assertEquals("[]", sut.getSiblings("P15").toString()); // will be blank
        assertEquals("[]", sut.getSiblings("P8").toString()); // will be blank
        assertEquals("[P6]", sut.getSiblings("P21").toString());
        assertEquals("[P21]", sut.getSiblings("P6").toString());
        assertEquals("[P18, P23]", sut.getSiblings("P7").toString());
        assertEquals("[P7, P23]", sut.getSiblings("P18").toString());
        assertEquals("[P7, P18]", sut.getSiblings("P23").toString());
        assertEquals("[P16]", sut.getSiblings("P17").toString());
        assertEquals("[P17]", sut.getSiblings("P16").toString());
        assertEquals("[P9]", sut.getSiblings("P25").toString());
        assertEquals("[P25]", sut.getSiblings("P9").toString());
        assertEquals("[P31]", sut.getSiblings("P30").toString());
        assertEquals("[P30]", sut.getSiblings("P31").toString());
        assertEquals("[P26]", sut.getSiblings("P27").toString());
        assertEquals("[P27]", sut.getSiblings("P26").toString());
    }

    @Test
    public void testPlantedGrandparents() {
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        assertEquals("[P20, P19, P10]", sut.getGrandparents("P1").toString()); 
        assertEquals("[P20, P19, P10]", sut.getGrandparents("P2").toString());
        assertEquals("[P20, P19, P10]", sut.getGrandparents("P3").toString());
        assertEquals("[]", sut.getGrandparents("P6").toString());
        assertEquals("[]", sut.getGrandparents("P7").toString());
        assertEquals("[]", sut.getGrandparents("P8").toString());
        assertEquals("[]", sut.getGrandparents("P10").toString());
        assertEquals("[]", sut.getGrandparents("P13").toString());
        assertEquals("[P6, P7]", sut.getGrandparents("P9").toString());
        assertEquals("[]", sut.getGrandparents("P15").toString());
        assertEquals("[P10]", sut.getGrandparents("P16").toString());
        assertEquals("[P10]", sut.getGrandparents("P17").toString());
        assertEquals("[]", sut.getGrandparents("P18").toString());
        assertEquals("[]", sut.getGrandparents("P19").toString());
        assertEquals("[]", sut.getGrandparents("P20").toString());
        assertEquals("[]", sut.getGrandparents("P21").toString());
        assertEquals("[]", sut.getGrandparents("P23").toString());
        assertEquals("[P6, P7]", sut.getGrandparents("P25").toString());
        assertEquals("[P18]", sut.getGrandparents("P30").toString());
        assertEquals("[P18]", sut.getGrandparents("P31").toString());
        assertEquals("[P1, P17]", sut.getGrandparents("P26").toString());
        assertEquals("[P1, P17]", sut.getGrandparents("P27").toString());
    }

    @Test
    public void testFindExactNameID1() {
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        assertEquals("[P1, P6, P9, P26]", sut.findExactNameID("Johnson", "Dick").toString());
    }

    @Test
    public void testFindExactSurnameID1() {
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        assertEquals("[P10, P23]",sut.findExactSurnameID("Lopez").toString());
    }

    @Test
    public void testFindExactFirstNameID1() {
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        assertEquals("[P3, P17]",sut.findExactFirstNameID("Sally Abigale").toString());
    }

    @Test
    public void testEditEntry1() {
        // TODO editEntry gives NP exception
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        sut.editEntry(sut.findPerson("P1"), 0, "Marcus"); // "Dick" to "Marcus"
        sut.editEntry(sut.findPerson("P2"), 1, "Young"); // "Johnson" to "Young"
        sut.editEntry(sut.findPerson("P6"), 2, "Sr"); // blank to "Sr"
        sut.editEntry(sut.findPerson("P18"), 3, "Tel Aviv"); // blank to "Tel Aviv"
        sut.editEntry(sut.findPerson("P15"), 4, "10/09/2020"); // "09/09/1888" to "10/09/2020"
        sut.editEntry(sut.findPerson("P30"), 5, "New York"); // blank to "New York"
        sut.editEntry(sut.findPerson("P1"), 6, "12/30/2021"); // "12/30/2020" to "12/30/2021"
        assertEquals("Marcus", sut.findPerson("P1").getGivenName());
        assertEquals("Young", sut.findPerson("P2").getFamilyName());
        assertEquals("Sr", sut.findPerson("P6").getSuffix());
        assertEquals("Tel Aviv", sut.findPerson("P18").getBirthPlace());
        assertEquals("10/09/2020", sut.findPerson("P15").getDob());
        assertEquals("New York", sut.findPerson("P30").getDeathPlace());
        assertEquals("12/30/2021", sut.findPerson("P1").getDod());
    }

    /* TODO test methods for adding new relationships */

    @Test
    public void testCreatePartnership1() {
        assertNotNull(sut); // assert sut was initialized
        // must plant tree manually; does not work with 'Before'
        try {
            sut.plantTree();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(!sut.exportData().isEmpty()); // assert that data has been entered into sut
        Person person;
        // neither married before
        sut.createPartnership(new String[]{"R10","P27","P13"," "," "," "," "," "});
        person = sut.findPerson("P27"); assertEquals("P13", person.getSpouse());
        person = sut.findPerson("P13"); assertEquals("P27", person.getSpouse());
        // one married before
        sut.createPartnership(new String[]{"R11","P31","P25"," "," "," "," "," "});
        person = sut.findPerson("P31"); assertEquals("P25", person.getSpouse());
        person = sut.findPerson("P25"); assertEquals("P31", person.getSpouse());
        // both married before
        sut.createPartnership(new String[]{"R12","P23","P9"," "," "," "," "," "});
        person = sut.findPerson("P9"); assertEquals("P23", person.getSpouse());
        person = sut.findPerson("P23"); assertEquals("P9", person.getSpouse());
    }

    @Test
    public void testCreateChildren1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testCreateParents1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testCreateGrandparents1() {
        // TODO
        fail("not implemented");
    }

    @Test
    public void testCreateSiblings1() {
        // TODO
        fail("not implemented");
    }
}