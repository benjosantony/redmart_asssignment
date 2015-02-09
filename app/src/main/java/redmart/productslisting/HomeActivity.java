package redmart.productslisting;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import redmart.productslisting.dao.ProductsDAO;
import redmart.productslisting.fragments.ProductDetailsFragment;
import redmart.productslisting.fragments.ProductFragment;
import redmart.productslisting.models.Product;


public class HomeActivity extends ActionBarActivity implements ProductFragment.onProductFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ProductFragment.newInstance(), ProductFragment.TAG)
                    .commit();


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onProductItemClick(int  position) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Product product = ProductsDAO.get(position);
        fragmentTransaction.replace(R.id.container, ProductDetailsFragment.newInstance(product.getId(),product.getTitle()));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }


}
