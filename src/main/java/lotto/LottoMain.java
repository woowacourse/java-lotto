package lotto;

import lotto.controller.LottoAnalyzeManager;
import lotto.controller.LottoManager;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {
    public static void main(String[] args) {
        LottoManager lottoManager = new LottoManager(InputView.inputMoney());

        OutputView.printPurchaseCount(lottoManager.getMoney().calculateLottoTicketCount());
        OutputView.printLottoNumbers(lottoManager.getBuyer().getLottos());

        lottoManager.setWinningLotto(InputView.inputWinningLottoNumbers(), InputView.inputBonusNumber());

        LottoAnalyzeManager lottoAnalyzeManager = new LottoAnalyzeManager(lottoManager.getBuyer(), lottoManager.getWinningLotto());
        OutputView.printLottoResults(lottoAnalyzeManager.analyzeLotto());
        OutputView.printRewardRate(lottoAnalyzeManager.analyzeRewardRate(lottoManager.getMoney()));
    }
}