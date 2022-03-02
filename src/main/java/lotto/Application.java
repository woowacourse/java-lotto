package lotto;

import lotto.controller.LottoController;
import lotto.controller.userController;
import lotto.domain.lotto.LottoWinningNumbers;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.ManualLottos;
import lotto.domain.result.LottoResult;
import lotto.domain.user.Money;
import lotto.domain.user.PurchaseLottoCount;

public class Application {

    public static void main(final String... args) {
        userController userController = new userController();
        LottoController lottoController = new LottoController();

        Money money = userController.inputMoney();
        PurchaseLottoCount purchaseLottoCount = userController.inputPurchaseLottoCount(money.getCount());
        ManualLottos manualLotto = lottoController.inputManualLotto(purchaseLottoCount.getPurchaseLottoCount());

        Lottos lottos = lottoController.inputLottoMoney(money.getAutoMoney(purchaseLottoCount.getPurchaseLottoCount()));
        lottoController.printLottos(lottos, purchaseLottoCount.getPurchaseLottoCount(), purchaseLottoCount.getRemainPurchaseLottoCount(
                money.getCount()));
        lottos = lottoController.addLottos(lottos, manualLotto.getLottos());

        LottoWinningNumbers lottoWinningNumbers = lottoController.createLottoWinningNumbers();
        LottoResult lottoResult = lottoController.calculateRanks(lottos, lottoWinningNumbers);
        lottoController.printWinningResult(lottoResult);

        double profit = userController.calculateProfit(money, lottoResult);
        userController.printProfit(profit);
    }
}
