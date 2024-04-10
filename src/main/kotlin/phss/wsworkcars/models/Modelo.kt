package phss.wsworkcars.models

import jakarta.persistence.*

@Entity
@Table(name = "modelos")
class Modelo {

    @Id @GeneratedValue var id: Long? = null
    @ManyToOne @JoinColumn(name = "marca_id") lateinit var marca: Marca

    var nome: String = ""
    var valor_fipe: Double = 0.0

    constructor()
    constructor(marca: Marca, nome: String, valor_fipe: Double) : this(
        null, marca, nome, valor_fipe
    )

    constructor(id: Long?, marca: Marca, nome: String, valor_fipe: Double) {
        this.id = id
        this.marca = marca
        this.nome = nome
        this.valor_fipe = valor_fipe
    }

}