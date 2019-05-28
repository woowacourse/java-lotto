package lotto;

import lotto.domain.*;
import lotto.domain.generator.LottoNosAutoGenerator;
import lotto.domain.generator.LottoNosGenerator;
import lotto.domain.generator.LottoNosManualGenerator;
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
        int countOfManual = inputView.inputCountOfManual();
        validateCountOfManual(money, countOfManual);
        List<Lotto> userLottos = inputManualLotto(countOfManual);
        addAutoLotto(money, countOfManual, userLottos);
        outputView.printLottos(userLottos, countOfManual, money.getCountOfPurchase());

        WinningLotto winningLotto = generateWinningLotto();
        WinPrize winPrize = getWinPrize(userLottos, winningLotto);

        outputView.printResult(winPrize);
        outputView.printRateOfProfit(money, winPrize);
    }

    private static void validateCountOfManual(final Money money, final int countOfManual) {
        if (countOfManual>money.getCountOfPurchase()){
            throw new IllegalArgumentException("구입 금액 초과");
        }
    }

    private static List<Lotto> inputManualLotto(final int countOfManual) {
        inputView.printInputManual();
        List<Lotto> userLottos = new ArrayList<>();
        for (int i = 0; i < countOfManual; i++) {
            List<LottoNo> lottoNos = new LottoNosManualGenerator(inputView.inputManual()).generate();
            userLottos.add(Lotto.of(lottoNos));
        }
        return userLottos;
    }

    private static void addAutoLotto(final Money money, final int countOfManual, final List<Lotto> userLottos) {
        LottoNosGenerator lottoNosGenerator = new LottoNosAutoGenerator();
        for (int i = 0; i < money.getCountOfPurchase() - countOfManual; i++) {
            List<LottoNo> lottoNos = lottoNosGenerator.generate();
            userLottos.add(Lotto.of(lottoNos));
        }
    }

    private static WinningLotto generateWinningLotto() {
        Lotto winLotto = Lotto.of(new LottoNosManualGenerator(inputView.inputWinningLotto()).generate());
        LottoNo bonusNo = LottoNo.from(inputView.inputBonusNo());
        return new WinningLotto(winLotto, bonusNo);
    }

    private static WinPrize getWinPrize(final List<Lotto> userLottos, final WinningLotto winningLotto) {
        WinPrize winPrize = new WinPrize();
        for (final Lotto userLotto : userLottos) {
            winPrize.addWinCount(winningLotto.match(userLotto));
        }
        return winPrize;
    }
}
