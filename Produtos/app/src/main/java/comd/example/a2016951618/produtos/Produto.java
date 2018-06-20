package comd.example.a2016951618.produtos;

/**
 * Created by a2016951618 on 14/06/18.
 */

public class Produto {
    private String Nome;
    private int Preco;

    public Produto (String Nome, int Preco) {
        this.Nome = Nome;
        this.Preco = Preco;
    }

    public int getPreco() {
        return Preco;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setPreco(int preco) {
        Preco = preco;
    }
}
