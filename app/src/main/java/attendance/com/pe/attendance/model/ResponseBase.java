package attendance.com.pe.attendance.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by snavarrr on 22/03/2018.
 */

public class ResponseBase<T> {

    private String codeResponse;
    private String descResponse;
    //private String detResponse;

    private T detResponse;

    public String getCodeResponse() {
        return codeResponse;
    }

    public void setCodeResponse(String codeResponse) {
        this.codeResponse = codeResponse;
    }

    public String getDescResponse() {
        return descResponse;
    }

    public void setDescResponse(String descResponse) {
        this.descResponse = descResponse;
    }

    public T getDetResponse() {
        return detResponse;
    }

    public void setDetResponse(T detResponse) {
        this.detResponse = detResponse;
    }

}
