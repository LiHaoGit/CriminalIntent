package com.example.CriminalIntent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 14/8/18/018.
 * 数据"连接池"
 */
public class CrimeLab {

    private static final String TAG = "CrimeLab";
    private static  final String FILENAME="crimes.json";

    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private  CriminalIntentJSONSerializer mSerializer;

    private ArrayList<Crime> mCrimes;

    private CrimeLab(Context appContext){
        this.mAppContext = appContext;
        mSerializer = new CriminalIntentJSONSerializer(FILENAME,mAppContext);
        mCrimes=new ArrayList<Crime>();
        try {
            mCrimes = mSerializer.LoadCrimes();
        }catch (Exception e){
            mCrimes = new ArrayList<Crime>();
            Log.e(TAG,"Error loading crimes :",e);
        }
    }

    public static CrimeLab get(Context c){
        if (sCrimeLab==null){
            sCrimeLab=new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes()
    {
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime c:mCrimes){
            if (c.getId().equals(id)){
                return c;

            }
        }
        return null;
    }

    public boolean saveCrimes(){
        try {
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG,"crimes saved to file");
            return true;
        }catch (Exception e){
            Log.e(TAG,"Error saving crimes: ",e);
            return false;
        }
    }



    public void newCrime(Crime crime){
        mCrimes.add(crime);
    }
}
