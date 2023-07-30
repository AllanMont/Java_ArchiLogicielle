package Controlleur;

import DAO.Factory.AbstractFactory;
import DAO.I_DAOCatalogue;
import Vue.FenetreAccueil;
import Vue.FenetrePrincipale;
import entite.Catalogue;
import entite.I_Catalogue;

import java.sql.SQLException;
import java.util.List;

public class ControleurCatalogue {

    private static ControleurCatalogue instance; // Instance unique du singleton
    private I_DAOCatalogue bddCatalogue;

    public ControleurCatalogue() {
        bddCatalogue = AbstractFactory.getInstance().createCatalogueDAO();
//        new FenetreAccueil();

    }

    public static ControleurCatalogue getInstance() {
        if (instance == null) {
            instance = new ControleurCatalogue();
        }
        return instance;
    }

    public boolean supprimerCatalogue(String nomCatalogue){
        I_Catalogue catalogueASupprimer = bddCatalogue.getCatalogueByName(nomCatalogue);
        if (bddCatalogue.delete(catalogueASupprimer)){
            return true;
        }else {
            System.out.println("Erreur: Echec lors de la suppression du catalogue " + nomCatalogue);
            return false;
        }
    }

    public List<I_Catalogue> getAllCatalogues() throws SQLException {
        List<I_Catalogue> allCatalogues = bddCatalogue.getAll();
        return allCatalogues;
    }

    public int getNbProduitInCatalogue(int idCatalogue){
       return bddCatalogue.getNbProduitByName(idCatalogue);
    }
    public boolean ajouterCatalogue(String nomCatalogueAjouter){
        I_Catalogue catalogue = new Catalogue(nomCatalogueAjouter);
        if (bddCatalogue.create(catalogue)){
            System.out.println("Ajout du catalogue "+ nomCatalogueAjouter);
            return true;
        }else {
            System.out.println("Erreur: echec lors de l'ajout du catalogue "+ nomCatalogueAjouter);
            return false;
        }
    }

    public int getIdCatalogueByName(String nomCatalogue){
        return bddCatalogue.getIdCatalogueByName(nomCatalogue);
    }

    public I_Catalogue getCatalogue(String nomCatalogue){
        return bddCatalogue.getCatalogueByName(nomCatalogue);
    }

    public void selectionnerCatalogue(String nomCatalogueSelectionner){
        System.out.println("Recupereation du catalogue : " + nomCatalogueSelectionner);
        I_Catalogue catalogue = getCatalogue(nomCatalogueSelectionner);
        catalogue.toString();
        new FenetrePrincipale(catalogue);
    }

    public void clearCatalogue(I_Catalogue catalogue){
        catalogue.clear();
    }

}
