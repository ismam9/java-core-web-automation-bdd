package runners;

import config.Log;
import config.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import utils.LoadProjectProperties;


public class HookStep extends WebDriver {
    
    @BeforeAll
    public static void beforeAll(){
        String log = "/**********************************************************************************/ \n" +
                "/**********************************************************************************/ \n" +
                "/************************************* SYSTEM INFO ********************************/ \n" +
                "/**********************************************************************************/ \n" +
                "/**********************************************************************************/ \n";
        Log.registerOnlyLog(log);
        Log.registerInit();
    }

    @Before
    public static void inicializateDriver(){
        String log = "/**********************************************************************************/ \n" +
                "/**********************************************************************************/ \n" +
                "/************************************* INIT DRIVER ********************************/ \n" +
                "/**********************************************************************************/ \n" +
                "/**********************************************************************************/ \n";
                Log.register(log);
        // Configurar el controlador con el navegador y la plataforma seleccionados
        LoadProjectProperties.configureBrowserAndPlatform(); // Utiliza las propiedades configuradas
        setupDriver(LoadProjectProperties.selectedBrowser, LoadProjectProperties.selectedPlatform);
    }

    @After
    public static void closeDriver(){
        Log.registerFinal();
        tearDown();
    }

    @AfterAll
    public static void afterAll(){
        String log = "/**********************************************************************************/ \n" +
                "/**********************************************************************************/ \n" +
                "/************************************ ALLURE REPORT *******************************/ \n" +
                "/**********************************************************************************/ \n" +
                "/**********************************************************************************/ \n";
        Log.registerOnlyLog(log);
        genReporQuitDriver();
    }
}
