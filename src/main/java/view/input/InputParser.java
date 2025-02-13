package view.input;

import java.util.List;

public interface InputParser {
    
    int parsePurchaseAmount(String purchaseAmountText);

    List<Integer> parseWinningNumbers(String winningNumbersText);
}
