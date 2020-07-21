package helpers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XMLParser {

    public static Object parseXML(String xml, Class obj) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(obj);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return unmarshaller.unmarshal(new StringReader(xml));
    }
}
