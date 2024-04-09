import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import phss.wsworkcars.models.Marca;
import phss.wsworkcars.models.Modelo;
import phss.wsworkcars.services.ModeloService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ModelosTest extends CarsApplicationTest {

    @Autowired
    private ModeloService service;

    @Test
    @Order(0)
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    @Order(1)
    @DirtiesContext
    void retrieveEmptyData() throws Exception {
        mockMvc.perform(get("/modelos"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

    @Test
    @Order(2)
    void addModelo() throws Exception {
        Marca marca = createMarca(new Marca(1, "Test1"));
        createModelo(new Modelo(1, marca, "Modelo1", 10.0));
    }

    @Test
    @Order(3)
    void findModelo() throws Exception {
        Marca marca = new Marca(1, "Test1");
        Modelo modelo = new Modelo(1, marca, "Modelo1", 10.0);

        mockMvc.perform(get("/getModelo/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(modelo)));
        mockMvc.perform(get("/getModelo/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @Order(4)
    @DirtiesContext
    void retrieveData() throws Exception {
        List<Modelo> modelos = service.getData();

        mockMvc.perform(get("/modelos"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(modelos)));
    }

    @Test
    @Order(5)
    @DirtiesContext
    void retrieveModelosByMarca() throws Exception {
        Marca marca1 = new Marca(1, "Marca1");
        Marca marca2 = new Marca(2, "Marca2");
        Marca marca3 = new Marca(3, "Marca3");
        List<Modelo> modelos = Arrays.asList(
                new Modelo(2, marca1, "Modelo2", 1.0),
                new Modelo(3, marca2, "Modelo3", 1.0),
                new Modelo(4, marca1, "Modelo4", 1.0),
                new Modelo(5, marca3, "Modelo5", 2));

        System.out.println("Adding modelos");

        createMarca(marca1);
        createMarca(marca2);
        createMarca(marca3);

        for (Modelo modelo : modelos) {
            createModelo(modelo);
        }

        List<Modelo> filteredModelos = service.getData().stream().filter(modelo -> modelo.getMarca().getId() == 2).toList();
        System.out.println("filtered modelos: " + filteredModelos);

        mockMvc.perform(get("/getModelosByMarca/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(filteredModelos)));
    }

    @Test
    @Order(6)
    void clearDatabase() {
        for (Modelo modelo : service.getData()) {
            service.deleteData(modelo);
        }
        System.out.println("db cleaned");
    }

}
