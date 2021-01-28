package managers;

import javax.swing.*;

public class ViewManager {
    /* Singleton */
    private static ViewManager instance = null;

    private ViewManager() { }

    public static ViewManager getInstance() {
        if (instance == null) {

            instance = new ViewManager();
        }

        return instance;
    }
    /* Singleton */

    //changeView put this on buttons

    public static void launchView(JFrame view) {
        view.setVisible(true);
    }

    public static void changeView(JFrame firstView, JFrame secondView) {
        firstView.dispose();
        secondView.setVisible(true);
    }

}
