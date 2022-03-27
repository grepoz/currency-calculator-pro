import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class Parser {

    public static Map<String, BigDecimal> parse(){
        String fileName = "xml-file/eurofxref-daily.xml";
        return parse(fileName);
    }

    public static Map<String, BigDecimal> parse(String fileName){

        Document document = getDocument(fileName);
        if(document == null) return null;

        List<String> currenciesAndRates = evaluateXPath(document);
        if(currenciesAndRates == null) return null;

        return createDictFromStrList(currenciesAndRates);
    }

    private static Map<String, BigDecimal> createDictFromStrList(List<String> currenciesAndRates){

        if(currenciesAndRates == null || currenciesAndRates.size() == 0) {
            System.out.println("Empty attributes!");
            return null;
        }

        Map<String, BigDecimal> exchangeMap = new HashMap<>();

        for (int i = 0; i < currenciesAndRates.size() / 2; i++) {

            try {
                BigDecimal rate = new BigDecimal(currenciesAndRates.get(2*i + 1));
                if(currenciesAndRates.get(2*i).length() != 3) throw new Exception();
                exchangeMap.put(currenciesAndRates.get(2*i), rate);

            } catch (Exception e){
                System.out.println("Invalid xml attribute!");
                return null;
            }
        }

        return exchangeMap;
    }

    private static List<String> evaluateXPath(Document document)
    {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        List<String> values = new ArrayList<>();

        try {
            XPathExpression expr = xpath.compile("/Envelope/Cube/Cube/Cube/@currency|/Envelope/Cube/Cube/Cube/@rate");
            NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); i++) {
                values.add(nodes.item(i).getNodeValue());
            }

        } catch (XPathExpressionException e) {
            System.out.println("Unable to evaluate xpath expression!");
            return null;
        }

        return values;
    }

    private static Document getDocument(String fileName)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(fileName);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println("Unable to build document!");
        }

        return document;
    }
}
