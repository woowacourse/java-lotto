package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.PurchaseAmount;
import lotto.view.Input;
import lotto.view.Output;

public class LottoController {
    private static final String DELIMITER = ",";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String ERROR_DUPLICATED_NUMBER = "번호가 중복됩니다!";

    private final LottoMachine lottoMachine = new LottoMachine();

    public void run() {
        Lottos lottos = buyLotto();

        Lotto lastWeeksWinningNumber = createLastWeeksWinningNumber();
        Ball bonusBall = createBonusBall();

        checkDuplicatedNumber(lastWeeksWinningNumber, bonusBall);
    }

    private Lottos buyLotto() {
        int lottoCount = getLottoCount();
        Lottos lottos = new Lottos(lottoCount);

        Output.lottoCount(lottoCount);
        Output.lottos(lottos);

        return lottos;
    }

    private int getLottoCount() {
        Output.askPurchaseAmount();

        PurchaseAmount purchaseAmount = new PurchaseAmount(Input.purchaseAmount());
        return lottoMachine.getLottoCount(purchaseAmount);
    }

    private Lotto createLastWeeksWinningNumber() {
        Output.askLastWeeksWinningNumber();
        List<String> numbers = Arrays.stream(Input.lastWeeksWinningNumbers().split(DELIMITER))
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
