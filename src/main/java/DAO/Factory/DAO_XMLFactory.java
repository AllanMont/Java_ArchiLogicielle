package DAO.Factory;

import DAO.I_DAOCatalogue;
import DAO.I_DAOProduit;
import DAO.ProduitDAO_Produit_XMLAdaptateur;

public class DAO_XMLFactory extends AbstractFactory {

    @Override
    public I_DAOProduit createProduitDAO() {
        return new ProduitDAO_Produit_XMLAdaptateur();
    }

    @Override
    public I_DAOCatalogue createCatalogueDAO() {
        return null;
    }
}
