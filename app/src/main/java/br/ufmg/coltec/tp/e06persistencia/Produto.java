package br.ufmg.coltec.tp.e06persistencia;

/**
 * Created by a2016951790 on 02/08/18.
 */

public class Produto {
    String Nome;
    Double Valor;

    Produto(String name, double value) {
        this.Nome = name;
        this.Valor = value;
    }

    Produto(){
        
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }




}
