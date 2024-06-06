package org.example.services;

import org.example.entities.dtos.Publicacao;
import org.example.repositories.daos.PublicacaoRepository;

public class PublicacaoService {
    private PublicacaoRepository publicacaoRepository;

    public PublicacaoService(){
        publicacaoRepository = new PublicacaoRepository();
    }

    public void Create(Publicacao publicacao){
        var validation = publicacao.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            publicacaoRepository.Create(publicacao);
    }

    public void Update(int id, Publicacao publicacao){
        var validation = publicacao.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            publicacaoRepository.Update(id, publicacao);
    }
}
