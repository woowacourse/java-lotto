package view;

public class OutputView {
    public static void printInputPurchaseAmountMessage() {
        System.out.println("구매금액을 입력해 주세요");
    }

    public static void printPurchaseCountMessage(int calculateCount) {
        System.out.printf("%d개를 구매했습니다.", calculateCount);
    }

}
