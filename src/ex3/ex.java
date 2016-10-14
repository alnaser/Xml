/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author User
 */
public class ex {

    /**
     * @param args the command line arguments
     */
    static Element racine = new Element("diplomes");
    //On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
    static org.jdom.Document document = new Document(racine);

    public static void main(String[] args) {
        // TODO code application logic here
//        Element licences = new Element("licences");
//        Element semestre = new Element("semestre");
//        Element parcour = new Element("parcour");
//        parcour.setAttribute("id", "Info");
//        Element nom = new Element("nom");
//        nom.setText("Algorithme");
//        Element nom2 = new Element("nom");
//        nom2.setText("Javascript");
//        racine.addContent(licences);
//        licences.addContent(semestre);
//        semestre.addContent(parcour);
//        parcour.addContent(nom);
//        parcour.addContent(nom2);
//
//        Element parcour2 = new Element("parcour");
//        parcour2.setAttribute("id", "ModSim");
//        Element nom3 = new Element("nom");
//        nom3.setText("Modélisation et simulation");
//
//        semestre.addContent(parcour2);
//        parcour2.addContent(nom3);
//
//        enregistrer("ex3.xml");
        SAXBuilder sxb = new SAXBuilder();
        try {
// On crée un nouveau document JDOM avec en argument le fichier XML
            document = sxb.build(new File("ex3.xml"));
        } catch (JDOMException | IOException e) {
        }
// On initialise un nouvel élément racine avec l'élément racine du
// document.
        racine = document.getRootElement();

//        searchNbname(semestre);
        searchIdname(racine.getChild("licences").getChild("semestre"));

    }
    
    

    public static void search(Element e) {
//        racine.getDescendants(null)
        List children = e.getChildren("parcour");
        for (Iterator it = children.iterator(); it.hasNext();) {
            Element el = (Element) it.next();
            if (el.getAttribute("id").getValue().equals("Info")) {
                List<Element> children1 = el.getChildren("nom");
                for (Element children11 : children1) {
//                    Element el2=(Element)children11;
                    System.out.println(children11.getText());
                }
            }
        }
    }

    public static void searchNbname(Element e) {
//        racine.getDescendants(null)
        List children = e.getChildren("parcour");
        List<Element> lE=new ArrayList<>();
        for (Iterator it = children.iterator(); it.hasNext();) {
            Element el = (Element) it.next();
            List<Element> children1 = el.getChildren("nom");
            if(children1.size()>1){
                lE.add(el);
            }
        }
        for(Element en:lE){
            System.out.println(en.getAttributeValue("id"));
        }
    }
    public static void searchIdname(Element e) {
//        racine.getDescendants(null)
        class Op{
            String id;
            String nom;
        }
        List<Element> parcours = e.getChildren("parcour");
        List<Element> lE=new ArrayList<>();
        List<String> map=new ArrayList();
        for (Iterator it = parcours.iterator(); it.hasNext();) {
            Element p = (Element) it.next();
            List<Element> names = p.getChildren("nom");
            for(Element n:names){
                String s=n.getText();
                for(Element ep:parcours){
                    List<Element> children = ep.getChildren("nom");
                    for(Element newnom:children){
                        if(newnom.getText().equals(s)&&!ep.getAttributeValue("id").equals(p.getAttributeValue("id")) ){
                            System.out.println(s +":"+ ep.getAttributeValue("id"));
                            
                        }
                    }
                }
            }
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
