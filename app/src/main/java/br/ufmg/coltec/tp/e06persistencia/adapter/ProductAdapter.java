package br.ufmg.coltec.tp.e06persistencia.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import br.ufmg.coltec.tp.e06persistencia.Product;

public class ProductAdapter extends BaseAdapter {

    private ArrayList<Product> products;
    private Context context;


    public ProductAdapter(Context context, List<Product> products){
        this.context = context;
        this.products = (ArrayList<Product>) products;
    }


    @Override
    public int getCount() {
        return this.products.size();
    }

    @Override
    public Object getItem(int i) {
        return this.products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Product p = this.products.get(i);

        TextView lblProduct = new TextView(this.context);
        lblProduct.setText(p.getName() + ": R$ " + p.getPrice());
        return lblProduct;
    }
}
