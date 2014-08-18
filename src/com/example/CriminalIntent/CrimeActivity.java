package com.example.CriminalIntent;


import android.app.Fragment;

/*
 *详情视图
 */

public class CrimeActivity extends SingleFragmentActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
