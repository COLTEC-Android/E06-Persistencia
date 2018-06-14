package br.ufmg.coltec.tp.e06persistencia;

/**
 * Created by a2016952894 on 14/06/18.
 */

public class Produto {
    private int id;
    private String nome;
    private Integer preco;

    public Produto(int id, String nome, Integer preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Produto () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }
}
