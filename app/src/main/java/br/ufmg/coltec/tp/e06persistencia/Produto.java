package br.ufmg.coltec.tp.e06persistencia;

public class Produto {
    private Integer id;
    private String name;
    private float valor;

    public Produto(int id, String name, Float valor){
        this.name = name;
        this.valor = valor;
    }


    public float getValor() {
        return valor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
