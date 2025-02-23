package view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;
    private final OutputView outputView;

    public InputView(OutputView outputView) {
        this.outputView = outputView;
        scanner = new Scanner(System.in);
    }

    public String inputBuyLottoMoney() {
        return input();
    }

    private String input() {
        return scanner.nextLine();
    }

    public String inputWinningNumber() {
        outputView.printInputWinningNumber();
        return input();
    }

    public String inputBonusNumber() {
        outputView.printInputBonusNumber();
        return input();
    }
}
