package com.example.company.dbConnection;

import com.example.company.data.CompanyException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private volatile static ConnectionManager instance;
    private Connection connection;

    private ConnectionManager(String db_url, String user, String password) throws CompanyException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException throwables) {
            throw new CompanyException("PostgresQL Driver is not found! Include it in your lib path: " + throwables.getMessage());
        }
        try {
            connection = DriverManager.getConnection(db_url, user, password);
        } catch (SQLException throwables) {
            throw new CompanyException("Connection failed: " + throwables.getMessage());
        }
    }

    /*private ConnectionManager() {
        try {
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/company");
            if (ds == null) {
                throw new IllegalArgumentException("Data source not found...");
            } else {
                connection = ds.getConnection();
            }
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }*/

    public static ConnectionManager getInstance() throws CompanyException {
        ConnectionManager localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionManager(DBConstant.DB_URL, DBConstant.USER_DB, DBConstant.PASS_DB);
                }
            }
        }
        return localInstance;
    }

    public Connection getConnection() {
        return connection;
    }
}
