package br.com.alura.screenmatch.services;

public interface IConverterDados {
    <T> T obterDados(String json, Class<T> classe);
}
