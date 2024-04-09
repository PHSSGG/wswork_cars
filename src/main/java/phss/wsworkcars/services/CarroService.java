package phss.wsworkcars.services;

import org.springframework.stereotype.Service;
import phss.wsworkcars.models.Carro;
import phss.wsworkcars.repositories.CarroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService extends DataService<CarroRepository, Carro> {

    public List<Carro.CarroDTO> getCarrosDTOList() {
        return getData().stream().map(Carro::convertToDTO).toList();
    }

    public Optional<Carro> getByTimestampCadastro(long timestamp) {
        return repository.findByTimestampCadastro(timestamp);
    }

    public List<Carro> getByCor(String cor) {
        return repository.findByCor(cor);
    }

    public List<Carro> getByCombustivel(String combustivel) {
        return repository.findByCombustivel(combustivel);
    }

    public List<Carro> getByNumPortas(int numPortas) {
        return repository.findByNumPortas(numPortas);
    }

    public List<Carro> getByAno(String ano) {
        return repository.findByAno(ano);
    }

    public List<Carro> getByModeloId(long modeloId) {
        return repository.findByModeloId(modeloId);
    }

    public Carro updateCarro(Carro carro) {
        Carro existingCarro = getDataById(carro.getId()).orElse(carro);
        existingCarro.setTimestampCadastro(carro.getTimestampCadastro());
        existingCarro.setModelo(carro.getModelo());
        existingCarro.setAno(carro.getAno());
        existingCarro.setCombustivel(carro.getCombustivel());
        existingCarro.setNumPortas(carro.getNumPortas());
        existingCarro.setCor(carro.getCor());

        return saveData(existingCarro);
    }

}
