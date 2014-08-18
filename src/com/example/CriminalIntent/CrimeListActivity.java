package com.example.CriminalIntent;

import android.app.Fragment;

/**
 * Created by Administrator on 14/8/18/018.
 * 数据展示列表
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
