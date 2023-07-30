package Controlleur;

import DAO.Factory.AbstractFactory;
import DAO.I_DAOProduit;
import Vue.FenetrePrincipale;
import entite.Catalogue;
import entite.I_Catalogue;
import entite.I_Produit;
import entite.Produit;


import java.sql.SQLException;
import java.util.List;


public class ControleurProduit {

    private static ControleurProduit instance; // Instance unique du singleton
    private I_DAOProduit bddProduit;
    private I_Catalogue catalogue;

    public ControleurProduit(I_Catalogue _catalogue) {
        bddProduit = AbstractFactory.getInstance().createProduitDAO();
        catalogue = _catalogue;
//        this.bddProduit = .getInstance().createProduitORM();
//        this.bddProduit = ProduitFactory.getInstance().createProduitXML();
        new FenetrePrincipale(catalogue);
        chargerCatalogue(catalogue);
    }


    public static ControleurProduit getInstance(I_Catalogue catalogue) {
        if (instance == null) {
            instance = new ControleurProduit(catalogue);
        }
        return instance;
    }

    public void chargerCatalogue(I_Catalogue catalogue){
        System.out.println("Chargement de tout les produits...");
        int idCatalogue = bddProduit.getIdCatalogueByName(catalogue.getNomCatalogue());
        List<I_Produit> allProduits = bddProduit.getProduitOfCatalogueById(idCatalogue);
        catalogue.addProduits(allProduits);
        System.out.println(catalogue.toString());
    }

    public boolean supprimerProduit(String nomProd){
        I_Produit produitASupprimer = bddProduit.getProduitByName(nomProd);
        if (bddProduit.delete(produitASupprimer)){
            catalogue.removeProduit(produitASupprimer.getNom());
            return true;
        }else {
            System.out.println("Erreur: Echec lors de la suppression du produit " + nomProd);
            return false;
        }
    }

    public String getAllProduits() throws SQLException {
        List<I_Produit> allProduits = bddProduit.getAll();
        return allProduits.toString();
    }

    public boolean ajouterProduit(String nomProd, double prixHT, int qteProd){
        I_Produit produit = new Produit(nomProd,prixHT,qteProd);
        bddProduit.create(produit);
        if (bddProduit.create(produit)){
            catalogue.addProduit(produit);
            System.out.println("Ajout du produit "+ nomProd + " " + prixHT + " " + qteProd);
            return true;
        }else {
            System.out.println("Erreur: echec lors de l'ajout du produit "+ nomProd);
            return false;
        }
    }

    public boolean achatProduit(String nomProduit, int qteAchete){
        I_Produit produitAModifier = bddProduit.getProduitByName(nomProduit);
        I_Produit produitModifier = new Produit(produitAModifier.getNom(),produitAModifier.getPrixUnitaireHT(),produitAModifier.getQuantite()+qteAchete);
        if (bddProduit.update(produitModifier)){
            return true;
        }else {
            System.out.println("Erreur: echec lors  de l'ajout à la quantité du produit " + nomProduit);
            return false;
        }
    }

    public boolean venteProduit(String nomProduit, int qteVendue){
        I_Produit produitAModifier = bddProduit.getProduitByName(nomProduit);
        I_Produit produitModifier = new Produit(produitAModifier.getNom(),produitAModifier.getPrixUnitaireHT(),produitAModifier.getQuantite()-qteVendue);
        if (bddProduit.update(produitModifier)){
            return true;
        }else {
            System.out.println("Erreur: echec lors  de la soustraction à la quantité du produit " + nomProduit);
            return false;
        }
    }

}
