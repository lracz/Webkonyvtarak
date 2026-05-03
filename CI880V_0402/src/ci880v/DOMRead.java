package ci880v;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMRead {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {

        File xmlFile = new File("XMLCI880V.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        System.out.println("----------------------------");

        NodeList etteremList = doc.getElementsByTagName("Etterem");

        for (int i = 0; i < etteremList.getLength(); i++) {
            Node nNode = etteremList.item(i);
            System.out.println("\nCurrent element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String id = elem.getAttribute("id");


                Node node1 = elem.getElementsByTagName("Nev").item(0);
                String nev = node1.getTextContent();


                Node cimNode = elem.getElementsByTagName("Cim").item(0);
                Element cimElem = (Element) cimNode;

                Node node2 = cimElem.getElementsByTagName("Varos").item(0);
                String city = node2.getTextContent();

                Node node3 = cimElem.getElementsByTagName("Utca").item(0);
                String street = node3.getTextContent();

                Node node4 = cimElem.getElementsByTagName("Hazszam").item(0);
                String number = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("Csillag").item(0);
                String stars = node5.getTextContent();

                String adr = city + ", " + street + " " + number + ".";

                System.out.println("Étterem ID: " + id);
                System.out.println("Név: " + nev);
                System.out.println("Cím: " + adr);
                System.out.println("Csillag: " + stars);
            }
        }


        NodeList foszakacsList = doc.getElementsByTagName("Foszakacs");

        for (int i = 0; i < foszakacsList.getLength(); i++) {
            Node nNode = foszakacsList.item(i);

            System.out.println("\nCurrent element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String fkod = elem.getAttribute("id");

                String etteremId = findParentEtteremId(elem);

                String work = "Ez a főszakács dolgozik az " + etteremId + " étteremben.";

                Node node1 = elem.getElementsByTagName("Nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("Eletkor").item(0);
                String age = node2.getTextContent();

                String edu = "";
                NodeList vegzettsegList = elem.getElementsByTagName("Vegzettseg");
                for (int j = 0; j < vegzettsegList.getLength(); j++) {
                    Node node3 = vegzettsegList.item(j);
                    edu += node3.getTextContent();
                    if (j < vegzettsegList.getLength() - 1) {
                        edu += ", ";
                    }
                }

                System.out.println("Főszakács ID: " + fkod);
                System.out.println("Nev: " + name);
                System.out.println("Eletkor: " + age);
                System.out.println("Végzettségek: " + edu);
                System.out.println(work);
            }
        }
    }

    private static String findParentEtteremId(Node foszakacsNode) {
        Node parent = foszakacsNode.getParentNode();
        while (parent != null) {
            if (parent.getNodeType() == Node.ELEMENT_NODE && parent.getNodeName().equals("Etterem")) {
                Element e = (Element) parent;
                return e.getAttribute("id");
            }
            parent = parent.getParentNode();
        }
        return "?";
    }
}