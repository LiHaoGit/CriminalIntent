package com.example.CriminalIntent;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by Administrator on 14/8/18/018.
 * 提供碎片的加入
 */
public abstract class SingleFragmentActivity extends Activity {
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        FragmentManager fm=getFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.fragment_Container);
        if (fragment==null){
            fragment=createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_Container,fragment)
                    .commit();
        }

    }
}
