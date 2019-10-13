package com.example.juandiegodiaz.brocommunity;

import android.app.ProgressDialog;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SigninActivity  extends AsyncTask<String, String, String>{

    ProgressDialog pDialog;
    private TextView statusField,roleField;                                                            //this field leaks a context object
    private Context context1;
    String newest, settle,change,profilw;
    private int actionFlag = 0;
    int juan=0;
    static InputStream is = null;

    public SigninActivity(Context context, TextView statusField, TextView roleField, int flag) {    // 0 means coffee price
                                                                                                    // 1 POST
                                                                                                    //2 Insert
                                                                                                    // 3 request dollar
                                                                                                    //4 check phone availability
        this.context1 = context;
        this.statusField = statusField;
        this.roleField = roleField;
        actionFlag = flag;
        juan=actionFlag;
    }

    protected void onPreExecute(){                                                                  //Loading message
        super.onPreExecute();
        pDialog = new ProgressDialog(context1);
        pDialog.setMessage("Cargando. Por favor espere...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... arg0) {

        if (actionFlag == 1) {                                                                  //uses Post to authenticate
            try {
                String username = (String) arg0[0];
                String password = (String) arg0[1];
                String link = "http://dialop.scienceontheweb.net/Login.php";
                String data = URLEncoder.encode("phone", "UTF-8") + "=" +
                        URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {             // Read Server Response
                    sb.append(line);
                    break;
                }
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        } else if (actionFlag == 2) {                                                                                        //Insert new user into DB using POST
            try {
                String nameR = (String) arg0[0];
                String passwordR = (String) arg0[1];
                String profileR = (String) arg0[2];
                profilw=profileR;
                String phoneR = (String) arg0[3];
                String genderR = (String) arg0[4];
                String link = "http://dialop.scienceontheweb.net/Insert.php";
                String data = URLEncoder.encode("name", "UTF-8") + "=" +
                        URLEncoder.encode(nameR, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(passwordR, "UTF-8");
                data += "&" + URLEncoder.encode("profile", "UTF-8") + "=" +
                        URLEncoder.encode(profileR, "UTF-8");
                data += "&" + URLEncoder.encode("phone", "UTF-8") + "=" +
                        URLEncoder.encode(phoneR, "UTF-8");
                data += "&" + URLEncoder.encode("Gender", "UTF-8") + "=" +
                        URLEncoder.encode(genderR, "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {             // Read Server Response
                    sb.append(line);
                    break;
                }
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }
        else if(actionFlag==4){
            try {
                String phone = (String) arg0[0];

                String link = "http://dialop.scienceontheweb.net/phoneCheck.php";
                String data = URLEncoder.encode("phone", "UTF-8") + "=" +
                        URLEncoder.encode(phone, "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {             // Read Server Response
                    sb.append(line);
                    break;
                }
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }
        else {
            return "nothing to return, no flag was called 910104";
        }
    }

    @Override
    protected void onPostExecute(String result){
        pDialog.dismiss();

        if(juan == 0) {
            String[] request = result.toString().split("$", -2);                                        //All this work is to get the yesterday`s date
            //i= Integer.parseInt(arrOfStr[2])-1;
            this.statusField.setText(settle);                                              //status if request was completed
            Log.i("ISABELLA", "checkpoint  2");
             Log.i("ISABELLA", "result" + result.toString());
            this.roleField.setText(change);

        }
        else if(juan==1){
            this.statusField.setText("Petición completa");                                              //status if request was completed
            // Log.i("JUAN", "result" + result.toString());
            if(result.compareTo("0")!=0){
                this.roleField.setText(result);
            }else{
                this.roleField.setText("datos invalidos");
            }

        }else if(juan==2){

            if (result.compareTo("1991")==0){
                Toast.makeText(context1, profilw + "  registrado con exito, gracias!", Toast.LENGTH_LONG).show();//Goes to SignActivity
            }
            else{
                Toast.makeText(context1, "Hay un problema con la base de datos, por favor intente mas tarde." + result, Toast.LENGTH_LONG).show();//Goes to SignActivity
            }

        }else if(juan==3){

            this.statusField.setText(settle);                                              //status if request was completed
            Log.i("ISABELLA", "settle" + settle + " result "+result);

        }
        else if(juan==4){


            Log.i("ISABELLA", "phone checked" +result);
            if (result.compareTo("8")!=0){
                Toast.makeText(context1,  "  Hay un usuario registrado con este celular!", Toast.LENGTH_LONG).show();
                //this.statusField.setText(result);
                this.statusField.setTextColor(Color.RED);
            }
            else{
                Toast.makeText(context1, "Este numero está disponible en la plataforma", Toast.LENGTH_LONG).show();
                //this.statusField.setText(result);                                              //status if request was completed
                this.statusField.setTextColor(Color.GREEN);
            }

        }
        else{
            //status if request was completed
             Log.i("ISABELLA", " nothing selected");
            //this.roleField.setText(change);
        }

    }
}