package phss.wsworkcars.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import phss.wsworkcars.models.Marca
import phss.wsworkcars.models.Modelo
import phss.wsworkcars.services.MarcaService
import phss.wsworkcars.services.ModeloService


@RestController
class ModeloController {

    @Autowired lateinit var service: ModeloService
    @Autowired lateinit var marcaService: MarcaService

    @GetMapping("/modelos")
    fun getModelos(): List<Modelo> {
        return service.getData()
    }

    @GetMapping("/getModelo/{id}")
    fun getModeloById(@PathVariable id: Long): Modelo? {
        return service.getDataById(id)
    }

    @GetMapping("/getModeloByName/{name}")
    fun getModeloByName(@PathVariable name: String): Modelo? {
        return service.getModeloByName(name)
    }

    @GetMapping("/getModelosByMarca/{marcaId}")
    fun getModelosByMarca(@PathVariable marcaId: Long): List<Modelo> {
        return service.getModelosByMarcaId(marcaId)
    }

    @PostMapping("/addModelo")
    fun addModelo(
        @RequestParam marcaId: Long,
        @RequestParam nome: String,
        @RequestParam valor_fipe: Double
    ): Modelo? {
        val marca: Marca = marcaService.getDataById(marcaId) ?: return null
        return service.saveData(Modelo(marca, nome, valor_fipe))
    }

    @PostMapping("/addModeloJson")
    fun addModeloJson(@RequestBody modelo: Modelo): Modelo {
        return service.saveData(modelo)
    }

    @PostMapping("/addModelos")
    fun addModelos(@RequestBody modelos: List<Modelo>): List<Modelo> {
        return service.saveDataList(modelos)
    }

    @PutMapping("/updateModelo")
    fun updateModelo(@RequestBody modelo: Modelo): Modelo {
        return service.updateModelo(modelo)
    }

    @DeleteMapping("/deleteModelo/{id}")
    fun deleteModelo(@PathVariable id: Long): HttpStatus {
        val result = service.deleteDataById(id)
        return if (result == null) HttpStatus.NOT_FOUND else HttpStatus.OK
    }

}