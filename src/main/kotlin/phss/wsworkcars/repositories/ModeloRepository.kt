package phss.wsworkcars.repositories

import org.springframework.data.jpa.repository.JpaRepository
import phss.wsworkcars.models.Modelo

interface ModeloRepository : JpaRepository<Modelo, Long> {
    fun findByNome(nome: String): Modelo?
    fun findByMarcaId(marcaId: Long): List<Modelo>
}