package phss.wsworkcars.services;

import org.springframework.stereotype.Service;
import phss.wsworkcars.models.Marca;
import phss.wsworkcars.repositories.MarcaRepository;

import java.util.Optional;

@Service
public class MarcaService extends DataService<MarcaRepository, Marca> {

    public Optional<Marca> getMarcaByName(String name) {
        return repository.findByNome(name);
    }

    public Marca updateMarca(Marca marca) {
        Marca existingMarca = getDataById(marca.getId()).orElse(marca);
        existingMarca.setNome(marca.getNome());

        return saveData(existingMarca);
    }

}
