import controller.MainController;
import service.IssuingService;
import service.StatisticsService;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(
                new IssuingService(),
                new StatisticsService()
        );
        mainController.run();
    }
}
