package org.example.repositories.daos;

import org.example.entities.dtos.Usuario;
import org.example.repositories._BaseRepository;
import org.example.repositories._Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsuarioRepository extends _BaseRepository implements _Logger<UsuarioRepository> {
    public static final String TB_NAME = "USUARIO";

    public static final Map<String, String> TB_COLUMNS = Map.of(
            "NOME", "NOME",
            "USUARIO", "USUARIO",
            "EMAIL", "EMAIL",
            "SENHA", "SENHA"
    );

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
