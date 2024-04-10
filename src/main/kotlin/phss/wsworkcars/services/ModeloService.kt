package phss.wsworkcars.services

import org.springframework.stereotype.Service
import phss.wsworkcars.models.Modelo
import phss.wsworkcars.repositories.ModeloRepository

@Service
class ModeloService : DataService<ModeloRepository, Modelo>() {

    fun getModeloByName(name: String): Modelo? {
        return repository.findByNome(name)
    }

    fun getModelosByMarcaId(marcaId: Long): List<Modelo> {
        return repository.findByMarcaId(marcaId)
    }

    fun updateModelo(modelo: Modelo): Modelo {
        getDataById(modelo.id!!)?.let {
            it.marca = modelo.marca
            it.nome = modelo.nome
            it.valor_fipe = modelo.valor_fipe

            return saveData(it)
        }

        return modelo
    }

}