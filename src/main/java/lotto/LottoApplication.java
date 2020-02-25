package lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.Lottos;
import lotto.domain.LottosFactory;
import lotto.domain.Money;
import lotto.domain.TotalResult;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        try {
            start();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private static void start() {
        LottoCount count = getCountByMoney();
        List<String> rawManualLottos = getRawManualLottos(count);
        OutputView.printLottoCount(count);

        Lottos lottos = LottosFactory.createLottos(rawManualLottos, count);
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = getWinningLotto();
        TotalResult result = winningLotto.getResult(lottos);
        OutputView.printStatistics(result);
    }

    private static List<String> getRawManualLottos(LottoCount count) {
        List<String> rawManualLottos = new ArrayList<>();
        if (count.hasManualLottoCount()) {
            rawManualLottos.addAll(InputView.inputManualLottos(count));
        }
        return rawManualLottos;
    }

    private static LottoCount getCountByMoney() {
        int inputMoney = InputView.inputMoney();
        Money money = new Money(inputMoney);
        return money.getLottoCount(InputView.inputManualCount());
    }

    private static WinningLotto getWinningLotto() {
        Lotto winningLotto = Lotto.of(InputView.inputWinningLotto());
        Ball bonusBall = Ball.of(InputView.inputWinningBonusBall());
        return new WinningLotto(winningLotto, bonusBall);
    }
}
