package com.example.CriminalIntent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 14/8/20/020.
 */
public class CrimePageViewActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager=new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fm = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                Crime c=mCrimes.get(i);
                return CrimeFragment.newInstance(c.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        //设置viewpager的当前item,改变当前activity的标题
        UUID crimeId=(UUID)getIntent().getSerializableExtra(CrimeFragment.CRIMEID);
        for (int i=0;i<mCrimes.size();i++){
            if (mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                if (mCrimes.get(i).getTitle()!=null){
                    setTitle(mCrimes.get(i).getTitle());
                }
                break;
            }
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                Log.d(CrimeFragment.TAG, "onPageScrolled() called! i:" + i+" v:"+v+" i2:"+i2);
            }

            //改变当前activity的标题
            @Override
            public void onPageSelected(int i) {
                Crime c= mCrimes.get(i);
                if (c.getTitle()!=null){
                    setTitle(c.getTitle());
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.d(CrimeFragment.TAG, "onPageSelected() called! i:" + i);

            }
        });

    }
}

