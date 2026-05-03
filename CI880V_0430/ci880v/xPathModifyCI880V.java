package ci880v;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class xPathModifyCI880V {
    public static void main(String[] args) {
        try {
            // Fájl beolvasása
            File inputFile = new File("studentCI880V.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // XPath inicializálása
            XPath xPath = XPathFactory.newInstance().newXPath();

            // Lekérdezzük az id="01" attribútummal rendelkező student elemet
            String expression = "/class/student[@id='01']";
            Node node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);

            if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                // Adatok módosítása
                eElement.getElementsByTagName("keresztnev").item(0).setTextContent("Módosított János");
                eElement.getElementsByTagName("vezeteknev").item(0).setTextContent("Kovács-Új");
                eElement.getElementsByTagName("becenev").item(0).setTextContent("Jancsika");
                eElement.getElementsByTagName("kor").item(0).setTextContent("25");

                // Módosított értékek kiolvasása a konzolos kiíráshoz
                String id = eElement.getAttribute("id");
                String keresztnev = eElement.getElementsByTagName("keresztnev").item(0).getTextContent();
                String vezeteknev = eElement.getElementsByTagName("vezeteknev").item(0).getTextContent();
                String becenev = eElement.getElementsByTagName("becenev").item(0).getTextContent();
                String kor = eElement.getElementsByTagName("kor").item(0).getTextContent();

                System.out.println("-------------------------");
                System.out.println("Módosított hallgató adatai:");
                System.out.println("-------------------------");
                System.out.println("Hallgató ID: " + id);
                System.out.println("Keresztnév: " + keresztnev);
                System.out.println("Vezetéknév: " + vezeteknev);
                System.out.println("Becenév: " + becenev);
                System.out.println("Kor: " + kor);
                System.out.println("-------------------------");
            } else {
                System.out.println("Nem található 01-es azonosítójú hallgató.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
