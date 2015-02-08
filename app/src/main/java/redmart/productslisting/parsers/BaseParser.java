package redmart.productslisting.parsers;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ben on 8/2/15.
 */
public class BaseParser {

    private final String TAG  = BaseParser.class.getSimpleName();

   public JSONObject jsonResults = null;

    /**
     *
     * @param response parses the string to json object
     */
    public BaseParser(String response) {
        try {
            this.jsonResults = new JSONObject(response);
        } catch (JSONException e) {

            Log.e(TAG,e.getMessage());
        }
    }



    /**
     *
     * @param jsonResults construct using this if you already have the parsed results
     */
    public BaseParser(JSONObject jsonResults) {
        this.jsonResults = jsonResults;
    }




}

