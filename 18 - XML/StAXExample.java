import javax.xml.stream.*;
import java.io.*;

public class StAXExample {

    public static void main(String[] args) {
        readerXML();
    }

    static void readerXML(){
        try{
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader  r = factory.createXMLStreamReader
                    ("app.xml", new FileInputStream("app.xml"));
            while (r.hasNext()) {
                parseEvent(r);
                r.next();
            }
        }catch(Exception e){

        }
    }

    static void parseEvent(XMLStreamReader reader) {
        switch (reader.getEventType()) {
            case XMLStreamConstants.START_DOCUMENT:
                System.out.println("Start of document");
                break;
            case XMLStreamConstants.START_ELEMENT:
                System.out.println("Start element = " + reader.getLocalName());
                break;
            case XMLStreamConstants.CHARACTERS:
                int beginIndex = reader.getTextStart();
                int endIndex = reader.getTextLength();
                String value = new String(reader.getTextCharacters(),
                        beginIndex,
                        endIndex).trim();
                if (!value.equalsIgnoreCase(""))
                    System.out.println("Value = " + value);
                break;
            case XMLStreamConstants.END_ELEMENT:
                System.out.println("End element = " + reader.getLocalName());
                break;
            case XMLStreamConstants.COMMENT:
                if (reader.hasText())
                    System.out.print(reader.getText());
                break;
        }
    }

    static void createXML(){
        try{
            FileOutputStream fos = new FileOutputStream("out.xml");
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(fos);

            writer.writeStartDocument();
            writer.writeStartElement("document");
            writer.writeStartElement("data");
            writer.writeAttribute("name", "value");
            writer.writeCharacters("Hello World!");
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();
        }catch(Exception e){

        }
    }
}
