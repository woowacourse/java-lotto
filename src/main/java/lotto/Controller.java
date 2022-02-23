package lotto;

public class Controller {

    public static void run() {
        Money money = Money.from(InputView.askMoneyAmount());
        int purchaseCount = Lotto.countAvailableTickets(money);
        ResultView.showPurchaseCount(purchaseCount);
    }
}
