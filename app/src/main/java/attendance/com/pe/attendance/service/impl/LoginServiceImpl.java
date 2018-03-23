package attendance.com.pe.attendance.service.impl;

import attendance.com.pe.attendance.service.UserConnect;
import attendance.com.pe.attendance.model.LoginRequest;
import attendance.com.pe.attendance.model.ResponseBase;
import attendance.com.pe.attendance.model.Session;
import attendance.com.pe.attendance.service.LoginService;
import attendance.com.pe.attendance.service.OnOperationComplete;
import attendance.com.pe.attendance.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by snavarrr on 18/03/2018.
 */

public class LoginServiceImpl implements LoginService {

    private static final String LOGTAG = "android-login";

    public OnOperationComplete onOperationComplete;


    @Override
    public void login(LoginRequest loginRequest) {
        Call<ResponseBase<Session>> responseBaseCall = RetrofitInstance.getRetrofitInstance()
                .create(UserConnect.class).login(loginRequest);

        onOperationComplete.onStart();
        responseBaseCall.enqueue(new Callback<ResponseBase<Session>>() {
            @Override
            public void onResponse(Call<ResponseBase<Session>> call, Response<ResponseBase<Session>> response) {
                try {
                    if (response.isSuccessful()) {
                        onOperationComplete.onSuccess(response.body());
                    } else {
                        onOperationComplete.onFailure(new Exception());
                    }
                } catch (Exception e) {
                    onOperationComplete.onFailure(e);
                } finally {
                    onOperationComplete.onComplete();
                }
            }

            @Override
            public void onFailure(Call<ResponseBase<Session>> call, Throwable t) {
                try {
                    onOperationComplete.onFailure(new Exception(t));
                } catch (Exception e) {
                    onOperationComplete.onFailure(e);
                } finally {
                    onOperationComplete.onComplete();
                }
            }
        });
    }

}
