package attendance.com.pe.attendance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import attendance.com.pe.attendance.Util.Constantes;
import attendance.com.pe.attendance.fragment.CuentaFragment;
import attendance.com.pe.attendance.fragment.HomeFragment;
import attendance.com.pe.attendance.fragment.MapFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private TextView textViewHeaderEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        //Este es para sacar el icono de mensaje en el pie de app_bar_main
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences prefs =  getSharedPreferences(Constantes.MyPREFERENCES, Context.MODE_PRIVATE);
        String email = prefs.getString("email", "User");


        View headerView = navigationView.getHeaderView(0);
        textViewHeaderEmail = headerView.findViewById(R.id.textViewHeaderEmail);
        textViewHeaderEmail.setText(email);


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment, homeFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            System.out.println("MI CUENTA...........");
            item.setChecked(true);
            setFragment(0);
        } else if (id == R.id.nav_gallery) {
            System.out.println("MI MAPA...........");
            item.setChecked(true);
            setFragment(1);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setFragment(int position) {
        switch (position) {
            case 0:
                /*fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                CuentaFragment perfilFragment = new CuentaFragment();
                fragmentTransaction.replace(R.id.fragment, perfilFragment);
                fragmentTransaction.commit();
                */
                CuentaFragment cuentaFragment = new CuentaFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, cuentaFragment)
                        .commit();

                break;
            case 1:
                /*fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                CuentaFragment perfilFragment = new CuentaFragment();
                fragmentTransaction.replace(R.id.fragment, perfilFragment);
                fragmentTransaction.commit();
                */
                MapFragment mapFragment = new MapFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, mapFragment)
                        .commit();

                break;
        }
    }

}
