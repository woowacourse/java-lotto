import controller.LottoController;

public class Application {

    public static void main(String[] args) {
        LottoConfig lottoConfig = LottoConfig.getInstance();
        LottoController lottoController = lottoConfig.lottoController();
        lottoController.run();
    }
}
