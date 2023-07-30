package DAO.OracleConnexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionOracle {
    static Connection cn;
    public static Connection getConnexionOracle(){
        try {
            System.out.println("Connexion a la bdd...");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "milhauj", "19092022");
            return cn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur lors de la connexion a la bdd");
            throw new RuntimeException(e);
        }
    }
}
