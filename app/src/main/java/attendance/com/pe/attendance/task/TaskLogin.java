package attendance.com.pe.attendance.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import attendance.com.pe.attendance.model.Session;

/**
 * Created by snavarrr on 20/03/2018.
 */

public class TaskLogin extends AsyncTask<String,Integer,Boolean> {

    private static final String LOGTAG = "android-login";

    @Override
    protected Boolean doInBackground(String... params) {
        Session session = null;
        boolean resul = true;
        String url ="https://qaxpy23pj0.execute-api.us-east-1.amazonaws.com/dev/login";
        HttpClient httpClient = new DefaultHttpClient();

        HttpPost post = new HttpPost(url);
        post.setHeader("content-type", "application/json");

        try
        {
            //Construimos el objeto cliente en formato JSON
            JSONObject dato = new JSONObject();

            dato.put("usuario", params[0]);
            dato.put("password", params[1]);

            StringEntity entity = new StringEntity(dato.toString());
            post.setEntity(entity);

            HttpResponse resp = httpClient.execute(post);

            if(resp != null) {
                String respStr = EntityUtils.toString(resp.getEntity());
                JSONObject obj = new JSONObject(respStr);
                JSONObject det = obj.getJSONObject("detResponse");
                System.out.println("Http Post Response::::" + obj.getJSONObject("codeResponse"));
                Gson gson = new GsonBuilder().create();
                session = new Session();
                session = gson.fromJson(det.toString(), Session.class);
                System.out.println("Http Post Response::::" + session.toString());
            }

        }
        catch(Exception ex)
        {
            Log.e(LOGTAG, "ServicioRest "+ ex.getMessage());
            resul = false;
        }

        return resul;
    }

    protected void onPostExecute(Boolean result) {

        if (result)
        {
            Log.d(LOGTAG, "Insertado OK ");
        }
    }


}
