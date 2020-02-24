package lotto;

import lotto.controller.LottoManager;
import lotto.domain.Buyer;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        LottoManager lottoManager = new LottoManager();
        Money money = new Money(InputView.inputMoney());
        Buyer buyer = new Buyer(money.calculateLottoTicketCount());

        OutputView.printPurchaseCount(money.calculateLottoTicketCount());
        OutputView.printLottoNumbers(buyer.getLottos());

        lottoManager.setWinningLotto(InputView.inputWinningLottoNumbers(), InputView.inputBonusNumber());

        OutputView.printLottoResults(lottoManager.analyzeLotto(buyer));
        OutputView.printRewardRate(lottoManager.analyzeRewardRate(money));
    }
}