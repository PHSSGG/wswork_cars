import org.hamcrest.Matchers.containsString
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import phss.wsworkcars.models.Carro
import phss.wsworkcars.models.Carro.CarroDTO
import phss.wsworkcars.models.Marca
import phss.wsworkcars.models.Modelo
import phss.wsworkcars.services.CarroService
import java.util.concurrent.TimeUnit

class CarrosTest : CarsApplicationTest() {

    @Autowired lateinit var service: CarroService

    @Test
    @Order(1)
    @DirtiesContext
    @Throws(Exception::class)
    fun retrieveEmptyData() {
        mockMvc.perform(get("/carros"))
            .andExpect(status().isOk)
            .andExpect(content().string(containsString("[]")))
    }

    @Test
    @Order(2)
    @Throws(Exception::class)
    fun addCarro() {
        createCarro(getMockCarro())
    }

    @Test
    @Order(3)
    @Throws(Exception::class)
    fun findCarro() {
        val carro = getMockCarro()

        mockMvc.perform(get("/getCarro/1"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(carro)))

        mockMvc.perform(get("/getCarro/2"))
            .andExpect(status().isOk)
            .andExpect(content().string(""))
    }

    @Test
    @Order(4)
    @Throws(Exception::class)
    fun findCarroByTimestamp() {
        val carro = getMockCarro()

        mockMvc.perform(get("/getCarroByTimestampCadastro/" + TimeUnit.DAYS.toMillis(10)))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(carro)))
    }

    @Test
    @Order(5)
    @Throws(Exception::class)
    fun findCarroByCor() {
        val carros = listOf(getMockCarro())

        mockMvc.perform(get("/getCarrosByCor/BEGE"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(carros)))
    }

    @Test
    @Order(6)
    @Throws(Exception::class)
    fun findCarroByCombustivel() {
        val carros = listOf(getMockCarro())

        mockMvc.perform(get("/getCarrosByCombustivel/FLEX"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(carros)))
    }

    @Test
    @Order(7)
    @Throws(Exception::class)
    fun findCarroByAno() {
        val carros = listOf(getMockCarro())

        mockMvc.perform(get("/getCarrosByAno/2024"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(carros)))
    }

    @Throws(Exception::class)
    private fun getMockCarro(): Carro {
        val marca = createMarca(Marca(1, "Test1"))
        val modelo = createModelo(Modelo(1, marca, "Modelo1", 10.0))

        return Carro(1, TimeUnit.DAYS.toMillis(10), modelo, "2024", "FLEX", 4, "BEGE")
    }

    @Test
    @Order(8)
    @DirtiesContext
    @Throws(Exception::class)
    fun retrieveData() {
        val carros = service.getData()

        mockMvc.perform(get("/carros"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(carros)))

        val response = HashMap<String, List<CarroDTO>>()
        response["cars"] = service.getCarrosDTOList()

        mockMvc.perform(get("/cars.json"))
            .andExpect(status().isOk)
            .andExpect(content().string(objectMapper.writeValueAsString(response)))
    }

    @Test
    @Order(9)
    fun clearDatabase() {
        for (carro in service.getData()) {
            service.deleteData(carro)
        }
        println("db cleaned")
    }

}