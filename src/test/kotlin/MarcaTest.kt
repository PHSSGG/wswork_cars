import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import phss.wsworkcars.models.Marca
import phss.wsworkcars.services.MarcaService

class MarcaTest : CarsApplicationTest() {

    @Autowired lateinit var service: MarcaService

    @Test
    @Order(1)
    @DirtiesContext
    @Throws(Exception::class)
    fun retrieveEmptyData() {
        mockMvc.perform(get("/marcas"))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("[]")))
    }

    @Test
    @Order(2)
    @Throws(Exception::class)
    fun addMarca() {
        val marca = Marca(1, "Test1")
        createMarca(marca)
    }

    @Test
    @Order(3)
    @Throws(Exception::class)
    fun findMarca() {
        val marca = Marca(1, "Test1")
        mockMvc.perform(get("/getMarca/1"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(marca)))
        mockMvc.perform(get("/getMarca/2"))
            .andExpect(status().isOk)
            .andExpect(content().string(""))
    }

    @Test
    @Order(4)
    @DirtiesContext
    @Throws(Exception::class)
    fun retrieveData() {
        val marcas = service.getData()
        mockMvc.perform(get("/marcas"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(marcas)))
    }

    @Test
    @Order(5)
    fun clearDatabase() {
        for (marca in service.getData()) {
            service.deleteData(marca)
        }
        println("db cleaned")
    }

}