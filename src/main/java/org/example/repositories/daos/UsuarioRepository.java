package org.example.repositories.daos;

import org.example.entities.dtos.Usuario;
import org.example.repositories._BaseRepository;
import org.example.repositories._Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Classe UsuarioRepository que fornece métodos para interagir com a tabela de usuários no banco de dados.
 * Estende a classe _BaseRepository para ter acesso à conexão com o banco de dados.
 * Implementa a interface _Logger para registrar mensagens de log.
 */
public class UsuarioRepository extends _BaseRepository implements _Logger<UsuarioRepository> {
    public static final String TB_NAME = "USUARIO";

    // Mapeamento das colunas da tabela de usuários
    public static final Map<String, String> TB_COLUMNS = Map.of(
            "NOME", "NOME",
            "USUARIO", "USUARIO",
            "EMAIL", "EMAIL",
            "SENHA", "SENHA"
    );

    /**
     * Cria um novo usuário no banco de dados.
     *
     * @param usuario o objeto Usuario a ser criado.
     */
    public void Create(Usuario usuario){
        try (var stmt = conn.prepareStatement(
                "INSERT INTO %s(%s, %s, %s, %s) VALUES (?, ?, ?, ?)"
                        .formatted(TB_NAME,
                                TB_COLUMNS.get("NOME"),
                                TB_COLUMNS.get("USUARIO"),
                                TB_COLUMNS.get("EMAIL"),
                                TB_COLUMNS.get("SENHA")))){
                    stmt.setString(1, usuario.getNome());
                    stmt.setString(2, usuario.getUsuario());
                    stmt.setString(3, usuario.getEmail());
                    stmt.setString(4, usuario.getSenha());
                    stmt.executeUpdate();
                    LogInfo("Usuario criado com sucesso!");
        } catch (SQLException e){
            LogError("Erro ao adicionar Usuario: ");
            e.printStackTrace();
        }
    }

    /**
     * Retorna uma lista de todos os usuários armazenados no banco de dados.
     *
     * @return uma lista contendo objetos Usuario que representam os usuários.
     */
    public List<Usuario> ReadAll(){
        var usuarios = new ArrayList<Usuario>();
        try (var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY ID")) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("USUARIO"),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")));
            }
        } catch (Exception e) {
            LogError("Erro ao filtrar Banco de Dados: ");
            e.printStackTrace();
        }
        return usuarios;
    }

    /**
     * Busca um usuário pelo ID no banco de dados.
     *
     * @param id o ID do usuário a ser buscado.
     * @return um Optional contendo o objeto Usuario se encontrado, ou vazio se não encontrado.
     */
    public Optional<Usuario> FindById(int id){
        try (var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?")
        ) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Usuario(
                        rs.getInt("ID"),
                        rs.getString("NOME"),
                        rs.getString("USUARIO"),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")));
            }
        } catch (Exception e) {
            LogError("Erro ao filtrar Banco de Dados: ");
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * Atualiza um usuário no banco de dados.
     *
     * @param id       o ID do usuário a ser atualizado.
     * @param usuario  o objeto Usuario com os novos dados.
     */
    public void Update(int id, Usuario usuario) {
        try (var stmt = conn.prepareStatement(
                "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ? WHERE ID = ?"
                        .formatted(TB_NAME,
                                TB_COLUMNS.get("NOME"),
                                TB_COLUMNS.get("USUARIO"),
                                TB_COLUMNS.get("EMAIL"),
                                TB_COLUMNS.get("SENHA")))) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getUsuario());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setInt(5, id);
            stmt.executeUpdate();
            LogInfo("Usuario atualizado com sucesso!");
        } catch (SQLException e) {
            LogError("Erro ao atualizar cliente: ");
            e.printStackTrace();
        }
    }

    /**
     * Deleta um usuário pelo ID no banco de dados.
     *
     * @param id o ID do usuário a ser deletado.
     */
    public void DeleteById(int id){
        try (var stmt = conn.prepareStatement("DELETE FROM %s WHERE ID = ?"
                .formatted(TB_NAME))) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            LogInfo("Usuario deletado com sucesso!");
        } catch (SQLException e) {
            LogError("Erro ao deletar cliente: ");
            e.printStackTrace();
        }
    }
}
