package org.example.repositories;

import org.example.infrastructure.OracleDBConfiguration;

import java.sql.Connection;

/**
 * Classe abstrata para configuração do banco de dados, possibilitando que as classes de repositório
 * herdem a configuração do banco de dados.
 */
public abstract class _BaseRepository {
    public static Connection conn = new OracleDBConfiguration().getConnection();
}
