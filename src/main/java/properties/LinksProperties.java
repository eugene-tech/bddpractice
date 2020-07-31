package properties;

import anotations.Cfg;
import enums.Language;
import helpers.PropertyReader;

public class LinksProperties {
    private static LinksProperties LINKS_PROPERTIES_INSTANCE =null;
    private static final String propFileName = "config/links.properties";
    //Our properties/configs
    @Cfg
    public static String bnm_ro_official_exchange_link;
    @Cfg
    public static String bnm_ru_official_exchange_link;
    @Cfg
    public static String bnm_en_official_exchange_link;
    @Cfg
    public static String home_page;


    public static String getURLBasedOnLang(String lang){
        if (Language.RO.getFullName().equals(lang)) {
            return bnm_ro_official_exchange_link;
        } else if (Language.RU.getFullName().equals(lang)) {
            return bnm_ru_official_exchange_link;
        } else if (Language.EN.getFullName().equals(lang)) {
            return bnm_en_official_exchange_link;
        } else {
            throw new IllegalStateException("Unexpected value: " + lang);
        }
    }


    private LinksProperties() throws Exception {
        PropertyReader.readProperties(propFileName,this.getClass());
    }

    public static LinksProperties readProperty() throws Exception {
        if (LINKS_PROPERTIES_INSTANCE == null) {
            //synchronized(LinksProperties.class) {
                LINKS_PROPERTIES_INSTANCE = new LinksProperties();
            //}
        }
        return LINKS_PROPERTIES_INSTANCE;
    }

}

