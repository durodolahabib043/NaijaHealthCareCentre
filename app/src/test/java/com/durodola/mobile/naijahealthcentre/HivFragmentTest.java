package com.durodola.mobile.naijahealthcentre;

import android.widget.TextView;

import com.durodola.mobile.naijahealthcentre.Activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Created by mobile on 2016-04-27.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class HivFragmentTest {
    MainActivity activity ;
    private TextView textView;

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(MainActivity.class)
                .create().get();
      //  activity.onCreate(null);
        textView = (TextView) activity.findViewById(R.id.treatmentdef);
    }
    @Test
    public void shouldGreet() {
      // assertThat(textView.getText().toString(),notNullValue());
        assertEquals(activity.getResources().getString(R.string.hivtreatment), textView.getText().toString());
    }

}
