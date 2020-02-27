package lotto.controller;

import lotto.domain.Buyer;
import lotto.domain.Money;
import lotto.domain.lottoTicket.LottoAmount;
import lotto.domain.lottoTicket.LottoNumber;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.domain.result.LottoResult;
import lotto.util.ConvertInput;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoManager {
    public static void run() {
        Money money = new Money(InputView.inputMoney());
        LottoAmount lottoAmount = purchaseLotto(money);
        Buyer buyer = makeLottos(lottoAmount);
        OutputView.printLottoAmount(lottoAmount);
        OutputView.printLottoNumbers(buyer.getLottos());
        WinningLotto winningLotto = readWinningLotto();
        LottoResult lottoResult = analyzeLotto(buyer, winningLotto);
        OutputView.printLottoResults(lottoResult.getLottoResult());
        OutputView.printRewardRate(analyzeRwardRate(lottoResult, money));
    }

    private static LottoAmount purchaseLotto(Money money) {
        return new LottoAmount(
                money.calculateTotalLottoAmount(),
                InputView.inputManualLottoAmount()
        );
    }

    private static Buyer makeLottos(LottoAmount lottoAmount) {
        List<String> manualLottos =
                InputView.inputManualLottoNumbers(lottoAmount.getManualLottoAmount());
        return new Buyer(manualLottos, lottoAmount);
    }

    private static WinningLotto readWinningLotto() {
        List<LottoNumber> winningLottoNumbers
                = ConvertInput.convertLottoNumbers(InputView.inputWinningLottoNumbers());
        int bonusNumber = InputView.inputBonusNumber();

        return new WinningLotto(winningLottoNumbers, bonusNumber);
    }

    private static LottoResult analyzeLotto(Buyer buyer, WinningLotto winningLotto) {
        return new LottoResult(buyer, winningLotto);
    }

    private static int analyzeRwardRate(LottoResult lottoResult, Money money) {
        return lottoResult.calculateRewardRate(money.getMoney());
    }
}
