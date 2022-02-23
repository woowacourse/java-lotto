package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.RandomLottoMachine;
import lotto.domain.WinLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(String[] args) {
        final Money money = new Money(InputView.inputMoney());
        final int buyCounts = money.calculateLottoCount();
        OutputView.outputBuyLottoCounts(buyCounts);

        final Lottos lottos = new Lottos(buyRandomLottos(buyCounts));
        OutputView.outputLottos(lottos.getLottos());

        final WinLotto winLotto = new WinLotto(inputWinLotto(), inputBonusNumber());
        OutputView.outputResult(lottos.createResult(winLotto));
    }

    private static Lotto inputWinLotto() {
        return new Lotto(InputView.inputWinLotto().stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private static LottoNumber inputBonusNumber() {
        return new LottoNumber(InputView.inputBonusNumber());
    }

    private static List<Lotto> buyRandomLottos(final int buyCounts) {
        return IntStream.rangeClosed(0, buyCounts)
                .mapToObj(index -> new Lotto(RandomLottoMachine.createRandomLottoNumbers()))
                .collect(Collectors.toList());
    }
}
