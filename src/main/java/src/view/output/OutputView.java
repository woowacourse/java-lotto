package src.view.output;

import java.util.List;

public interface OutputView {

    void printInputPurchaseMoneyMessage();

    void printPurchasedLottos(List<LottoResponse> responses);
}
