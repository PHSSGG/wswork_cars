import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import phss.wsworkcars.CarsApplication
import phss.wsworkcars.models.Carro
import phss.wsworkcars.models.Marca
import phss.wsworkcars.models.Modelo

@SpringBootTest(classes = [CarsApplication::class], webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations = ["classpath:application-test.properties"])
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@EnableWebMvc
open class CarsApplicationTest {

    @Autowired protected lateinit var mockMvc: MockMvc

    protected var objectMapper = ObjectMapper()

    @Throws(Exception::class)
    protected fun createMarca(marca: Marca): Marca {
        mockMvc.perform(
            post("/addMarcaJson")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(marca))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.nome").value(marca.nome))

        return marca
    }

    @Throws(java.lang.Exception::class)
    protected open fun createModelo(modelo: Modelo): Modelo {
        mockMvc.perform(
            post("/addModeloJson")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modelo))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.nome").value(modelo.nome))
            .andExpect(jsonPath("$.valor_fipe").value(modelo.valor_fipe))

        return modelo
    }

    @Throws(java.lang.Exception::class)
    protected open fun createCarro(carro: Carro): Carro {
        mockMvc.perform(
            post("/addCarroJson")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(carro))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.ano").value(carro.ano))
            .andExpect(jsonPath("$.cor").value(carro.cor))

        return carro
    }

}