package attendance.com.pe.attendance.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import attendance.com.pe.attendance.R;


public class CuentaFragment extends Fragment {

    private EditText editTextCtaFrgInput;
    private TextView textViewctaFrgHeader;

    public CuentaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);

        TextView textViewctaFrgHeader = view.findViewById(R.id.textViewctaFrgHeader);
        EditText editTextCtaFrgInput = view.findViewById(R.id.editTextctaFrgName);

        String texto = "BIENVENIDO FRG:::::";
        textViewctaFrgHeader.setText(texto);

        return view;
    }



}
