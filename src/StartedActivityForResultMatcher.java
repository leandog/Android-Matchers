

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import android.app.Activity;

import com.xtremelabs.robolectric.shadows.ShadowActivity.IntentForResult;

public class StartedActivityForResultMatcher extends TypeSafeMatcher<Activity> {
    private final String expectedActivityClass;

    private String message;

    private final int expectedResultCode;

    public StartedActivityForResultMatcher(Class<? extends Activity> expectedActivityClass, int resultCode) {
        this.expectedResultCode = resultCode;
        this.expectedActivityClass = expectedActivityClass.getName();
    }

    @Override
    public boolean matchesSafely(Activity actualActivity) {
        
        message = "to start " + expectedActivityClass + " with result code " + expectedResultCode + ", but ";
        
        final IntentForResult actualIntentForResult = shadowOf(actualActivity).getNextStartedActivityForResult();

        if (actualIntentForResult == null) {
            message += "didn't start anything";
            return false;
        }
        
        String actualIntentClass = actualIntentForResult.intent.getComponent().getClassName();

        boolean intentsMatch = expectedActivityClass.equals(actualIntentClass) && expectedResultCode == actualIntentForResult.requestCode;
        
        if (!intentsMatch) {
            message += "started " + actualIntentClass + " with code " + actualIntentForResult.requestCode;
        }
        return intentsMatch;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(message);
    }
    
    public static Matcher<Activity> startedForResult(Class<? extends Activity> expectedClass, int resultCode) {
        return new StartedActivityForResultMatcher(expectedClass, resultCode);
    }
}
