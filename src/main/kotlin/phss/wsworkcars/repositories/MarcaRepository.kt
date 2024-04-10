package phss.wsworkcars.repositories

import org.springframework.data.jpa.repository.JpaRepository
import phss.wsworkcars.models.Marca

interface MarcaRepository : JpaRepository<Marca, Long> {
    fun findByNome(nome: String): Marca?
}