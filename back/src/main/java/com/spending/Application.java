package com.spending;

import com.spending.config.MongoConfiguration;
import com.spending.config.SwaggerConfiguration;
import com.spending.config.WebConfiguration;
import com.spending.model.Category;
import com.spending.model.Type;
import com.spending.service.CategoryService;
import com.spending.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class,
//				WebConfiguration.class,
				SwaggerConfiguration.class,
				MongoConfiguration.class
		).run(args);
	}


	@Autowired
	private TypeService typeService;

	@Autowired
	private CategoryService categoryService;

	@Bean
	public List<Type> getTypeAll() {
		return this.typeService.findAll();
	}

	@Override
	public void run(String... args) throws Exception {
		this.typeService.deleteAll();
		this.categoryService.deleteAll();

		List<Type> types = Arrays.asList(
				Type.builder().name("Outros").build(),
				Type.builder().name("Pagamento Boleto de Cobrança").patterns(new HashSet<>(Arrays.asList("Pagto Cobranca", "Pagamento Cobrança"))).build(),
				Type.builder().name("Transferência Bancária").patterns(new HashSet<>(
						Arrays.asList(
								"Transf Fdos Doc",
								"Transf Autoriz",
								"Tr.aut.c/c/poup"
						)
				)).build(),
				Type.builder().name("Pagamento Água, Luz, Telefone, TV e Gás").patterns(new HashSet<>(
						Arrays.asList(
								"Conta Telefone",
								"Conta de Luz",
								"Conta Agua/esgo",
								"tv p/assinatura Vivo",
								"Recarga Claro Pre Pago"
						)
				)).build(),
				Type.builder().name("Tributos (Impostos, Taxas ou Contribuições)").patterns(new HashSet<>(
						Arrays.asList(
								"Trib Internet B-pm Guarulhos",
								"Trib Bradesco C-p.m S.p - Denatran"
						)
				)).build(),
				Type.builder().name("Pagamento Cartao Debito").patterns(new HashSet<>(Arrays.asList("Visa Electron"))).build(),
				Type.builder().name("Saque Caixa Automatico").patterns(new HashSet<>(Arrays.asList("Saque c/c Bdn", "sq c/c Bco24h"))).build(),
				Type.builder().name("Transferência Bancária").patterns(new HashSet<>(Arrays.asList("Transf Fdos Doc"))).build(),
				Type.builder()
						.name("Debito em Conta")
						.patterns(new HashSet<>(
								Arrays.asList("Deb.automatico",
											  "Enc Lim Credito Encargo",
										      "Iof Util Limite",
										 	  "Parc Cred Imob",
										      "Tarifa Bancaria Cesta Exclusive")
								)
						)
						.build()
		);
		this.typeService.save(types);


		List<Category> categories = Arrays.asList(
				Category.builder().name("Outros").build(),
				Category.builder().name("Mercado").patterns(new HashSet<>(
						Arrays.asList(
								"Supermercado",
								"Mercado", "Wal Mart")
				)).build(),
				Category.builder().name("Transporte").patterns(new HashSet<>(
						Arrays.asList(
							"Estacionamento",
							"Prestacao Carro",
							"Cobranca Azul Companhia de Seguros Gerais",
							"Posto Extra Guaru",
							"Portal de Guarul",
							"Cobranca Carro"
						))
				).build(),
				Category.builder().name("Educação").patterns(new HashSet<>(
						Arrays.asList(
								"Eniac",
								"Unicsul",
								"Escola Davi")
				)).build(),
				Category.builder().name("Saúde").patterns(new HashSet<>(Arrays.asList("Convenio Amil"))).build(),
				Category.builder().name("Moradia").patterns(new HashSet<>(
						Arrays.asList("Condominio",
								"Parc Cred Imob")
				)).build(),
				Category.builder().name("Telefone").patterns(new HashSet<>(
						Arrays.asList(
								"Claro Pre Pago",
								"Telefone Telefonica",
								"Net Servicos",
								"Conta Telefone"
						)
				)).build(),
				Category.builder().name("Água").patterns(new HashSet<>(Arrays.asList("Agua"))).build(),
				Category.builder().name("Luz").patterns(new HashSet<>(Arrays.asList("Luz"))).build(),
				Category.builder().name("TV/Internet").patterns(new HashSet<>(Arrays.asList("tv p/assinatura Vivo"))).build(),
				Category.builder().name("Tarifa Bancária").patterns(new HashSet<>(
						Arrays.asList(
								"Iof Util Limite",
								"Tarifa Bancaria",
								"Enc Lim Credito Encargo")
				)).build(),
				Category.builder().name("Saque").patterns(new HashSet<>(
						Arrays.asList(
								"Saque c/c Bdn",
								"sq c/c Bco24h"
						)
				)).build(),
				Category.builder().name("Transferência").patterns(new HashSet<>(
						Arrays.asList(
								"Transf Fdos Doc",
								"Transf Autoriz",
								"Tr.aut.c/c/poup"
						)
				)).build(),
				Category.builder().name("Cartão de Crédito").patterns(new HashSet<>(
						Arrays.asList("Cobranca Cartao",
								"Cartao Credito Nubank")
				)).build(),
				Category.builder().name("Açougue").patterns(new HashSet<>(Arrays.asList("Jbs s a – açougue", "açougue", "Swfit Bosque Maia"))).build(),
				Category.builder().name("Impostos").patterns(new HashSet<>(
						Arrays.asList(
								"Trib Internet B-pm Guarulhos sp",
								"C-p.m S.p - Denatran",
								"simples Nacional",
								"inss/gps")
				)).build(),
				Category.builder().name("Lanches e Refeições").patterns(new HashSet<>(
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
				Category.builder().name("Serviços").patterns(new HashSet<>(
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
