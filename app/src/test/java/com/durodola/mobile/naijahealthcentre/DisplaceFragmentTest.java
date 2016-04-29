package com.durodola.mobile.naijahealthcentre;

import com.durodola.mobile.naijahealthcentre.Activity.MainActivity;
import com.durodola.mobile.naijahealthcentre.Fragment.DisplayContentFragment;
import com.durodola.mobile.naijahealthcentre.Fragment.HivFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by mobile on 2016-04-27.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class DisplaceFragmentTest {
    private MainActivity activity;
    HivFragment hivFragment;

    @Test
    public void shouldHaveHappySmiles() throws Exception {
        String hello = new MainActivity().getResources().getString(R.string.hello_world);
        assertThat(hello, equalTo("hello world"));
    }

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create().get();
    }

    @Test
    public void shouldNotBeNull() throws Exception {
        DisplayContentFragment fragment = new DisplayContentFragment();
        SupportFragmentTestUtil.startFragment(fragment);
        assertNotNull(fragment);
    }

    @Test
    public void buttonClickShouldStartNewFragment() throws Exception {
        hivFragment = new HivFragment();
        SupportFragmentTestUtil.startFragment(hivFragment);
        assertNotNull(hivFragment);

    }

}
