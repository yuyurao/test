import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readfile {

    public static void main(String[] args) throws FileNotFoundException {
        // Replace "your_file.txt" with the actual path to your text file
        String filePath = "C:\\Users\\eraocui\\OneDrive - Ericsson\\ERICSSON\\MyScripts\\JavaScripts\\allAlarmToCSV\\lstlogs\\SG2_active.lst";

        try (Scanner scanner = new Scanner(new File(filePath))) {
            List<String> lines = new ArrayList<>();
            Set<List<String>> data = new HashSet<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(scanner.nextLine().strip());
                //System.out.println(line);
            }
            int textsize = lines.size();
            System.out.println(textsize);

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).contains("presentSeverity :")) {
                    for (int j = 1; j < i + 34; j++) {
                        String presentSeverity = lines.get(i).strip().split("presentSeverity :")[1].strip();
                        if (lines.get(j).contains("NodeName :")) {
                            String NodeName = lines.get(j).strip().split("NodeName :")[1].strip();
                            String city = NodeName.substring(0, 2);
                            //String network_Mode = defineType(NodeName);
                            String network_Mode = "none";
                            String output = String.format(" %s, %s, %s, %s", presentSeverity, city,NodeName, network_Mode);
                            System.out.println(output);
                        }

                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + e.getMessage());
        }
    }
}
