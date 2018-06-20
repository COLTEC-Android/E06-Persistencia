package br.ufmg.coltec.tp.e06persistencia;

/**
 * Created by a2016951715 on 20/06/18.
 */

public class Produto {

    private String nome;
    private float preco;


    Produto(String n, float p){
        this.nome = n;
        this.preco = p;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
