package labs.leandog.android.testing.matchers;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import android.app.Activity;

import com.xtremelabs.robolectric.shadows.ShadowActivity;

public class ActivityFinishedMatcher extends BaseMatcher<Activity> {

    private String message;

    @Override
    public boolean matches(Object item) {
        final Activity actualActivity = (Activity) item;
        ShadowActivity shadowActivity = shadowOf(actualActivity);

        message = " finish called for " + actualActivity.getClass().getSimpleName();
        return shadowActivity.isFinishing();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(message);
    }

    public static Matcher<Activity> finished() {
        return new ActivityFinishedMatcher();
    }

    public static Matcher<Activity> finishedWithTheResultCode(int resultCode) {
        return new ActivityFinishedWithResultCodeMatcher(resultCode);
    }

}