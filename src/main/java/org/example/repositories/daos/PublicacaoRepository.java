package org.example.repositories.daos;

import org.example.entities.dtos.Publicacao;
import org.example.repositories._BaseRepository;
import org.example.repositories._Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Classe PublicacaoRepository que fornece métodos para interagir com a tabela de publicações no banco de dados.
 * Estende a classe _BaseRepository para ter acesso à conexão com o banco de dados.
 * Implementa a interface _Logger para registrar mensagens de log.
 */
public class PublicacaoRepository extends _BaseRepository implements _Logger<PublicacaoRepository> {
    public static final String TB_NAME = "PUBLICACAO";

    // Mapeamento das colunas da tabela de publicações
    public static final Map<String, String> TB_COLUMNS = Map.of(
            "TITULO", "TITULO",
            "DESCRICAO", "DESCRICAO",
            "URL_IMAGE", "URL_IMAGE",
            "DATA_PUBLICACAO", "DATA_PUBLICACAO",
            "ID_USUARIO", "ID_USUARIO",
            "IS_DENUNCIA", "IS_DENUNCIA",
            "ID_ENDERECO", "ID_ENDERECO"
    );

    /**
     * Cria uma nova publicação no banco de dados.
     *
     * @param publicacao o objeto Publicacao a ser criado.
     */
    public void Create(Publicacao publicacao){
        try (var stmt = conn.prepareStatement(
                "INSERT INTO %s(%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)"
                        .formatted(TB_NAME,
                                TB_COLUMNS.get("TITULO"),
                                TB_COLUMNS.get("DESCRICAO"),
                                TB_COLUMNS.get("URL_IMAGE"),
                                TB_COLUMNS.get("DATA_PUBLICACAO"),
                                TB_COLUMNS.get("ID_USUARIO"),
                                TB_COLUMNS.get("IS_DENUNCIA"),
                                TB_COLUMNS.get("ID_ENDERECO")))){
            stmt.setString(1, publicacao.getTitulo());
            stmt.setString(2, publicacao.getDescricao());
            stmt.setString(3, publicacao.getUrlImage());
            stmt.setString(4, publicacao.getDataPublicacao());
            stmt.setInt(5, publicacao.getIdUsuario());
            stmt.setBoolean(6, publicacao.isDenuncia());
            stmt.setInt(7, publicacao.getIdEndereco());
            stmt.executeUpdate();
            LogInfo("Publicação criado com sucesso!");
        } catch (SQLException e){
            LogError("Erro ao adicionar Publicação: ");
            e.printStackTrace();
        }
    }

    /**
     * Retorna uma lista de todas as publicações armazenadas no banco de dados.
     *
     * @return uma lista contendo objetos Publicacao que representam as publicações.
     */
    public List<Publicacao> ReadAll(){
        var publicacoes = new ArrayList<Publicacao>();
        try (var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY ID")) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                publicacoes.add(new Publicacao(
                        rs.getInt("ID"),
                        rs.getString("TITULO"),
                        rs.getString("DESCRICAO"),
                        rs.getString("URL_IMAGE"),
                        rs.getString("DATA_PUBLICACAO"),
                        rs.getInt("ID_USUARIO"),
                        rs.getBoolean("IS_DENUNCIA"),
                        rs.getInt("ID_ENDERECO")));
            }
        } catch (Exception e) {
            LogError("Erro ao filtrar Banco de Dados: ");
            e.printStackTrace();
        }
        return publicacoes;
    }

    /**
     * Busca uma publicação pelo ID no banco de dados.
     *
     * @param id o ID da publicação a ser buscada.
     * @return um Optional contendo o objeto Publicacao se encontrado, ou vazio se não encontrado.
     */
    public Optional<Publicacao> FindById(int id){
        try (var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?")
        ) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Publicacao(
                        rs.getInt("ID"),
                        rs.getString("TITULO"),
                        rs.getString("DESCRICAO"),
                        rs.getString("URL_IMAGE"),
                        rs.getString("DATA_PUBLICACAO"),
                        rs.getInt("ID_USUARIO"),
                        rs.getBoolean("IS_DENUNCIA"),
                        rs.getInt("ID_ENDERECO")));
            }
        } catch (Exception e) {
            LogError("Erro ao filtrar Banco de Dados: ");
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * Atualiza uma publicação no banco de dados.
     *
     * @param id         o ID da publicação a ser atualizada.
     * @param publicacao o objeto Publicacao com os novos dados.
     */
    public void Update(int id, Publicacao publicacao) {
        try (var stmt = conn.prepareStatement(
                "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE ID = ?"
                        .formatted(TB_NAME,
                                TB_COLUMNS.get("TITULO"),
                                TB_COLUMNS.get("DESCRICAO"),
                                TB_COLUMNS.get("URL_IMAGE"),
                                TB_COLUMNS.get("DATA_PUBLICACAO"),
                                TB_COLUMNS.get("ID_USUARIO"),
                                TB_COLUMNS.get("IS_DENUNCIA"),
                                TB_COLUMNS.get("ID_ENDERECO")))){

            stmt.setString(1, publicacao.getTitulo());
            stmt.setString(2, publicacao.getDescricao());
            stmt.setString(3, publicacao.getUrlImage());
            stmt.setString(4, publicacao.getDataPublicacao());
            stmt.setInt(5, publicacao.getIdUsuario());
            stmt.setBoolean(6, publicacao.isDenuncia());
            stmt.setInt(7, publicacao.getIdEndereco());
            stmt.setInt(8, id);
            stmt.executeUpdate();
            LogInfo("Publicação atualizado com sucesso!");
        } catch (SQLException e) {
            LogError("Erro ao atualizar publicação: ");
            e.printStackTrace();
        }
    }

    /**
     * Deleta uma publicação pelo ID no banco de dados.
     *
     * @param id o ID da publicação a ser deletada.
     */
    public void DeleteById(int id){
        try (var stmt = conn.prepareStatement("DELETE FROM %s WHERE ID = ?"
                .formatted(TB_NAME))) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            LogInfo("Publicação deletado com sucesso!");
        } catch (SQLException e) {
            LogError("Erro ao deletar publicação: ");
            e.printStackTrace();
        }
    }
}
