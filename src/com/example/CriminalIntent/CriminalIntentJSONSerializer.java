package com.example.CriminalIntent;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Administrator on 14/12/5/005.
 * 数据与JSON转换
 */
public class CriminalIntentJSONSerializer {
    private Context context;
    private String mFilename;
    private static final String TAG ="CriminalIntentJSONSerializer";

    public CriminalIntentJSONSerializer(String mFilename,Context context) {
        this.mFilename = mFilename;
        this.context = context;
    }

    //保存为JSON
    public void saveCrimes(ArrayList<Crime> crimes)
        throws JSONException,IOException{

        JSONArray array = new JSONArray();
        for (Crime c:crimes)
            array.put(c.toJSON());

        //write the file to disk
        Writer writer = null;
        try {
            OutputStream out = context
                    .openFileOutput(mFilename,Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        }finally {
            if (writer != null)
                writer.close();
        }
    }

    public ArrayList<Crime> LoadCrimes()throws IOException,JSONException{
        ArrayList<Crime> crimes = new ArrayList<Crime>();
        BufferedReader reader = null;
        try {
            InputStream in = context.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine())!=null){
                stringBuilder.append(line);
            }
            JSONArray array = (JSONArray) new JSONTokener(stringBuilder.toString())
                    .nextValue();
            for (int i = 0;i<array.length();i++){
                crimes.add(new Crime(array.getJSONObject(i)));
            }
        }catch (Exception e){
            Log.e(TAG,"Error Loading crimes :",e);
        }finally {
            if (reader!= null)
                reader.close();
        }
        return crimes;
    }
}
