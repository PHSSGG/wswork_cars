package phss.wsworkcars.services

import org.springframework.stereotype.Service
import phss.wsworkcars.models.Marca
import phss.wsworkcars.repositories.MarcaRepository

@Service
class MarcaService : DataService<MarcaRepository, Marca>() {

    fun getMarcaByName(name: String): Marca? {
        return repository.findByNome(name)
    }

    fun updateMarca(marca: Marca): Marca {
        getDataById(marca.id!!)?.let {
            it.nome = marca.nome
            return saveData(it)
        }

        return marca
    }

}