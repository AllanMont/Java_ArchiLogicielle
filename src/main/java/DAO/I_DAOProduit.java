package DAO;


import entite.I_Produit;

import java.util.List;

public interface I_DAOProduit {
//    public abstract boolean connexion();
    public abstract List<I_Produit> getAll();
    public abstract I_Produit getProduitByName(String nom);
    public abstract boolean create(I_Produit produit);
    public abstract boolean delete(I_Produit produit);
    public abstract boolean update(I_Produit produit);
    public abstract int getIdCatalogueByName(String nom);
    public abstract List<I_Produit> getProduitOfCatalogueById(int idCatalogue);
    public abstract void clear();
}
