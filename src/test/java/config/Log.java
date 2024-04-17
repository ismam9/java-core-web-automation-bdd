package config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class Log {
    static Properties properties = System.getProperties();

    private static BufferedWriter bw = null;
    private static FileWriter fw = null;
    private static String ruta = null;

    // Constantes para rutas y formatos de fecha
    private static final String OUTPUTS_DIRECTORY = "outputs";
    private static final String LOGS_DIRECTORY = OUTPUTS_DIRECTORY + File.separator + "logs";
    private static final String LOG_FILE_PREFIX = "log";
    private static final String LOG_DATE_FORMAT = "hh_mm_ss---dd_MMM";
    private static final String LOG_HOUR_FORMAT = "HH_mm_ss";

    static {
        initializeLogDirectory();
    }

    private static void initializeLogDirectory() {
        File logDirectory = new File(LOGS_DIRECTORY);

        try {
            if (!logDirectory.exists()) {
                if (!logDirectory.getParentFile().exists()) {
                    if (!logDirectory.getParentFile().mkdir()) {
                        throw new IOException("No se pudo crear el directorio padre de logs.");
                    }
                }
                if (!logDirectory.mkdir()) {
                    throw new IOException("No se pudo crear el directorio de logs.");
                }
            }

            ruta = System.getProperty("user.dir") + File.separator + LOGS_DIRECTORY + File.separator + LOG_FILE_PREFIX + systemDate() + ".txt";
        } catch (IOException e) {
            // Manejar la excepción de manera adecuada, por ejemplo, imprimir un mensaje de error
            e.printStackTrace();
        }
    }

    // Método para registrar mensajes en el log
    public static void register(String msg) {
        String log = "[" + systemHour() + "] - " + msg;
        System.out.println(log);
        writeLog(log);
    }

    public static void registerOnlyLog(String msg) {
        String log = "[" + systemHour() + "] - \n" + msg;
        writeLog(log);
    }

    public static void registerInit(){
        properties.forEach((k, v) -> writeLog(k + ":" + v));
    }

    // Método para registrar el cierre del log
    public static void registerFinal() {
        String log = "/**********************************************************************************/ \n" +
                "/**********************************************************************************/ \n" +
                "/************************************* CLOSE DRIVER *******************************/ \n" +
                "/**********************************************************************************/ \n" +
                "/**********************************************************************************/ \n";
        writeLog(log);
    }

    private static String systemHour() {
        Date hour = new Date();
        SimpleDateFormat objSDF = new SimpleDateFormat(LOG_HOUR_FORMAT);
        return objSDF.format(hour);
    }

    private static String systemDate() {
        Date date = new Date();
        SimpleDateFormat objSDF = new SimpleDateFormat(LOG_DATE_FORMAT);
        return objSDF.format(date);
    }

    private static void writeLog(String log) {
        try {
            File logFile = new File(ruta);
            if (!logFile.exists()) {
                if (!logFile.createNewFile()) {
                    throw new IOException("No se pudo crear el archivo de log.");
                }
            }
            try (FileWriter fw = new FileWriter(logFile.getAbsoluteFile(), true);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(log);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprime la traza completa de la excepción
            System.err.println(Arrays.toString(e.getStackTrace())); // Imprime la traza de la excepción en el log
        }
    }
}
