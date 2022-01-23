package ca.dal.cs.csci3130.a2;

import android.content.Context;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);
    public IntentsTestRule<MainActivity> myIntentRule = new IntentsTestRule<>(MainActivity.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @AfterClass
    public static void tearDown() {
        System.gc();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("ca.dal.cs.csci3130.a2", appContext.getPackageName());
    }

    /*** UAT-I**/
    @Test
    public void checkIfRegistrationPageIsVisible() {
        onView(withId(R.id.netID)).check(matches(withText(R.string.EMPTY_STRING)));
        onView(withId(R.id.emailAddress)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    /*** UAT-II**/
    @Test
    public void checkIfNetIDIsEmpty() {
        onView(withId(R.id.netID)).perform(typeText(""));
        onView(withId(R.id.emailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_NET_ID)));
    }

    /*** UAT-III**/
    @Test
    public void checkIfNetIDIsValid() {
        onView(withId(R.id.emailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.netID)).perform(typeText("xy123456"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    /*** UAT-III**/
    @Test
    public void checkIfNetIDIsInvalid() {
        onView(withId(R.id.emailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.netID)).perform(typeText("4512*bn!"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_NET_ID)));
    }

    /*** UAT-IV**/
    @Test
    public void checkIfEmailIsValid() {
        onView(withId(R.id.netID)).perform(typeText("xy123456"));
        onView(withId(R.id.emailAddress)).perform(typeText("abc.123@dal.ca"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    /*** UAT-IV**/
    @Test
    public void checkIfEmailIsInvalid() {
        onView(withId(R.id.netID)).perform(typeText("xy123896"));
        onView(withId(R.id.emailAddress)).perform(typeText("abc123.dal.ca"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_EMAIL_ADDRESS)));
    }

    /*** UAT-IV**/
    @Test
    public void checkIfEmailIsNotDALEmail() {
        onView(withId(R.id.netID)).perform(typeText("xy123896"));
        onView(withId(R.id.emailAddress)).perform(typeText("abc123@usask.ca"));
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_DAL_EMAIL)));
    }

    /**check UAT-5 in isolation*/
    /***UAT-V**/
    @Test
    public void checkIfMoved2WelcomePage() {
        onView(withId(R.id.netID)).perform(typeText("xy456236"));
        onView(withId(R.id.emailAddress)).perform(typeText("abc123@dal.ca"));
        onView(withId(R.id.registerButton)).perform(click());
        intended(hasComponent(WelcomeActivity.class.getName()));
    }

}