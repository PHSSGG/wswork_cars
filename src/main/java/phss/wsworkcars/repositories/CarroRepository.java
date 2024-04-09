package phss.wsworkcars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import phss.wsworkcars.models.Carro;

import java.util.List;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    Optional<Carro> findByTimestampCadastro(long timestampCadastro);
    List<Carro> findByCor(String cor);
    List<Carro> findByCombustivel(String combustivel);
    List<Carro> findByNumPortas(int numPortas);
    List<Carro> findByAno(String ano);
    List<Carro> findByModeloId(long modeloId);
}
