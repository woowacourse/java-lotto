package view;

public class OutputView {
    private static final String PURCHASE_COUNT_MESSAGE = "개를 구매하였습니다.";

    public void printPurchaseCount(int count) {
        System.out.printf("%d%s\n", count, PURCHASE_COUNT_MESSAGE);
    }

}
