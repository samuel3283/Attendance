package attendance.com.pe.attendance.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import attendance.com.pe.attendance.LoginActivity;
import attendance.com.pe.attendance.R;
import attendance.com.pe.attendance.Util.Constantes;


public class HomeFragment extends Fragment {

    private static final String LOGTAG = "android-login";
    private EditText editTextFrgHome;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /*
        String email =  LoginActivity.sharedpreferences.getString("email",null);
        String nombre =  LoginActivity.sharedpreferences.getString("nombre",null)+" "+LoginActivity.sharedpreferences.getString("apellido",null) ;
*/

        SharedPreferences prefs =  view.getContext().getSharedPreferences(Constantes.MyPREFERENCES, view.getContext().MODE_PRIVATE);

        String nombre = prefs.getString("nombre", "User");

        editTextFrgHome = view.findViewById(R.id.editTextFrgHome);
        editTextFrgHome.setText(nombre);
        Log.d(LOGTAG, "Usuario: " + nombre);

        return view;
    }

}
