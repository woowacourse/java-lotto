package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;
    public static final String MONEY_NOT_ENOUGH_ERROR_MESSAGE = "금액으로 구매 가능한 로또의 매수보다 많은 양입니다.";

    public void process() {
        Lottos purchasedLottos = buyLotto();
        WinningLotto winningLotto = decideWinningLotto();
        Map<LottoRank, Integer> lottoResultStatistics = purchasedLottos.getStatistics(winningLotto);
        printLottoResult(lottoResultStatistics, purchasedLottos);
    }

    public Lottos buyLotto() {
        Money money = new Money(InputView.inputMoney());
        int affordableLottoTicketCount = calculateAffordableLottoTickets(money);
        List<String> manualLottoNumbers = manualLottoNumbers(affordableLottoTicketCount);
        Lottos purchasedLottos = new Lottos(manualLottoNumbers, affordableLottoTicketCount);
        OutputView.printPurchasedLottos(purchasedLottos);
        return purchasedLottos;
    }

    private List<String> manualLottoNumbers(int affordableLottoTicketCount) {
        int manualLottoCount = InputView.inputManualLottoCount();
        if (manualLottoCount == 0) {
            return new ArrayList<>();
        }
        if (manualLottoCount > affordableLottoTicketCount) {
            throw new IllegalArgumentException(MONEY_NOT_ENOUGH_ERROR_MESSAGE);
        }
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