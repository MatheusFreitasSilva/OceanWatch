package org.example.repositories.daos;

import org.example.entities.dtos.ViaCepApiResponse;
import org.example.repositories._BaseRepository;
import org.example.repositories._Logger;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

/**
 * Classe EnderecoRepository que fornece métodos para interagir com a tabela de endereços no banco de dados.
 * Estende a classe _BaseRepository para ter acesso à conexão com o banco de dados.
 * Implementa a interface _Logger para registrar mensagens de log.
 */
public class EnderecoRepository extends _BaseRepository implements _Logger<EnderecoRepository> {
    public static final String TB_NAME = "ENDERECO";

    // Mapeamento das colunas da tabela de endereços
    public static final Map<String, String> TB_COLUMNS = Map.of(
            "CEP", "CEP",
            "LOGRADOURO", "LOGRADOURO",
            "COMPLEMENTO", "COMPLEMENTO",
            "BAIRRO", "BAIRRO",
            "LOCALIDADE", "LOCALIDADE",
            "UF", "UF"
    );

    /**
     * Cria um novo registro de endereço no banco de dados.
     *
     * @param endereco o objeto ViaCepApiResponse contendo as informações do endereço a ser criado.
     */
    public void Create(ViaCepApiResponse endereco){
        try (var stmt = conn.prepareStatement(
                "INSERT INTO %s(%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)"
                        .formatted(TB_NAME,
                                TB_COLUMNS.get("CEP"),
                                TB_COLUMNS.get("LOGRADOURO"),
                                TB_COLUMNS.get("COMPLEMENTO"),
                                TB_COLUMNS.get("BAIRRO"),
                                TB_COLUMNS.get("LOCALIDADE"),
                                TB_COLUMNS.get("UF")))){
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getLogradouro());
            stmt.setString(3, endereco.getComplemento());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getLocalidade());
            stmt.setString(6, endereco.getUf());
            stmt.executeUpdate();
            LogInfo("Endereço criado com sucesso!");
        } catch (SQLException e){
            LogError("Erro ao adicionar Endereço: ");
            e.printStackTrace();
        }
    }

    /**
     * Busca um registro de endereço pelo CEP no banco de dados.
     *
     * @param cep o CEP do endereço a ser buscado.
     * @return um Optional contendo o objeto ViaCepApiResponse se encontrado, ou vazio se não encontrado.
     */
    public Optional<ViaCepApiResponse> FindByCep(String cep){
        try (var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE %s = ?"
                .formatted(TB_COLUMNS.get("CEP")))
        ) {
            stmt.setString(1, cep);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new ViaCepApiResponse(
                        rs.getInt("ID"),
                        rs.getString("CEP"),
                        rs.getString("LOGRADOURO"),
                        rs.getString("COMPLEMENTO"),
                        rs.getString("BAIRRO"),
                        rs.getString("LOCALIDADE"),
                        rs.getString("UF")
                        ));
            }
        } catch (Exception e) {
            LogError("Erro ao filtrar Banco de Dados: ");
            e.printStackTrace();
        }

        return Optional.empty();
    }

    /**
     * Obtém o ID de um endereço pelo seu CEP.
     *
     * @param cep O Cep do endereço.
     * @return Resposta com o ID da empresa no formato JSON se encontrado, caso contrário, retorna NOT_FOUND.
     */
    public int FindIdByCep(String cep) {
        Optional<ViaCepApiResponse> enderecos = FindByCep(cep);

        Optional<Integer> idOptional = enderecos.stream()
                .map(ViaCepApiResponse::getId)
                .findFirst();

        return idOptional.orElse(0);
    }

    /**
     * Busca um endereço pelo ID no banco de dados.
     *
     * @param id o ID do endereço a ser buscado.
     * @return um Optional contendo o objeto Usuario se encontrado, ou vazio se não encontrado.
     */
    public Optional<ViaCepApiResponse> FindById(int id) {
        try (var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?")
        ) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new ViaCepApiResponse(
                        rs.getInt("ID"),
                        rs.getString("CEP"),
                        rs.getString("LOGRADOURO"),
                        rs.getString("COMPLEMENTO"),
                        rs.getString("BAIRRO"),
                        rs.getString("LOCALIDADE"),
                        rs.getString("UF")
                ));
            }
        } catch (Exception e) {
            LogError("Erro ao filtrar Banco de Dados: ");
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
