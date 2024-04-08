package phss.wsworkcars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phss.wsworkcars.models.Marca;
import phss.wsworkcars.repositories.MarcaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> getMarcas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> getMarcaById(long id) {
        return marcaRepository.findById(id);
    }

    public Optional<Marca> getMarcaByName(String name) {
        return marcaRepository.findByNome(name);
    }

    public Marca saveMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public List<Marca> saveMarcas(List<Marca> marcas) {
        return marcaRepository.saveAll(marcas);
    }

    public Marca updateMarca(Marca marca) {
        Marca existingMarca = getMarcaById(marca.getId()).orElse(marca);
        existingMarca.setNome(marca.getNome());

        return saveMarca(existingMarca);
    }

    public Marca deleteMarcaById(long id) {
        Marca marca = getMarcaById(id).orElse(null);
        if (marca == null) return null;

        return deleteMarca(marca);
    }

    public Marca deleteMarca(Marca marca) {
        marcaRepository.delete(marca);
        return marca;
    }

}
