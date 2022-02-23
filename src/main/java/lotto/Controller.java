package lotto;

public class Controller {

    public static void run() {
        InputView inputView = new InputView();
        Money.from(inputView.askMoneyAmount());
    }
}
