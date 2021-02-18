package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Map;
import java.util.Map.Entry;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    public void process() {
        Lottos lottos = buyLotto();
        WinningLotto winningLotto = new WinningLotto(new Lotto(InputView.inputWinningNumbers()), InputView.inputBonusNumber());
        Map<LottoRank, Integer> lottoResultStatistics = lottos.getStatistics(winningLotto);
        printLottoResult(lottoResultStatistics, lottos);
    }

    public Lottos buyLotto() {
        Money money = new Money(InputView.inputMoney());
        Lottos purchasedLottos = new Lottos(calculateAffordableLottoTickets(money));
        OutputView.printPurchasedLottos(purchasedLottos);
        return purchasedLottos;
    }

    public void printLottoResult(Map<LottoRank, Integer> lottoResultStatistics, Lottos lottos) {
        double profitRate = calculateProfitRate(lottoResultStatistics, lottos.getSize());
        OutputView.printLottoStatistics(lottoResultStatistics, profitRate);
    }

    public double calculateProfitRate(Map<LottoRank, Integer> lottoResultStatistics, int purchasedLottoCount) {
        double sum = 0;
        for (Entry<LottoRank, Integer> keyValue : lottoResultStatistics.entrySet()) {
            sum += keyValue.getKey().getPrizeMoney() * keyValue.getValue();
        }
        double initialCapital = purchasedLottoCount * LOTTO_PRICE;
        double rawProfitRate = sum / initialCapital;
        return Math.round(rawProfitRate * 100) / 100.00;
    }

    public int calculateAffordableLottoTickets(Money money) {
        return money.getAffordableLottoTickets(LOTTO_PRICE);
    }
}