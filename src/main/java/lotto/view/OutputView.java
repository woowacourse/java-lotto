package lotto.view;

public class OutputView {
    public static final void printMoneyMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printTicketCountMessage(int counts) {
        System.out.println(counts + "개를 구매했습니다.");
    }
}
