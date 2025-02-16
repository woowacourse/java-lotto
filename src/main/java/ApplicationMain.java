import controller.InputView;
import controller.LottoController;
import controller.OutputView;
import java.util.Scanner;

public class ApplicationMain {
    public static void main(String[] args) {

        final InputView inputView = new InputView(new Scanner(System.in));
        final OutputView outputView = new OutputView();
        final LottoController lottoController = new LottoController(inputView, outputView);

        lottoController.run();
    }
}
