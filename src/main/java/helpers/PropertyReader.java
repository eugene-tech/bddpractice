package helpers;



import anotations.Cfg;
import exceptions.IllegalConfigException;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertyReader {
    public static final Properties prop = new Properties();


    public static void readProperties(String propFileName, Class classObject) throws Exception {

        InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propFileName);
        try{
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            assert inputStream != null;
            inputStream.close();
        }

        for (Field field : classObject.getFields())
        {

            if (field.isAnnotationPresent(Cfg.class)) {
                if (field.getAnnotation(Cfg.class).include()){
                    String property = prop.getProperty(field.getName());
                    if(property != null) {
                        field.setAccessible(true);
                        field.set(classObject, prop.getProperty(field.getName()));
                    }else{
                        throw new IllegalConfigException("We could not find property \""+field.getName()+"\" in property file "+propFileName);
                    }

                }
            }
        }

    }

}
