package com.example.CriminalIntent;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

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

        CrimeAdapter crimeAdapter=new CrimeAdapter(mCrimes);
        setListAdapter(crimeAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //由于checkbox是可聚焦的,要把checkbox的 focusable属性设置为false (在布局文件里面设置)
        Crime c=((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(CrimeFragment.TAG, c.getTitle()+" was clicked");

    }

    private class CrimeAdapter extends ArrayAdapter<Crime>{
        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(),0,crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //if we weren't given view, inflate one
            if (convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_crime,null);
            }

            // Configure the view for this Crime
            Crime c=getItem(position);

            TextView titleTextView=(TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView=(TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDateFormat());
            CheckBox solvedCheckbox=(CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckbox);
            solvedCheckbox.setChecked(c.isSolved());

            return convertView;

        }
    }
}
