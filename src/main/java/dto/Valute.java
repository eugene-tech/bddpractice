package dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Valute {
    @XmlAttribute(name="ID")
    private int id;
    @XmlElement(name="NumCode")
    private int NumCode;
    @XmlElement(name="CharCode")
    private String CharCode;
    @XmlElement(name="Nominal")
    private int Nominal;
    @XmlElement(name="Name")
    private String Name;
    @XmlElement(name="Value")
    private double Value;

    public int getId() {
        return id;
    }

    public int getNumCode() {
        return NumCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public int getNominal() {
        return Nominal;
    }

    public String getName() {
        return Name;
    }

    public double getValue() {
        return Value;
    }
}
