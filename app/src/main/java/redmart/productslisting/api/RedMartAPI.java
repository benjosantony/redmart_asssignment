package redmart.productslisting.api;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 8/2/15.
 */
public class RedMartAPI {
    private RequestQueue mRequestQueue = null;

    public RedMartAPI(RequestQueue requestQueue)
    {
        this.mRequestQueue = requestQueue;
    }

    public void getProducts (int page , int pageSize, String uri , Response.Listener<JSONObject> listener,
                              Response.ErrorListener errorListener){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("page",String.valueOf(page)));
        params.add(new BasicNameValuePair("pageSize",String.valueOf(pageSize)));
        params.add(new BasicNameValuePair("uri",String.valueOf(uri)));

        this.mRequestQueue.add(new ProductsRequest(listener,errorListener,params));
    }

}
