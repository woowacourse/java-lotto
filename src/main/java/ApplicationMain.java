import controller.InputView;
import controller.LottoController;
import controller.OutputView;

import java.util.Scanner;

public class ApplicationMain {
    public static void main(String[] args) {
        
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        LottoController lottoController = new LottoController(inputView, outputView);
        
        lottoController.run();
    }
}
