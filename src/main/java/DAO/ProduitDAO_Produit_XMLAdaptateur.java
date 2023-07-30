package DAO;

import entite.I_Produit;

import java.util.List;

public class ProduitDAO_Produit_XMLAdaptateur implements I_DAOProduit {
    private ProduitDAO_XML produitDAOXml;
    public ProduitDAO_Produit_XMLAdaptateur() {
        produitDAOXml = new ProduitDAO_XML();
    }

    @Override
    public List<I_Produit> getAll() {
        return produitDAOXml.lireTous();
    }

    @Override
    public boolean create(I_Produit produit) {
        return produitDAOXml.creer(produit);
    }

    @Override
    public I_Produit getProduitByName(String nom) {
        return produitDAOXml.lire(nom);
    }

    @Override
    public boolean delete(I_Produit produit) {
        return produitDAOXml.supprimer(produit);
    }


    @Override
    public boolean update(I_Produit produit) {
        return produitDAOXml.maj(produit);
    }

    @Override
    public int getIdCatalogueByName(String nom) {
        return 0;
    }

    @Override
    public List<I_Produit> getProduitOfCatalogueById(int idCatalogue) {
        return null;
    }

    @Override
    public void clear() {}
}
