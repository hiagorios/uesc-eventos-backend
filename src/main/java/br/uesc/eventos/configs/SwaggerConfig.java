package br.uesc.eventos.configs;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String DEFAULT_INCLUDE_PATTERN = "/.*";
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                             
          .apis(RequestHandlerSelectors.basePackage("br.uesc.lavoura"))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(metaData())
		  .securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey()))
;
	}
	
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
		.title("Exemplo de documentação da nossa API REST")
		.description("\"Lorem ipslum\"")
		.version("2.0.0")
		.license("Apache License Version 2.0")
		.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
		.build();
	}
	
	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN)).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}
}
