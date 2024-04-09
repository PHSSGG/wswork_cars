package phss.wsworkcars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import phss.wsworkcars.models.Carro;
import phss.wsworkcars.services.CarroService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping("/cars.json")
    public Map<String, List<Carro.CarroDTO>> getCarsJson() {
        Map<String, List<Carro.CarroDTO>> response = new HashMap<>();
        response.put("cars", service.getCarrosDTOList());

        return response;
    }

    @GetMapping("/carros")
    public List<Carro> getCarros() {
        return service.getData();
    }

    @GetMapping("/getCarro/{id}")
    public Carro getCarroById(@PathVariable long id) {
        return service.getDataById(id).orElse(null);
    }

    @GetMapping("/getCarroByTimestampCadastro/{timestamp}")
    public Carro getCarroByTimestampCadastro(@PathVariable long timestamp) {
        return service.getByTimestampCadastro(timestamp).orElse(null);
    }

    @GetMapping("/getCarrosByCor/{cor}")
    public List<Carro> getCarrosByCor(@PathVariable String cor) {
        return service.getByCor(cor);
    }

    @GetMapping("/getCarrosByCombustivel/{combustivel}")
    public List<Carro> getCarrosByCombustivel(@PathVariable String combustivel) {
        return service.getByCombustivel(combustivel);
    }

    @GetMapping("/getCarrosByNumPortas/{numPortas}")
    public List<Carro> getCarrosByNumPortas(@PathVariable int numPortas) {
        return service.getByNumPortas(numPortas);
    }

    @GetMapping("/getCarrosByAno/{ano}")
    public List<Carro> getCarrosByAno(@PathVariable String ano) {
        return service.getByAno(ano);
    }

    @GetMapping("/getCarrosByModeloId/{modeloId}")
    public List<Carro> getCarrosByModeloId(@PathVariable long modeloId) {
        return service.getByModeloId(modeloId);
    }

    @PostMapping("/addCarro")
    public Carro addCarro(@RequestBody Carro carro) {
        return service.saveData(carro);
    }

    @PostMapping("/addCarros")
    public List<Carro> addCarros(@RequestBody List<Carro> carros) {
        return service.saveDataList(carros);
    }

    @PutMapping("/updateCarro")
    public Carro updateCarro(@RequestBody Carro carro) {
        return service.updateCarro(carro);
    }

    @DeleteMapping("/deleteCarro/{id}")
    public HttpStatus deleteCarro(@PathVariable long id) {
        Carro result = service.deleteDataById(id);

        if (result == null) return HttpStatus.NOT_FOUND;
        else return HttpStatus.OK;
    }

}
