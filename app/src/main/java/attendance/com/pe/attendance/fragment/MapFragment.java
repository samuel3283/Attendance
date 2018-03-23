package attendance.com.pe.attendance.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import attendance.com.pe.attendance.R;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mapa;

    private Button btnOpciones;
    private Button btnMover;
    private Button btnAnimar;
    private Button btnPosicion;

    public MapFragment() {
        // Required empty public constructor
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);

        //mapFragment.getMapAsync(this);
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
    }

    private void cambiarOpciones()
    {
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);
    }

    private void moverMadrid()
    {
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(40.41, -3.69), 5);

        mapa.moveCamera(camUpd1);
    }

    private void animarMadrid()
    {
        LatLng madrid = new LatLng(40.417325, -3.683081);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)   //Centramos el mapa en Madrid
                .zoom(19)         //Establecemos el zoom en 19
                .bearing(45)      //Establecemos la orientación con el noreste arriba
                .tilt(70)         //Bajamos el punto de vista de la cámara 70 grados
                .build();

        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);

        mapa.animateCamera(camUpd3);
    }

    private void obtenerPosicion()
    {
        CameraPosition camPos = mapa.getCameraPosition();

        LatLng coordenadas = camPos.target;
        double latitud = coordenadas.latitude;
        double longitud = coordenadas.longitude;
        String cadena ="Lat: " + latitud + " Long: " + longitud;
        Toast.makeText(this.getContext(), cadena, Toast.LENGTH_SHORT).show();
    }

}
