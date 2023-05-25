import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLogger {
    private static final String LOG_FILE_PATH = "C:/Users/dantu/Desktop/Facultate/Anul 2 Sem 2/Programare Avansata pe Obiecte (Java)/Proiect/Proiect/audit/audit1.csv";


    public static void logAction(String action) {
        String timestamp = getCurrentTimestamp();
        String logEntry = action + "," + timestamp;

        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.println(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
        }
    }

    private static String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return dateFormat.format(now);
    }
}

