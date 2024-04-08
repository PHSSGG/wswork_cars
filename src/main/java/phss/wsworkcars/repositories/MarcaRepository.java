package phss.wsworkcars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import phss.wsworkcars.models.Marca;

import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Optional<Marca> findByNome(String nome);
}
