package org.example.infrastructure.api.viacep;

import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.example.entities.dtos.ViaCepApiResponse;

public class ViaCepApi {
    private static final String VIA_CEP_API_URL = "https://viacep.com.br/ws/";

    public static ViaCepApiResponse getEndereco(String cep) throws Exception {
        Client client = ClientBuilder.newClient();
        String response = client.target(VIA_CEP_API_URL + cep + "/json/")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        return JsonbBuilder.create().fromJson(response, ViaCepApiResponse.class);
    }
}
