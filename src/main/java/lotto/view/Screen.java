package lotto.view;

public class Screen {

    public static String getInputMoney() {
        OutputView.printInputMoneyMessage();
        return InputView.getInputLine();
    }

    public static String getLottoNumbers() {
        OutputView.printLottoNumbersMessage();
        return InputView.getInputLine();
    }

    public static String getBonusBallNumber() {
        OutputView.printInputBonusBallMessage();
        String value = InputView.getInputLine();
        OutputView.printNewLineMessage();
        return value;
    }
}
