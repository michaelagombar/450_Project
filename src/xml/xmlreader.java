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

public class xmlreader {

    public static void main(String[] args) {

        try {
            String fileName = "facMgr.xml";

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
                if (!entryName.equals("Facility")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return;
                }
                
                // Get a node attribute
                NamedNodeMap aMap = facInfo.item(i).getAttributes();
                

                // Get a named nodes
                Element elem = (Element) facInfo.item(i);
                Element elem1 = (Element) facInfo.item(i);
                String facName = elem.getElementsByTagName("Name").item(0).getTextContent();
                String facRate = elem.getElementsByTagName("Rate").item(0).getTextContent();
                String facRTD = elem.getElementsByTagName("rtd").item(0).getTextContent();
              //  String facLinks = elem.getElementsByTagName("Links").item(0).getTextContent();
               // String facItem = elem.getElementsByTagName("InvItem").item(0).getTextContent();

                // Get all nodes named "LINK" - there can be 0 or more
                ArrayList<String> linkDescriptions = new ArrayList<>();
                NodeList linkList = elem.getElementsByTagName("Links");
                for (int j = 0; j < linkList.getLength(); j++) {
                    if (linkList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = linkList.item(j).getNodeName();
                    if (!entryName.equals("Links")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    // Get some named nodes from links
                    elem = (Element) linkList.item(j);
                    String linkCity = elem.getElementsByTagName("City").item(0).getTextContent();
                    String linkDistance = elem.getElementsByTagName("Distance").item(0).getTextContent();
                    // Create a string summary of the book
                    linkDescriptions.add("City name: " + linkCity + " | Distance: " + linkDistance);
                }
                
                
                // get all nodes names "ITEM"
                ArrayList<String> itemDescription = new ArrayList<>();
                NodeList itemList = elem1.getElementsByTagName("InvItem");
                for (int j = 0; j < itemList.getLength(); j++) {
                    if (itemList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = itemList.item(j).getNodeName();
                    if (!entryName.equals("InvItem")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return;
                    }

                    // Get some named nodes from Item
                    elem1 = (Element) itemList.item(j);
                    String itemID = elem1.getElementsByTagName("id").item(0).getTextContent();
                    String itemQty = elem1.getElementsByTagName("qty").item(0).getTextContent();
                    // Create a string summary of the book
                    itemDescription.add("ID of Item: " + itemID + " amount of item: " + itemQty + "\n");
                }

                // Here I would create a Store object using the data I just loaded from the XML
                System.out.println(facName + "\n" + "-------" + "\n" + 
                "Rate Per Day: " + facRTD + "\n" +
                "Cost Per Day: " + facRate + "\n" + "\n" +
                "Direct Links" + "\n" 
                + linkDescriptions + "\n" + itemDescription +"\n");
               
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
    }

}