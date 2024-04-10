import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import phss.wsworkcars.CarsApplication;
import phss.wsworkcars.models.Carro;
import phss.wsworkcars.models.Marca;
import phss.wsworkcars.models.Modelo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CarsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@EnableWebMvc
public class CarsApplicationTest {

    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected Marca createMarca(Marca marca) throws Exception {
        mockMvc.perform(post("/addMarcaJson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(marca)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(marca.getNome()));

        return marca;
    }

    protected Modelo createModelo(Modelo modelo) throws Exception {
        mockMvc.perform(post("/addModeloJson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(modelo.getNome()))
                .andExpect(jsonPath("$.valor_fipe").value(modelo.getValor_fipe()));

        return modelo;
    }

    protected Carro createCarro(Carro carro) throws Exception {
        mockMvc.perform(post("/addCarroJson")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ano").value(carro.getAno()))
                .andExpect(jsonPath("$.cor").value(carro.getCor()));

        return carro;
    }

}
