package DAO;

import entite.I_Catalogue;
import entite.I_Produit;

import java.util.List;

public interface I_DAOCatalogue {
    //    public abstract boolean connexion();
    public abstract List<I_Catalogue> getAll();
    public abstract I_Catalogue getCatalogueByName(String nom);

    public abstract boolean create(I_Catalogue catalogue);
    public abstract boolean delete(I_Catalogue catalogue);
    public abstract int getNbProduitByName(int idCatalogue);

    public abstract int getIdCatalogueByName(String nom);
    public abstract boolean update(I_Catalogue catalogue);
    public abstract void clear();
}
