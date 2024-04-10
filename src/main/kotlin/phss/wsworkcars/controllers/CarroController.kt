package phss.wsworkcars.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import phss.wsworkcars.models.Carro
import phss.wsworkcars.services.CarroService
import phss.wsworkcars.services.ModeloService

@RestController
class CarroController {

    @Autowired lateinit var service: CarroService
    @Autowired lateinit var modeloService: ModeloService

    @GetMapping("/cars.json")
    fun getCarsJson(): Map<String, List<Carro.CarroDTO>> {
        val response = HashMap<String, List<Carro.CarroDTO>>()
        response["cars"] = service.getCarrosDTOList()
        return response
    }

    @GetMapping("/carros")
    fun getCarros(): List<Carro> {
        return service.getData()
    }

    @GetMapping("/getCarro/{id}")
    fun getCarroById(@PathVariable id: Long): Carro? {
        return service.getDataById(id)
    }

    @GetMapping("/getCarroByTimestampCadastro/{timestamp}")
    fun getCarroByTimestampCadastro(@PathVariable timestamp: Long): Carro? {
        return service.getCarroByTimestampCadastro(timestamp)
    }

    @GetMapping("/getCarrosByModeloId/{modeloId}")
    fun getCarrosByModeloId(@PathVariable modeloId: Long): List<Carro> {
        return service.getCarrosByModeloId(modeloId)
    }

    @GetMapping("/getCarrosByAno/{ano}")
    fun getCarrosByAno(@PathVariable ano: String): List<Carro> {
        return service.getCarrosByAno(ano)
    }

    @GetMapping("/getCarrosByCombustivel/{combustivel}")
    fun getCarrosByCombustivel(@PathVariable combustivel: String): List<Carro> {
        return service.getCarrosByCombustivel(combustivel)
    }

    @GetMapping("/getCarrosByNumPortas/{numPortas}")
    fun getCarrosByNumPortas(@PathVariable numPortas: Int): List<Carro> {
        return service.getCarrosByNumPortas(numPortas)
    }

    @GetMapping("/getCarrosByCor/{cor}")
    fun getCarrosByCor(@PathVariable cor: String): List<Carro> {
        return service.getCarrosByCor(cor)
    }

    @PostMapping("/addCarro")
    fun addCarro(
        @RequestParam modeloId: Long,
        @RequestParam ano: String,
        @RequestParam combustivel: String,
        @RequestParam numPortas: Int,
        @RequestParam cor: String
    ): Carro? {
        val modelo = modeloService.getDataById(modeloId) ?: return null
        return service.saveData(Carro(modelo, ano, combustivel, numPortas, cor))
    }

    @PostMapping("/addCarroJson")
    fun addCarroJson(@RequestBody carro: Carro): Carro {
        return service.saveData(carro)
    }

    @PostMapping("/addCarros")
    fun addCarros(@RequestBody carros: List<Carro>): List<Carro> {
        return service.saveDataList(carros)
    }

    @PutMapping("/updateCarro")
    fun updateCarro(@RequestBody carro: Carro): Carro {
        return service.updateCarro(carro)
    }

    @DeleteMapping("/deleteCarro/{id}")
    fun deleteCarro(@PathVariable id: Long): HttpStatus {
        val result = service.deleteDataById(id)
        return if (result == null) HttpStatus.NOT_FOUND else HttpStatus.OK
    }

}