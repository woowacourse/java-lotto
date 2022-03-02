package lotto;

import lotto.controller.LottoController;
import lotto.controller.MoneyController;
import lotto.domain.lotto.LottoWinningNumbers;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.ManualLottos;
import lotto.domain.result.LottoResult;
import lotto.domain.user.Money;
import lotto.domain.user.PurchaseLottoCount;

public class Application {

    public static void main(final String... args) {
        MoneyController moneyController = new MoneyController();
        LottoController lottoController = new LottoController();

        Money money = moneyController.inputMoney();
        PurchaseLottoCount purchaseLottoCount = moneyController.inputPurchaseLottoCount(money.getCount());
        ManualLottos manualLotto = lottoController.inputManualLotto(purchaseLottoCount.getPurchaseLottoCount());

        Lottos lottos = lottoController.inputLottoMoney(money.getAutoMoney(purchaseLottoCount.getPurchaseLottoCount()));
        lottoController.printLottos(lottos, purchaseLottoCount.getPurchaseLottoCount(), purchaseLottoCount.getRemainPurchaseLottoCount(
                money.getCount()));
        lottos = lottoController.addLottos(lottos, manualLotto.getLottos());

        LottoWinningNumbers lottoWinningNumbers = lottoController.createLottoWinningNumbers();
        LottoResult lottoResult = lottoController.calculateRanks(lottos, lottoWinningNumbers);
        lottoController.printWinningResult(lottoResult);

        double profit = moneyController.calculateProfit(money, lottoResult);
        moneyController.printProfit(profit);
    }
}
