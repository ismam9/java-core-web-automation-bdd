package utils;

import config.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class LoadProjectProperties {

    private static Properties properties = new Properties();
    public static String selectedBrowser;
    public static String selectedPlatform;

    static {
        try {
            // Cargar el archivo de propiedades desde la raíz del proyecto
            properties.load(new FileInputStream("src/test/resources/project.properties"));
        } catch (IOException e) {
            System.err.println("[ERROR]: No se pudo cargar el archivo project.properties.");
            e.printStackTrace();
        }
    }

    public static boolean isValidBrowser(String browser) {
        List<String> validBrowsers = Arrays.asList("chrome", "firefox", "edge");
        return validBrowsers.contains(browser.toLowerCase());
    }

    public static boolean isValidPlatform(String platform) {
        List<String> validPlatforms = Arrays.asList("windows", "mac", "linux");
        return validPlatforms.contains(platform.toLowerCase());
    }

    public static void configureBrowserAndPlatform() {
        selectedBrowser = System.getProperty("browser");
        selectedPlatform = System.getProperty("platform");

        if (selectedBrowser == null || selectedBrowser.isEmpty()) {
            selectedBrowser = "chrome"; // Valor predeterminado si no se especifica el navegador
            Log.register("No se ha especificado un navegador. Se establecerá el navegador predeterminado como 'chrome'.");
        }

        if (!isValidBrowser(selectedBrowser)) {
            throw new IllegalArgumentException("El navegador especificado no es válido: " + selectedBrowser);
        }

        if (selectedPlatform == null || selectedPlatform.isEmpty()) {
            selectedPlatform = "windows"; // Valor predeterminado si no se especifica la plataforma
            Log.register("No se ha especificado una plataforma. Se establecerá la plataforma predeterminada como 'windows'.");
        }

        if (!isValidPlatform(selectedPlatform)) {
            throw new IllegalArgumentException("La plataforma especificada no es válida: " + selectedPlatform);
        }
    }
}
