package entite;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

public class Catalogue implements I_Catalogue{
    private List<I_Produit> lesProduits;
    private String nomCatalogue;
    public Catalogue(String nom) {
        nomCatalogue = nom;
        lesProduits = new ArrayList<>();
    }

    @Override
    public boolean addProduit(I_Produit produit){
        if ((produit==null)||(produit.getNom().equals(""))||(produit.getPrixUnitaireHT()<=0)||(produit.getQuantite()<0)){
            return false;
        }
        String nomProduit = produit.getNom().replaceAll("\\s+", "");
        for (I_Produit existant : lesProduits) {
            if (nomProduit.equals(existant.getNom().replaceAll("\\s+", ""))) {
                return false;
            }
        }
        lesProduits.add(produit);
        return true;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        Produit p = new Produit(nom,prix,qte);
        return addProduit(p);
    }

    @Override
    public int addProduits(List<I_Produit> l) {
        int compteur=0;
        if (l==null || l.size()==0 || l.contains(null) ){
            return 0;
        }else {
            for (I_Produit produit : l) {
                if (addProduit(produit)){
                    compteur++;
                }
            }
        }
        return compteur;
    }


    @Override
    public boolean removeProduit(String nom) {
        if (nom != null && !nom.equals("") && lesProduits.size() != 0) {
            for (I_Produit produit : lesProduits) {
                if (produit.getNom().equals(nom)) {
                    lesProduits.remove(produit);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        if (nomProduit.equals("")||qteAchetee<=0){
            return false;
        }
        for (I_Produit produit : lesProduits) {
            if (produit.getNom().equals(nomProduit)) {
                produit.ajouter(qteAchetee);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        if (!nomProduit.equals("") && qteVendue > 0) {
            for (I_Produit produit : lesProduits) {
                if (produit.getNom().equals(nomProduit)) {
                    if (produit.getQuantite() < qteVendue) {
                        return false;
                    } else {
                        produit.enlever(qteVendue);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String[] getNomProduits() {
        List<String> temp = new ArrayList<>();
        for (I_Produit produit : lesProduits) {
            temp.add(produit.getNom().trim().replaceAll("\t", " "));
        }
        sort(temp);
        String[] arr = new String[temp.size()];
        temp.toArray(arr);
        return arr;
    }


    public String getNomCatalogue() {
        return nomCatalogue;
    }

    @Override
    public double getMontantTotalTTC() {
        double montantTotal = 0;
        for (I_Produit produit :
                lesProduits) {
            montantTotal = montantTotal + produit.getPrixStockTTC();
        }

        return Math.round(montantTotal * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");

        String produitString = "";
        for (I_Produit produit: lesProduits) {
            produitString += produit.toString();
        }

        produitString += "\n" + "Montant total TTC du stock : "+df.format(getMontantTotalTTC())+" €";
        return produitString;
    }



    @Override
    public void clear() {
        lesProduits.clear();
    }

}
