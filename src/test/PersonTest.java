package src.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

// why is this needed? want to confirm with team
import src.main.Person;

public class PersonTest {
    
    @Test
    public void testFormatDate1() {
        Person sut = new Person("", "", "", "", "11/18/1998", "", "", "", "");
        assertEquals(sut.formatDate(sut.getDob()).toString(), "1998-11-18");
    }

    @Test
    public void testCalculateAge() {
        // TODO 
    }

    @Test
    public void testRelateParents() {
        // TODO 
    }

    @Test
    public void testRelateSiblings() {
        // TODO 
    }

    @Test
    public void testRelateSpouse() {
        // TODO 
    }

    



}