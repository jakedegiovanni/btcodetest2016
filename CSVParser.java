import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Jake Degiovanni
 *
 * This class will parse the specified csv file using the supplied delimeter.
 * It will then return a list of string arrays where the arrays represent contain column in each line
 */
public class CSVParser {
    private String filename;
    private String delimeter;

    public CSVParser(String filename,
                     String delimeter) {

        this.filename = filename;
        this.delimeter = delimeter;
    }

    public List<String[]> parse() {
        List<String[]> brokenUpCsvFile = new ArrayList<>();

        // int numOfColumns = 0; // used to ensure that the line is always split even on empty values

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            String line = br.readLine();

            boolean firstLine = true;

            while (line != null) {
                
                if (firstLine) {
                    firstLine = false;
                    // numOfColumns = line.split(delimeter).length;
                } else {
                    brokenUpCsvFile.add(line.split(delimeter));
                }

                line = br.readLine();
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return brokenUpCsvFile;
    }
}