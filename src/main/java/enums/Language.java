package enums;

import properties.GeneralProperties;

public enum Language {
    EN("en","English", GeneralProperties.val_curs_data_file_location_en),
    RU("ru","Русский",GeneralProperties.val_curs_data_file_location_ru),
    RO("ro","Română",GeneralProperties.val_curs_data_file_location_ro);

    private final String code;
    private final String fullName;
    private final String dataFilePath;

    private Language(String code, String fullName, String dataFilePath){
        this.code = code;
        this.fullName= fullName;
        this.dataFilePath = dataFilePath;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDataFilePath() {
        return dataFilePath;
    }
}
