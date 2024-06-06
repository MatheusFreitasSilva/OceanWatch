package org.example.services;

import org.example.entities.dtos.Publicacao;
import org.example.repositories.daos.PublicacaoRepository;

/**
 * A classe PublicacaoService fornece serviços relacionados à criação e atualização de publicações.
 */
public class PublicacaoService {
    private PublicacaoRepository publicacaoRepository;

    /**
     * Construtor padrão da classe PublicacaoService.
     * Inicializa o repositório de publicações.
     */
    public PublicacaoService(){
        publicacaoRepository = new PublicacaoRepository();
    }

    /**
     * Cria uma nova publicação.
     *
     * @param publicacao a publicação a ser criada.
     * @throws IllegalArgumentException se a validação da publicação falhar.
     */
    public void Create(Publicacao publicacao){
        var validation = publicacao.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            publicacaoRepository.Create(publicacao);
    }

    /**
     * Atualiza uma publicação existente.
     *
     * @param id o ID da publicação a ser atualizada.
     * @param publicacao os novos dados da publicação.
     * @throws IllegalArgumentException se a validação da publicação falhar.
     */
    public void Update(int id, Publicacao publicacao){
        var validation = publicacao.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            publicacaoRepository.Update(id, publicacao);
    }
}
