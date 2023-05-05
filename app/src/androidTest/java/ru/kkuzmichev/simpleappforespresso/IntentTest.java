package ru.kkuzmichev.simpleappforespresso;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntentTest {

    @Rule
    public IntentsTestRule intentsTestRule = new IntentsTestRule(MainActivity.class);

    @Test
    public void intentTest() {
        ViewInteraction menuButton = onView(Matchers.allOf(withParent(withParent(withId(R.id.toolbar)))));
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(androidx.recyclerview.R.id.title),
                       withParent(allOf(withId(androidx.constraintlayout.widget.R.id.content),
                        isDisplayed()))));
        textView.check(matches(withText("Settings")));
        textView.perform(click());
        intended(hasData(Uri.parse("https://google.com")));
        intended(hasAction(Intent.ACTION_VIEW));
    }
}
