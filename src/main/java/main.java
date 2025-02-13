import controller.LottoController;
import factory.LottoFactory;

public class main {

    public static void main(String[] args) {
        final LottoController controller =
            new LottoController(LottoFactory.inputView(), LottoFactory.outputView(), LottoFactory.numberGenerator());
        controller.run();
    }
}
