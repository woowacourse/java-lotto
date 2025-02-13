package view.input;

import java.util.List;

public interface InputParser {

    int parsePurchaseAmount(final String purchaseAmountText);

    List<Integer> parseWinningNumbers(final String winningNumbersText);

    int parseBonusBall(final String bonusBallText);
}
