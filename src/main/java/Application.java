import config.LottoConfig;
import controller.LottoController;

public class Application {


    private static LottoController lottoController = LottoConfig.create();

    public static void main(String[] args) {
        lottoController.start();
    }
}
