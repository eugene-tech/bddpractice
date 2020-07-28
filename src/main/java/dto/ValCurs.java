package dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="ValCurs")

public class ValCurs {
    @XmlAttribute(name = "Date")
    private String date;

    @XmlElement(name="Valute")
    private List<dto.Valute> Valute;


    public String getDate() {
        return date;
    }

    public List<Valute> getValute() {
        return Valute;
    }

    public double getValueBaseOnCharCode(String charCode){
        return getValute().stream().filter(val->val.getCharCode()
                .equals(charCode))
                .findFirst().get().getValue();
    }



}
