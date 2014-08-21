package com.example.CriminalIntent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.util.Log;

import java.util.Date;
import java.util.UUID;


/**
 * Created by lee on 2014/8/17.
 * 详情碎片
 */
public class CrimeFragment extends Fragment {
    private Crime mCrime;
    EditText mTitleField;
    Button mDateButton;
    CheckBox mSolvedCheckBox;

    public static final String TAG="CrimeFragment";
    public static final String CRIMEID="com.example.CriminalIntent.CrimeId";
    private static final int REQUEST_DATE = 0;

    //当activity调用时,传来crime的值
    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args=new Bundle();
        args.putSerializable(CRIMEID,crimeId);
        CrimeFragment fragment=new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //初始化数据
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId=(UUID)getArguments().getSerializable(CRIMEID);
        mCrime=CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    //设置对应布局的显示的值
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_crime,parent,false);

        mTitleField=(EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //This space intentionally left blank;
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This one too;
            }
        });

        mDateButton=(Button)v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDateFormat());
        //mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当点击按钮的时候,创建一个新的DatePickerFragment对话窗
                DialogFragment dialogFragment=DatePickerFragment.newInstance(mCrime.getDate());
                //将次对话窗的回传目标设置为CrimeFragment
                dialogFragment.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                //DatePickerFragment对话窗启动
                dialogFragment.show(getFragmentManager(),"datePicker");
            }
        });

        mSolvedCheckBox=(CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });





        Log.d(TAG,"onCreateView() called!");
        return v;
    }

    //拿到DatePickerFragment回传的数据
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (resultCode!= Activity.RESULT_OK) return;
        if (requestCode==REQUEST_DATE){
            Date date=(Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            mDateButton.setText(mCrime.getDateFormat());
        }
    }
}
