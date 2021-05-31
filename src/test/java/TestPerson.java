import de.telekom.sea2.model.Person;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPerson {
    private Person cut;

    @BeforeEach
    void setup(){
        cut = new Person();
    }
    @Test
    void setFirstname_test(){
//        fail();
//        Assertions.fail();
        cut.setSurname("Anna");
        var result = cut.getSurname();
        assertEquals("Anna", result);
        assertSame("Anna",result);
    }
//    void setFirstname_null_test(){
//        fail();
//        Assertions.fail();
//        cut.setSurname(null);
//        var result = cut.getSurname();
//        assertNull(null, result);
//    }


    @AfterEach
    void teardown(){
        cut = null;
    }
}
