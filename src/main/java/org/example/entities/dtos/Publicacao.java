package org.example.entities.dtos;

import org.example.entities._BaseEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Classe Publicacao que representa uma publicação.
 * Estende a classe _BaseEntity para incluir um identificador único.
 * Contém informações sobre o título, descrição, URL da imagem, data de publicação,
 * ID do usuário, se é uma denúncia e ID do endereço.
 */
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
        Date data = new Date();
        this.dataPublicacao = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(data);
        return dataPublicacao;
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

    /**
     * Valida os dados da publicação.
     * Verifica se o título e a descrição não estão vazios.
     *
     * @return um mapa com o resultado da validação. A chave é um booleano indicando sucesso ou falha,
     * e o valor é uma lista de mensagens de erro, se houver.
     */
    public Map<Boolean, ArrayList<String>> validate() {
        var errors = new ArrayList<String>();

        if (titulo == null || titulo.isBlank())
            errors.add("Titulo da publicação não pode ser vazio");
        if (descricao == null || descricao.isBlank())
            errors.add("Descrição não pode ser vazia");

        return !errors.isEmpty() ?
                Map.of(false, errors) :
                Map.of(true, errors);
    }
}
