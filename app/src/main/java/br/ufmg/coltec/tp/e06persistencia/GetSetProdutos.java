package br.ufmg.coltec.tp.e06persistencia;

/**
 * Created by Guilherme Assis on 21/06/18.
 */

public class GetSetProdutos {

    private String nome;
    private float preco;

    GetSetProdutos(String nome, float preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" + "Preco: " + preco;
    }

    public String getNome() {
        return nome;
    }

    public float getPreco() {
        return preco;
    }

}