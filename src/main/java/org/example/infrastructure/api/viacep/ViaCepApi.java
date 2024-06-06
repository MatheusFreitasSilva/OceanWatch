package org.example.infrastructure.api.viacep;

import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.example.entities.dtos.ViaCepApiResponse;

/**
 * Classe ViaCepApi que fornece métodos para obter informações de endereços utilizando a API ViaCEP.
 */
public class ViaCepApi {
    private static final String VIA_CEP_API_URL = "https://viacep.com.br/ws/";

    /**
     * Obtém informações de endereço a partir de um CEP utilizando a API ViaCEP.
     *
     * @param cep o CEP para o qual se deseja obter informações de endereço.
     * @return um objeto ViaCepApiResponse contendo informações do endereço correspondente ao CEP.
     * @throws Exception se ocorrer algum erro durante a solicitação à API ou ao processar a resposta.
     */
    public static ViaCepApiResponse getEndereco(String cep) throws Exception {
        Client client = ClientBuilder.newClient();
        String response = client.target(VIA_CEP_API_URL + cep + "/json/")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        return JsonbBuilder.create().fromJson(response, ViaCepApiResponse.class);
    }
}
