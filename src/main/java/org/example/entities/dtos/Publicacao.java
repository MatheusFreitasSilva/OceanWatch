package org.example.entities.dtos;

import org.example.entities._BaseEntity;

public class Publicacao extends _BaseEntity {
    private String titulo;
    private String descricao;
    private String urlImage;
    private String dataPublicacao;
    private int idUsuario;
    private boolean isDenuncia;
    private int idEndereco;

    public Publicacao(){}


    public Publicacao(String titulo, String descricao, String urlImage, String dataPublicacao, int idUsuario, boolean isDenuncia, int idEndereco) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.urlImage = urlImage;
        this.dataPublicacao = dataPublicacao;
        this.idUsuario = idUsuario;
        this.isDenuncia = isDenuncia;
        this.idEndereco = idEndereco;
    }

    public Publicacao(int id, String titulo, String descricao, String urlImage, String dataPublicacao, int idUsuario, boolean isDenuncia, int idEndereco) {
        super(id);
        this.titulo = titulo;
        this.descricao = descricao;
        this.urlImage = urlImage;
        this.dataPublicacao = dataPublicacao;
        this.idUsuario = idUsuario;
        this.isDenuncia = isDenuncia;
        this.idEndereco = idEndereco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isDenuncia() {
        return isDenuncia;
    }

    public void setDenuncia(boolean denuncia) {
        isDenuncia = denuncia;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    @Override
    public String toString() {
        return "Publicacao{" +
                "id='" + super.getId() + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", dataPublicacao='" + dataPublicacao + '\'' +
                ", idUsuario=" + idUsuario +
                ", isDenuncia=" + isDenuncia +
                ", idEndereco=" + idEndereco +
                "} ";
    }
}
