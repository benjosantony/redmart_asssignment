package redmart.productslisting.parsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import redmart.productslisting.models.Image;
import redmart.productslisting.utilities.ParseUtils;

/**
 * Created by Ben on 8/2/15.
 */
public class ImageParser extends BaseParser {
    private static final String TAG = ImageParser.class.getSimpleName();


    private static final String KEY_NAME_IMAGE_NAME = "name";
    private static final String KEY_NAME_IMAGE_POSITION = "position";

    public ImageParser(String response) {
        super(response);
    }

    public ImageParser(JSONObject jsonResults) {
        super(jsonResults);
    }

    public static Image parseImage(JSONObject jsonObjectImage) {
        Image image = new Image();
        try {
            if (jsonObjectImage.has(KEY_NAME_IMAGE_NAME))
                image.setName(jsonObjectImage.getString(KEY_NAME_IMAGE_NAME));
            if (jsonObjectImage.has(KEY_NAME_IMAGE_POSITION))
                image.setPosition(ParseUtils.getInteger(jsonObjectImage.getString(KEY_NAME_IMAGE_POSITION)));
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
        return image;
    }

    public static ArrayList<Image> parseImages(JSONArray jsonArrayImages) {
        ArrayList<Image> images = new ArrayList<Image>();
        if (jsonArrayImages == null) return null;
        try {
            for (int i = 0; i < jsonArrayImages.length(); i++) {
                JSONObject jsonObjectImage = null;
                jsonObjectImage = jsonArrayImages.getJSONObject(i);
                Image image = parseImage(jsonObjectImage);
                images.add(image);
            }
        } catch (JSONException e) {
           Log.e(TAG, e.getMessage());
        }

        return images;
    }

}
