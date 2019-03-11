package app.seehow.squaretextview;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void mainActivityTest2() {
        ViewInteraction textView = onView(
                allOf(withText("Hello there!"),
                        isDisplayed()));
        textView.check(matches(withText("Hello there!")));
        textView.check(matches(withSquareDimensions()));
    }

    private Matcher<? super View> withSquareDimensions() {
        return new SquareViewDimensionMatcher();
    }

    private class SquareViewDimensionMatcher extends TypeSafeMatcher<View> {

        int width;
        int height;


        @Override
        public void describeTo(Description description) {
            description.appendText(" with width = ");
            description.appendValue(width);
            description.appendText(",");

            description.appendText(" height = ");
            description.appendValue(height);
        }

        @Override
        protected boolean matchesSafely(View item) {
            width = item.getWidth();
            height = item.getHeight();
            return item.getWidth() == item.getHeight();
        }
    }
}
