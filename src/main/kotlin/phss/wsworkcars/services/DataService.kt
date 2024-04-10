package phss.wsworkcars.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository

abstract class DataService<R : JpaRepository<T, Long>, T> {

    @Autowired
    protected lateinit var repository: R

    fun getData(): List<T> {
        return repository.findAll()
    }

    fun getDataById(id: Long): T? {
        return repository.findById(id).orElse(null)
    }

    fun saveData(data: T): T {
        return repository.save(data)
    }

    fun saveDataList(data: List<T>): List<T> {
        return repository.saveAll(data)
    }

    fun deleteDataById(id: Long): T? {
        val data: T = getDataById(id) ?: return null
        return deleteData(data)
    }

    fun deleteData(data: T): T {
        repository.delete(data)
        return data
    }

}
