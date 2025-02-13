import controller.LottoController;
import java.io.IOException;
import view.InputView;

public class LottoApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        LottoController lottoController = new LottoController(inputView);

        try {
            lottoController.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
