package org.example.services;

import org.example.entities.dtos.Usuario;
import org.example.repositories.daos.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(){
        usuarioRepository = new UsuarioRepository();
    }

    public void Create(Usuario usuario){
        var validation = usuario.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            usuarioRepository.Create(usuario);
    }

    public void Update(int id, Usuario usuario){
        var validation = usuario.validate();

        if(validation.containsKey(false))
            throw new IllegalArgumentException(validation.get(false).toString());
        else
            usuarioRepository.Update(id, usuario);
    }
}
