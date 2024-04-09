import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import phss.wsworkcars.models.Modelo;
import phss.wsworkcars.services.ModeloService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        Modelo modelo = new Modelo(1, -1, "Modelo1", 10.0);

        mockMvc.perform(post("/addModelo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Modelo1"))
                .andExpect(jsonPath("$.valor_fipe").value(10.0));
    }

    @Test
    @Order(3)
    void findModelo() throws Exception {
        Modelo modelo = new Modelo(1, -1, "Modelo1", 10.0);

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
        List<Modelo> modelos = Arrays.asList(
                new Modelo(2, 1, "Modelo2", 1.0),
                new Modelo(3, -1, "Modelo3", 1.0),
                new Modelo(4, 1, "Modelo4", 1.0),
                new Modelo(5, 100, "Modelo5", 2));
        System.out.println("Adding modelos");

        mockMvc.perform(post("/addModelos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelos)))
                .andExpect(status().isOk());

        List<Modelo> filteredModelos = service.getData().stream().filter(modelo -> modelo.getMarcaId() == -1).toList();
        System.out.println("filtered modelos: " + filteredModelos);

        mockMvc.perform(get("/getModelosByMarca/-1"))
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
