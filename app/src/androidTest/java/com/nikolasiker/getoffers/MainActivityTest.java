package com.nikolasiker.getoffers;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.nikolasiker.feature_offer_list.OfferListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    private String appId;
    private String userId;
    private String apiKey;

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);


    @Before
    public void setUp() {
        appId = "2070";
        userId = "superman";
        apiKey = "1c915e3b5d42d05136185030892fbb846c278927";
    }

    /*
        This could be done much better with idling resources,
        but I didn't have enough time to set it up.
     */
    @Test
    public void testValuesTyped() {

        onView(withId(R.id.appIdEditText))
                .check(matches(isDisplayed()))
                .perform(typeText(appId), closeSoftKeyboard());

        onView(withId(R.id.userIdEditText))
                .check(matches(isDisplayed()))
                .perform(typeText(userId), closeSoftKeyboard());


        onView(withId(R.id.apiKeyEditText))
                .check(matches(isDisplayed()))
                .perform(typeText(apiKey), closeSoftKeyboard());

        onView(withId(R.id.getOffersButton))
                .check(matches(isDisplayed()))
                .perform(click());

        intended(hasComponent(OfferListActivity.class.getName()));

        onView(withId(R.id.loadingView)).check(matches(isDisplayed()));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.offerRecyclerView)).check(matches(isDisplayed()));
    }
}