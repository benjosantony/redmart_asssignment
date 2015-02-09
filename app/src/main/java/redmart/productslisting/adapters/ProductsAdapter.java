package redmart.productslisting.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import redmart.productslisting.R;
import redmart.productslisting.dao.ProductsDAO;
import redmart.productslisting.models.Image;
import redmart.productslisting.models.Price;
import redmart.productslisting.models.Product;
import redmart.productslisting.utilities.FormatUtils;


public class ProductsAdapter extends BaseAdapter {

    Context context;
    onProductsAdapterReachLastItemListener mListener;

    public ProductsAdapter(Context context, onProductsAdapterReachLastItemListener listener) {
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public int getCount() {

        return ProductsDAO.count();


    }

    @Override
    public Object getItem(int position) {

        return ProductsDAO.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ProductsDAO.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (getCount() != 0 && (position + 2) == getCount()) {
            // Time to fetch more items
            // Lets as the owner to provide more products
            mListener.onProductsAdapterReachLastItem();
        }

        Product product = ProductsDAO.get(position);

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_layout_products, null);
            ViewHolder holder = new ViewHolder();
            holder.ivProductImage = (ImageView) convertView.findViewById(R.id.ivProductImage);
            holder.tvOriginalPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            // Set the strike through
            holder.tvOriginalPrice.setPaintFlags(holder.tvOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvPromotionalPrice = (TextView) convertView.findViewById(R.id.tvPromoPrice);
            holder.tvPromo = (TextView) convertView.findViewById(R.id.tvProductPromoText);
            holder.tvQty = (TextView) convertView.findViewById(R.id.tvProductQty);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvProductTitle);

           convertView.setTag(holder);

        }
        ViewHolder holder = (ViewHolder) convertView.getTag();

        Image image = product.getImg();
        String imageUrl = (image == null) ? null : image.getImageUrl();
        if (imageUrl != null)
            Picasso.with(context).load(imageUrl).into(holder.ivProductImage);

        holder.tvTitle.setText(product.getTitle());
        holder.tvQty.setText(product.getVolumeWeight());
        Price price = product.getPrice();
        String savingsText = price.getSavingsText();
        savingsText = price.isOnSale() ? savingsText : "SOLD OUT";
        holder.tvPromo.setText(savingsText);

        if (price.isOnSale()){
             holder.tvPromo.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.promo_text_background));
        } else {
            holder.tvPromo.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.sold_out_text_background));
        }

        holder.tvPromotionalPrice.setText("S$" + FormatUtils.getDollarString(price.getPromoPrice()));
        holder.tvOriginalPrice.setText("S$" + FormatUtils.getDollarString(price.getPrice()));
        return convertView;
    }


    class ViewHolder {
        TextView tvTitle;
        TextView tvQty;
        TextView tvPromo;
        TextView tvOriginalPrice;
        TextView tvPromotionalPrice;
        ImageView ivProductImage;

    }

    public interface onProductsAdapterReachLastItemListener {

        public void onProductsAdapterReachLastItem();
    }
}

