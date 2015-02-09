package redmart.productslisting;

import android.app.Application;
import android.content.pm.PackageManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import redmart.productslisting.api.RedMartAPI;

/**
 * Created by Ben on 9/2/15.
 */
public class ProductsListingApplication  extends Application{


    private static ProductsListingApplication sProductListinApplication ;
    private RequestQueue mRequestQueue = null;
    private  RedMartAPI redMartAPI;

    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }


    public static ProductsListingApplication get()
    {
        return sProductListinApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sProductListinApplication = this;
        this.mRequestQueue = Volley.newRequestQueue(this);
        this.redMartAPI = new RedMartAPI(this.mRequestQueue);

    }

    public RedMartAPI getRedMartAPI() {
        return redMartAPI;
    }

    public int getVersionCode()
    {
        int code = 1;
        try
        {
            if (getPackageManager() != null)
                code = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            return code;
        }
        catch (PackageManager.NameNotFoundException nameNotFoundException)
        {
            nameNotFoundException.printStackTrace();
            return code;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return code;
    }
}
