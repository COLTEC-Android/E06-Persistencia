package br.ufmg.coltec.tp.e06persistencia;

public class Produto {
    private Integer id;
    private String name;
    private String valor;

    public Produto(int id, String name, String valor){
        this.name = name;
        this.valor = valor;
    }

    public Produto(String nome, String valor) {
        this.name = nome;
        this.valor = valor;
    }


    public String getValor() {
        return valor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
