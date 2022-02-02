package com.ankit.tasker;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class NetworkManager extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... the_url) {
        String responseString = null;
        HttpURLConnection conn;
        String response;
        URL url = null;
        try {
            url = new URL(the_url[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if(conn.getResponseCode() == HttpsURLConnection.HTTP_OK){
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                }
                finally {
                    conn.disconnect();
                    Globals.RESULT_FROM_ASYNC_TASK = "true";
                    return "true";
                }
            }
            else {
                Globals.RESULT_FROM_ASYNC_TASK = "false";
                return "false"; // See documentation for more info on response handling
            }
        } catch (IOException e) {
            e.printStackTrace();
            Globals.RESULT_FROM_ASYNC_TASK = "false";
            return "false";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..
    }
}
