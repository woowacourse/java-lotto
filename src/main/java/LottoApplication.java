import controller.LottoController;
import java.io.IOException;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) throws IOException {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();

        LottoController lottoController = new LottoController(inputView, outputView);

        try {
            lottoController.run();
        } catch (IllegalArgumentException e2) {
            System.out.println(e2.getMessage());
        }
    }
}
