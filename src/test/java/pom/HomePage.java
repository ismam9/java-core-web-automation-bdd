package pom;

import config.Log;
import config.WebActions;

public class HomePage {
    private final HomePageObjects homepageObjects;

    public HomePage(HomePageObjects homepageObjects){
        this.homepageObjects = homepageObjects;
    }

    public void accedemosHomePage(String url){
        WebActions.access(url);
        WebActions.waitSecs(2);
        WebActions.waitForVisibility(homepageObjects.Cookies);
        WebActions.click(homepageObjects.Cookies);
    }

    public void hola(){
        Log.register("Hello World");
        WebActions.waitSecs(2);
    }
}
