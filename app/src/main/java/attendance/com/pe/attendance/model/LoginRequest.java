package attendance.com.pe.attendance.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by snavarrr on 18/03/2018.
 */

public class LoginRequest {

    @SerializedName("email")
    private String usuario;

    @SerializedName("password")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
