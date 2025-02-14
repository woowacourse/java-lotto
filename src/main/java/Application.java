import config.LottoConfig;
import controller.LottoController;

public class Application {
    private static LottoController lottoController = LottoConfig.createController();

    public static void main(String[] args) {
        lottoController.start();
    }
}
