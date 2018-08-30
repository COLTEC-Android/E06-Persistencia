package br.ufmg.coltec.tp.e06persistencia;

/**
 * Created by a2016951561 on 21/06/18.
 */

public class Produto {
    private String nome;
    private String preco;


    public Produto(String nome, String preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getPreco() {
        return preco;
    }
}
