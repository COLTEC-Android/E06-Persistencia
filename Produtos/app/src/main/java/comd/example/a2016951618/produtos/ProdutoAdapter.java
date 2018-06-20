package comd.example.a2016951618.produtos;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by a2016951618 on 14/06/18.
 */

public class ProdutoAdapter extends BaseAdapter {

    private List<Produto> produtos;
    private Context context;

    public ProdutoAdapter(Context context, List<Produto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }

    @Override
    public int getCount() {
        return this.produtos.size();
    }

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

        Produto produto = this.produtos.get(i);

        TextView lbl_nome = new TextView(this.context);
        lbl_nome.setText(produto.getNome() + "    Preço:R$ " + produto.getPreco());
        return lbl_nome;
    }
}
