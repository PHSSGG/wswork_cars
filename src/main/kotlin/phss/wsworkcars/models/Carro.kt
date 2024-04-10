package phss.wsworkcars.models

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "carros")
class Carro {

    @Id @GeneratedValue var id: Long? = null
    var timestampCadastro: Long = 0L
    @ManyToOne @JoinColumn(name = "modelo_id") lateinit var modelo: Modelo
    var ano: String = ""
    var combustivel: String = ""
    var numPortas: Int = 0
    var cor: String = ""

    constructor()
    constructor(
        modelo: Modelo,
        ano: String,
        combustivel: String,
        numPortas: Int,
        cor: String
    ) : this(null, Timestamp(System.currentTimeMillis()).time, modelo, ano, combustivel, numPortas, cor)

    constructor(
        id: Long?,
        timestampCadastro: Long,
        modelo: Modelo,
        ano: String,
        combustivel: String,
        numPortas: Int,
        cor: String
    ) {
        this.id = id
        this.timestampCadastro = timestampCadastro
        this.modelo = modelo
        this.ano = ano
        this.combustivel = combustivel
        this.numPortas = numPortas
        this.cor = cor
    }

    inner class CarroDTO(
        val id: Long,
        val timestamp_cadastro: Long,
        val modelo_id: Long,
        val ano: String,
        val combustivel: String,
        val num_portas: Int,
        val cor: String,
        val nome_modelo: String,
        val valor: Double
    )

}

fun Carro.toDTO() = CarroDTO(
    id ?: 0L,
    timestampCadastro,
    modelo.id!!,
    ano,
    combustivel,
    numPortas,
    cor,
    modelo.nome,
    modelo.valor_fipe
)