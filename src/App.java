import controllers.LoginController;
import dataModels.AccountDataModel;
import managers.AppManager;
import managers.DatabaseManager;
import views.ApplicationFrame;

public class App {

    public static void main(String[] args) {

        ApplicationFrame app = new ApplicationFrame("Rock Paper Scissors - Taleon Final Project", new LoginController().view.panelInstance);

        DatabaseManager.loadData();
    }
}
