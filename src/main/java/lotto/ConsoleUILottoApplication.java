package lotto;

import lotto.domain.*;
import lotto.service.LottoService;
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
        CountOfManual countOfManual = CountOfManual.of(inputView.inputCountOfManual(), money.getCountOfPurchase());
        List<String> manualLottos = inputManualLottos(countOfManual);
        List<Lotto> userLottos = LottoService.generateLottos(manualLottos, money);
        outputView.printLottos(userLottos, countOfManual.value(), money.getCountOfPurchase());

        WinningLotto winningLotto = LottoService.generateWinningLotto(inputView.inputWinningLotto(), inputView.inputBonusNo());
        WinPrize winPrize = LottoService.generateWinPrize(userLottos, winningLotto);
        outputView.printResult(winPrize);
        outputView.printRateOfProfit(money, winPrize);
    }

    private static List<String> inputManualLottos(final CountOfManual countOfManual) {
        List<String> lottoNoStrings = new ArrayList<>();
        inputView.printInputManual();
        for (int i = 0; i < countOfManual.value(); i++) {
            lottoNoStrings.add(inputView.inputManual());
        }
        return lottoNoStrings;
    }
}
