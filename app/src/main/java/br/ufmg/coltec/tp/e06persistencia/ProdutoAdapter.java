package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProdutoAdapter extends BaseAdapter {

    private Context context;
    private List<Produto> produtoList;

    public ProdutoAdapter(Context context){
        AppDB appDB = new AppDB(context);
        ProdutoDAO produtoDAO = new ProdutoDAO(appDB);

        this.context = context;
        this.produtoList =produtoDAO.getAll();
    }

    @Override
    public int getCount() {
        return this.produtoList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.produtoList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Produto produto = this.produtoList.get(position);

        View produtoView = LayoutInflater.from(this.context).inflate(R.layout.adapter_produtos,parent,false);
        TextView produtoNome = produtoView.findViewById(R.id.produtonome);
        TextView produtoValor = produtoView.findViewById(R.id.produtovalor);

        produtoNome.setText(produto.getName());
        produtoValor.setText(produto.getValor());
        return produtoView;
    }

    public void updateList() {
        AppDB appDB = new AppDB(context);
        ProdutoDAO produtoDAO = new ProdutoDAO(appDB);
        this.produtoList = produtoDAO.getAll();
    }

}
