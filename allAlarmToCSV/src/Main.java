//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;





public class Main {
     public static Set<List<String>> extractData(String srcFile) throws IOException {
        List<String> lines = new ArrayList<>();
        Set<List<String>> data = new HashSet<>();
        String enm = new File(srcFile).getName().split("_")[0];

        try (Scanner scanner = new Scanner(new FileReader(srcFile))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine().strip());
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains("presentSeverity :")) {
                for (int j = 1; j < i + 34; j++) {
                    String presentSeverity = lines.get(i).strip().split("presentSeverity :")[1].strip();
                    if (lines.get(j).contains("NodeName :")) {
                        String NodeName = lines.get(j).strip().split("NodeName :")[1].strip();
                        String city = NodeName.substring(0, 2);
                        //String network_Mode = defineType(NodeName);
                        String network_Mode = "none";
                    }
                        // Extract other fields...
                    if (lines.get(j).contains("previousSeverity  :")) {
                        String previousSeverity = lines.get(i).strip().split("presentSeverity :")[1].strip();
                    }
                    if (lines.get(j).contains("eventTime  :")) {
                        String eventTime = lines.get(i).strip().split("eventTime :")[1].strip();
                    }
                    if (lines.get(j).contains("ceaseTime  :")) {
                        String ceaseTime = lines.get(i).strip().split("ceaseTime :")[1].strip();
                    }
                    if (lines.get(j).contains("objectOfReference  :")) {
                        String objectOfReference = lines.get(i).strip().split("objectOfReference :")[1].strip();
                    }
                    if (lines.get(j).contains("alarmingObject  :")) {
                        String alarmingObject = lines.get(i).strip().split("alarmingObject :")[1].strip();
                    }
                    if (lines.get(j).contains("specificProblem  :")) {
                        String specificProblem = lines.get(i).strip().split("specificProblem :")[1].strip();
                    }
                    if (lines.get(j).contains("problemText  :")) {
                        String problemText = lines.get(i).strip().split("problemText :")[1].strip();
                    }
                    if (lines.get(j).contains("probableCause  :")) {
                        String probableCause = lines.get(i).strip().split("probableCause :")[1].strip();
                    }
                    if (lines.get(j).contains("eventPoId  :")) {
                        String eventPoId = lines.get(i).strip().split("eventPoId :")[1].strip();
                    }

                        data.add(List.of(enm, city, network_Mode, presentSeverity, previousSeverity, NodeName,
                                eventTime, ceaseTime, objectOfReference, alarmingObject, specificProblem,
                                problemText, probableCause, eventPoId));
                    }
                }
            }
        return data;
        }
    }

//    public static String defineType(String node) {
//        // ... (logic for defining network type based on node remains the same)
//        if (node.contains("-ER")) {
//            return "NR";
//        } else if (node.contains("RD-") || node.contains("R2D-") || node.contains("R3D-")) {
//            return "TDD_RD";
//        } else if (node.contains("-EF")) {
//            return "FDD";
//        } else if (node.contains("-EN")) {
//            return "NB";
//        } else if (node.contains("-EL") && !node.contains("RD-") && !node.contains("R2D-") && !node.contains("R3D-")) {
//            return "TDD";
//        } else {
//            return "UNKNOWN"; // Default case if no matching type is found
//        }
//    }

        public static void main(String[] args) throws IOException {
        //String alarmLstPath = args[0];
        String alarmLstPath = "C:\\Users\\eraocui\\OneDrive - Ericsson\\ERICSSON\\MyScripts\\JavaScripts\\allAlarmToCSV\\lstlogs\\";
        //String alarmOutFile = args[1];
        String alarmOutFile = "C:\\Users\\eraocui\\OneDrive - Ericsson\\ERICSSON\\MyScripts\\JavaScripts\\allAlarmToCSV\\SG2_active.csv";

        String basePath = System.getProperty("user.dir");
        List<String> header = List.of("ENM", "City", "network_Mode", "presentSeverity", "previousSeverity", "NodeName",
                "eventTime", "ceaseTime", "objectOfReference", "alarmingObject", "specificProblem",
                "problemText", "probableCause", "eventPoId");
        Set<List<String>> finalData = new HashSet<>();

        File[] files = new File(alarmLstPath).listFiles();
        System.out.println(files);
        if (files != null) {
            for (File file : files) {
                System.out.println(file.getName());
                Set<List<String>> data = extractData(file.getAbsolutePath());
                finalData.addAll(data);
            }
        }

        try (FileWriter writer = new FileWriter(new File(basePath, alarmOutFile));
             CSVWriter csvWriter = new CSVWriter(writer)) {
            csvWriter.writeNext(header.toArray(new String[0]));
            for (List<String> item : finalData) {
                csvWriter.writeNext(item.toArray(new String[0]));
            }
        }
    }
}