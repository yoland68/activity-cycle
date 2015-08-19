package com.example.android.lifecycle;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.util.Log;
import android.widget.Button;

import com.example.android.lifecycle.ActivityA;
import com.example.android.lifecycle.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by yolandyan on 8/18/15.
 */


public class ActivitySwitchTest extends ActivityInstrumentationTestCase2<ActivityA> {
    private final String LOGTAG =
            String.format("###Yoland: %s", ActivitySwitchTest.class.getSimpleName());
    private static final int TIMEOUT_IN_MS = 10000;
    private ActivityA mActivityA;

    public ActivitySwitchTest() {
        super(ActivityA.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivityA = getActivity();
    }

    public void testButtonNotNull() {
        assertNotNull(mActivityA.findViewById(R.id.btn_finish_a));
    }

    private void testActivitySwitchHelper(int buttonId, Class endActivityClass) {
        Log.d(LOGTAG, String.format("endActivityClass type is %s", endActivityClass));
        Button mButton = (Button) mActivityA.findViewById(buttonId);
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(endActivityClass.getName(), null, false);
        TouchUtils.clickView(this, mButton);
        Activity mEndActivity = receiverActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        Log.d(LOGTAG, "After timeout or end activity starts");
        assertNotNull("###The activity is null", mEndActivity);
        assertEquals("###Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is wrong type", mEndActivity.getClass(), endActivityClass);
        Log.d(LOGTAG, "*_* test passed");
        getInstrumentation().removeMonitor(receiverActivityMonitor);
    }

    public void testActivityAtoActivityB() {
        testActivitySwitchHelper(R.id.btn_start_b, ActivityB.class);
    }
}
