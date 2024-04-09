package phss.wsworkcars.services;

import org.springframework.stereotype.Service;
import phss.wsworkcars.models.Modelo;
import phss.wsworkcars.repositories.ModeloRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService extends DataService<ModeloRepository, Modelo> {

    public Optional<Modelo> getModeloByName(String name) {
        return repository.findByNome(name);
    }

    public List<Modelo> getModelosByMarcaId(long marcaId) {
        return repository.findByMarcaId(marcaId);
    }

    public Modelo updateModelo(Modelo modelo) {
        Modelo existingModelo = getDataById(modelo.getId()).orElse(modelo);
        existingModelo.setMarcaId(modelo.getMarcaId());
        existingModelo.setNome(modelo.getNome());
        existingModelo.setValor_fipe(modelo.getValor_fipe());

        return saveData(existingModelo);
    }


}
