package com.durodola.mobile.naijahealthcentre;

import com.durodola.mobile.naijahealthcentre.Activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by mobile on 2016-04-28.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class EbolaFragmentTest {
    MainActivity activity;


    @Before
    public void setup() {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create().get();
    }

    @Test
    public void testEbolaDisplay() {

        assertThat(activity.getResources().getString(R.string.treatment), equalTo("TREATMENT AND VACCINE"));
        assertThat(activity.getResources().getString(R.string.prevention), equalTo("PREVENTION AND CONTROL"));
        assertThat(activity.getResources().getString(R.string.who), equalTo("WHO RESPONSE"));
    }
}
