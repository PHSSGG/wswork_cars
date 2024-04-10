package phss.wsworkcars.repositories

import org.springframework.data.jpa.repository.JpaRepository
import phss.wsworkcars.models.Carro

interface CarroRepository : JpaRepository<Carro, Long> {
    fun findByTimestampCadastro(timestampCadastro: Long): Carro?
    fun findByModeloId(modeloId: Long): List<Carro>
    fun findByAno(ano: String): List<Carro>
    fun findByCombustivel(combustivel: String): List<Carro>
    fun findByNumPortas(numPortas: Int): List<Carro>
    fun findByCor(cor: String): List<Carro>
}