/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadom;

import java.io.FileOutputStream;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author User
 */
public class JavaDOM {

    /**
     * @param args the command line arguments
     */
    static Element racine = new Element("personnes");
    //On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
    static org.jdom.Document document = new Document(racine);

    public static void main(String[] args) {
        Element etudiant = new Element("etudiant");
        racine.addContent(etudiant);
        //On crée un Attribut classe et on l'ajoute à etudiant grâce à la méthode setAttribute
        Attribute classe = new Attribute("classe", "IG3");
        etudiant.setAttribute(classe);
        //On crée un élément nom, on lui assigne du texte
        Element nom = new Element("nom");
        Element preNom = new Element("Prenom");
        Element cin = new Element("CIN");
        cin.setAttribute("num", "08888909");
        cin.setAttribute("date", "13/09/1999");
        nom.setText("alnaser");
        preNom.setText("alnaser");
        
        etudiant.addContent(nom);
        etudiant.addContent(preNom);
        etudiant.addContent(cin);
        affiche();
        enregistrer("ex1.xml");
    }

    static void affiche() {
        try {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, System.out);
        } catch (java.io.IOException e) {
        }
    }

    static void enregistrer(String fichier) {
        try {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileOutputStream(fichier));
        } catch (java.io.IOException e) {
        }
    }
}
