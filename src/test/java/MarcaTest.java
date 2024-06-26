import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import phss.wsworkcars.models.Marca;
import phss.wsworkcars.services.MarcaService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MarcaTest extends CarsApplicationTest {

    @Autowired
    private MarcaService service;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Order(0)
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    @Order(1)
    @DirtiesContext
    void retrieveEmptyData() throws Exception {
        mockMvc.perform(get("/marcas"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

    @Test
    @Order(2)
    void addMarca() throws Exception {
        Marca marca = new Marca(1, "Test1");
        createMarca(marca);
    }

    @Test
    @Order(3)
    void findMarca() throws Exception {
        Marca marca = new Marca(1, "Test1");

        mockMvc.perform(get("/getMarca/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(marca)));
        mockMvc.perform(get("/getMarca/2"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @Order(4)
    @DirtiesContext
    void retrieveData() throws Exception {
        List<Marca> marcas = service.getData();

        mockMvc.perform(get("/marcas"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(marcas)));
    }

    @Test
    @Order(5)
    void clearDatabase() {
        for (Marca marca : service.getData()) {
            service.deleteData(marca);
        }
        System.out.println("db cleaned");
    }

}
