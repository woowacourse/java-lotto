package view;

public class OutputView {
    private final static String BUY_LOTTO_MONEY = "구입금액을 입력해 주세요.";
    public static void printBuyLottoMoney() {
        printLine(BUY_LOTTO_MONEY);
    }

    private static void printLine(String message) {
        System.out.println(message);
    }
}
