package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEspisodio;
import br.com.alura.screenmatch.model.DadosSeries;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.principal.Principal;
import br.com.alura.screenmatch.services.ConsumoApi;
import br.com.alura.screenmatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Aula 1
		System.out.println("Primeiro projeto spring sem web");
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		System.out.println("Comusindo API 1 => https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		System.out.println(json);
		System.out.println();

		var json2 = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		System.out.println("Comusindo API 2 => https://coffee.alexflipnote.dev/random.json");
		System.out.println(json2);
		System.out.println();
		ConverteDados conversor = new ConverteDados();
		DadosSeries dados = conversor.obterDados(json, DadosSeries.class);
		System.out.println("Comusindo API 3 => https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
    	System.out.println(dados);
		System.out.println();

		// Aula 2
		var jsonEpisodio = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&Season=1&Episode=1&apikey=6585022c");
		DadosEspisodio dadosEpisodio = conversor.obterDados(jsonEpisodio, DadosEspisodio.class);
		System.out.println("Comusindo API 4 => https://www.omdbapi.com/?t=gilmore+girls&Season=1&Episode=1&apikey=6585022c");
		System.out.println(dadosEpisodio);
		System.out.println();

		var jsonTemporada = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&Season=1&apikey=6585022c");
		DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);
		System.out.println("Comusindo API 5 => https://www.omdbapi.com/?t=gilmore+girls&Season=1&apikey=6585022c");
		System.out.println(dadosTemporada);
		System.out.println();

		List<DadosTemporada> listaTemporadas = new ArrayList<DadosTemporada>();

		for(int i = 1; i <= dadosTemporada.totalSeasons(); i++) {
			var jsonCadaTemporada = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&Season=" + i + "&apikey=6585022c");
			DadosTemporada dadosCadaTemporada = conversor.obterDados(jsonCadaTemporada, DadosTemporada.class);
			System.out.println("Comusindo API 6 => https://www.omdbapi.com/?t=gilmore+girls&Season=&apikey=6585022c");
			System.out.println(dadosCadaTemporada);
			listaTemporadas.add(dadosCadaTemporada);
			System.out.println();
		}
		System.out.println("Comusindo API 7 => https://www.omdbapi.com/?t=gilmore+girls&Season=&apikey=6585022c");
		System.out.println(listaTemporadas);
		System.out.println();
		System.out.println("Comusindo API 8 => https://www.omdbapi.com/?t=gilmore+girls&Season=&apikey=6585022c");
		listaTemporadas.forEach(System.out::println);
		System.out.println();

		System.out.println("Comusindo API 9 => https://www.omdbapi.com/?t=gilmore+girls&Season=&apikey=6585022c");
		Principal principal = new Principal();
		principal.exibeMenu();


	}
}
