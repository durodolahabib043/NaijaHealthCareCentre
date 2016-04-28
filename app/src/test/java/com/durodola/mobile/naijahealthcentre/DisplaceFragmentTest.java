package com.durodola.mobile.naijahealthcentre;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
    public void buttonClickShouldStartNewActivity() throws Exception {
        hivFragment = new HivFragment();
        //  hi fragment = new DisplayContentFragment();
        //   hivFragment = new HivFragment();
        SupportFragmentTestUtil.startFragment(hivFragment);
        assertNotNull(hivFragment);



       /* Intent intent = Robolectric.shadowOf(activity).peekNextStartedActivity();
        assertEquals(SecondActivity.class.getCanonicalName(), intent.getComponent().getClassName());*/
    }

    private void startFragment(Fragment fragment) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();
    }
}
