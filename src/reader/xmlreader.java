/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author User
 */
public class xmlreader {

    /**
     * @param args the command line arguments
     */
    static org.jdom.Document document;
    static Element racine;

    public static void main(String[] args) {
        // TODO code application logic here
        // On crée une instance de SAXBuilder
        SAXBuilder sxb = new SAXBuilder();
        try {
// On crée un nouveau document JDOM avec en argument le fichier XML
            document = sxb.build(new File("ex2.xml"));
        } catch (JDOMException | IOException e) {
        }
// On initialise un nouvel élément racine avec l'élément racine du
// document.
        racine = document.getRootElement();
        long time1 = new Date().getTime();
        showClassesHasStudents();
        System.out.println(new Date().getTime() - time1 + " milliseconds");
    }

    static void afficheNom() {
        List listEtudiants = racine.getChildren("etudiant");
        Iterator i = listEtudiants.iterator();
        while (i.hasNext()) {
            Element courant = (Element) i.next();
            System.out.println(courant.getChild("nom").getText());
        }
    }

    static void afficheClasse() {
        List listEtudiants = racine.getChildren("etudiant");
        Iterator i = listEtudiants.iterator();

        while (i.hasNext()) {
            Element courant = (Element) i.next();
            if (courant.getChild("nom").getText().equals("Ben Saied")) {
                System.out.println("a classe de Ben Saied est: " + courant.getAttributeValue("classe"));
            }
        }
    }

    static void showClassesHasStudents() {

        List listEtudiants = racine.getChildren("etudiant");

        Iterator i = listEtudiants.iterator();
        List<String> listStd = new ArrayList<>();

        while (i.hasNext()) {
            Element courant = (Element) i.next();
            String val = courant.getAttributeValue("classe");
            int s = 0;
            for (Object student : listEtudiants) {
                if (!listStd.contains(val) && ((Element) student).getAttributeValue("classe").equals(val)) {
                    s++;
                    if (s > 1) {
                        System.out.println(val + " contained " + listStd.contains(val));
                        listStd.add(val);
                    }
                }
            }
        }
        for (String str : listStd) {
            System.out.println("La classe :" + str);
        }

    }

}
