package lotto.view.output;

import java.util.List;

public interface OutputView {

    void printInputPurchaseMoneyMessage();

    void printPurchasedLottos(List<LottoResponse> responses);

    void printInputWinningLottoNumbers();

    void printInputBonusNumber();

    void printLottoResults(List<LottoPrizeResponse> lottoPrizeResponses, int purchaseMoney);
}
