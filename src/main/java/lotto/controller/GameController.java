package lotto.controller;

import lotto.View.InputView;
import lotto.View.OutputView;
import lotto.domain.Rank;
import lotto.domain.lottoData.Lotto;
import lotto.domain.lottoData.LottoGenerator;
import lotto.domain.lottoData.Lottos;
import lotto.domain.Money;
import lotto.domain.lottoData.WinningLotto;
import lotto.domain.stats.LottoResults;

import java.util.List;
import java.util.Map;

public class GameController {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static Money money;
    private static Lottos lottos;

    public GameController() {
        LottoGenerator.generate(LOTTO_MIN, LOTTO_MAX);
    }

    public void run() {
        inputMoney();
        buyLotto();
        OutputView.printResult(matchLottos());
    }

    private Map<Rank, Integer> matchLottos() {
        return lottos.matchCount(inputWinningLotto());
    }

    private WinningLotto inputWinningLotto() {
        String numbers = InputView.inputWinningLottoNumbers();
        String bonusNumber = InputView.inputBonusNumber();
        return new WinningLotto(numbers, bonusNumber);
    }


    private void buyLotto() {
        int quantity = money.lottoQuantity();
        List<Lotto> lottoGroup = LottoGenerator.makeLottos(quantity);
        lottos = new Lottos(lottoGroup);
        printPurchasedLottos(quantity);
    }

    private void printPurchasedLottos(int quantity) {
        OutputView.showLottoQuantity(quantity);
        OutputView.showLottos(lottos.values());
    }

    private void inputMoney() {
        money = new Money(InputView.inputMoney());
    }
}
