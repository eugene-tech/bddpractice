package helpers;

import dto.ValCurs;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class ValCursManager {
    public static List<ValCurs> getValCursByDates(List<String> dates,String url) throws JAXBException {
       List<ValCurs > valCurs = new ArrayList<>();
       for(String date : dates){
           valCurs.add((ValCurs) XMLParser.parseXML(HttpClient.get(url+date),ValCurs.class));
       }
       return valCurs;
    }

    public static ValCurs getValCursByDate(List<ValCurs> valCurs,String date){
        return valCurs.stream().filter(valCursElement -> valCursElement.getDate()
                .equals(date)).findFirst().get();
    }
}
