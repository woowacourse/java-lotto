package lotto;

import static lotto.view.ErrorView.printErrorMessage;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

    public static void main(final String[] args) {
        final LottoGenerator lottoGenerator = createLottoGenerator(payMoney());
        final Lottos lottos = lottoGenerator.generateLottos(inputManualLottoNumbers(lottoGenerator));

        OutputView.outputBuyLottoCounts(lottoGenerator.getManualCount(), lottoGenerator.getAutoCount());
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

    private static List<List<Integer>> inputManualLottoNumbers(final LottoGenerator lottoGenerator) {
        try {
            return InputView.inputManualLottos(lottoGenerator.getManualCount());
        } catch (IllegalArgumentException e) {
            printErrorMessage(e);
            return inputManualLottoNumbers(lottoGenerator);
        }
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
