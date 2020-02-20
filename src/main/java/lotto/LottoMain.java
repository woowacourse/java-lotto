package lotto;

import lotto.controller.LottoManager;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        LottoManager lottoManager = new LottoManager(InputView.inputMoney());
        OutputView.printPurchaseCount(lottoManager.moneyManager.purchase());
        OutputView.printLottoNumbers(lottoManager.buyer.getLottos());
        lottoManager.setWinningLotto(InputView.inputWinningLottoNumbers(), InputView.inputBonusNumber());
        OutputView.printLottoResults(lottoManager.analyzeLotto());
        OutputView.printRewardRate(lottoManager.analyzeRewardRate());
    }
}