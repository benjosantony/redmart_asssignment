package redmart.productslisting.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import redmart.productslisting.dao.ProductsDAO;


public class ProductsAdapter extends BaseAdapter {



    @Override
    public int getCount() {
        return ProductsDAO.getProductsList().size();


    }

    @Override
    public Object getItem(int position) {

        return ProductsDAO.getProductsList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return ProductsDAO.getProductsList().get(position).id ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

