package biz.gelicon.dub.test;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * Разбор xml
 */
public class XMLParseTest {

    /**
     * Разбор xml из строки
     */
    public void test1() {
        String xml = "<data>\n"
                + "    <field name=\"req_id\" value=\"602\"/>\n"
                + "    <field name=\"requesttype_id\" value=\"10001\"/>\n"
                + "    <field name=\"redirect\" value=\"newrequesttp\"/>\n"
                + "</data>";
        System.out.println(xml);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            Document document = builder.parse(is);
            // Получаем корневой элемент
            Node root = document.getDocumentElement();
            String s = root.getNodeName();
            // Просматриваем все подэлементы корневого - т.е. поля
            NodeList fields = root.getChildNodes();
            for (int ii = 0; ii < fields.getLength(); ii++) {
                Node field = fields.item(ii);
                // Если это текст - это то что нам нужно
                //if (field.getNodeType() == Node.TEXT_NODE) {
                if (field.getNodeType() == Node.ELEMENT_NODE && field.getNodeName()
                        .equals("field")) {
                    Element element = (Element) field;
                    String fieldName = element.getAttribute("name");
                    String fieldValue = element.getAttribute("value");

                    System.out.println(field.getNodeName());
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}
