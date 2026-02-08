package MODELO.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOAbstrato implements AutoCloseable {

    private final String url = "jdbc:mysql://10.225.0.4/20231164010027_CONSUTORIA";
    private final String usuario = "20231164010027";
    private final String senha = "20231164010027+lima";

    protected Connection conexao;

    public DAOAbstrato() {
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}