package attendance.com.pe.attendance.service;

import attendance.com.pe.attendance.model.LoginRequest;
import attendance.com.pe.attendance.model.Session;

/**
 * Created by snavarrr on 18/03/2018.
 */

public interface LoginService {

        public static final String BASE_URL="https://qaxpy23pj0.execute-api.us-east-1.amazonaws.com/dev/login";

        void login(LoginRequest loginRequest);

}
