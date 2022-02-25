package lotto;

import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinLotto;
import lotto.view.InputConvertor;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(final String[] args) {
        final Money money = InputConvertor.createMoney();
        final int buyCounts = money.calculateLottoCount();
        OutputView.outputBuyLottoCounts(buyCounts);

        final Lottos lottos = InputConvertor.createLottos(buyCounts);
        OutputView.outputLottos(lottos.getLottos());

        final WinLotto winLotto = InputConvertor.createWinLotto();
        OutputView.outputResult(lottos.createResult(winLotto));
    }
}
