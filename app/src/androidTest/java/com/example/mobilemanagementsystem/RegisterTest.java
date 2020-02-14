package com.example.mobilemanagementsystem;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.mobilemanagementsystem.Activity.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterTest {
    @Rule
    public ActivityTestRule<RegisterActivity>
            testRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void login() {
        onView(withId(R.id.name)).perform(typeText("ritesh"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rites@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("9878675656"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("ritz"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("123456"), closeSoftKeyboard());

        onView(withId(R.id.signup))
                .check(matches(isDisplayed()));
    }
}