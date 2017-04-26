package com.edupointbd.amirul.webapplogin;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by amirul on 26-Apr-17.
 */

public class BackgroundTask extends AsyncTask<String, Void, String> {


    AlertDialog alertDialog ;
    Context ctx;

    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }


    @Override
    protected String doInBackground(String... params) {

        String reg_url = "http://10.0.2.2/webapp/register.php";
        String logun_url = "http://10.0.2.2/webapp/login.php";

        String method = params[0];

        if ((method.equals("register"))){

            String name = params[1];
            String user_name = params[2];
            String user_pass = params[3];

            try {
                URL url = new URL(reg_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
              //  httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter  = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("user","UTF-8") +"="+URLEncoder.encode(name,"UTF-8")+"&"+
                                URLEncoder.encode("user_name","UTF-8") +"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8") +"="+URLEncoder.encode(user_pass,"UTF-8");



                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Registration successful........";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

          else if(method.equals("login")){

            String user_name = params[1];
            String user_pass = params[2];


            try {
                URL url = new URL(logun_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();


                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));

                String respone = "";
                String line = "";

                while ((line=bufferedReader.readLine()) != null){

                    respone += line;
                }

                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return respone;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


       return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information");
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("Registration successful........")){
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }

        else {
            alertDialog.setMessage("welcome to "+result);
            alertDialog.show();
        }
    }
}