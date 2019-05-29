package lotto;

import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.domain.*;

public class Main {
    public static void main(String[] args) {
        Money money = new Money(InputView.insertMoney());
        int countOfCustomLottos = InputView.countOfCustomLottos();

        CustomLottoPaper customLottoPaper = new CustomLottoPaper();

        for (int i = 0; i < countOfCustomLottos; i++) {
            customLottoPaper.addCustomLotto(InputView.inputCustomLotto());
        }

        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine();
        LottoPaper lottoPaper = lottoVendingMachine.buyLotto(money, customLottoPaper.getPaper());

        OutputView.printPurchase(money, countOfCustomLottos);
        OutputView.printLottoPaper(lottoPaper);

        Lotto Lotto = new Lotto(customLottoPaper.makeNumbers(InputView.inputWinningLotto()));
        LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());

        WinningLotto winningLotto = new WinningLotto(Lotto, bonusNumber);

        Statistics statistics = new Statistics(lottoPaper.matchLotto(winningLotto));

        OutputView.printStatistics(statistics);
    }
}
