package phss.wsworkcars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Classe que servirá como base para os serviços.
 * Essa classe já possui alguns métodos pré-feitos para
 * prevenir códigos duplicados na aplicação
 *
 * @param <R> A classe de repositório do dado
 * @param <T> O tipo do dado que esse serviço vai gerenciar
 */
public abstract class DataService<R extends JpaRepository<T, Long>, T> {

    @Autowired
    protected R repository;

    public List<T> getData() {
        return repository.findAll();
    }

    public Optional<T> getDataById(long id) {
        return repository.findById(id);
    }

    public T saveData(T data) {
        return repository.save(data);
    }

    public List<T> saveDataList(List<T> data) {
        return repository.saveAll(data);
    }

    public T deleteDataById(long id) {
        T data = getDataById(id).orElse(null);
        if (data == null) return null;

        return deleteData(data);
    }

    public T deleteData(T data) {
        repository.delete(data);
        return data;
    }

}
