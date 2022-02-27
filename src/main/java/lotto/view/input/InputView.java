package lotto.view.input;

import java.util.List;

public interface InputView {
    String inputPurchaseAmount();

    String inputManualPurchaseCounts();

    List<List<String>> inputManualPurchaseWinningNumbers(int counts);

    List<String> inputLastWeekWinningNumbers();

    String inputBonusNumber();

    void printErrorMessage(final String errorMessage);
}
