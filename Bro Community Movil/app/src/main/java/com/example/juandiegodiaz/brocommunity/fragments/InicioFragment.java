package com.example.juandiegodiaz.brocommunity.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juandiegodiaz.brocommunity.Interfaces.iComunicaFragments;
import com.example.juandiegodiaz.brocommunity.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InicioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    View view1;
    Activity actividad;
    CardView cardCalculadora;
    CardView cardDefectos;
    CardView cardMedir;
    CardView cardTransacciones;
    CardView cardPerfil;
    CardView cardOtros;

    iComunicaFragments interfaceFragments;


    public InicioFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view1= inflater.inflate(R.layout.fragment_inicio, container, false);
        // Inflate the layout for this fragment
        cardCalculadora =view1.findViewById(R.id.cardCalculadora);
        cardDefectos =view1.findViewById(R.id.cardDefectos);
        cardMedir =view1.findViewById(R.id.cardMedirCultivo);
        cardTransacciones =view1.findViewById(R.id.cardTransacciones);
        cardPerfil=view1.findViewById(R.id.cardPerfil);
        cardOtros =view1.findViewById(R.id.carOtros);


        events4Menu();
        return view1;
    }

    private void events4Menu() {

        cardCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragments.iniciarCalculadora();
                //Toast.makeText(getContext(),"inicar calculadora desde el fragment", Toast.LENGTH_LONG).show();
            }
        });

        cardDefectos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragments.defectos();
            }
        });

        cardMedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragments.medirCultivo();
                //Toast.makeText(getContext(),"inicar calculadora desde el fragment", Toast.LENGTH_LONG).show();
            }
        });

        cardTransacciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragments.transacciones();
            }
        });


        cardPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragments.perfil();
                //Toast.makeText(getContext(),"inicar calculadora desde el fragment", Toast.LENGTH_LONG).show();
            }
        });

        cardOtros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceFragments.otros();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            actividad=(Activity)context;
            interfaceFragments= (iComunicaFragments) actividad;
        }
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
