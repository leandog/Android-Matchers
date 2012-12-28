package labs.leandog.android.testing.matchers;

import static com.xtremelabs.robolectric.Robolectric.shadowOf;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import android.content.*;

public class StartedActivityFlagsMatcher extends TypeSafeMatcher<Context> {

    private final int flag;
    private final String hexFormat = "0x%08x";
    private String message;

    public StartedActivityFlagsMatcher(int flag) {
        this.flag = flag;
    }

    @Override
    protected boolean matchesSafely(Context actualContext) {

        message = "to contain the flag " + String.format(hexFormat, flag) + ", but ";

        Intent actualStartedIntent = shadowOf((ContextWrapper) actualContext).getNextStartedActivity();

        if (actualStartedIntent == null) {
            message += "didn't start anything";
            return false;
        }

        boolean intentsMatch = 0 != (actualStartedIntent.getFlags() & flag);

        if (!intentsMatch) {
            message += "did not.  Actual flags were " + String.format(hexFormat, actualStartedIntent.getFlags());
        }
        return intentsMatch;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(message);
    }
    
    public static StartedActivityFlagsMatcher startedWithTheFlag(int flag) {
        return new StartedActivityFlagsMatcher(flag);
    }
    
}
