package com.example.CriminalIntent;


import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;





public class DatePickerFragment extends DialogFragment{

    public static final String DatePicker="DatePickerFragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);

        View v= getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(DatePicker, "sure!");
                    }
                }) //对话窗的确认按钮 第一个参数是按钮text,第二个是监听器
                .create();
    }


}
