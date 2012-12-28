package labs.leandog.android.testing.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import android.content.Intent;
import android.os.Parcelable;

public class HasParcelableExtraMatcher extends TypeSafeMatcher<Intent> {

    private String message;
    private final String expectedKey;
    private final Parcelable expectedValue;

    public HasParcelableExtraMatcher(String expectedKey, Parcelable expectedValue) {
        this.expectedKey = expectedKey;
        this.expectedValue = expectedValue;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(message);
    }

    @Override
    protected boolean matchesSafely(Intent actualIntent) {
        message = "extra with key: " + expectedKey + " with expected value " + expectedValue + " but ";

        if (actualIntent == null)
            message += "actual is <null>";

        boolean matched = false;

        if (!actualIntent.hasExtra(expectedKey))
            message += "did not have " + expectedKey + " in intent.";
        else if (!expectedValue.equals(actualIntent.getParcelableExtra(expectedKey)))
            message += "got " + actualIntent.getParcelableExtra(expectedKey);
        else
            matched = true;

        return matched;
    }

    public static HasParcelableExtraMatcher hasExtra(String expectedKey, Parcelable expectedValue) {
        return new HasParcelableExtraMatcher(expectedKey, expectedValue);
    }

}
