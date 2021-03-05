package Library.utilities;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileUtils {

    public static String readFromFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath), StandardCharsets.UTF_8);

    }

    public static void writeToFile(String filePath, String fileContent){
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(filePath);
            myWriter.write(fileContent);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
