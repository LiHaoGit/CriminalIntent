package com.example.CriminalIntent;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Administrator on 14/8/18/018.
 * 数据展示碎片
 */
public class CrimeListFragment extends ListFragment {

    private ArrayList<Crime> mCrimes;

    public static final String TAG="CrimeListFragment";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes =CrimeLab.get(getActivity()).getCrimes();

        CrimeAdapter crimeAdapter=new CrimeAdapter(mCrimes);
        setListAdapter(crimeAdapter);
        setHasOptionsMenu(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //由于checkbox是可聚焦的,要把checkbox的 focusable属性设置为false (在布局文件里面设置)
        Crime c=((CrimeAdapter)getListAdapter()).getItem(position);
        //Log.d(CrimeFragment.TAG, c.getTitle()+" was clicked");
        Intent i=new Intent(getActivity(),CrimePageViewActivity.class);
        i.putExtra(CrimeFragment.CRIMEID,c.getId());
        startActivity(i);

    }

    //跟新数据列表
    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter) getListAdapter()).notifyDataSetChanged();
        Log.d(TAG,"notifyDataSetChanged() called!");
    }

    //配置 列表适配器
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

    //创建actionbar菜单
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

    //监视新增菜单
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_crime:
                Crime c=new Crime();
                CrimeLab.get(getActivity()).newCrime(c);
                Intent i=new Intent(getActivity(),CrimePageViewActivity.class);
                i.putExtra(CrimeFragment.CRIMEID,c.getId());
                startActivityForResult(i,0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }



    }
}
