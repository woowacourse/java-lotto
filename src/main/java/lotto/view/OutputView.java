package lotto.view;


public class OutputView {

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printPurchaseAmountGuideMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }
}
