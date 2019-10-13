package com.example.juandiegodiaz.brocommunity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private EditText eName,eUser,ePassword,ePhone;

    private TextView status;
    public TextView role00;
    private String eProfile,eGender;
    RadioGroup rg,rgg;
    boolean validFields=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                                                          //what to do in each new register
        setContentView(R.layout.activity_registro);

        status=findViewById(R.id.tvPhone);
        status.setTextColor(Color.RED);
        eName=findViewById(R.id.etName);
        eUser=findViewById(R.id.etName);
        ePassword=findViewById(R.id.etPass);
        ePhone=findViewById(R.id.etPhone);

        rg =(RadioGroup)findViewById(R.id.radioGr);
        rgg =(RadioGroup)findViewById(R.id.radioGrGndr);
        eProfile="Caficultor";
        eGender="Hombre";

        rgg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.btnHombre:
                        eGender="Hombre";
                        break;
                    case R.id.btnMujer:
                        eGender="Mujer";
                        break;
                    case R.id.btnOtro:
                        eGender="Otro";
                        break;
                }
            }
        });


    }

    public void insertValue(View view){                                                             //called from the button

        String Nm1 = null,Psswrd1 = null,Prfl1,Phn1=null,PrfGndr;
        if (eName.getText().toString().length() == 0 || !eName.getText().toString().matches(getResources().getString(R.string.regex_name))) {
            eName.setError("Escriba su nombre");
            validFields = false;
        } else {
            Nm1 = eName.getText().toString();
            eName.setError(null);
        }

        if (ePassword.getText().toString().length() < 5 ) {
            ePassword.setError("Escriba una contraseña de por lo menos 5 caracteres");
            validFields = false;
        } else {
            Psswrd1 = ePassword.getText().toString();
            ePassword.setError(null);
        }
        if((ePhone.getText().toString().length() == 0 || !ePhone.getText().toString().matches(getResources().getString(R.string.regex_phone)))) {
            ePhone.setError("Ingrese un número de celular válido");
            validFields = false;
        }else {
            Phn1 = ePhone.getText().toString();
            ePhone.setError(null);
        }
        Prfl1=eProfile.toString();
         PrfGndr=eGender.toString();

         if(status.getCurrentTextColor()==Color.RED){
             validFields = false;
             Toast.makeText(this,  "  Por favor confirme el celular ingresado", Toast.LENGTH_LONG).show();//Goes to SignActivity
         }else if(status.getCurrentTextColor()==Color.GREEN){
             validFields = true;
         }
         if(validFields){
             new SigninActivity(this,status,role00,2).execute(Nm1,Psswrd1,Prfl1,Phn1,PrfGndr);    //Starts the register with new data

             Intent home = new Intent(Registro.this,MainActivity.class);
             startActivity(home);
            // Toast.makeText(this, "Resultado" + status.toString() + role.toString(), Toast.LENGTH_LONG).show();//Goes to SignActivity
         }

    }
    public void validarTele(View view) {                                                             //Goes straight to the calculator jumping
        if((ePhone.getText().toString().length() == 0 || !ePhone.getText().toString().matches(getResources().getString(R.string.regex_phone)))) {
            ePhone.setError("Ingrese un número de celular válido");
        }else{
            new SigninActivity(this,status,role00,4).execute(ePhone.getText().toString());
        }
        }
}

