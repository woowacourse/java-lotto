package lotto.controller;

import lotto.domain.LottosFactory;
import lotto.domain.Money;
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
        Money money = new Money(InputView.getMoneyForLotto());
        int amountOfManualLottos = getAmountOfManualLottos(money);
        // Todo: 수동생성 로또 번호 입력받기 구현
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
}
