package sk.stuba.fei.api.msus.dp.mainservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Import
import sk.stuba.fei.api.msus.dp.mainservice.config.SwaggerConfiguration

@SpringBootApplication
@EnableFeignClients
@Import(SwaggerConfiguration::class)
class MainServiceApplication

fun main(args: Array<String>) {
    runApplication<MainServiceApplication>(*args)
}