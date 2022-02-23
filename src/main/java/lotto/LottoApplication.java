package lotto;

import static lotto.view.ErrorView.printErrorMessage;

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
        final Money money = createMoney();
        final int buyCounts = money.calculateLottoCount();
        OutputView.outputBuyLottoCounts(buyCounts);

        final Lottos lottos = createLottos(buyCounts);
        OutputView.outputLottos(lottos.getLottos());

        final WinLotto winLotto = createWinLotto();
        OutputView.outputResult(lottos.createResult(winLotto));
    }

    private static Money createMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return createMoney();
        }
    }

    private static Lottos createLottos(int buyCounts) {
        try {
            return new Lottos(buyRandomLottos(buyCounts));
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return createLottos(buyCounts);
        }
    }

    private static List<Lotto> buyRandomLottos(final int buyCounts) {
        return IntStream.range(0, buyCounts)
                .mapToObj(index -> new Lotto(RandomLottoMachine.createRandomLottoNumbers()))
                .collect(Collectors.toList());
    }

    private static WinLotto createWinLotto() {
        try {
            return new WinLotto(inputWinLotto(), inputBonusNumber());
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return createWinLotto();
        }
    }

    private static Lotto inputWinLotto() {
        return new Lotto(InputView.inputWinLotto().stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private static LottoNumber inputBonusNumber() {
        return new LottoNumber(InputView.inputBonusNumber());
    }
}
