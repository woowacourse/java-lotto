package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.LottosFactory;
import lotto.domain.Money;
import lotto.domain.ResultStatistic;
import lotto.domain.WinningInformation;
import lotto.view.IllegalUserInputException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ManualLottoController {

    public static void run() {
        try {
            runWithoutExceptionCatch();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
        }
    }

    private static void runWithoutExceptionCatch() {
        Money money = getMoney();
        int amountOfManualLottos = getAmountOfManualLottos(money);
        Lottos lottos = getLottos(money, amountOfManualLottos);

        printPurchasedLottos(lottos, amountOfManualLottos);

        WinningInformation winningInformation = getWinningInformation();

        ResultStatistic result = ResultStatistic.calculate(lottos, winningInformation);
        OutputView.printResultStatistic(result, money);
    }

    private static Money getMoney() {
        Money money = new Money(InputView.getMoneyForLotto());
        LottosFactory.validateMoneyIsEnough(money);
        return money;
    }

    private static int getAmountOfManualLottos(Money money) {
        int amountOfManualLottos = InputView.getManualLottoAmount();

        if (amountOfManualLottos > LottosFactory.getHowMuchCanBuyLottoWith(money)) {
            throw new IllegalUserInputException(
                "수동으로 구매할 로또의 갯수가 전체 로또의 갯수보다 클 수 없습니다."
            );
        }
        return amountOfManualLottos;
    }

    private static Lottos getLottos(Money money, int manualLottosAmount) {
        return LottosFactory.createLottosManual(
            money,
            InputView.getManualLottos(manualLottosAmount)
        );
    }

    private static void printPurchasedLottos(Lottos lottos, int amountOfManualLottos) {
        int amountOfAutoLottos = lottos.getAmountOfLottos() - amountOfManualLottos;
        OutputView.printPurchasedLottos(amountOfManualLottos, amountOfAutoLottos, lottos);
    }

    private static WinningInformation getWinningInformation() {
        Lotto winningLotto = Lotto.createLottoManual(InputView.getWinningLotto());
        LottoNumber bonus = LottoNumber.of(InputView.getBonusLottoNumber());
        return new WinningInformation(winningLotto, bonus);
    }
}
