package src.view.output;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printInputPurchaseMoneyMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }
}
