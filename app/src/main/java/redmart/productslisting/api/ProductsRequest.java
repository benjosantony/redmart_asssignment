package redmart.productslisting.api;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;

import java.util.List;

import redmart.productslisting.utilities.ApiConstants;




public class ProductsRequest extends JsonObjectRequest {



    public ProductsRequest(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener,List<NameValuePair> params) {
        super(ApiConstants.PRODUCTS_LIST_URL + "?" + URLEncodedUtils.format(params, "UTF-8"),
                null, listener, errorListener);

    }
}
