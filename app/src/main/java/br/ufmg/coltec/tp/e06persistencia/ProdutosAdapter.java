package br.ufmg.coltec.tp.e06persistencia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProdutosAdapter extends BaseAdapter{
    private ArrayList<Produto> produtos;
    private Context context;

    public ProdutosAdapter(Context context) {
        this.context = context;
        //ProdutoDAO dao = new ProdutoDAO(context);
        //produtos = dao.getAll();
        produtos=new ArrayList<>();
        produtos.add(new Produto("Teste",50));
    }
    @Override
    public int getCount() {
        return this.produtos.size();
    };

    @Override
    public Object getItem(int i) {
        return this.produtos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Produto prod = this.produtos.get(i);
        View newView  = LayoutInflater.from(this.context).inflate(R.layout.activity_produtos_adapter, viewGroup, false);

        // cria o componente que será carregado na lista
        TextView nome = newView.findViewById(R.id.nomeProduto);
        TextView valor = newView.findViewById(R.id.valorProduto);

        nome.setText(prod.getNome());
        valor.setText(String.format("%s%s", "R$", String.valueOf(prod.getPreco())));
        return newView;
    }
}
