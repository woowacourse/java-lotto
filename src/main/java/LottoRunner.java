import controller.LottoController;
import view.InputValidator;
import view.InputView;

public class LottoRunner {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new InputView(), new InputValidator());
        lottoController.start();
    }
}
