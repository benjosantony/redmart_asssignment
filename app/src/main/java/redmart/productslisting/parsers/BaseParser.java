package redmart.productslisting.parsers;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import redmart.productslisting.utilities.ParseUtils;

/**
 * Created by Ben on 8/2/15.
 */
public class BaseParser {

    private final String TAG = BaseParser.class.getSimpleName();

    public JSONObject jsonResults = null;

    /**
     * @param response parses the string to json object
     */
    public BaseParser(String response) {
        try {
            this.jsonResults = new JSONObject(response);
        } catch (JSONException e) {

            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * @param jsonResults construct using this if you already have the parsed results
     */
    public BaseParser(JSONObject jsonResults) {
        this.jsonResults = jsonResults;
    }

    public int totalCount() {
        if (jsonResults.has("total")) {
            try {
                return ParseUtils.getInteger(jsonResults.getString("total"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 0;

    }

    public int page() {
        if (jsonResults.has("page")) {
            try {
                return ParseUtils.getInteger(jsonResults.getString("page"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 0;

    }


}

