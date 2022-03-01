
import static view.OutputView.printErrorMessage;

import controller.LottoController;

public class Application {
    public static void main(String[] args) {
        try {
            LottoController lottoController = new LottoController();
            lottoController.run();
        } catch (Exception e) {
            printErrorMessage(e);
        }
    }
}
