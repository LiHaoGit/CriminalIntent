package com.example.CriminalIntent;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DatePickerFragment extends DialogFragment{



    public static final String EXTRA_DATE="DatePickerFragment.date";

    private Date mDate;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        //得到从CrimeFragment中传来的Date数据
        mDate=(Date)getArguments().getSerializable(EXTRA_DATE);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(mDate);
        int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int hour= calendar.get(Calendar.HOUR_OF_DAY);
        final int minute=calendar.get(Calendar.MINUTE);

        View v= getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);

        //拿到对话窗,并设置对话窗的显示数据
        DatePicker datePicker=(android.widget.DatePicker)v.findViewById(R.id.date_DatePicker);
        datePicker.init(year,month,day,new DatePicker.OnDateChangedListener() {

            //将跟新过后的数据保存
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate= new  GregorianCalendar(year,month,day,hour,minute).getTime();

                //将跟新过后的时间保存,这样当屏幕横向等情况时,可以保存数据
                getArguments().putSerializable(EXTRA_DATE,mDate);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    //当用户按ok按钮的时候,把数据回传到CrimeFragment
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SetResult(Activity.RESULT_OK);


                    }
                }) //对话窗的确认按钮 第一个参数是按钮text,第二个是监听器
                .create();
    }


   public static DatePickerFragment newInstance(Date date){
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_DATE,date);

       DatePickerFragment fragment=new DatePickerFragment();
       fragment.setArguments(args);
       return fragment;
   }

    //回传数据
    private void SetResult(int resultCode){
        if (getTargetFragment()==null){
            return;
        }

        Intent i=new Intent();
        i.putExtra(EXTRA_DATE,mDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }



}
