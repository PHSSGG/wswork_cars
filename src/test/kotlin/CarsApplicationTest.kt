import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import phss.wsworkcars.CarsApplication
import phss.wsworkcars.models.Marca

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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

}