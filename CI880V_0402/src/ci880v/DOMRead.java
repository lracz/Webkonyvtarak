package ci880v;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class DOMRead {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {


        File xmlFile = new File("XMLCI880V.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        System.out.println("----------------------------");


        NodeList nList = doc.getElementsByTagName("Etterem");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            System.out.println("\nCurrent element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String id = elem.getAttribute("id");

                Node node1 = elem.getElementsByTagName("Nev").item(0);
                String nev = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("Varos").item(0);
                String city = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("Utca").item(0);
                String street = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("Hazszam").item(0);
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
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            System.out.println("\nCurrent element: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String id = elem.getAttribute("fkod");
                String eid = elem.getAttribute("e_f");

                String work = "Ez a főszakacs dolgozik az " + eid + " étteremben.";

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("eletkor").item(0);
                String age = node2.getTextContent();

                Node node3;
                String edu = "";
                for (int j = 0; j < elem.getElementsByTagName("vegzettseg").getLength(); j++) {
                    node3 = elem.getElementsByTagName("vegzettseg").item(j);
                    if (j == elem.getElementsByTagName("vegzettseg").getLength() - 1) {
                        edu += node3.getTextContent();
                    } else {
                        edu += node3.getTextContent() + ", ";
                    }
                }
                System.out.println("Főszakács ID: "+ id);
                System.out.println("Nev: " + name);
                System.out.println("Eletkor: " + age);
                System.out.println("Végzettségek: " + edu);
                System.out.println(work);

            }

        }



        nList = doc.getElementsByTagName("Foszakacs");
    }
}