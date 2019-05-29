package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        Money money = new Money(InputView.insertMoney());
        int countOfCustomLottos = InputView.countOfCustomLottos();

        LottoPaper lottoPaper = createLottoPaper(money, countOfCustomLottos);

        OutputView.printPurchase(money, countOfCustomLottos);
        OutputView.printLottoPaper(lottoPaper);

        WinningLotto winningLotto = insertWinningLotto();

        Statistics statistics = new Statistics(lottoPaper.matchLotto(winningLotto));

        OutputView.printStatistics(statistics);
    }

    private static LottoPaper createLottoPaper(Money money, int countOfCustomLottos) {
        try {
            LottoOMRCard lottoOMRCard = new LottoOMRCard();

            for (int i = 0; i < countOfCustomLottos; i++) {
                lottoOMRCard.addCustomLotto(InputView.inputCustomLotto());
            }

            LottoVendingMachine lottoVendingMachine = new LottoVendingMachine();

            return lottoVendingMachine.buyLotto(money, lottoOMRCard.getPaper());
        } catch (Exception e) {
            return createLottoPaper(money, countOfCustomLottos);
        }
    }

    private static WinningLotto insertWinningLotto() {
        try {
            Lotto Lotto = CustomLottoGenerator.makeLotto(InputView.inputWinningLotto());
            LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());

            return new WinningLotto(Lotto, bonusNumber);
        } catch (Exception e) {
            return insertWinningLotto();
        }

    }
}
