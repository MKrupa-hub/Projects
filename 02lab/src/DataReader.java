
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataReader {

    public DataReader(String fileName) {


        try (Scanner s = new Scanner(new File(fileName))) {

            s.nextLine();
            s.useDelimiter(";|\\r?\\n|\\r");

        new DataSetter(s);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Błąd w otwieraniu pliku");
        }

    }



    public static void main(String args[]) {
        new DataReader("inputData.txt");
    }

}
