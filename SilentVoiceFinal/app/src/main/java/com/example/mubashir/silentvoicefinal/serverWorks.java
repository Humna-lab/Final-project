package com.example.mubashir.silentvoicefinal;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IInterface;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;


public class serverWorks extends AsyncTask<Void, Void, String>
{
    public static String resulting;
    public static byte[] image;
    Context context;
    static Boolean flagsss=true;

    ArrayList<NameValuePair> parameters;
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
        InputStream in = entity.getContent();


        StringBuffer out = new StringBuffer();
        int n = 1;
        while (n>0) {
            byte[] b = new byte[4096];
            n =  in.read(b);


            if (n>0) out.append(new String(b, 0, n));
        }


        return out.toString();
    }
    public serverWorks(Context c){
        this.context=c;
    }
    protected String doInBackground(Void... params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        flagsss=true;
        HttpPost httpGet = new HttpPost("http://192.168.43.161:8000/");
        // File file = new File("");
        // MultipartEntity mpEntity = new MultipartEntity();
        // ContentBody cbFile = new FileBody(file, "image/jpeg");
        String text = null;

        httpGet.setEntity(new ByteArrayEntity(image));

        try {
            HttpResponse response = httpClient.execute(httpGet, localContext);


            HttpEntity entity = response.getEntity();


            text = getASCIIContentFromEntity(entity);


        } catch (Exception e) {
            return e.getLocalizedMessage();
        }


        return text;
    }


    protected void onPostExecute(String results) {
        int flag = 0;
        if (results != null) {


            resulting = results;


            Log.d("debugingyrr", resulting + "");
            // MainActivity ma=new MainActivity();
            //ma.showresult(results+"");
            flagsss=false;


        }
        Log.d("getit", "onPostExecute: "+results);


    }

}

