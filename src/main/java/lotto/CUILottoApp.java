package lotto;

import java.util.List;

import lotto.domain.AutoLottoCreator;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class CUILottoApp {
    public static void main(String[] args) {
        Money money = InputView.createMoney();
        List<Lotto> lottos = LottoFactory.createLottos(money.getLottoSize(), new AutoLottoCreator());
        OutputView.printLottos(lottos);

        Lotto winnerLotto = InputView.createWinnerLotto();

    }
}
