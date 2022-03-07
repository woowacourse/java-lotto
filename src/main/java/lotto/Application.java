package lotto;

import lotto.controller.LottoController;
import lotto.controller.userController;
import lotto.domain.lotto.LottoWinningNumbers;
import lotto.domain.lotto.Lottos;
import lotto.domain.result.LottoResult;
import lotto.domain.user.Money;
import lotto.domain.user.PurchaseLottoCount;

public class Application {

    public static void main(final String... args) {
        userController userController = new userController();
        LottoController lottoController = new LottoController();

        Money money = userController.inputMoney();
        PurchaseLottoCount purchaseLottoCount = userController.calculatePurchaseLottoCountInfo(money);

        Lottos manualLottos = lottoController.createManualLottos(purchaseLottoCount);
        Lottos autoLottos = lottoController.createAutoLottos(purchaseLottoCount);

        Lottos totalLottos = lottoController.combineLottos(autoLottos, manualLottos);
        lottoController.printLottos(totalLottos, purchaseLottoCount);

        LottoWinningNumbers lottoWinningNumbers = lottoController.createLottoWinningNumbers();
        LottoResult lottoResult = lottoController.calculateRanks(totalLottos, lottoWinningNumbers);

        lottoController.printWinningResult(lottoResult);
        userController.printProfit(userController.calculateProfit(money, lottoResult));
    }
}
