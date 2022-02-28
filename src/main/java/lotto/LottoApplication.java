package lotto;

import static lotto.view.ErrorView.printErrorMessage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.RandomLottoMachine;
import lotto.domain.WinLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(final String[] args) {
        final Money money = payMoney();
        final int buyCounts = money.calculateLottoCount();
        OutputView.outputBuyLottoCounts(buyCounts);

        final Lottos lottos = buyLottos(buyCounts);
        OutputView.outputLottos(lottos.getLottos());

        final WinLotto winLotto = revealWinLotto();
        OutputView.outputResult(LottoResult.createLottoResult(lottos, winLotto));
    }

    private static Money payMoney() {
        try {
            return new Money(InputView.inputMoney());
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return payMoney();
        }
    }

    private static LottoGenerator createLottoGenerator(final Money money) {
        try {
            return LottoGenerator.of(money, InputView.inputManualCount());
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return createLottoGenerator(money);
        }
    }

    private static Lottos buyLottos(final int buyCounts) {
        try {
            return new Lottos(buyRandomLottos(buyCounts));
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return buyLottos(buyCounts);
        }
    }

    private static List<Lotto> buyRandomLottos(final int buyCounts) {
        return IntStream.range(0, buyCounts)
                .mapToObj(index -> new Lotto(RandomLottoMachine.createRandomLottoNumbers()))
                .collect(Collectors.toList());
    }

    private static WinLotto revealWinLotto() {
        try {
            return new WinLotto(inputWinLotto(), inputBonusNumber());
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return revealWinLotto();
        }
    }

    private static Lotto inputWinLotto() {
        return new Lotto(InputView.inputWinLotto().stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet()));
    }

    private static LottoNumber inputBonusNumber() {
        return LottoNumber.valueOf(InputView.inputBonusNumber());
    }
}
