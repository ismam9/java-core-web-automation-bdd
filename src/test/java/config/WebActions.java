package config;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static config.WebDriver.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Web Actions * Acciones Básicas con el navegador.
 *
 * @see WebActions metodos donde encontramos las acciones básicas
 *            access, sendKeys,
 *            click, waitAndClick,
 *            waitForVisibility, waitTillPageIsLoaded, waitSecs, waitSecsRandomize
 *            scrollTop, scrollDown, scrollBottom, scrollTo
 *            exists, lookupElement
 */
public class WebActions {

    public static String msg;

    public static boolean access(String urlAddress){
        try {
            msg = "[OK]: Correct access to the URL: " + urlAddress;
            getDriver().get(urlAddress);
            Log.register(msg);
            return true;
        } catch (Exception e) {
            String errorMessage = "[ERROR]: Failed to access URL: " + urlAddress + ". Exception: " + e.getMessage();
            Log.register(errorMessage);
            return false;
        }
    }

    public static void sendKeys(WebElement elm, String texto) {
        try {
            elm.clear();
            elm.sendKeys(texto);
            msg = "[OK]" + ": \"" + texto + "\" enviado correcto a: " + elm.toString();
            Log.register(msg);
        } catch (Exception ex) {
            msg = "[ERROR]" + ": No se ha podido enviar \"" + texto + "\" al elemento " + elm.toString();
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }

    public static void click(WebElement elm) {
        try {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(elm).click().perform();
            msg = "[OK] " + ": Click correcto en: " + elm.toString();
            Log.register(msg);
        } catch (Exception e) {
            msg = "[ERROR] " + ": Fallo al dar click: " + elm.toString();
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }

    public static void waitAndClick(WebElement elm) {
        int espera = 35;
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(espera));
            wait.until(ExpectedConditions.elementToBeClickable(elm));
            msg = ("[OK] " + ": Elemento clickable " + elm.toString() + " visible");
            Log.register(msg);
            waitSecs(3);
            elm.click();
        } catch (Exception ex) {
            msg = ("[WARNING] " + ": Tiempo de espera superado (" + espera + "seg.). Elemento se mantiene visible.");
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }

    /**
     * @param xpath Accion igual que la anterior pero con el String del xpath, en vez del WebElement
     * */
    public static void waitAndClick(String xpath) {
        int espera = 35;
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(espera));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            msg = ("[OK] " + ": Elemento clickable " + xpath + " visible");
            Log.register(msg);
            waitSecs(3);
            getDriver().findElement(By.xpath(xpath)).click();
        } catch (Exception ex) {
            msg = ("[ERROR] " + ": Tiempo de espera superado. Elemento no clickable.");
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }

    /**
     * Metodo que espera a que un elemento sea visible. Este metodo recibe un
     * WebElement. Se recomienda el uso de este metodo para elementos que existen en
     * el DOM, aunque no sean visibles. Si son elementos generados dinamicamente, se
     * recomienda utilizar los metodos que utilizan xpath o ID.
     *
     * @param elm Objeto WebElement sobre el cual se quiere esperar su visibilidad.
     */
    public static void waitForVisibility(WebElement elm) {
        int espera = 35;
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(espera));
            wait.until(ExpectedConditions.visibilityOf(elm));
            msg = ("[OK] " + ": Espera OK. Elemento " + elm.toString() + " visible");
            Log.register(msg);
        } catch (Exception ex) {
            msg = ("[WARNING] " + ": Tiempo de espera superado. Elemento no visible.");
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }

    public static void waitTillPageIsLoaded(){
        int espera = 35;
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(espera));
            ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            wait.until(expectation);
            msg = ("[OK] " + ": SE HA ESPERADO CORRECTAMENTE HASTA QUE LA WEB ESTA CARGADA");
            Log.register(msg);
            waitSecs(2);
        } catch (Exception ex) {
            msg = ("[WARNING] " + ": Tiempo de espera superado. Elemento no visible.");
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }


    public static void waitSecs(int secs) {
        try {
            Thread.sleep(secs * 1000L);
            msg = ("[OK] " + ": Espera de " + secs + " seg. realizada.");
            Log.register(msg);
        } catch (Exception ex) {
            msg = ("[ERROR] " + ": Error al realizar la espera.");
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }
    public static void waitSecsRandomize(int min, int max) {
        int random = (int) (Math.random() * (max - min) + min);
        try {
            Thread.sleep(random * 1000L);
            msg = ("[OK] " + ": Espera de " + random + " seg. realizada.");
            Log.register(msg);
        } catch (Exception ex) {
            msg = ("[ERROR] " + ": Error al realizar la espera.");
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }

    /** SCROLLS */
    public static void scrollTop() {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, document.body.scrollTop)");
            msg = "[OK][" + Thread.currentThread().getStackTrace()[1].getClassName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]"
                    + ": Page was scrolled to the top";
            Log.register(msg);
        } catch (Exception e) {
            msg = "[ERROR][" + Thread.currentThread().getStackTrace()[1].getClassName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]"
                    + ": Page wasn't scrolled to the top: " + e.getMessage();
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }

    /**
     * Metodo que ejecuta un desplazamiento al tope inferior de la pagina.
     */
    public static void scrollBottom() {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight)");
            msg = "[OK][" + Thread.currentThread().getStackTrace()[1].getClassName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]"
                    + ": Page was scrolled to the bottom";
            Log.register(msg);
        } catch (Exception e) {
            msg = "[ERROR][" + Thread.currentThread().getStackTrace()[1].getClassName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]"
                    + ": Page wasn't scrolled to the bottom: " + e.getMessage();
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }

    /**
     * Metodo que ejecuta un desplazamiento de la pagina hasta la ubicacion del
     * elemento indicado.
     *
     * @param elm WebElement hacia el cual se quiere hacer el desplazamiento.
     * //@see https://developer.mozilla.org/es/docs/Web/API/Element/scrollIntoView
     */
    public static void scrollTo(WebElement elm) {
        try {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block:'end', behavior:'smooth'});", elm);
            msg = "[OK][" + Thread.currentThread().getStackTrace()[1].getClassName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]"
                    + ": Page was scrolled to the element";
            Log.register(msg);
        } catch (Exception e) {
            msg = "[ERROR][" + Thread.currentThread().getStackTrace()[1].getClassName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]" + "["
                    + Thread.currentThread().getStackTrace()[1].getMethodName() + "]"
                    + ": Page wasn't scrolled to the element: " + e.getMessage();
            Log.register(msg);
            throw new AssertionError(msg);
        }
    }

    /**
     * Metodo que comprueba si un elemento existe en el DOM. Recibe un xpath para
     * obtener la cantidad de elementos con esas caracteristicas y con ello evaluar
     * su existencia.
     *
     * @param xpath Localizador del elemento.
     */
    public static boolean exists(String xpath) {
        try {
            int cant = getDriver().findElements(By.xpath(xpath)).size();
            if (cant == 0) {
                msg = ("[OK] " + ": Busqueda correcta. El elemento no existe.");
                return false;
            } else {
                msg = ("[OK] " + ": Busqueda correcta. El elemento existe (" + cant + ")");
                return true;
            }
        } catch (Exception ex) {
            msg = ("[ERROR] " + ": Error al buscar existencia del elemento");
            return false;
        } finally {
            Log.register(msg);
        }

    }

    /**
     * Extras de acciones con los elementos
     *
     * */
    public static void lookupElement(WebElement elm) throws Exception {
        try {
            assertTrue(elm.isDisplayed());
            msg = "[OK][" + WebActions.class.getSimpleName() + "][El elemento " + elm.toString() + " existe]";
            Log.register(msg);
        } catch (Exception ex) {
            msg = "[ERROR][" + WebActions.class.getSimpleName() + "][El elemento " + elm.toString() + " no existe";
            Log.register(msg);
            throw new Exception(msg);
        }
    }
}
