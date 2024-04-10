package phss.wsworkcars.services

import org.springframework.stereotype.Service
import phss.wsworkcars.models.Carro
import phss.wsworkcars.models.toDTO
import phss.wsworkcars.repositories.CarroRepository

@Service
class CarroService : DataService<CarroRepository, Carro>() {

    fun getCarrosDTOList(): List<Carro.CarroDTO> {
        return getData().map { it.toDTO() }.toList()
    }

    fun getCarroByTimestampCadastro(timestampCadastro: Long): Carro? {
        return repository.findByTimestampCadastro(timestampCadastro)
    }

    fun getCarrosByModeloId(modeloId: Long): List<Carro> {
        return repository.findByModeloId(modeloId)
    }

    fun getCarrosByAno(ano: String): List<Carro> {
        return repository.findByAno(ano)
    }

    fun getCarrosByCombustivel(combustivel: String): List<Carro> {
        return repository.findByCombustivel(combustivel)
    }

    fun getCarrosByNumPortas(numPortas: Int): List<Carro> {
        return repository.findByNumPortas(numPortas)
    }

    fun getCarrosByCor(cor: String): List<Carro> {
        return repository.findByCor(cor)
    }

    fun updateCarro(carro: Carro): Carro {
        getDataById(carro.id!!)?.let {
            it.timestampCadastro = carro.timestampCadastro
            it.modelo = carro.modelo
            it.ano = carro.ano
            it.combustivel = carro.combustivel
            it.numPortas = carro.numPortas
            it.cor = carro.cor

            return saveData(it)
        }

        return carro
    }

}