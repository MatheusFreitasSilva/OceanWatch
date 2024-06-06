package org.example.repositories.daos;

import org.example.entities.dtos.PoluicaoAguasCidades;
import org.example.repositories._BaseRepository;
import org.example.repositories._Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Classe PoluicaoAguasCidadesRepository que fornece métodos para interagir com a tabela de informações de poluição das águas das cidades no banco de dados.
 * Estende a classe _BaseRepository para ter acesso à conexão com o banco de dados.
 * Implementa a interface _Logger para registrar mensagens de log.
 */
public class PoluicaoAguasCidadesRepository extends _BaseRepository implements _Logger<PoluicaoAguasCidadesRepository> {

    public static final String TB_NAME = "POLUICAO_AGUAS_CIDADES";

    // Mapeamento das colunas da tabela de informações de poluição das águas das cidades
    public static final Map<String, String> TB_COLUMNS = Map.of(
            "CIDADE", "CIDADE",
            "REGIAO", "REGIAO",
            "ENTIDADE", "ENTIDADE",
            "QUALIDADE_DO_AR", "QUALIDADE_DO_AR",
            "POLUICAO_DA_AGUA", "POLUICAO_DA_AGUA"
    );

    /**
     * Retorna uma lista de todas as informações de poluição das águas das cidades armazenadas no banco de dados.
     *
     * @return uma lista contendo objetos PoluicaoAguasCidades que representam as informações de poluição das águas das cidades.
     */
    public List<PoluicaoAguasCidades> ReadAll(){
        var informacoesPoluicao = new ArrayList<PoluicaoAguasCidades>();
        try (var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " ORDER BY ID")) {
            var rs = stmt.executeQuery();
            while (rs.next()) {
                informacoesPoluicao.add(new PoluicaoAguasCidades(
                        rs.getInt("ID"),
                        rs.getString("CIDADE"),
                        rs.getString("REGIAO"),
                        rs.getString("ENTIDADE"),
                        rs.getDouble("QUALIDADE_DO_AR"),
                        rs.getDouble("POLUICAO_DA_AGUA")));
            }
        } catch (Exception e) {
            LogError("Erro ao filtrar Banco de Dados: ");
            e.printStackTrace();
        }
        return informacoesPoluicao;
    }

    /**
     * Busca uma informação de poluição das águas das cidades pelo ID no banco de dados.
     *
     * @param id o ID da informação de poluição das águas das cidades a ser buscada.
     * @return um Optional contendo o objeto PoluicaoAguasCidades se encontrado, ou vazio se não encontrado.
     */
    public Optional<PoluicaoAguasCidades> FindById(int id){
        try (var stmt = conn.prepareStatement("SELECT * FROM " + TB_NAME + " WHERE ID = ?")
        ) {
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new PoluicaoAguasCidades(
                        rs.getInt("ID"),
                        rs.getString("CIDADE"),
                        rs.getString("REGIAO"),
                        rs.getString("ENTIDADE"),
                        rs.getDouble("QUALIDADE_DO_AR"),
                        rs.getDouble("POLUICAO_DA_AGUA")));
            }
        } catch (Exception e) {
            LogError("Erro ao filtrar Banco de Dados: ");
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
