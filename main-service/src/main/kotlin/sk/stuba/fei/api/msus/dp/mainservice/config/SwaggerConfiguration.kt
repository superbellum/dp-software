package sk.stuba.fei.api.msus.dp.mainservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun swaggerApi(): Docket =
        Docket(DocumentationType.SWAGGER_2)
            .groupName("API")
            .select()
            .paths(PathSelectors.ant("/api/**"))
            .apis(RequestHandlerSelectors.any())
            .build()
            .apiInfo(getApiInfo())

    private fun getApiInfo() =
        ApiInfoBuilder()
            .title("MAIN-SERVICE-API")
            .version("1.0")
            .description("REST API documentation for main-service of DP project")
            .build()
}