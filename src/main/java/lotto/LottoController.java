package lotto;

public class LottoController {

    private final InputView inputView;

    LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void start() {
        int purchaseAmount = inputView.requestPurchaseAmount();
    }
}
