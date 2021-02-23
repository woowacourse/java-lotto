package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;

    public void process() {
        Lottos purchasedLottos = buyLotto();
        WinningLotto winningLotto = decideWinningLotto();
        Map<LottoRank, Integer> lottoResultStatistics = purchasedLottos.getStatistics(winningLotto);
        printLottoResult(lottoResultStatistics, purchasedLottos);
    }

    public Lottos buyLotto() {
        Money money = new Money(InputView.inputMoney());
        Lottos purchasedLottos = new Lottos(manualLottoNumbers(), calculateAffordableLottoTickets(money));
        OutputView.printPurchasedLottos(purchasedLottos);
        return purchasedLottos;
    }

    private List<String> manualLottoNumbers() {
        int manualLottoCount = InputView.inputManualLottoCount();
        return InputView.inputManualLottoNumbers(manualLottoCount);
    }

    private WinningLotto decideWinningLotto() {
        Lotto winningLotto = Lotto.from(InputView.inputWinningNumbers());
        BonusBall bonusBall = new BonusBall(InputView.inputBonusNumber(), winningLotto);
        return new WinningLotto(winningLotto, bonusBall);
    }

    public void printLottoResult(Map<LottoRank, Integer> lottoResultStatistics, Lottos lottos) {
        double profitRate = calculateProfitRate(lottoResultStatistics, lottos.getSize());
        OutputView.printLottoStatistics(lottoResultStatistics, profitRate);
    }

    public double calculateProfitRate(Map<LottoRank, Integer> lottoResultStatistics, int purchasedLottoCount) {
        int initialCapital = purchasedLottoCount * LOTTO_PRICE;

        double sum = lottoResultStatistics.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        double rawProfitRate = sum / initialCapital;

        return Math.round(rawProfitRate * 100) / 100.00;
    }

    public int calculateAffordableLottoTickets(Money money) {
        return money.getMoney() / LOTTO_PRICE;
    }
}