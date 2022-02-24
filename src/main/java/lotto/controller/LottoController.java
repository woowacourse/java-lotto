package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Profit;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningLotto;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    private static final String DELIMITER = ",";

    private final LottoMachine lottoMachine = new LottoMachine();

    public void run() {
        Output.askPurchaseAmount();
        PurchaseAmount purchaseAmount = createPurchaseAmount();

        Lottos lottos = buyLotto(purchaseAmount);

        createLottoResult(lottos, purchaseAmount);
    }

    private PurchaseAmount createPurchaseAmount() {
        try {
            return new PurchaseAmount(Input.purchaseAmount());
        } catch (IllegalArgumentException error) {
            Output.error(error.getMessage());
            return createPurchaseAmount();
        }
    }

    private Lottos buyLotto(PurchaseAmount purchaseAmount) {
        int lottoCount = getLottoCount(purchaseAmount);
        Lottos lottos = new Lottos(lottoCount);

        Output.lottoCount(lottoCount);
        Output.lottos(lottos);

        return lottos;
    }

    private int getLottoCount(PurchaseAmount purchaseAmount) {
        return lottoMachine.getLottoCount(purchaseAmount);
    }

    private void createLottoResult(Lottos lottos, PurchaseAmount purchaseAmount) {
        Output.askWinNumber();
        Lotto winLotto = createWinNumber();
        Output.askBonusBall();
        WinningLotto winningLotto = createWinningLotto(winLotto);

        showResult(lottos, purchaseAmount, winningLotto);
    }

    private Lotto createWinNumber() {
        try {
            List<String> numbers = Arrays.stream(Input.winNumber().split(DELIMITER))
                    .map(String::trim)
                    .collect(Collectors.toList());
            return new Lotto(numbers);
        } catch (IllegalArgumentException error) {
            Output.error(error.getMessage());
            return createWinNumber();
        }
    }

    private WinningLotto createWinningLotto(Lotto winLotto) {
        try {
            Ball bonusBall = new Ball(Input.bonusBall());
            return new WinningLotto(winLotto, bonusBall);
        } catch (IllegalArgumentException error) {
            Output.error(error.getMessage());
            return createWinningLotto(winLotto);
        }
    }

    private void showResult(Lottos lottos, PurchaseAmount purchaseAmount, WinningLotto winningLotto) {
        Output.statisticsTitle();

        LottoResult lottoResult = new LottoResult();
        lottos.addMatchingCount(lottoResult, winningLotto);
        Output.lottoResult(lottoResult);

        Profit profit = new Profit();
        double profitRate = profit.calculate(lottoResult.getTotalMoney(), purchaseAmount);
        Output.profitRate(profitRate);
    }
}
