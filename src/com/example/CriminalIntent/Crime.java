package com.example.CriminalIntent;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by lee on 2014/8/17.
 * 数据模型
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime()
    {
        mId=mId.randomUUID();
        mDate=new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public String getDateFormat(){
        return DateFormat.format("yyyy-MM-dd kk:mm:ss",this.mDate).toString();
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    //重写toString()方法,格式化时间
    @Override
    public String toString() {
        return mTitle;

    }
}
