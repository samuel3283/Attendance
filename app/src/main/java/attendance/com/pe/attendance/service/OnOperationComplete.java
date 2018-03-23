package attendance.com.pe.attendance.service;

/**
 * Created by snavarrr on 22/03/2018.
 */

public interface OnOperationComplete<T> {

    void onStart();
    void onSuccess(T t);
    void onFailure(Exception e);
    void onComplete();

}
