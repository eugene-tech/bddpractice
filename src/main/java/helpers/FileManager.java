package helpers;



import enums.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class FileManager {
    private static final Logger log = LogManager.getLogger(HttpClient.class);
    public static void writeToFile(String content, String filePath) throws IOException {
        File file = new File(filePath);
        log.info("Started write to -> "+file.getName());
        BufferedWriter myWriter = new BufferedWriter(new FileWriter(file));
        myWriter.write(content);
        myWriter.close();
    }

    public static String  readFromFile(String filePath) throws IOException {
        File file = new File(filePath);
        log.info("Started read from file -> "+file.getName());
        BufferedReader myReader = new BufferedReader(new FileReader(file));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = myReader.readLine() )!= null){
            result.append(line).append("\n");
        }
        myReader.close();
        return result.toString();
    }

    public static String  readFromFileBasedOnLang(String lang) throws IOException {
        if (Language.RO.getFullName().equals(lang)) {
            return readFromFile(Language.RO.getDataFilePath());
        } else if (Language.RU.getFullName().equals(lang)) {
            return readFromFile(Language.RU.getDataFilePath());
        } else if (Language.EN.getFullName().equals(lang)) {
            return readFromFile(Language.EN.getDataFilePath());
        } else {
            throw new IllegalStateException("Unexpected value: " + lang);
        }
    }

}
