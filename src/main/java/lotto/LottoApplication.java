package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.RandomLottoMachine;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        final Money money = new Money(InputView.inputMoney());
        final int buyCounts = money.calculateLottoCount();
        OutputView.outputBuyLottoCounts(buyCounts);

        Lottos lottos = new Lottos(buyRandomLottos(buyCounts));
        OutputView.outputLottos(lottos.getLottos());

    }

    private static List<Lotto> buyRandomLottos(int buyCounts) {
        return IntStream.rangeClosed(0, buyCounts)
                .mapToObj(index -> new Lotto(RandomLottoMachine.createRandomLottoNumbers()))
                .collect(Collectors.toList());
    }
}
