package DAO.Factory;

import DAO.CatalogueDAO;
import DAO.I_DAOCatalogue;
import DAO.I_DAOProduit;
import DAO.ProduitDAO;

import java.sql.Connection;

public class DAOFactory extends AbstractFactory {
    Connection cn;

    public DAOFactory(Connection connexion) {
        cn = connexion;
    }

    @Override
    public I_DAOProduit createProduitDAO() {
        return new ProduitDAO(cn);
    }

    @Override
    public I_DAOCatalogue createCatalogueDAO() {
        return new CatalogueDAO(cn);
    }
}
