package phss.wsworkcars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import phss.wsworkcars.models.Marca;
import phss.wsworkcars.models.Modelo;
import phss.wsworkcars.services.MarcaService;
import phss.wsworkcars.services.ModeloService;

import java.util.List;

@RestController
public class ModeloController {

    @Autowired
    private ModeloService service;

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/modelos")
    public List<Modelo> getModelos() {
        return service.getData();
    }

    @GetMapping("/getModelo/{id}")
    public Modelo getModeloById(@PathVariable long id) {
        return service.getDataById(id).orElse(null);
    }

    @GetMapping("/getModeloByName/{name}")
    public Modelo getModeloByName(@PathVariable String name) {
        return service.getModeloByName(name).orElse(null);
    }

    @GetMapping("/getModelosByMarca/{marcaId}")
    public List<Modelo> getModelosByMarca(@PathVariable long marcaId) {
        return service.getModelosByMarcaId(marcaId);
    }

    @PostMapping("/addModelo")
    public Modelo addModelo(@RequestParam long marcaId, @RequestParam String nome, @RequestParam double valor_fipe) {
        Marca marca = marcaService.getDataById(marcaId).orElse(null);
        if (marca == null) return null;

        return service.saveData(new Modelo(marca, nome, valor_fipe));
    }

    @PostMapping("/addModeloJson")
    public Modelo addModeloJson(@RequestBody Modelo modelo) {
        return service.saveData(modelo);
    }

    @PostMapping("/addModelos")
    public List<Modelo> addModelos(@RequestBody List<Modelo> modelos) {
        return service.saveDataList(modelos);
    }

    @PutMapping("/updateModelo")
    public Modelo updateModelo(@RequestBody Modelo modelo) {
        return service.updateModelo(modelo);
    }

    /**
     * Caso o sistema encontre um modelo com o @param id informado
     * o retorno será OK, caso contrário o retorno será NOT_FOUND.
     */
    @DeleteMapping("/deleteModelo/{id}")
    public HttpStatus deleteModelo(@PathVariable long id) {
        Modelo result = service.deleteDataById(id);

        if (result == null) return HttpStatus.NOT_FOUND;
        else return HttpStatus.OK;
    }

}