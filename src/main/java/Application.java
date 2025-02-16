import controller.MainController;
import domain.StatisticsService;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(new StatisticsService());
        mainController.run();
    }
}
