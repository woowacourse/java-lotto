import controller.MainController;
import domain.LottoMachine;
import domain.RandomIntegerGenerator;
import domain.StatisticsService;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(
                new StatisticsService(),
                new LottoMachine(new RandomIntegerGenerator())
        );
        mainController.run();
    }
}
