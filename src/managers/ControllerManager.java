package managers;

import controllers.Controller;
import views.ApplicationFrame;

import javax.swing.*;

public class ControllerManager {
    /* Singleton */
    private static ControllerManager instance = null;

    private ControllerManager() { }

    public static ControllerManager getInstance() {
        if (instance == null) {

            instance = new ControllerManager();
        }

        return instance;
    }
    /* Singleton */

    //changeView put this on buttons

    public static void changeController(Controller secondController ) {
        ApplicationFrame.instance.changePanel(secondController.view.panelInstance);
    }

}
