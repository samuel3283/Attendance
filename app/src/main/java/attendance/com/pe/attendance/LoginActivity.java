package attendance.com.pe.attendance;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import attendance.com.pe.attendance.Util.Constantes;
import attendance.com.pe.attendance.model.LoginRequest;
import attendance.com.pe.attendance.model.ResponseBase;
import attendance.com.pe.attendance.model.Session;
import attendance.com.pe.attendance.service.RetrofitInstance;
import attendance.com.pe.attendance.service.UserConnect;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static SharedPreferences sharedpreferences;
    public static SharedPreferences.Editor editor;

    private EditText textViewLoginUsername;
    private EditText textViewLoginClave;
    private Button buttonLogin;
    private static final String LOGTAG = "android-login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewLoginUsername = (EditText) findViewById(R.id.textViewLoginUsername);
        textViewLoginClave = (EditText) findViewById(R.id.textViewLoginClave);

        buttonLogin = (Button) findViewById(R.id.butonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarLogin();
            }
        });

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(LOGTAG, "Token actualizado: " + refreshedToken);

        //Habilita modo estricto para permitir conexiones
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
    }

    private void validarLogin() {

        String email = textViewLoginUsername.getText().toString();
        String clave = textViewLoginClave.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            textViewLoginUsername.setError("Email requerido");
            focusView = textViewLoginUsername;
            cancel = true;
        } else if (!isEmailValid(email)) {
            textViewLoginUsername.setError("Email inconrrecto");
            focusView = textViewLoginUsername;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            UserConnect service = RetrofitInstance.getRetrofitInstance().create(UserConnect.class);

            LoginRequest request = new LoginRequest(email,clave);
            Call<ResponseBase<Session>> call =  service.login(request);
            System.out.println("========>URL Called"+call.request().url());
            Log.wtf("URL Called", call.request().url() + "");

            call.enqueue(new Callback<ResponseBase<Session>>() {
                @Override
                public void onResponse(Call<ResponseBase<Session>> call, Response<ResponseBase<Session>> response) {
                    System.out.println("========>getCodeResponse"+response.body().getCodeResponse());

                    if(response.body().getCodeResponse().equals("0000")) {
                        sharedpreferences = getSharedPreferences(Constantes.MyPREFERENCES, Context.MODE_PRIVATE);
                        editor = sharedpreferences.edit();
                        editor.putString("token", response.body().getDetResponse().getToken());
                        editor.putString("email", response.body().getDetResponse().getEmail());
                        editor.putString("nombre", response.body().getDetResponse().getNombre());
                        editor.putString("apellido", response.body().getDetResponse().getApellidos());
                        editor.putString("telefono", response.body().getDetResponse().getTelefono());
                        editor.putString("tipoDocumento", response.body().getDetResponse().getTipoDocumento());
                        editor.putString("numeroDocumento", response.body().getDetResponse().getNumeroDocumento());
                        editor.commit();

                        Log.d(LOGTAG, "Datos cargados OK: " + response.body().getDetResponse().toString());
                        Intent intent = new Intent("attendance.com.pe.attendance.MainActivity");
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this,"Usuario y Password incorrectos", Toast.LENGTH_SHORT).show();

                    }



                }

                @Override
                public void onFailure(Call<ResponseBase<Session>> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Ocurrió un error, Intentar más tarde!", Toast.LENGTH_SHORT).show();
                }
            });


            /*
            LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsuario(email);
            loginRequest.setPassword(clave);
            loginServiceImpl.onOperationComplete = new OnOperationComplete<Session>() {
                @Override
                public void onStart() {
                    Log.d(LoginActivity.class.getCanonicalName(), "onStart");
                }

                @Override
                public void onSuccess(Session session) {
                    Log.d(LoginActivity.class.getCanonicalName(), "onSuccess");
                    Log.d(LoginActivity.class.getCanonicalName(), "apellidos: "+session.getApellidos());
                }

                @Override
                public void onFailure(Exception e) {
                    Log.d(LoginActivity.class.getCanonicalName(), "onFailure");
                }

                @Override
                public void onComplete() {
                    Log.d(LoginActivity.class.getCanonicalName(), "onComplete");
                }
            };
            loginServiceImpl.login(loginRequest);
*/


/*
            TaskLogin tarea = new TaskLogin();
            tarea.execute();


            LoginServiceImpl loginService = new LoginServiceImpl();
            LoginRequest login = new LoginRequest(email,clave);
            Session session = loginService.login(login);

            if(session!=null) {
                sharedpreferences = getSharedPreferences(Constantes.MyPREFERENCES, Context.MODE_PRIVATE);
                editor = sharedpreferences.edit();
                editor.putString("token", session.getToken());
                editor.putString("email", session.getEmail());
                editor.putString("nombre", session.getNombre());
                editor.putString("apellido", session.getApellidos());
                editor.putString("telefono", session.getTelefono());
                editor.putString("tipoDocumento", session.getTipoDocumento());
                editor.putString("numeroDocumento", session.getNumeroDocumento());
                editor.commit();

                Log.d(LOGTAG, "Datos cargados OK: " + session.toString());
                Intent intent = new Intent("attendance.com.pe.attendance.MainActivity");
                startActivity(intent);

            } else {
                Toast.makeText(LoginActivity.this,"Usuario y Password incorrectos",
                        Toast.LENGTH_SHORT).show();

            }
*/
        }


    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

}
