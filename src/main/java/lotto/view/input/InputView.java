package lotto.view.input;

import java.util.List;

public interface InputView {
    String inputPurchaseAmount();

    String inputManualPurchaseAmount();

    List<String> inputManualLottoNumbers(final int manualPurchaseAmount);

    List<String> inputLastWeekWinningNumbers();

    String inputBonusNumber();

    void printErrorMessage(final String errorMessage);
}
