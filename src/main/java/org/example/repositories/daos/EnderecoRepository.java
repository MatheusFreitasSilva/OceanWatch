package org.example.repositories.daos;

import org.example.entities.dtos.ViaCepApiResponse;
import org.example.repositories._BaseRepository;
import org.example.repositories._Logger;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class EnderecoRepository extends _BaseRepository implements _Logger<EnderecoRepository> {
    public static final String TB_NAME = "ENDERECO";

    public static final Map<String, String> TB_COLUMNS = Map.of(
            "CEP", "CEP",
            "LOGRADOURO", "LOGRADOURO",
            "COMPLEMENTO", "COMPLEMENTO",
            "BAIRRO", "BAIRRO",
            "LOCALIDADE", "LOCALIDADE",
            "UF", "UF"
    );

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
            stmt.setString(2, endereco.getLocalidade());
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
}
