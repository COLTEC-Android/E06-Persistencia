package pack.rafaalt.ex06;

public class Produto {
    private String nome;
    private double preco;

    Produto(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome(){
        return this.nome;
    }

    public double getPreco(){
        return this.preco;
    }

    @Override
    public String toString() {
        return "" + this.nome + " - R$ " + this.preco + "\n";
    }
}