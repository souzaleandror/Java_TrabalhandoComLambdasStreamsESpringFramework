package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEspisodio;
import br.com.alura.screenmatch.model.DadosSeries;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.services.ConsumoApi;
import br.com.alura.screenmatch.services.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private ConsumoApi consumoApi = new ConsumoApi();
    ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        System.out.println();
        System.out.println("Digite o nome da serie para buscar:");
        var nomeSerie = leitura.nextLine();
        var URL_COMPLETO = ENDERECO + nomeSerie.replace(" ", "+") + API_KEY + "&Season=1";
        var json = consumoApi.obterDados(URL_COMPLETO);
        DadosSeries dados = conversor.obterDados(json, DadosSeries.class);
        System.out.println("Comusindo API 10 => " + URL_COMPLETO);
        System.out.println(dados);

        var jsonTemporada = consumoApi.obterDados(URL_COMPLETO);
        DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);
        System.out.println("Comusindo API 11 => " + URL_COMPLETO);
        System.out.println(dadosTemporada);

        List<DadosTemporada> listaTemporadas = new ArrayList<DadosTemporada>();


        for(int i = 1; i <= dadosTemporada.totalSeasons(); i++) {
            var url_temporada = ENDERECO + nomeSerie.replace(" ", "+") + "&Season=" + i + API_KEY;

            var jsonCadaTemporada = consumoApi.obterDados(url_temporada);
            DadosTemporada dadosCadaTemporada = conversor.obterDados(jsonCadaTemporada, DadosTemporada.class);
            System.out.println("Comusindo API 12 => " + url_temporada);
            System.out.println(dadosCadaTemporada);
            listaTemporadas.add(dadosCadaTemporada);
            System.out.println();
        }
        System.out.println("Comusindo API 13");
        System.out.println(listaTemporadas);
        System.out.println();
        System.out.println("Comusindo API 14");
        listaTemporadas.forEach(System.out::println);
        System.out.println();

        System.out.println("Comusindo API 15");
        for(int i = 0; i < dados.totalTemporadas(); i++) {
            List<DadosEspisodio> episodiosTemporada = listaTemporadas.get(i).episodios();
            for(int j = 0; j < episodiosTemporada.size(); j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }

        System.out.println("Comusindo API 16");
        listaTemporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

    }
}
