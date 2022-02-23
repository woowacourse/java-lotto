package lotto;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.RandomLottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        final Money money = new Money(InputView.inputMoney());
        final int buyCounts = money.calculateLottoCount();
        OutputView.outputBuyLottoCounts(buyCounts);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < buyCounts; i++) {
            lottos.add(new Lotto(RandomLottoMachine.createRandomLottoNumbers()));
        }
        OutputView.outputLottos(lottos);

    }
}
