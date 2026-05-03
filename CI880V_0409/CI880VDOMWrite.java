package ci880v;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CI880VDOMWrite {

    public static void main(String[] args) {

        try {
            File inputFile = new File("XMLCI880V.xml");
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(inputFile);

            Node etterem = doc.getElementsByTagName("Etterem").item(0);

            // Új elem hozzáadása
            Element ujSzakacs = doc.createElement("Szakacs");
            ujSzakacs.setAttribute("id", "sz002");

            Element nev = doc.createElement("Nev");
            nev.appendChild(doc.createTextNode("Teszt Szakács CI880V"));
            ujSzakacs.appendChild(nev);

            etterem.appendChild(ujSzakacs);

            // Elem módosítása
            NodeList foszakacsok = doc.getElementsByTagName("Foszakacs");
            if (foszakacsok.getLength() > 0) {
                Node elsoFoszakacs = foszakacsok.item(0);
                NodeList gyerekek = elsoFoszakacs.getChildNodes();
                for(int i=0; i<gyerekek.getLength(); i++) {
                    Node n = gyerekek.item(i);
                    if(n.getNodeName().equals("Eletkor")) {
                        n.setTextContent("45"); // Módosítjuk a kort
                    }
                }
            }

            // Mentés új fájlba
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(System.out);
            StreamResult fileResult = new StreamResult(new File("XMLCI880V_modositott.xml"));

            transformer.transform(source, consoleResult);
            transformer.transform(source, fileResult);

            System.out.println("\nXML sikeresen módosítva és elmentve.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
