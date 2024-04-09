package phss.wsworkcars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import phss.wsworkcars.models.Modelo;

import java.util.List;
import java.util.Optional;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    Optional<Modelo> findByNome(String nome);
    List<Modelo> findByMarcaId(long marcaId);
}
