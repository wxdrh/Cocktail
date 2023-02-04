package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class FileLoaderFactory {

    private Properties properties;
    private String propertiesPath;

    /**
     * This method returns the properties from the .properties file stored in the
     * user directory of the user running the test
     *
     * @param filename String
     * @return Returns properties from the pre-defined .properties file
     * @throws Exception Throws exception if .properties file or path is not found
     */
    public Properties getPropertyFile(String filename) throws Exception {

        String path = null;

        try {
            path = System.getProperty("user.dir") + File.separator + "src/test/resources/Properties/";
        } catch (Exception e) {
            System.err.println("Could not locate .properties file");
            e.printStackTrace();
        }
        return getProperty(path, filename);
    }

    /**
     * This method returns properties from user identified .properties file
     *
     * @param path     String
     * @param filename String
     * @return Returns properties from user identified .properties file
     * @throws Exception Throws exception when file is not found
     */
    public Properties getProperty(String path, String filename) throws Exception {

        propertiesPath = path;

        try {

            File file;

            file = new File(propertiesPath + filename);

            FileInputStream fileInput = new FileInputStream(file);
            properties = new Properties();
            properties.load(fileInput);

        } catch (Exception e) {

            System.err.println("Could not get Properties from the .properties file");
            e.printStackTrace();
            throw e;
        }
        return properties;
    }

    /**
     * Read Json file and return json as a string
     *
     * @param fileName string
     * @return String
     * @throws IOException
     */
    public String readFile(String fileName) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + "src/test/resources/JsonSchemas/" + fileName;
        return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    }

}
