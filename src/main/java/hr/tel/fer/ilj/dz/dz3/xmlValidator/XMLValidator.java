package hr.tel.fer.ilj.dz.dz3.xmlValidator;


import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class XMLValidator {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        Scanner sc = new Scanner(System.in);
        String xmlPath;
        String inputSchema;
        System.out.println("Input path to xml schema:");
        inputSchema = sc.nextLine();
        System.out.println("Input path to xml file you want to validate:");
        xmlPath = sc.nextLine();

        // parse an XML document into a DOM tree
        Document document = null;
        File xmlFile = new File(xmlPath);
        DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        try {
            document = parser.parse(xmlFile);
        }catch (SAXException e){
            System.out.println("XML file on " + xmlFile.getAbsolutePath() + " is NOT valid!");
            System.out.println(e.getLocalizedMessage());
            System.exit(0);
        }

        // create a SchemaFactory capable of understanding WXS schemas
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        // load a WXS schema, represented by a Schema instance
        Source schemaFile = new StreamSource(new File(inputSchema));
        Schema schema = factory.newSchema(schemaFile);

        // create a Validator instance, which can be used to validate an instance document
        Validator validator = schema.newValidator();

        // validate the DOM tree
        try {
            validator.validate(new DOMSource(document));
            System.out.println("XML file on " + xmlFile.getAbsolutePath() + " is valid!");
        } catch (SAXException e) {
            // instance document is invalid!
            System.out.println("XML file on " + xmlFile.getAbsolutePath() + " is NOT valid!");
            System.out.println(e.getLocalizedMessage());
        }
        sc.close();
    }
}
