package com.example.juandiegodiaz.brocommunity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    ProgressDialog pDialog;                                                                         //Declaracion de as variables
    private EditText phoneField,passwordField;
    private TextView status,role,method;
    String phoneNumber, passWord;
    ImageView ima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {                                            //What to do once the app is launched
        super.onCreate(savedInstanceState);                                                         //MainActivity is launched
        setContentView(R.layout.activity_main);

        ima=findViewById(R.id.imEntrar);
        phoneField = (EditText)findViewById(R.id.usrET);
        passwordField = (EditText)findViewById(R.id.passEt);

        status = (TextView)findViewById(R.id.tv6);
        role = (TextView)findViewById(R.id.tv7);
        method = (TextView)findViewById(R.id.tv8);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //toolbar.setTitle("911 Drinks");

        /* ImageView imageView = (ImageView) findViewById(R.id.imagen_banner);
        Picasso.with(MainActivity.this)
                .load(Constants.url_logos +"juan.png")
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .resize(840, 240)
                .centerCrop()
                .into(imageView);
        */

    }

    protected boolean isOnline() {                                                                   // Connectivity manager gives you access to the current state of the connection
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void login(View view){                                                                   //Useless since 20.1
        phoneNumber = phoneField.getText().toString();                                       //was called from button's declaration
        passWord = passwordField.getText().toString();
        method.setText("Get Method");
        new SigninActivity(this,status,role,0).execute(phoneNumber,passWord);
    }

    public void loginPost(View view){                                                               //Post Method, goes `til the DB and check the
        boolean validFields = true;
        if(isOnline()) {
            if((phoneField.getText().toString().length() == 0 || !phoneField.getText().toString().matches(getResources().getString(R.string.regex_phone)))) {
                phoneField.setError("Ingrese un número de celular válido");
                validFields = false;
            }else {
                phoneNumber = phoneField.getText().toString();
                phoneField.setError(null);
            }
            if (passwordField.getText().toString().length() == 0) {
                passwordField.setError("Ingrese su contraseña");
                validFields = false;
            }else{
                passWord = passwordField.getText().toString();
                passwordField.setError(null);
            }
            if(validFields){
                method.setText("Post Method");
                new SigninActivity(this, status, role, 1).execute(phoneNumber, passWord);
            }
            }else{
            checkConnection();
        }
    }

    public void registrar(View view){
        if(isOnline()) {
            Intent registroIntent = new Intent(MainActivity.this, Registro.class);
            startActivity(registroIntent);
        }else{
            checkConnection();
        }
    }

    public void checkConnection(){
        Toast.makeText(this, "Por favor revisa tu conexión a internet!", Toast.LENGTH_LONG).show();
        /*
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Por favor revise su conexión a internet");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(true);
        pDialog.show();
        */
    }
    public void saltarTodo(View view) {                                                             //Goes straight to the calculator jumping
        Intent registroIntent = new Intent(MainActivity.this,MainMenu.class);      // everything
        startActivity(registroIntent);
    }
}
