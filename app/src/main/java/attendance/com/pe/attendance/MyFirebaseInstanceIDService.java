package attendance.com.pe.attendance;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import android.util.Log;

/**
 * Created by snavarrr on 16/03/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String LOGTAG = "android-fcm";
    @Override
    public void onTokenRefresh() {
        //Se obtiene el token actualizado
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(LOGTAG, "Token actualizado: " + refreshedToken);
        System.out.println("Token actualizado: " + refreshedToken);
    }

}
