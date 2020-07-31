package properties;

import anotations.Cfg;
import helpers.PropertyReader;

public class GeneralProperties {
    private static GeneralProperties GENERAL_PROPS =null;
    private static final String propFileName = "config/general.properties";
    //Our properties/configs
    @Cfg
    public static String date;
    @Cfg
    public static String val_curs_data_file_location_en;
    @Cfg
    public static String val_curs_data_file_location_ro;
    @Cfg
    public static String val_curs_data_file_location_ru;
    @Cfg
    public static String chrome_driver;
    @Cfg
    public static String HttpClientSocketTimeout;
    @Cfg
    public static String HttpClientConnectTimeout;
    @Cfg
    public static String PageLoadTimeOut;
    @Cfg
    public static String ImplicitlyWait;


    private GeneralProperties() throws Exception {
        PropertyReader.readProperties(propFileName,this.getClass());
    }



    public static GeneralProperties readProperty() throws Exception {
        if (GENERAL_PROPS == null) {
            //synchronized(GeneralProperties.class) {
            GENERAL_PROPS = new GeneralProperties();
            //}
        }
        return GENERAL_PROPS;
    }
}
