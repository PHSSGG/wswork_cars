package phss.wsworkcars.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carros")
public class Carro {

    @Id
    @GeneratedValue
    private long id;

    private long timestampCadastro;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    private String ano;
    private String combustivel;
    private int numPortas;
    private String cor;

    public Carro() {
    }

    public Carro(long timestampCadastro, Modelo modelo, String ano, String combustivel, int numPortas, String cor) {
        this.timestampCadastro = timestampCadastro;
        this.modelo = modelo;
        this.ano = ano;
        this.combustivel = combustivel;
        this.numPortas = numPortas;
        this.cor = cor;
    }

    public Carro(long id, long timestampCadastro, Modelo modelo, String ano, String combustivel, int numPortas, String cor) {
        this.id = id;
        this.timestampCadastro = timestampCadastro;
        this.modelo = modelo;
        this.ano = ano;
        this.combustivel = combustivel;
        this.numPortas = numPortas;
        this.cor = cor;
    }

    public long getId() {
        return id;
    }

    public long getTimestampCadastro() {
        return timestampCadastro;
    }

    public void setTimestampCadastro(long timestampCadastro) {
        this.timestampCadastro = timestampCadastro;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public int getNumPortas() {
        return numPortas;
    }

    public void setNumPortas(int numPortas) {
        this.numPortas = numPortas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public CarroDTO convertToDTO() {
        return new CarroDTO(id, timestampCadastro, modelo.getId(), ano, combustivel, numPortas, cor, modelo.getNome(), modelo.getValor_fipe());
    }

    public static class CarroDTO {
        private long id;
        private long timestamp_cadastro;
        private long modelo_id;
        private String ano;
        private String combustivel;
        private int num_portas;
        private String cor;
        private String nome_modelo;
        private double valor;

        public CarroDTO() {
        }

        public CarroDTO(long id, long timestamp_cadastro, long modelo_id, String ano, String combustivel, int num_portas, String cor, String nome_modelo, double valor) {
            this.id = id;
            this.timestamp_cadastro = timestamp_cadastro;
            this.modelo_id = modelo_id;
            this.ano = ano;
            this.combustivel = combustivel;
            this.num_portas = num_portas;
            this.cor = cor;
            this.nome_modelo = nome_modelo;
            this.valor = valor;
        }

        public long getId() {
            return id;
        }

        public long getTimestamp_cadastro() {
            return timestamp_cadastro;
        }

        public long getModelo_id() {
            return modelo_id;
        }

        public String getAno() {
            return ano;
        }

        public String getCombustivel() {
            return combustivel;
        }

        public int getNum_portas() {
            return num_portas;
        }

        public String getCor() {
            return cor;
        }

        public String getNome_modelo() {
            return nome_modelo;
        }

        public double getValor() {
            return valor;
        }
    }

}
