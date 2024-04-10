package phss.wsworkcars

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class CarsApplication

fun main(args: Array<String>) {
    runApplication<CarsApplication>(*args)
}