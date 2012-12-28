package labs.leandog.android.testing.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivityCoreMatchers {

    public static Matcher<Activity> finished() {
        return ActivityFinishedMatcher.finished();
    }

    public static Matcher<Activity> finishedWithTheResultCode(int resultCode) {
        return ActivityFinishedMatcher.finishedWithTheResultCode(resultCode);
    }

    public static TypeSafeMatcher<Context> startedWithTheFlag(int flag) {
        return StartedActivityFlagsMatcher.startedWithTheFlag(flag);
    }

    public static Matcher<Activity> startedForResult(Intent intent, int resultCode) {
        return StartedActivityForResultWithIntentMatcher.startedForResult(intent, resultCode);
    }

    public static Matcher<Activity> startedForResult(Class<? extends Activity> expectedClass, int resultCode) {
        return StartedActivityForResultMatcher.startedForResult(expectedClass, resultCode);
    }

    public static Matcher<Context> started(Class<? extends Activity> expectedClass) {
        return StartedActivityMatcher.started(expectedClass);
    }

}