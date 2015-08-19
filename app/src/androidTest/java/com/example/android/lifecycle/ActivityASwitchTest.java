package com.example.android.lifecycle;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.util.Log;

/**
 * Created by yolandyan on 8/19/15.
 */
public class ActivityASwitchTest extends BasicActivitySwitchTest<ActivityA> {

    private final String LOGTAG =
            String.format("###Yoland: %s", ActivityASwitchTest.class.getSimpleName());
    private static final int TIMEOUT_IN_MS = 1000;

    public ActivityASwitchTest() {
        super(ActivityA.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        mCurrentActivity = getActivity();
    }

    public void testActivityNotNull() {
        assertNotNull(mCurrentActivity);
    }

    public void testButtonNotNull() {
        assertNotNull(mCurrentActivity.findViewById(R.id.btn_finish_a));
    }

    public void testSingleActivitySwitch() {
        testActivitySwitchHelper(R.id.btn_start_b, ActivityB.class);
    }
}
