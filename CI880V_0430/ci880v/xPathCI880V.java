package ci880v;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathCI880V {
    public static void main(String[] args) {
        try {
            // Fájl beolvasása
            File inputFile = new File("studentCI880V.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // XPath környezet inicializálása
            XPath xPath = XPathFactory.newInstance().newXPath();

            // =====================================================================
            // AZ ÖSSZES LEKÉRDEZÉS LISTÁJA
            // =====================================================================

            // 1) Válassza ki az összes student element, amely a class gyermekei!
            String expression = "/class/student";
            
            // 2) Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "02"!
            // String expression = "/class/student[@id='02']";
            
            // 3) Kiválasztja az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban!
            // String expression = "//student";
            
            // 4) Válassza ki a második student element, amely a class root element gyermeke!
            // String expression = "/class/student[2]";
            
            // 5) Válassza ki az utolsó student elemet, amely a class root element gyermeke!
            // String expression = "/class/student[last()]";
            
            // 6) Válassza ki az utolsó előtti student elemet, amely a class root element gyermeke!
            // String expression = "/class/student[last()-1]";
            
            // 7) Válassza ki az első két student elemet, amelyek a root element gyermekei!
            // String expression = "/class/student[position()<3]";
            
            // 8) Válassza ki class root element összes gyermek elemét!
            // String expression = "/class/*";
            
            // 9) Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútummal!
            // String expression = "//student[@*]";
            
            // 10) Válassza ki a dokumentum összes elemét!
            // String expression = "//*";
            
            // 11) Válassza ki a class root element összes student elemét, amelynél a kor>20!
            // String expression = "/class/student[kor>20]";
            
            // 12) Válassza ki az összes student elem összes keresztnev or vezeteknev csomópontot!
            // String expression = "//student/keresztnev | //student/vezeteknev";

            // Lekérdezés végrehajtása
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            System.out.println("-------------------------");
            System.out.println("Lekérdezés kifejezése: " + expression);
            System.out.println("Találatok száma: " + nodeList.getLength());
            System.out.println("-------------------------");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    // Ha a talált elem egy 'student'
                    if (eElement.getNodeName().equals("student")) {
                        String id = eElement.getAttribute("id");
                        String keresztnev = eElement.getElementsByTagName("keresztnev").item(0).getTextContent();
                        String vezeteknev = eElement.getElementsByTagName("vezeteknev").item(0).getTextContent();
                        String becenev = eElement.getElementsByTagName("becenev").item(0).getTextContent();
                        String kor = eElement.getElementsByTagName("kor").item(0).getTextContent();

                        System.out.println("Student ID: " + id);
                        System.out.println("Keresztnév: " + keresztnev);
                        System.out.println("Vezetéknév: " + vezeteknev);
                        System.out.println("Becenév: " + becenev);
                        System.out.println("Kor: " + kor);
                    } 
                    // Ha a talált elem valami más
                    else {
                        System.out.println("Elem neve: " + eElement.getNodeName());
                        // Csak a közvetlen szöveges tartalmat írjuk ki (ha van)
                        if(eElement.getChildNodes().getLength() == 1) {
                            System.out.println("Értéke: " + eElement.getTextContent());
                        }
                    }
                    System.out.println("-------------------------");
                }
            }

            // XML formában fájlba írás
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("studentCI880V1.xml"));
            transformer.transform(source, result);
            System.out.println("Az XML mentése megtörtént: studentCI880V1.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
