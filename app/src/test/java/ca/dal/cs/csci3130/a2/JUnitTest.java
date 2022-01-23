package ca.dal.cs.csci3130.a2;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTest {
    static MainActivity mainActivity;

    @BeforeClass
    public static void setup() {

        mainActivity = new MainActivity();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**UAT-II*/
    @Test
    public void checkIfNetIDIsEmpty() {
        assertTrue(mainActivity.isEmptyNetID(""));
        assertFalse(mainActivity.isEmptyNetID("xyz$56"));
    }

    /**UAT-III*/
    @Test
    public void checkIfNetIDIsValid() {
        assertTrue(mainActivity.isValidNetID("xy123456"));
    }

    /**UAT-III*/
    @Test
    public void checkIfNetIDIsInvalid() {
        assertFalse(mainActivity.isValidNetID("123ab456"));
        assertFalse(mainActivity.isValidNetID("abc12345"));
    }

    /**UAT-IV*/
    @Test
    public void checkIfEmailIsValid() {
        assertTrue(mainActivity.isValidEmailAddress("abc123@dal.ca"));
    }

    /**UAT-IV*/
    @Test
    public void checkIfEmailIsInvalid() {
        assertFalse(mainActivity.isValidEmailAddress("abc.123dal.ca"));
    }

    @Test
    public void checkIfEmailIsNotDALEmail() {
        assertTrue(mainActivity.isDALEmailAddress("abc.123@dal.ca"));
        assertFalse(mainActivity.isDALEmailAddress("abc.123@usask.ca"));
    }

}