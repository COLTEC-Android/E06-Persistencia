package br.ufmg.coltec.tp.e06persistencia;

public class Produto {
    private String nome;
    private Integer ID;
    private double preco;

    public Produto(Integer ID, String nome, double preco ){
        this.ID=ID;
        this.nome=nome;
        this.preco=preco;
    }

    public Produto(String nome, double preco ){
        this.ID=null;
        this.nome=nome;
        this.preco=preco;
    }

    public Integer getID(){
        return this.ID;
    }
    public void setID(Integer categoria) {
        this.ID = ID;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco(){ return this.preco;}
    public void setPreco(Float preco) {
        this.preco = preco;
    }
}
