package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public void run() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(InputView.inputPurchaseAmount());
        OutputView.printCountMessage(purchaseAmount.countNumberOfPurchases());
        LottoTicket lottoTicket = issueTicket(purchaseAmount);

        MatchResults matchResults = new MatchResults(getMatchResultDtos(lottoTicket));
        WinningResult winningResult = new WinningResult(matchResults.getWinningResult());

        OutputView.printWinningStatics(matchResults.getWinningResult());
        OutputView.printProfitRate(winningResult.calculateProfitRate(purchaseAmount));
    }

    public LottoTicket issueTicket(final PurchaseAmount purchaseAmount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < purchaseAmount.countNumberOfPurchases(); i++) {
            List<Integer> lottoNumbers = LottoNumberGenerator.generate();
            OutputView.printLottos(lottoNumbers);
            lottos.add(new Lotto(lottoNumbers));
        }
        return new LottoTicket(lottos);
    }

    private List<MatchResultDto> getMatchResultDtos(final LottoTicket lottoTicket) {
        WinningNumber winningNumber = new WinningNumber(InputView.inputWinningNumbers());
        BonusNumber bonusNumber = new BonusNumber(InputView.inputBonusNumber());
        return lottoTicket.deriveMatchResults(winningNumber, bonusNumber);
    }
}
