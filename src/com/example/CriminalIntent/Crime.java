package com.example.CriminalIntent;

import android.text.format.DateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by lee on 2014/8/17.
 * 数据模型
 */
public class Crime {

    private static final  String JSON_ID = "id";
    private static final  String JSON_TITLE = "title";
    private static final  String JSON_SOLVED = "solved";
    private static final  String JSON_DATE = "date";

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime()
    {
        mId=mId.randomUUID();
        mDate=new Date();
    }

    public Crime(JSONObject json)throws JSONException{
        mId = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        mDate = new Date(json.getLong(JSON_DATE));
        mSolved = json.getBoolean(JSON_SOLVED);
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
        return DateFormat.format("yyyy-MM-dd kk:mm",this.mDate).toString();
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

    public JSONObject toJSON()throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_ID,mId.toString());
        json.put(JSON_TITLE,mTitle);
        json.put(JSON_SOLVED,mSolved);
        json.put(JSON_DATE,mDate);
        return json;
    }


}
