import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import phss.wsworkcars.models.Marca
import phss.wsworkcars.models.Modelo
import phss.wsworkcars.services.ModeloService

class ModelosTest : CarsApplicationTest() {

    @Autowired
    private lateinit var service: ModeloService

    @Test
    @Order(1)
    @DirtiesContext
    @Throws(Exception::class)
    fun retrieveEmptyData() {
        mockMvc.perform(get("/modelos"))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("[]")))
    }

    @Test
    @Order(2)
    @Throws(Exception::class)
    fun addModelo() {
        val marca: Marca = createMarca(Marca(1, "Test1"))
        createModelo(Modelo(1, marca, "Modelo1", 10.0))
    }

    @Test
    @Order(3)
    @Throws(Exception::class)
    fun findModelo() {
        val marca = Marca(1, "Test1")
        val modelo = Modelo(1, marca, "Modelo1", 10.0)

        mockMvc.perform(get("/getModelo/1"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(modelo)))

        mockMvc.perform(get("/getModelo/2"))
            .andExpect(status().isOk)
            .andExpect(content().string(""))
    }

    @Test
    @Order(4)
    @DirtiesContext
    @Throws(Exception::class)
    fun retrieveData() {
        val modelos: List<Modelo> = service.getData()
        mockMvc.perform(get("/modelos"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(modelos)))
    }

    @Test
    @Order(5)
    @DirtiesContext
    @Throws(Exception::class)
    fun retrieveModelosByMarca() {
        val marca1 = Marca(1, "Marca1")
        val marca2 = Marca(2, "Marca2")
        val marca3 = Marca(3, "Marca3")
        val modelos: List<Modelo> = listOf(
            Modelo(2, marca1, "Modelo2", 1.0),
            Modelo(3, marca2, "Modelo3", 1.0),
            Modelo(4, marca1, "Modelo4", 1.0),
            Modelo(5, marca3, "Modelo5", 2.0)
        )

        println("Adding modelos")

        createMarca(marca1)
        createMarca(marca2)
        createMarca(marca3)

        for (modelo in modelos) {
            createModelo(modelo)
        }

        val filteredModelos: List<Modelo> = service.getData().filter { it.id == 2L }

        println("filtered modelos: $filteredModelos")

        mockMvc.perform(get("/getModelosByMarca/2"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(filteredModelos)))
    }

    @Test
    @Order(6)
    fun clearDatabase() {
        for (modelo in service.getData()) {
            service.deleteData(modelo)
        }
        println("db cleaned")
    }

}