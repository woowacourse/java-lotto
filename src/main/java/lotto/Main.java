package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        Money money = new Money(InputView.insertMoney());
        CustomLottoCount CustomLottoCount = new CustomLottoCount(money, InputView.countOfCustomLottos());

        LottoPaper lottoPaper = makeLottoPaper(money, CustomLottoCount);

        OutputView.printPurchase(money, CustomLottoCount);
        OutputView.printLottoPaper(lottoPaper);

        WinningLotto winningLotto = makeWinningLotto();

        Statistics statistics = new Statistics(lottoPaper.matchLotto(winningLotto));

        OutputView.printStatistics(statistics);
    }

    private static LottoPaper makeLottoPaper(Money money, CustomLottoCount customLottoCount) {
        LottoOMRCard lottoOMRCard = new LottoOMRCard();
        int count = customLottoCount.getCount();

        for (int i = 0; i < count; i++) {
            lottoOMRCard.addCustomLotto(InputView.inputCustomLotto());
        }

        LottoVendingMachine lottoVendingMachine = new LottoVendingMachine();

        return lottoVendingMachine.buyLotto(money, lottoOMRCard);

    }

    private static WinningLotto makeWinningLotto() {
        Lotto Lotto = new CustomLottoGenerator(InputView.inputWinningLotto()).makeLotto();
        LottoNumber bonusNumber = LottoNumber.generateNumber(InputView.inputBonusNumber());

        return new WinningLotto(Lotto, bonusNumber);
    }
}
