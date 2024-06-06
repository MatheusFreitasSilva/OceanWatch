package org.example.infrastructure.api.emailvalidation;

import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.example.entities.dtos.EmailValidationResponse;

/**
 * Classe EmailValidationApi que fornece métodos para validar endereços de e-mail
 * utilizando a API de validação de e-mail.
 */
public class EmailValidationApi {
    private static final String URL_API = "https://api.emailvalidation.io/v1/info?apikey=";
    private static final String API_KEY = "ema_live_QxDpDWz9fR5jvL5kkGmxuYONRYHKxTBP4EupEmtI";

    /**
     * Realiza a validação de um endereço de e-mail utilizando a API de validação de e-mail.
     *
     * @param email o endereço de e-mail a ser validado.
     * @return um objeto EmailValidationResponse contendo informações sobre a validação do e-mail.
     * @throws Exception se ocorrer algum erro durante a solicitação à API.
     */
    public static EmailValidationResponse emailValidation(String email) throws Exception{
        Client client = ClientBuilder.newClient();
        String response = client.target(URL_API + API_KEY + "&email=" + email)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        return JsonbBuilder.create().fromJson(response, EmailValidationResponse.class);
    }
}
