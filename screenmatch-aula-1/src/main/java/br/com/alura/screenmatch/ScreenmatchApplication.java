package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosSeries;
import br.com.alura.screenmatch.services.ConsumoApi;
import br.com.alura.screenmatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Primeiro projeto spring sem web");
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		System.out.println(json);
		var json2 = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json2);
		ConverteDados conversor = new ConverteDados();
		DadosSeries dados = conversor.obterDados(json, DadosSeries.class);
    	System.out.println(dados);
	}
}
