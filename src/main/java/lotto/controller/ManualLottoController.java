package lotto.controller;

import lotto.domain.Money;
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
        // Todo: 수동생성할 로또 갯수 입력받기
    }
}
