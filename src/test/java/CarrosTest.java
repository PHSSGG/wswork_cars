import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import phss.wsworkcars.models.Carro;
import phss.wsworkcars.models.Marca;
import phss.wsworkcars.models.Modelo;
import phss.wsworkcars.services.CarroService;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CarrosTest extends CarsApplicationTest {

    @Autowired
    private CarroService service;

    @Test
    @Order(0)
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    @Order(1)
    @DirtiesContext
    void retrieveEmptyData() throws Exception {
        mockMvc.perform(get("/carros"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")));
    }

    @Test
    @Order(2)
    void addCarro() throws Exception {
        createCarro(getMockCarro());
    }

    @Test
    @Order(3)
    void findCarro() throws Exception {
        Carro carro = getMockCarro();

        mockMvc.perform(get("/getCarro/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(carro)));
        mockMvc.perform(get("/getCarro/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @Order(4)
    void findCarroByTimestamp() throws Exception {
        Carro carro = getMockCarro();

        mockMvc.perform(get("/getCarroByTimestampCadastro/" + TimeUnit.DAYS.toMillis(10)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(carro)));
    }

    @Test
    @Order(5)
    void findCarroByCor() throws Exception {
        List<Carro> carros = Collections.singletonList(getMockCarro());

        mockMvc.perform(get("/getCarrosByCor/BEGE"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(carros)));
    }

    @Test
    @Order(6)
    void findCarroByCombustivel() throws Exception {
        List<Carro> carros = Collections.singletonList(getMockCarro());

        mockMvc.perform(get("/getCarrosByCombustivel/FLEX"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(carros)));
    }

    @Test
    @Order(7)
    void findCarroByAno() throws Exception {
        List<Carro> carros = Collections.singletonList(getMockCarro());

        mockMvc.perform(get("/getCarrosByAno/2024"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(carros)));
    }

    private Carro getMockCarro() throws Exception {
        Marca marca = createMarca(new Marca(1, "Test1"));
        Modelo modelo = createModelo(new Modelo(1, marca, "Modelo1", 10.0));

        return new Carro(
                1,
                new Timestamp(TimeUnit.DAYS.toMillis(10)).getTime(),
                modelo, "2024", "FLEX", 4, "BEGE");
    }

    @Test
    @Order(8)
    @DirtiesContext
    void retrieveData() throws Exception {
        List<Carro> carros = service.getData();

        mockMvc.perform(get("/carros"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(carros)));

        Map<String, List<Carro.CarroDTO>> response = new HashMap<>();
        response.put("cars", service.getCarrosDTOList());

        mockMvc.perform(get("/cars.json"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @Test
    @Order(9)
    void clearDatabase() {
        for (Carro carro : service.getData()) {
            service.deleteData(carro);
        }
        System.out.println("db cleaned");
    }

}
