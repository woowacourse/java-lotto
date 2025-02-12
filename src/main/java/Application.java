import config.AppConfig;
import config.Appconfig;
import controller.LottoController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        LottoController lottoController = appConfig.createLottoController();
        lottoController.run();
    }
}
