package lotto;

public class ResultView {
    private static final String MESSAGE_PURCHASE_COUNT = "개를 구매했습니다.";

    public static void showPurchaseCount(int count) {
        System.out.println(count + MESSAGE_PURCHASE_COUNT);
    }
}
