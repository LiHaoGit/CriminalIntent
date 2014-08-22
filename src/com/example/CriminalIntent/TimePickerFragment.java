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
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 14/8/22/022.
 * 时间选择器
 */
public class TimePickerFragment extends DialogFragment {


    private Date mDate;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate= (Date)getArguments().getSerializable(DatePickerFragment.EXTRA_DATE);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(mDate);
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int hour= calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);

        //return super.onCreateDialog(savedInstanceState);
        View v= getActivity().getLayoutInflater().inflate(R.layout.dialog_time,null);
        TimePicker timePicker=(TimePicker)v.findViewById(R.id.time_TimePicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mDate=new GregorianCalendar(year,month,day,hourOfDay,minute).getTime();
                getArguments().putSerializable(DatePickerFragment.EXTRA_DATE,mDate);
            }
        });


        return new AlertDialog.Builder(getActivity())
                .setTitle("Time of crime")
                .setPositiveButton(android.R.string.ok,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SetResult(Activity.RESULT_OK);
                    }
                })
                .setView(v)
                .create();

    }

    public static TimePickerFragment newInstance(Date date){
        Bundle args=new Bundle();
        args.putSerializable(DatePickerFragment.EXTRA_DATE,date);
        TimePickerFragment fragment=new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void SetResult(int resultCode){
        if (getTargetFragment()==null){
            return;
        }

        Intent i=new Intent();
        i.putExtra(DatePickerFragment.EXTRA_DATE,mDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
