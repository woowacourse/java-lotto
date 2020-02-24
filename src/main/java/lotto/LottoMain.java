package lotto;

import lotto.controller.LottoManager;
import lotto.domain.Buyer;
import lotto.domain.Money;
import lotto.domain.lottoTicket.Lotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        LottoManager lottoManager = new LottoManager(InputView.inputMoney());

        OutputView.printPurchaseCount(lottoManager.getMoney().calculateLottoTicketCount());
        OutputView.printLottoNumbers(lottoManager.getBuyer().getLottos());

        lottoManager.setWinningLotto(InputView.inputWinningLottoNumbers(), InputView.inputBonusNumber());

        OutputView.printLottoResults(lottoManager.analyzeLotto());
        OutputView.printRewardRate(lottoManager.analyzeRewardRate());
    }
}