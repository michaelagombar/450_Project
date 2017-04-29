package xml;
 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class invxmlreader {

    public static void main(String[] args) {

        try {
            String fileName = "inventory.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList facInfo = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < facInfo.getLength(); i++) {
                if (facInfo.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                
                String entryName = facInfo.item(i).getNodeName();
                if (!entryName.equals("Item")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }
                
                // Get a node attribute
                NamedNodeMap aMap = facInfo.item(i).getAttributes();
      

                // Get a named nodes
                Element elem = (Element) facInfo.item(i);
                String invID = elem.getElementsByTagName("id").item(0).getTextContent();
                String invPrice = elem.getElementsByTagName("Price").item(0).getTextContent();
  

                // Get all nodes named "LINK" - there can be 0 or more
                ArrayList<String> linkDescriptions = new ArrayList<>();
                NodeList linkList = elem.getElementsByTagName("Item");
                for (int j = 0; j < linkList.getLength(); j++) {
                    if (linkList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = linkList.item(j).getNodeName();
                    if (!entryName.equals("Item")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

            
                }
 

                // Here I would create a Store object using the data I just loaded from the XML
                System.out.println(invID + " : " + "$"+ invPrice);
               
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
    }

}