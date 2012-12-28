package labs.leandog.android.testing.matchers;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import android.app.Activity;
import android.content.Intent;

import com.xtremelabs.robolectric.shadows.ShadowActivity.IntentForResult;

public class StartedActivityForResultWithIntentMatcher extends TypeSafeMatcher<Activity> {
    private String message;
    private final int expectedResultCode;
    private final Intent expectedIntent;

    public StartedActivityForResultWithIntentMatcher(Intent expectedIntent, int resultCode) {
        this.expectedIntent = expectedIntent;
        this.expectedResultCode = resultCode;
    }

    @Override
    public boolean matchesSafely(Activity actualActivity) {
        
        message = "to start " + expectedIntent.getAction() + " with result code " + expectedResultCode + ", but ";
        
        final IntentForResult actualIntentForResult = shadowOf(actualActivity).getNextStartedActivityForResult();

        if (actualIntentForResult == null) {
            message += "didn't start anything";
            return false;
        }

        Intent intent = actualIntentForResult.intent;
        boolean intentsMatch = 
               expectedIntent.equals(intent) &&
               expectedResultCode == actualIntentForResult.requestCode;
        
        if (!intentsMatch) {
            message += "started " + expectedIntent.getAction() + " with code " + actualIntentForResult.requestCode;
        }
        return intentsMatch;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(message);
    }
    
    public static Matcher<Activity> startedForResult(Intent expectedIntent, int resultCode) {
        return new StartedActivityForResultWithIntentMatcher(expectedIntent, resultCode);
    }
}
