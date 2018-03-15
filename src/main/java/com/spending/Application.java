package com.spending;

import com.spending.config.SwaggerConfiguration;
import com.spending.model.RegistryType;
import com.spending.service.RegistryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(
				Application.class,
				SwaggerConfiguration.class
		).run(args);
	}

	@Autowired
	private RegistryTypeService registryTypeService;

	@Bean
	public List<RegistryType> getRegistryTypeAll() {
		List<RegistryType> registryTypes = this.registryTypeService.findAll();
		registryTypes.forEach(System.out::println);
		return registryTypes;
	}

	@Override
	public void run(String... args) throws Exception {
		this.registryTypeService.deleteAll();
		List<RegistryType> registryTypes = Arrays.asList(
				RegistryType.builder().name("Pagamento Boleto").pattern(new HashSet<>(Arrays.asList("Pagto Cobranca", "Pagamento Cobrança"))).build(),
				RegistryType.builder().name("Pagamento Cartao Debito").pattern(new HashSet<>(Arrays.asList("Visa Electron"))).build(),
				RegistryType.builder().name("Saque Caixa Automatico").pattern(new HashSet<>(Arrays.asList("Saque c/c Bdn", "sq c/c Bco24h"))).build(),
				RegistryType.builder()
						.name("Debito em Conta")
						.pattern(new HashSet<>(
								Arrays.asList("Deb.automatico",
											  "Enc Lim Credito Encargo",
										      "Iof Util Limite",
										 	  "Parc Cred Imob",
										      "Tarifa Bancaria Cesta Exclusive")
								)
						)
						.build()
		);
		this.registryTypeService.save(registryTypes);
	}
}
