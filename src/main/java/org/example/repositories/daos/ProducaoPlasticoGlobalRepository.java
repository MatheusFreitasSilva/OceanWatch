package org.example.repositories.daos;

import org.example.entities.dtos.ProducaoPlasticoGlobal;
import org.example.repositories._BaseRepository;
import org.example.repositories._Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Classe ProducaoPlasticoGlobalRepository que fornece métodos para interagir com a tabela de informações de produção global de plástico no banco de dados.
 * Estende a classe _BaseRepository para ter acesso à conexão com o banco de dados.
 * Implementa a interface _Logger para registrar mensagens de log.
 */
public class ProducaoPlasticoGlobalRepository extends _BaseRepository implements _Logger<ProducaoPlasticoGlobalRepository> {
    public static final String TB_NAME = "PRODUCAO_PLASTICO_GLOBAL";

    // Mapeamento das colunas da tabela de informações de produção global de plástico
    public static final Map<String, String> TB_COLUMNS = Map.of(
            "ENTIDADE", "ENTIDADE",
            "ANO", "ANO",
            "PRODUCAO_ANUAL_PLASTICO", "PRODUCAO_ANUAL_PLASTICO"
    );

    /**
     * Retorna uma lista de todas as informações de produção global de plástico armazenadas no banco de dados.
     *
     * @return uma lista contendo objetos ProducaoPlasticoGlobal que representam as informações de produção global de plástico.
     */
    public List<ProducaoPlasticoGlobal> ReadAll(){
        var informacoesProducao = new ArrayList<ProducaoPlasticoGlobal>();
        try (var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY ID")) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                informacoesProducao.add(new ProducaoPlasticoGlobal(
                        rs.getInt("ID"),
                        rs.getString("ENTIDADE"),
                        rs.getString("ANO"),
                        rs.getDouble("PRODUCAO_ANUAL_PLASTICO")));
            }
        } catch (Exception e) {
            LogError("Erro ao filtrar Banco de Dados: ");
            e.printStackTrace();
        }
        return informacoesProducao;
    }

    /**
     * Busca uma informação de produção global de plástico pelo ID no banco de dados.
     *
     * @param id o ID da informação de produção global de plástico a ser buscada.
     * @return um Optional contendo o objeto ProducaoPlasticoGlobal se encontrado, ou vazio se não encontrado.
     */
    public Optional<ProducaoPlasticoGlobal> FindById(int id){
        try (var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?")
        ) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new ProducaoPlasticoGlobal(
                        rs.getInt("ID"),
                        rs.getString("ENTIDADE"),
                        rs.getString("ANO"),
                        rs.getDouble("PRODUCAO_ANUAL_PLASTICO")));
            }
        } catch (Exception e) {
            LogError("Erro ao filtrar Banco de Dados: ");
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
