package labs.leandog.android.testing.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import android.content.Intent;


public class HasBooleanExtraMatcher extends TypeSafeMatcher<Intent> {

    private String message = "";
    private final String expectedKey;
    private final Boolean expectedValue;

    public HasBooleanExtraMatcher(String expectedKey, Boolean expectedValue) {
        this.expectedKey = expectedKey;
        this.expectedValue = expectedValue;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(message);
    }

    @Override
    protected boolean matchesSafely(Intent actualIntent) {
        message = "Intent to have extra with key of: " + expectedKey + " with of expected value, but ";

        boolean matched = false;

        if (actualIntent == null)
            message += "Intent was <null>";

        if (!actualIntent.hasExtra(expectedKey))
            message += "did not have " + expectedKey + " in intent.";
        else if (expectedValue != actualIntent.getBooleanExtra(expectedKey, false))
            message += "expected: <" + expectedValue + "> but was <" + actualIntent.getBooleanExtra(expectedKey, false) + ">";
        else
            matched = true;

        return matched;
    }

    public static Matcher<Intent> hasExtra(String expectedKey, Boolean expectedValue) {
        return new HasBooleanExtraMatcher(expectedKey, expectedValue);
    }

}
