import controller.LottoController;
import view.InputView;
import view.OutputView;

public class LottoRunner {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new InputView(), new OutputView());
        lottoController.start();
    }
}
