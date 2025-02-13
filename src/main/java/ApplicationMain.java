import config.LottoConfig;
import controller.Controller;
import service.LottoMaker;
import view.InputView;
import view.OutputView;

public class ApplicationMain {
    public static void main(String[] args) {
        LottoConfig lottoConfig = new LottoConfig();
        Controller controller = lottoConfig.controller();
        controller.start();
    }
}