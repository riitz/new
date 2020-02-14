package com.example.mobilemanagementsystem;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.mobilemanagementsystem.Activity.EditProfile;

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
public class EditTest {
    @Rule
    public ActivityTestRule<EditProfile>
            testRule = new ActivityTestRule<>(EditProfile.class);

    @Test
    public void edit() {
        onView(withId(R.id.name)).perform(typeText("ritesh"), closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText("rites@gmail.com"), closeSoftKeyboard());
        onView(withId(R.id.phone)).perform(typeText("9878675656"), closeSoftKeyboard());
        onView(withId(R.id.username)).perform(typeText("ritz"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText("123456"), closeSoftKeyboard());

        onView(withId(R.id.updateuser))
                .check(matches(isDisplayed()));
    }
}