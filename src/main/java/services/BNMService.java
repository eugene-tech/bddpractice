package services;

import core.Config;
import helpers.DataUtil;
import helpers.Lang;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class BNMService {

    private static final Logger log = LogManager.getLogger(BNMService.class);

    private static InputStream getXMLCurrencies(LocalDate date, Lang lang) {
        try {
            log.info("Requesting Currency rate from BNM for " + date.format(DataUtil.DATE_FORMAT) + " (lang=" + lang.name + ")");
            URIBuilder builder = new URIBuilder(Config.getString("bnm.url." + lang.code));
            builder.addParameter("date", date.format(DataUtil.DATE_FORMAT));
            HttpGet get = new HttpGet(builder.build());
            return HttpClientBuilder.create().build().execute(get).getEntity().getContent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static String parseElement(Element element, String tag) {
        return element.getElementsByTagName(tag).item(0).getTextContent();
    }

    public static Map<String, Currency> getCurrencyRate(LocalDate date, Lang lang) {
        return getCurrencyRate(date, Collections.emptyList(), lang);
    }

    public static Map<String, Currency> getCurrencyRate(LocalDate date, List<String> charCodes, Lang lang) {
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(getXMLCurrencies(date, lang));
            log.info("Currencies to search" + (charCodes.isEmpty() ? " not defined" : ": " + String.join(", ", charCodes)));
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("Valute");
            return IntStream.range(0, nList.getLength())
                    .filter(i -> nList.item(i).getNodeType() == Node.ELEMENT_NODE)
                    .mapToObj(i -> (Element) nList.item(i))
                    .map(element -> new Currency(log, element))
                    .collect(toMap(Currency::getCharCode, Function.identity()));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}


