import domain.PurchaseAmount;
import view.InputView;

public class LottoGameLauncher {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
    }

    private static PurchaseAmount getPurchaseAmount() {
        try {
            int moneyFromPlayer = InputView.inputPurchaseAmount();
            return PurchaseAmount.of(moneyFromPlayer);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getPurchaseAmount();
        }
    }
}
