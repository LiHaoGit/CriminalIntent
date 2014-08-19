package com.example.CriminalIntent;


import android.app.Fragment;

import java.util.UUID;

/*
 *详情视图
 */

public class CrimeActivity extends SingleFragmentActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    protected Fragment createFragment() {
        UUID crimeId =(UUID)getIntent().getSerializableExtra(CrimeFragment.CRIMEID);
        return CrimeFragment.newInstance(crimeId);
    }
}
