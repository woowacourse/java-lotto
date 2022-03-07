

import domain.Lotto;
import domain.LottoGenerator;

import domain.LottoNumber;
import domain.Lottos;

import domain.Money;

import domain.WinningChecker;
import domain.WinningNumbers;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class LottoController {

    public static void main(String[] args) {

        Money money = new Money(InputView.askMoneyInput(), InputView.askManualAmount());

        LottoGenerator lottoGenerator = new LottoGenerator(
            askManualLottoNumbers(money), money.getAutoAmount());

        Lottos lottos = new Lottos(lottoGenerator.generate());

        OutputView.printLottosInformation(money, lottos);

        WinningChecker winningChecker = new WinningChecker(lottos, askWinningNumbers());

        winningChecker.check();

        OutputView.printWinningStatistic(winningChecker.getStatisticMap());
        OutputView.printYield(winningChecker.getYield());
    }

    public static WinningNumbers askWinningNumbers() {

        return new WinningNumbers(new Lotto(InputView.askWinningNumber()
            .stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList())),
            new LottoNumber(InputView.askBonusNumber()));

    }

    public static List<List<LottoNumber>> askManualLottoNumbers(Money money) {

        return InputView.askManualLottoNumbers(money.getManualAmount())
            .stream()
            .map(number -> number.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList()))
            .collect(Collectors.toList());

    }
}
