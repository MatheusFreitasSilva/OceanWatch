package org.example.services;

import org.example.entities.dtos.Usuario;
import org.example.repositories.daos.UsuarioRepository;

/**
 * A classe UsuarioService fornece serviços relacionados à criação e atualização de usuários.
 */
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    /**
     * Construtor padrão da classe UsuarioService.
     * Inicializa o repositório de usuários.
     */
    public UsuarioService(){
        usuarioRepository = new UsuarioRepository();
    }

    /**
     * Cria um novo usuário.
     *
     * @param usuario o usuário a ser criado.
     * @throws IllegalArgumentException se a validação do usuário falhar.
     */
    public void Create(Usuario usuario){
        var validation = usuario.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            usuarioRepository.Create(usuario);
    }

    /**
     * Atualiza um usuário existente.
     *
     * @param id o ID do usuário a ser atualizado.
     * @param usuario os novos dados do usuário.
     * @throws IllegalArgumentException se a validação do usuário falhar.
     */
    public void Update(int id, Usuario usuario){
        var validation = usuario.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            usuarioRepository.Update(id, usuario);
    }
}
