package org.example.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() throws SQLException{
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/projeto_01",
                "root",
                "filho270"
        );
    }
}
