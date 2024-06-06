package org.example.services;

import org.example.entities.dtos.ViaCepApiResponse;
import org.example.infrastructure.api.viacep.ViaCepApi;
import org.example.repositories.daos.EnderecoRepository;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * A classe EnderecoService fornece serviços relacionados à obtenção de endereços com base no CEP.
 */
public class EnderecoService {
    private EnderecoRepository enderecoRepository;

    /**
     * Construtor padrão da classe EnderecoService.
     * Inicializa o repositório de endereços.
     */
    public EnderecoService(){
        enderecoRepository = new EnderecoRepository();
    }

    /**
     * Obtém um endereço com base no CEP fornecido.
     *
     * @param cep o CEP do endereço a ser obtido.
     * @return um objeto Optional contendo o endereço se encontrado na base de dados
     *         ou na API, ou vazio se não encontrado.
     * @throws Exception se ocorrer um erro ao acessar a API ViaCep.
     */
    public Optional<ViaCepApiResponse> GetByCep(String cep) throws Exception {
        String formattedCep = cep;

        // Verifica se o formato do Cep está incorreto.
        if (!formattedCep.contains("-")){
            formattedCep = MessageFormat.format("{0}-{1}", cep.substring(0, 5), cep.substring(5));
        }

        // Verifica se o endereço já consta na base de dados.
        if (enderecoRepository.FindByCep(formattedCep).isPresent()) {
            var endereco = enderecoRepository.FindByCep(formattedCep);
            return endereco;
        }
        else{
            // Se não constar o endereço na base de dados, retorna o endereço direto da API, como o ID zerado.
            var endereco = ViaCepApi.getEndereco(formattedCep);
            return Optional.ofNullable(endereco);
        }
    }
}
