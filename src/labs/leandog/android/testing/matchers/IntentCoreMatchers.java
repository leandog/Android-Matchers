package labs.leandog.android.testing.matchers;

import org.hamcrest.Matcher;

import android.content.Intent;
import android.os.Parcelable;


public class IntentCoreMatchers {

    public static Matcher<Intent> hasExtra(String expectedKey, Parcelable expectedValue) {
        return HasParcelableExtraMatcher.hasExtra(expectedKey, expectedValue);
    }

    public static Matcher<Intent> hasExtra(String expectedKey, Boolean expectedValue) {
        return HasBooleanExtraMatcher.hasExtra(expectedKey, expectedValue);
    }
}
