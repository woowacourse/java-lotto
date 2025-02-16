import controller.LottoController;
import infrastructure.AppConfig;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        LottoController lottoController = appConfig.controller();
        lottoController.run();
    }
}
