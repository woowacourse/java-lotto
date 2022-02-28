import controller.LottoController;
import model.generator.LottoNumberGenerator;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        final LottoController lottoController = new LottoController(new LottoNumberGenerator(), new InputView(),
                new OutputView());
        lottoController.run();
    }
}

