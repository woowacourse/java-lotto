import controller.LottoController;
import view.InputValidator;
import view.InputView;
import view.OutputView;

public class LottoRunner {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new InputView(), new InputValidator(), new OutputView());
        lottoController.start();
    }
}
