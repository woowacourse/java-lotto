package lotto.view.input;

import java.util.List;

public interface InputView {
    String inputPurchaseAmount();

    List<String> inputLastWeekWinningNumbers();

    String inputBonusNumber();

    void printErrorMessage(final String errorMessage);
}
