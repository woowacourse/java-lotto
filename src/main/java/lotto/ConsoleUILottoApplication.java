package lotto;

import lotto.domain.*;
import lotto.domain.generator.LottoNosManualGenerator;
import lotto.domain.generator.LottosGenerator;
import lotto.view.InputConsoleView;
import lotto.view.InputView;
import lotto.view.OutputConsoleView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleUILottoApplication {
    private static InputView inputView = new InputConsoleView();
    private static OutputView outputView = new OutputConsoleView();

    public static void main(String[] args) {
        Money money = Money.from(inputView.inputMoney());
        CountOfManual countOfManual = CountOfManual.from(inputView.inputCountOfManual(), money.getCountOfPurchase());
        List<Lotto> userLottos = generateLottos(countOfManual, money);
        outputView.printLottos(userLottos, countOfManual.value(), money.getCountOfPurchase());

        WinningLotto winningLotto = generateWinningLotto();
        WinPrize winPrize = generateWinPrize(userLottos, winningLotto);

        outputView.printResult(winPrize);
        outputView.printRateOfProfit(money, winPrize);
    }

    private static List<Lotto> generateLottos(final CountOfManual countOfManual, final Money money) {
        List<String> lottoNoStrings = new ArrayList<>();
        inputView.printInputManual();
        for (int i = 0; i < countOfManual.value(); i++) {
            lottoNoStrings.add(inputView.inputManual());
        }
        return LottosGenerator.of(lottoNoStrings, money.getCountOfPurchase()).generate();
    }


    private static WinningLotto generateWinningLotto() {
        Lotto winLotto = Lotto.of(new LottoNosManualGenerator(inputView.inputWinningLotto()).generate());
        LottoNo bonusNo = LottoNo.from(inputView.inputBonusNo());
        return new WinningLotto(winLotto, bonusNo);
    }

    private static WinPrize generateWinPrize(final List<Lotto> userLottos, final WinningLotto winningLotto) {
        WinPrize winPrize = new WinPrize();
        for (final Lotto userLotto : userLottos) {
            winPrize.addWinCount(winningLotto.match(userLotto));
        }
        return winPrize;
    }
}
