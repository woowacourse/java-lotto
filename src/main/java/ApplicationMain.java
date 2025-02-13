import controller.InputView;
import controller.LottoController;
import controller.OutputView;
import util.NumberPicker;
import util.RandomNumberPicker;

import java.util.Random;
import java.util.Scanner;

public class ApplicationMain {
    public static void main(String[] args) {
        
        InputView inputView = new InputView(new Scanner(System.in));
        OutputView outputView = new OutputView();
        NumberPicker numberPicker = new RandomNumberPicker(new Random());
        LottoController lottoController = new LottoController(inputView, outputView, numberPicker);
        
        lottoController.run();
    }
}
