package phss.wsworkcars.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import phss.wsworkcars.models.Marca
import phss.wsworkcars.services.MarcaService

@RestController
class MarcaController {

    @Autowired
    lateinit var service: MarcaService

    @GetMapping("/marcas")
    fun getMarcas(): List<Marca> {
        return service.getData()
    }

    @GetMapping("/getMarca/{id}")
    fun getMarcaById(@PathVariable id: Long): Marca? {
        return service.getDataById(id)
    }

    @GetMapping("/getMarcaByName/{name}")
    fun getMarcaByName(@PathVariable name: String): Marca? {
        return service.getMarcaByName(name)
    }

    @PostMapping("/addMarca")
    fun addMarca(@RequestParam nome: String): Marca {
        return service.saveData(Marca(nome))
    }

    @PostMapping("/addMarcaJson")
    fun addMarcaJson(@RequestBody marca: Marca): Marca {
        return service.saveData(marca)
    }

    @PutMapping("/updateMarca")
    fun updateMarca(@RequestBody marca: Marca): Marca {
        return service.updateMarca(marca)
    }

    @DeleteMapping("/deleteMarca/{id}")
    fun deleteMarca(@PathVariable id: Long): HttpStatus {
        val result = service.deleteDataById(id)

        return if (result == null) HttpStatus.NOT_FOUND else HttpStatus.OK
    }

}