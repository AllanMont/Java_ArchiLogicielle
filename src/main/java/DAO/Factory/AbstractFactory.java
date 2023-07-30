package DAO.Factory;

import DAO.I_DAOCatalogue;
import DAO.OracleConnexion.ConnexionOracle;
import DAO.I_DAOProduit;

import java.sql.Connection;

public abstract class AbstractFactory {
    private static AbstractFactory instance;
    private static Connection cn;
    public AbstractFactory() {

    }

    public static AbstractFactory getInstance(){
        if (instance==null)
            cn = ConnexionOracle.getConnexionOracle();
            instance = new DAOFactory(cn);
        return instance;
    }

    public abstract I_DAOProduit createProduitDAO();
    public abstract I_DAOCatalogue createCatalogueDAO();
}
