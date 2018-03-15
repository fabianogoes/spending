package com.spending;

import com.spending.config.SwaggerConfiguration;
import com.spending.model.Category;
import com.spending.model.RegistryType;
import com.spending.repository.CategoryRepository;
import com.spending.service.CategoryService;
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

	@Autowired
	private CategoryService categoryService;

	@Bean
	public List<RegistryType> getRegistryTypeAll() {
		List<RegistryType> registryTypes = this.registryTypeService.findAll();
		registryTypes.forEach(System.out::println);
		return registryTypes;
	}

	@Override
	public void run(String... args) throws Exception {
		this.registryTypeService.deleteAll();
		this.categoryService.deleteAll();

		List<RegistryType> registryTypes = Arrays.asList(
				RegistryType.builder().name("Outros").build(),
				RegistryType.builder().name("Pagamento Boleto de Cobrança").pattern(new HashSet<>(Arrays.asList("Pagto Cobranca", "Pagamento Cobrança"))).build(),
				RegistryType.builder().name("Transferência Bancária").pattern(new HashSet<>(Arrays.asList("Transf Fdos Doc"))).build(),
				RegistryType.builder().name("Pagamento Água, Luz, Telefone, TV e Gás").pattern(new HashSet<>(
						Arrays.asList(
								"Conta Telefone",
								"Conta de Luz",
								"Conta Agua/esgo",
								"tv p/assinatura Vivo",
								"Recarga Claro Pre Pago"
						)
				)).build(),
				RegistryType.builder().name("Tributos (Impostos, Taxas ou Contribuições)").pattern(new HashSet<>(
						Arrays.asList(
								"Trib Internet B-pm Guarulhos",
								"Trib Bradesco C-p.m S.p - Denatran"
						)
				)).build(),
				RegistryType.builder().name("Pagamento Cartao Debito").pattern(new HashSet<>(Arrays.asList("Visa Electron"))).build(),
				RegistryType.builder().name("Saque Caixa Automatico").pattern(new HashSet<>(Arrays.asList("Saque c/c Bdn", "sq c/c Bco24h"))).build(),
				RegistryType.builder().name("Transferência Bancária").pattern(new HashSet<>(Arrays.asList("Transf Fdos Doc"))).build(),
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


		List<Category> categories = Arrays.asList(
				Category.builder().name("Outros").build(),
				Category.builder().name("Mercado").pattern(new HashSet<>(
						Arrays.asList(
								"Supermercado",
								"Mercado", "Wal Mart")
				)).build(),
				Category.builder().name("Transporte").pattern(new HashSet<>(
						Arrays.asList(
							"Estacionamento",
							"Prestacao Carro",
							"Cobranca Azul Companhia de Seguros Gerais",
							"Posto Extra Guaru",
							"Portal de Guarul",
							"Cobranca Carro"
						))
				).build(),
				Category.builder().name("Educação").pattern(new HashSet<>(
						Arrays.asList(
								"Eniac",
								"Unicsul",
								"Escola Davi")
				)).build(),
				Category.builder().name("Saúde").pattern(new HashSet<>(Arrays.asList("Convenio Amil"))).build(),
				Category.builder().name("Moradia").pattern(new HashSet<>(
						Arrays.asList("Condominio",
								"Parc Cred Imob")
				)).build(),
				Category.builder().name("Telefone").pattern(new HashSet<>(
						Arrays.asList(
								"Claro Pre Pago",
								"Telefone Telefonica",
								"Net Servicos",
								"Conta Telefone"
						)
				)).build(),
				Category.builder().name("Água").pattern(new HashSet<>(Arrays.asList("Agua"))).build(),
				Category.builder().name("Luz").pattern(new HashSet<>(Arrays.asList("Luz"))).build(),
				Category.builder().name("TV/Internet").pattern(new HashSet<>(Arrays.asList("tv p/assinatura Vivo"))).build(),
				Category.builder().name("Tarifa Bancária").pattern(new HashSet<>(
						Arrays.asList(
								"Iof Util Limite",
								"Tarifa Bancaria",
								"Enc Lim Credito Encargo")
				)).build(),
				Category.builder().name("Saque").pattern(new HashSet<>(
						Arrays.asList(
								"Saque c/c Bdn",
								"sq c/c Bco24h")
				)).build(),
				Category.builder().name("Cartão de Crédito").pattern(new HashSet<>(
						Arrays.asList("Cobranca Cartao",
								"Cartao Credito Nubank")
				)).build(),
				Category.builder().name("Açougue").pattern(new HashSet<>(Arrays.asList("Jbs s a – açougue", "açougue", "Swfit Bosque Maia"))).build(),
				Category.builder().name("Impostos").pattern(new HashSet<>(
						Arrays.asList(
								"Trib Internet B-pm Guarulhos sp",
								"C-p.m S.p - Denatran",
								"simples Nacional",
								"inss/gps")
				)).build(),
				Category.builder().name("Lanches e Refeições").pattern(new HashSet<>(
						Arrays.asList(
								"Acopiara Paralelo",
								"Lojas Americanas",
								"Carrefour Express",
								"Lojas Uniao Famil",
								"Lojao do 1 Real",
								"mc Donalds",
								"Yoshinobu Nakao",
								"tk Sushi",
								"Star073",
								"Bomboniere Delici",
								"Panificadora v mo",
								"Fifty Cafe"
						))
				).build(),
				Category.builder().name("Serviços").pattern(new HashSet<>(
						Arrays.asList(
								"Dest.alencars Contabilidade",
								"Cgmp-sem Parar/sp",
								"Central Cabeleireiro"
						)
				)).build()
		);
		this.categoryService.save(categories);
	}
}
