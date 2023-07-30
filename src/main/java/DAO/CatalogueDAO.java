package DAO;

import entite.Catalogue;
import entite.I_Catalogue;
import entite.I_Produit;
import entite.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAO implements I_DAOCatalogue {

    Connection cn;
    PreparedStatement pst;
    ResultSet rs;


    public CatalogueDAO(Connection connection) {
        cn = connection;
    }


    @Override
    public List<I_Catalogue> getAll() {
        List<I_Catalogue> allCatalogues = new ArrayList<I_Catalogue>();
        try {
            pst = cn.prepareStatement("SELECT * FROM Catalogues");
            rs = pst.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nomCatalogue");
                I_Catalogue catalogue = new Catalogue(nom);
                allCatalogues.add(catalogue);
            }
            return allCatalogues;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public I_Catalogue getCatalogueByName(String nom) {
        try {
            pst = cn.prepareStatement("SELECT * FROM Catalogues WHERE nomCatalogue = ?");
            pst.setString(1, nom);
            rs = pst.executeQuery();
            if (rs.next()){
                I_Catalogue catalogue = new Catalogue(rs.getString("nomCatalogue"));
                return catalogue;
            }else {
                System.out.println("Erreur: aucun produit trouve avec le nom " + nom);
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erreur: aucun produit trouve avec le nom " + nom);
            return null;
        }
    }

    @Override
    public boolean create(I_Catalogue catalogue) {
        try {
            pst = cn.prepareStatement("CALL nouveauCatalogue(?)");
            pst.setString(1,catalogue.getNomCatalogue());
            rs = pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur: Echec lors de la création du Catalogue");
            return false;
        }
    }

    @Override
    public boolean delete(I_Catalogue catalogue) {
        try {
            pst = cn.prepareStatement("DELETE FROM Produits WHERE nomCatalogue = ?");
            pst.setString(1,catalogue.getNomCatalogue());
            rs=pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur: echec lors de la suppression du produit " + catalogue.getNomCatalogue());
            return false;
        }
    }

    @Override
    public int getNbProduitByName(int idCatalogue) {
        try {
            pst = cn.prepareStatement("SELECT COUNT(*) AS nbProduits FROM Produits WHERE idCatalogue = ?");
            pst.setInt(1,idCatalogue);
            rs = pst.executeQuery();
            if (rs.next()){
                int nbProduit = rs.getInt("nbProduits");
                return nbProduit;
            }else {
                System.out.println("Erreur: aucun produit trouve avec le nom " + idCatalogue);
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int getIdCatalogueByName(String nom){
        try {
            pst = cn.prepareStatement("SELECT idCatalogue FROM Catalogues WHERE nomCatalogue = ?");
            pst.setString(1,nom);
            rs = pst.executeQuery();
            if (rs.next()){
                int idCatalogue = rs.getInt("idCatalogue");
                return idCatalogue;
            }else {
                System.out.println("Erreur: aucun Catalogue trouve avec le nom " + nom);
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(I_Catalogue catalogue) {
        return false;
    }

    @Override
    public void clear() {
        try {
            pst.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
