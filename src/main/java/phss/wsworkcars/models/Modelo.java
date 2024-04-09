package phss.wsworkcars.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "modelos")
public class Modelo {

    @Id
    @GeneratedValue
    private long id;

    private long marcaId;
    private String nome;
    private double valor_fipe;

    public Modelo() {
    }

    public Modelo(long marcaId, String nome, double valor_fipe) {
        this.marcaId = marcaId;
        this.nome = nome;
        this.valor_fipe = valor_fipe;
    }

    public Modelo(long id, long marcaId, String nome, double valor_fipe) {
        this.id = id;
        this.marcaId = marcaId;
        this.nome = nome;
        this.valor_fipe = valor_fipe;
    }

    public long getId() {
        return id;
    }

    public long getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(long marcaId) {
        this.marcaId = marcaId;
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
