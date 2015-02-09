package redmart.productslisting.api;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The  serves as the custom Request to return JSONObject
 * as for a normal JsonObjectRequest getParams is only effective (by default) in a POST or PUT request, but not in a GET request,
 *
 */
public class AppRequest extends Request<JSONObject> {

    private Listener<JSONObject> listener;
    private HashMap<String, String> params;
    private Map<String, JSONObject> mSubParams = new HashMap();

    public AppRequest(String url, HashMap<String, String> params,
                         Listener<JSONObject> responseListener, ErrorListener errorListener) {


        super(Method.GET, url, errorListener);
        this.listener = responseListener;
        this.params = params;
    }

    public AppRequest(int method, String url, HashMap<String, String> params,
                         Listener<JSONObject> responseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = responseListener;
        this.params = params;
    }

    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return params;
    };

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {

        listener.onResponse(response);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return getJSONByteArray(this.params);
    }

    public byte[] getJSONByteArray(HashMap<String, String> paramHashMap)
    {
        JSONObject localJSONObject;
        try
        {
            localJSONObject = new JSONObject();
            Iterator localIterator1 = paramHashMap.keySet().iterator();
            while (localIterator1.hasNext())
            {
                String str2 = (String)localIterator1.next();
                String str3 = (String)paramHashMap.get(str2);
                if (str3 == null)
                    str3 = "";
                localJSONObject.accumulate(str2, str3);
            }
        }
        catch (JSONException localJSONException)
        {
            localJSONException.printStackTrace();
            return null;
        }
        Iterator localIterator2 = this.mSubParams.keySet().iterator();
        while (localIterator2.hasNext())
        {
            String str1 = (String)localIterator2.next();
            Object localObject = (JSONObject)this.mSubParams.get(str1);
            if (localObject == null)
                localObject = "";
            try {
                localJSONObject.accumulate(str1, localObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("HTTP: json pose", localJSONObject.toString());
        byte[] arrayOfByte = localJSONObject.toString().getBytes();
        return arrayOfByte;
    }
}