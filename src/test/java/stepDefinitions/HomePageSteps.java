package stepDefinitions;

import config.Log;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.HomePage;
import pom.HomePageObjects;

public class HomePageSteps{

    private HomePage homePage;
    String url = "https://archive.org/";

    public HomePageSteps() {
        // Crea los objetos necesarios para HomePage
        HomePageObjects homepageObjects = new HomePageObjects(); // Instancia de HomePageObjects si es necesario
        // Inicializa homePage con los objetos creados
        this.homePage = new HomePage(homepageObjects);
    }

    @When("Accedemos a la Web")
    public void accedemos_a_la_web() {
        Log.register("Access primero");
        homePage.accedemosHomePage(url);
    }

    @Then("Hola")
    public void hola() {
        homePage.hola();
    }

    @Then("Holaadios")
    public void holaadios() {
        Log.register("Access primero");
        homePage.accedemosHomePage(url);
    }

    @When("Accedemos a la Webd")
    public void accedemosALaWeb() {
        homePage.hola();
    }
}
