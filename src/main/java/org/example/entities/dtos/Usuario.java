package org.example.entities.dtos;

import org.example.entities._BaseEntity;
import org.example.infrastructure.api.emailvalidation.EmailValidationApi;

import java.util.ArrayList;
import java.util.Map;

public class Usuario extends _BaseEntity {
    private String nome;
    private String usuario;
    private String email;
    private String senha;

    public Usuario(){}

    public Usuario(String nome, String usuario, String email, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int id, String nome, String usuario, String email, String senha) {
        super(id);
        this.nome = nome;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + super.getId() + '\'' +
                ", nome='" + nome + '\'' +
                ", usuario='" + usuario + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                "} ";
    }

    public Map<Boolean, ArrayList<String>> validate() {
        var errors = new ArrayList<String>();


        if (nome == null || nome.isBlank())
            errors.add("Nome do usuario não pode ser vazio");
        if (usuario == null || usuario.isBlank())
            errors.add("Usuário não pode ser vazio");
        try {
            if (!EmailValidationApi.emailValidation(email).isFormat_valid())
                errors.add("O E-mail não é valido");
        } catch (Exception e) {
            throw new RuntimeException("O E-mail informado não é valido", e);
        }


        return !errors.isEmpty() ?
                Map.of(false, errors) :
                Map.of(true, errors);
    }
}
