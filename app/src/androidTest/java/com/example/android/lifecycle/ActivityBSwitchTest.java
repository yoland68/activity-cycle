package com.example.android.lifecycle;

import android.test.ActivityTestCase;
import android.util.Log;

/**
 * Created by yolandyan on 8/19/15.
 */
public class ActivityBSwitchTest extends BasicActivitySwitchTest<ActivityB> {
    private final String LOGTAG =
            String.format("###Yoland: %s", ActivityBSwitchTest.class.getSimpleName());
    private static final int TIMEOUT_IN_MS = 1000;

    public ActivityBSwitchTest() {
        super(ActivityB.class);
    }

    @Override
    public void setUp() throws Exception{
        super.setUp();
        setActivityInitialTouchMode(false);
        mCurrentActivity = getActivity();
    }

    public void testActivityNotNull() {
        assertNotNull(mCurrentActivity);
    }

    public void testButtonNotNull() {
        assertNotNull(mCurrentActivity.findViewById(R.id.btn_finish_b));
    }

    public void testSingleActivitySwitch() {
        testActivitySwitchHelper(R.id.btn_start_a, ActivityA.class);
    }
}
