package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;


import java.util.ArrayList;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;
    public static final String MONEY_NOT_ENOUGH_ERROR_MESSAGE = "[ERROR] 금액으로 구매 가능한 로또의 매수보다 많은 양입니다.";
    public static final String LOTTO_COUNT_NOT_AVAILABLE_ERROR_MESSAGE = "[ERROR] 구매할 로또의 수는 0매 이상이어야 합니다.";

    public void run() {
        try {
            process();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    private void process() {
        Lottos purchasedLottos = buyLotto();
        WinningLotto winningLotto = decideWinningLotto();
        Map<LottoRank, Integer> lottoResultStatistics = purchasedLottos.getStatistics(winningLotto);
        printLottoResult(lottoResultStatistics, purchasedLottos);
    }

    private Lottos buyLotto() {
        Money money = new Money(InputView.inputMoney());
        int affordableLottoTicketCount = calculateAffordableLottoTickets(money);
        List<String> manualLottoNumbers = manualLottoNumbers(affordableLottoTicketCount);
        Lottos purchasedManualLottos = LottoGenerator.createManualLottos(manualLottoNumbers);
        Lottos purchasedAutoLottos =
                LottoGenerator.createAutoLottos(affordableLottoTicketCount - manualLottoNumbers.size());
        Lottos purchasedLottos = new Lottos(purchasedManualLottos, purchasedAutoLottos);
        OutputView.printPurchasedLottos(manualLottoNumbers.size(), purchasedLottos);
        return purchasedLottos;
    }

    private List<String> manualLottoNumbers(int affordableLottoTicketCount) {
        int manualLottoCount = InputView.inputManualLottoCount();
        if (manualLottoCount == 0) {
            return new ArrayList<>();
        }
        validateManualLottoCount(affordableLottoTicketCount, manualLottoCount);
        return InputView.inputManualLottoNumbers(manualLottoCount).stream()
                .sorted().collect(Collectors.toList());
    }

    private void validateManualLottoCount(int affordableLottoTicketCount, int manualLottoCount) {
        if (manualLottoCount < 0) {
            throw new IllegalArgumentException(LOTTO_COUNT_NOT_AVAILABLE_ERROR_MESSAGE);
        }
        if (manualLottoCount > affordableLottoTicketCount) {
            throw new IllegalArgumentException(MONEY_NOT_ENOUGH_ERROR_MESSAGE);
        }
    }

    private WinningLotto decideWinningLotto() {
        Lotto winningLotto = Lotto.from(InputView.inputWinningNumbers());
        BonusBall bonusBall = new BonusBall(InputView.inputBonusNumber(), winningLotto);
        return new WinningLotto(winningLotto, bonusBall);
    }

    private void printLottoResult(Map<LottoRank, Integer> lottoResultStatistics, Lottos lottos) {
        double profitRate = calculateProfitRate(lottoResultStatistics, lottos.getSize());
        OutputView.printLottoStatistics(lottoResultStatistics, profitRate);
    }

    private double calculateProfitRate(Map<LottoRank, Integer> lottoResultStatistics, int purchasedLottoCount) {
        int initialCapital = purchasedLottoCount * LOTTO_PRICE;

        double sum = lottoResultStatistics.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();

        double rawProfitRate = sum / initialCapital;

        return Math.round(rawProfitRate * 100) / 100.00;
    }

    private int calculateAffordableLottoTickets(Money money) {
        return money.getMoney() / LOTTO_PRICE;
    }
}