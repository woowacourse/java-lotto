import controller.InputController;
import controller.LottoController;
import controller.OutputController;
import model.LottoNumberGenerateStrategy;

public class LottoMain {

    public static void main(String[] args) {
        final LottoController LottoController = new LottoController(new LottoNumberGenerateStrategy(),
                new InputController(), new OutputController());
        LottoController.runGame();
    }
}

