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


public class BasicActivitySwitchTest <T extends Activity>
        extends ActivityInstrumentationTestCase2<T> {

    private final int DEFAULT_TIMEOUT_IN_MS = 10000;
    protected T mCurrentActivity;

    public BasicActivitySwitchTest(Class x) {
        super(x);
    }

    public void testActivitySwitchHelper(int buttonId, Class endActivityClass) {
        testActivitySwitchHelper(buttonId, endActivityClass, DEFAULT_TIMEOUT_IN_MS);
    }

    public void testActivitySwitchHelper(int buttonId, Class endActivityClass,int TIMEOUT_IN_MS) {
        Button mButton = (Button) mCurrentActivity.findViewById(buttonId);
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(endActivityClass.getName(), null, false);
        TouchUtils.clickView(this, mButton);
        Activity mEndActivity = receiverActivityMonitor.waitForActivityWithTimeout(TIMEOUT_IN_MS);
        assertNotNull("###The activity is null", mEndActivity);
        assertEquals("###Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is wrong type", mEndActivity.getClass(), endActivityClass);
        getInstrumentation().removeMonitor(receiverActivityMonitor);
    }
}
