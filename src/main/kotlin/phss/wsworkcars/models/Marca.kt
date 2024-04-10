package phss.wsworkcars.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "marcas")
class Marca {

    @Id @GeneratedValue var id: Long? = null
    var nome: String = ""

    constructor()
    constructor(nome: String) : this(null, nome)

    constructor(id: Long?, nome: String) {
        this.id = id
        this.nome = nome
    }

}