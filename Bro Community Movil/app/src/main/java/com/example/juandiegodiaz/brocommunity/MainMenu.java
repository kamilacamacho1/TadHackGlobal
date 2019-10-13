package com.example.juandiegodiaz.brocommunity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.juandiegodiaz.brocommunity.Interfaces.iComunicaFragments;
import com.example.juandiegodiaz.brocommunity.fragments.InicioFragment;

public class MainMenu extends AppCompatActivity implements iComunicaFragments, InicioFragment.OnFragmentInteractionListener {

    Fragment fragmentInicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        fragmentInicio = new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void iniciarCalculadora() {
        Toast.makeText(getApplicationContext(),"inicar calculadora desde main menu", Toast.LENGTH_LONG).show();
    }

    @Override
    public void defectos() {
        Toast.makeText(getApplicationContext(),"inicar Defectos desde main menu", Toast.LENGTH_LONG).show();
    }

    @Override
    public void medirCultivo() {
        Toast.makeText(getApplicationContext(),"inicar medición desde main menu", Toast.LENGTH_LONG).show();
    }

    @Override
    public void transacciones() {
        Toast.makeText(getApplicationContext(),"inicar transacciones desde main menu", Toast.LENGTH_LONG).show();
    }

    @Override
    public void perfil() {
        Toast.makeText(getApplicationContext(),"inicar perfil desde main menu", Toast.LENGTH_LONG).show();
    }

    @Override
    public void otros() {
        Toast.makeText(getApplicationContext(),"inicar otros desde main menu", Toast.LENGTH_LONG).show();
    }
}
