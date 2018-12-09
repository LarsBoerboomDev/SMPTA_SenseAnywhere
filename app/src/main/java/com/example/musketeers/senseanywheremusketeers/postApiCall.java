package com.example.musketeers.senseanywheremusketeers;

import android.os.AsyncTask;

import com.example.musketeers.senseanywheremusketeers.Interface.ASyncResponse;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;

public class postApiCall extends AsyncTask<String, String ,String> {

    public ASyncResponse delegate = null;

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0]; // URL to call
        String data = params[1]; //data to post
        OutputStream out = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();

            int responsecode = urlConnection.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            StringBuilder output = new StringBuilder();

            while ((line = br.readLine())  != null){
                output.append(line);
            }
            br.close();
            System.out.print(output.toString());
            out.close();

        return output.toString();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        delegate.processFinish(s);
    }



}
