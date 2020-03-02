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
    private static Money money;
    private static Buyer buyer;

    public static void run() {
        purchaseLotto();
        analyzeLotto();
    }

    private static void purchaseLotto() {
        int budget = InputView.inputMoney();
        money = new Money(budget);

        LottoAmount lottoAmount = calculateLottoAmount(money);
        buyer = issueLottos(lottoAmount);

        OutputView.printLottoAmount(lottoAmount);
        OutputView.printLottoNumbers(buyer.getLottos());
    }

    private static LottoAmount calculateLottoAmount(Money money) {
        int totalAmount = money.calculateTotalLottoAmount();
        int manualAmount = InputView.inputManualLottoAmount();

        return new LottoAmount(totalAmount, manualAmount);
    }

    private static Buyer issueLottos(LottoAmount lottoAmount) {
        List<String> manualLottoNumbers =
                InputView.inputManualLottoNumbers(lottoAmount.getManualLottoAmount());

        return new Buyer(manualLottoNumbers, lottoAmount);
    }

    private static void analyzeLotto() {
        WinningLotto winningLotto = readWinningLotto();
        LottoResult lottoResult = new LottoResult(buyer, winningLotto);

        OutputView.printLottoResults(lottoResult.getLottoResult());
        OutputView.printRewardRate(lottoResult.calculateRewardRate(money.getMoney()));
    }

    private static WinningLotto readWinningLotto() {
        List<LottoNumber> winningLottoNumbers =
                ConvertInput.convertLottoNumbers(InputView.inputWinningLottoNumbers());
        int bonusNumber = InputView.inputBonusNumber();

        return new WinningLotto(winningLottoNumbers, bonusNumber);
    }
}
