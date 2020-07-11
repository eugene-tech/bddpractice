package helpers;

import java.util.Arrays;

public enum Lang {
    RU("ru", "Русский"),
    EN("en", "English"),
    RO("ro", "Română");

    public final String code;
    public final String name;

    Lang(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Lang getLang(String language) {
        return Arrays.stream(values())
                .filter(lang -> lang.name.equals(language))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Language <" + language + "> not found!"));
    }
}