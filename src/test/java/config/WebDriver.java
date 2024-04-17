package config;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.util.List;

public class WebDriver {
    protected static final String CHROME_DRIVER_PATH = "driver/chromedriver.exe";
    //protected static final String FIREFOX_DRIVER_PATH = "driver/geckodriver.exe";
    protected static final String EDGE_DRIVER_PATH = "driver/msedgedriver.exe";

    public static org.openqa.selenium.WebDriver driver;
    public static org.openqa.selenium.WebDriver getDriver() {
        return driver;
    }

    public static void setupDriver(String browser, String platform) {
        switch (browser.toLowerCase()) {
            case "chrome":
                setupChromeDriver();
                break;
            case "firefox":
                setupFirefoxDriver();
                break;
            case "edge":
                setupEdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Additional configuration based on platform and dataname if needed
    }

    private static void setupChromeDriver() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        //Log.register("System Properties: "+ String.valueOf(System.getProperties()));

        // OPTIONS
        ChromeOptions options = new ChromeOptions();

        // WebDriver waits until DOMContentLoaded event fire is returned.
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        // This options reduce system consumption
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // SERVICES
        ChromeDriverService service = new ChromeDriverService.Builder()
                .withVerbose(true) // Establece el nivel de verbosidad del registro
                .withLogFile(new File("outputs/chromeDriverLogs.log")) // Establece el archivo de registro
                .build();

        driver = new ChromeDriver(service, options);
        driver.manage().window().maximize();
    }

    /**
     * @Firefox We dow no include firefox-gecko driver for this one.
     * We are Testing the Automatic Download :)
     * */
    private static void setupFirefoxDriver() {
        //System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_PATH);

        // OPTIONS
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");

        // SERVICES
        FirefoxDriverService service = new GeckoDriverService.Builder()
                .withLogFile(new File("outputs/firefoxDriverLogs.log"))
                .build();
        driver = new FirefoxDriver(options);
    }

    private static void setupEdgeDriver() {
        System.setProperty("webdriver.msedge.driver", EDGE_DRIVER_PATH);

        // OPTIONS
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));

        // SERVICES
        EdgeDriverService service = new EdgeDriverService.Builder()
                .withVerbose(true) // Establece el nivel de verbosidad del registro
                .withLogFile(new File("outputs/edgeDriverLogs.log")) // Establece el archivo de registro
                .build();

        driver = new EdgeDriver(service, options);
    }

    /**
     * Método que finaliza la ejecución del driver y escribe el reporte.
     */
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void genReporQuitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Método que convierte un archivo de imagen en una cadena de Base64.
     *
     * @param imageFile fichero de imagen a convertir.
     */
    /*public static String imageToBase64(File imageFile) {
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            byte[] bytes = new byte[(int) imageFile.length()];
            fis.read(bytes);
            return new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al convertir la imagen a Base 64", e);
        }
    }*/
    /*public static String returnScreenshot() {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = new Date();

        String folder = "reportesHTML" + File.separator + formatoFecha.format(fecha) + File.separator + "screenshots";
        File capturaPantalla = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy__hh_mm_ss_SS");

        // Comprobar si la carpeta existe. Si no, se crea
        File folderScreenshots = new File(folder);

        if (!folderScreenshots.exists()) {
            if (!folderScreenshots.getParentFile().exists()) {
                folderScreenshots.getParentFile().mkdir();
            }
            folderScreenshots.mkdir();
        }

        String FechaHora = df.format(new Date());
        String destFile = System.getProperty("user.dir") + File.separator + "reportesHTML" +
                File.separator +formatoFecha.format(fecha)+ File.separator + "screenshots" + File.separator + FechaHora + ".jpg";
        File target = new File(destFile);
        String destFileRelative = "screenshots" + File.separator + FechaHora + ".jpg";
        try {
            // FileUtils.copyFile(capturaPantalla, new File(folder + File.separator +
            // destFile));
            FileUtils.copyFile(capturaPantalla, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFileRelative;
    }*/
}
