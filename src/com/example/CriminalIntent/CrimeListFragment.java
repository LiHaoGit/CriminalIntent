package com.example.CriminalIntent;

import android.app.ListFragment;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by Administrator on 14/8/18/018.
 * 数据展示碎片
 */
public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes =CrimeLab.get(getActivity()).getCrimes();
    }
}
