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
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    private static final String DELIMITER = ",";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";

    private final LottoMachine lottoMachine = new LottoMachine();

    public void run() {
        Output.askPurchaseAmount();
        PurchaseAmount purchaseAmount = new PurchaseAmount(Input.purchaseAmount());

        Lottos lottos = buyLotto(purchaseAmount);

        createLottoResult(lottos, purchaseAmount);
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
        Lotto winNumber = createWinNumber();
        Ball bonusBall = createBonusBall();
        checkDuplicatedNumber(winNumber, bonusBall);

        showResult(lottos, purchaseAmount, winNumber, bonusBall);
    }

    private void showResult(Lottos lottos, PurchaseAmount purchaseAmount, Lotto winNumber, Ball bonusBall) {
        Output.statisticsTitle();

        LottoResult lottoResult = new LottoResult();
        lottos.addMatchingCount(lottoResult, winNumber, bonusBall);
        Output.lottoResult(lottoResult);

        Profit profit = new Profit();
        double profitRate = profit.calculate(lottoResult.getTotalMoney(), purchaseAmount);
        Output.profitRate(profitRate);
    }

    private Lotto createWinNumber() {
        Output.askWinNumber();
        List<String> numbers = Arrays.stream(Input.winNumber().split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    private Ball createBonusBall() {
        Output.askBonusBall();
        return new Ball(Input.bonusBall());
    }

    private void checkDuplicatedNumber(Lotto lotto, Ball ball) {
        if (lotto.contains(ball)) {
            throw new IllegalArgumentException(ERROR_PREFIX + ERROR_DUPLICATED_NUMBER);
        }
    }
}
