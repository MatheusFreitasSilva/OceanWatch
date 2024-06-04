package org.example.repositories;

import org.example.infrastructure.OracleDBConfiguration;

import java.sql.Connection;

public abstract class _BaseRepository {
    public static Connection conn = new OracleDBConfiguration().getConnection();
}
