package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private List<Product> products;
    private Context context;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
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

        Product product = this.products.get(i);
        TextView lblProduct = new TextView(this.context);
        lblProduct.setText("Produto: " + product.getName() + "     " + "Preço: R$ " + product.getPrice());
        return lblProduct;
    }
}
