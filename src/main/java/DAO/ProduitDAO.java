package DAO;

import entite.I_Produit;
import entite.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO implements I_DAOProduit {
    Connection cn;
    PreparedStatement pst;
    ResultSet rs;

    public ProduitDAO(Connection connexion) {
        cn = connexion;
    }



//    @Override
//    public boolean connexion() {
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut","milhauj","19092022");
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return false;
//    }

    @Override
    public List<I_Produit> getAll() {
        List<I_Produit> allProduits = new ArrayList<I_Produit>();
        try {

            pst = cn.prepareStatement("SELECT * FROM Produits");
            rs = pst.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nomProduit");
                float prixHT = rs.getFloat("PrixHT"); // Récupérer le prix HT du produit depuis le ResultSet
                int quantite = rs.getInt("quantite"); // Récupérer la quantité du produit depuis le ResultSet
                I_Produit produit = new Produit(nom, prixHT, quantite);
                allProduits.add(produit);
            }
            return allProduits;

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


    public List<I_Produit> getProduitOfCatalogueById(int idCatalogue){
        List<I_Produit> allProduits = new ArrayList<I_Produit>();
        try {
            pst = cn.prepareStatement("SELECT * FROM Produits WHERE idCatalogue = ?");
            pst.setInt(1,idCatalogue);
            rs = pst.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nomProduit");
                float prixHT = rs.getFloat("PrixHT"); // Récupérer le prix HT du produit depuis le ResultSet
                int quantite = rs.getInt("quantite"); // Récupérer la quantité du produit depuis le ResultSet
                I_Produit produit = new Produit(nom, prixHT, quantite);
                allProduits.add(produit);
            }
            return allProduits;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean create(I_Produit produit) {
        try {
            pst = cn.prepareStatement("CALL nouveauProduit(?,?,?)");
            pst.setString(1,produit.getNom());
            pst.setDouble(2,produit.getPrixUnitaireHT());
            pst.setInt(3,produit.getQuantite());
            rs = pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur: Echec lors de la création du produit");
            return false;
        }
    }

    @Override
    public I_Produit getProduitByName(String nom) {
        try {
            pst = cn.prepareStatement("SELECT * FROM Produits WHERE nomProduit = ?");
            pst.setString(1, nom);
            rs = pst.executeQuery();
            if (rs.next()){
                I_Produit produit = new Produit(rs.getString("nomProduit"),rs.getDouble("prixHT"),rs.getInt("quantite"));
                return produit;
            }else {
                System.out.println("DANS LE IF Erreur: aucun produit trouve avec le nom " + nom);
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erreur: aucun produit trouve avec le nom " + nom);
            return null;
        }
    }

    @Override
    public boolean delete(I_Produit produit) {
        try {
            pst = cn.prepareStatement("DELETE FROM Produits WHERE nomProduit = ?");
            pst.setString(1,produit.getNom());
            rs=pst.executeQuery();
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur: echec lors de la suppression du produit " + produit.getNom());
            return false;
        }
    }

    @Override
    public boolean update(I_Produit produit) {
        try {
            pst = cn.prepareStatement("UPDATE Produits SET quantite = ? WHERE nomProduit = ?");
            pst.setInt(1,produit.getQuantite());
            pst.setString(2,produit.getNom());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur: Echec lors de la mise à jour du produit");
            return false;
        }
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
