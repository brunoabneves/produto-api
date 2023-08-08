package store.ojuara.produtoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@OpenAPIDefinition(
//		info = @Info(
//				title = "Documentação da API",
//				version = "1.0.0",
//				description = "Exemplo de documentação Swagger para uma aplicação Spring Boot",
//				contact = @Contact(name = "Nome do Contato", url = "https://exemplo.com", email = "contato@exemplo.com"),
//				license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
//		)
//)
public class ProdutoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoApiApplication.class, args);
	}
}