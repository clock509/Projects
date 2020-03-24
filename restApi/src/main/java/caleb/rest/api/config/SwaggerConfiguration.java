package caleb.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("caleb.rest.api.controller"))
                .paths(PathSelectors.any()) //basePackage(“caleb.rest.api.controller”)).paths(PathSelectors.any())==>caleb.rest.api.controller 하단의 Controller 내용을 읽어 mapping된 resource들을 문서화시킨다.
                .build() //PathSelectors.ant("/v1/**") 으로 한다면 v1으로 시작하는 resource들만 문서화시킬 수 있음.
                .useDefaultResponseMessages(false); // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음.
    }

    private ApiInfo swaggerInfo() { //swaggerInfo: 문서에 대한 설명과 작성자 정보를 노출시킬 수 있음.
        return new ApiInfoBuilder().title("Spring API Documentation: Elastic Search Cluster list")
                .description("엘라스틱서치 클러스터 생성 서비스에 사용되는 서버 API에 대한 연동 문서입니다.")
                .license("Caleb_ElasticSearch").licenseUrl("http://daddyprogrammer.org").version("1").build();
    }
}