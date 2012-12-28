package labs.leandog.android.testing.matchers;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import android.app.Activity;

public class ActivityFinishedWithResultCodeMatcher extends BaseMatcher<Activity> {

    private int resultCode;
    private String message;

    public ActivityFinishedWithResultCodeMatcher(int resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(message);
    }

    @Override
    public boolean matches(Object activityObject) {
        message = String.format("to finish with result code %s, but was %s", resultCode, shadowOf((Activity) activityObject).getResultCode());
        return shadowOf((Activity) activityObject).getResultCode() == resultCode;
    }

    public Matcher<Activity> finishedWithTheResultCode(int resultCode) {
        return new ActivityFinishedWithResultCodeMatcher(resultCode);
    }

}
