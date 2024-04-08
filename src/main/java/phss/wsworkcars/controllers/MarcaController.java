package phss.wsworkcars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import phss.wsworkcars.models.Marca;
import phss.wsworkcars.services.MarcaService;

import java.util.List;

@RestController
public class MarcaController {

    @Autowired
    private MarcaService service;

    @GetMapping("/marcas")
    public List<Marca> getMarcas() {
        return service.getData();
    }

    @GetMapping("/getMarca/{id}")
    public Marca getMarcaById(@PathVariable long id) {
        return service.getDataById(id).orElse(null);
    }

    @GetMapping("/getMarcaByName/{name}")
    public Marca getMarcaByName(@PathVariable String name) {
        return service.getMarcaByName(name).orElse(null);
    }

    @PostMapping("/addMarca")
    public Marca addMarca(@RequestBody Marca marca) {
        return service.saveData(marca);
    }

    @PostMapping("/addMarcas")
    public List<Marca> addMarcas(@RequestBody List<Marca> marcas) {
        return service.saveDataList(marcas);
    }

    @PutMapping("/updateMarca")
    public Marca updateMarca(@RequestBody Marca marca) {
        return service.updateMarca(marca);
    }

    @DeleteMapping("/deleteMarca/{id}")
    public HttpStatus deleteMarca(@PathVariable long id) {
        Marca result = service.deleteDataById(id);

        if (result == null) return HttpStatus.NOT_FOUND;
        else return HttpStatus.OK;
    }

}
