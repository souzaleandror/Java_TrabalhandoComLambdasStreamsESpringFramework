package br.com.alura.screenmatch.services;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.core.JacksonException;

public class ConverteDados implements IConverterDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JacksonException e) {
            throw new RuntimeException(e);
        }
    }
}
