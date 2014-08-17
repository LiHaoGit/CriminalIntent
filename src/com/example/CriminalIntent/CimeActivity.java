package com.example.CriminalIntent;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class CimeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crime_activity);

        FragmentManager fm=getFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.fragment_Container);
        if (fragment==null){
            fragment=new CrimeFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_Container,fragment)
                    .commit();
        }

    }
}
