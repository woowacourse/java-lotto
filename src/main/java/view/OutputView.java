package view;

import exception.LottoException;

public class OutputView {

    private final static String BUY_LOTTO_MONEY = "구입금액을 입력해 주세요.";
    private final static String INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private final static String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    public static void printBuyLottoMoney() {
        printLine(BUY_LOTTO_MONEY);
    }

    private static void printLine(String message) {
        System.out.println(message);
    }

    public static void printError(LottoException lottoException) {
        printLine(lottoException.getMessage());
    }

    public static void printBuyLotto(String message) {
        printLine(message);
    }

    public static void printInputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER);
    }

    public static void printInputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
    }

}
