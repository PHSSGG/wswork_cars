package phss.wsworkcars.models;

import jakarta.persistence.*;

@Entity
@Table(name = "modelos")
public class Modelo {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    private String nome;
    private double valor_fipe;

    public Modelo() {
    }

    public Modelo(Marca marca, String nome, double valor_fipe) {
        this.marca = marca;
        this.nome = nome;
        this.valor_fipe = valor_fipe;
    }

    public Modelo(long id, Marca marca, String nome, double valor_fipe) {
        this.id = id;
        this.marca = marca;
        this.nome = nome;
        this.valor_fipe = valor_fipe;
    }

    public long getId() {
        return id;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor_fipe() {
        return valor_fipe;
    }

    public void setValor_fipe(double valor_fipe) {
        this.valor_fipe = valor_fipe;
    }

}
