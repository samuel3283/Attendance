package attendance.com.pe.attendance.service;

import attendance.com.pe.attendance.model.LoginRequest;
import attendance.com.pe.attendance.model.ResponseBase;
import attendance.com.pe.attendance.model.Session;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by snavarrr on 22/03/2018.
 */

public interface UserConnect {

    public final static String HOST = "https://qaxpy23pj0.execute-api.us-east-1.amazonaws.com/";

    @POST("dev/login")
    Call<ResponseBase<Session>> login(@Body LoginRequest loginRequest);

}
