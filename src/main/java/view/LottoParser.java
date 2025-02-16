package view;

import common.NumberParser;
import common.NumberValidator;
import java.util.List;
import java.util.stream.Stream;

public class LottoParser {

    private static final String LOTTO_NUMBER_SEPARATOR = "\", \"";

    public int parsePurchaseAmount(String rawPurchaseAmount) {
        return parsePositiveInteger(rawPurchaseAmount);
    }

    public int parseBonusNumber(String rawBonusNumber) {
        return parsePositiveInteger(rawBonusNumber);
    }

    public List<Integer> parseWinningNumbers(String rawWinningNumbers) {
        String[] splitWinningNumbers = rawWinningNumbers.split(LOTTO_NUMBER_SEPARATOR);
        return Stream.of(splitWinningNumbers)
                .map(this::parsePositiveInteger)
                .toList();
    }

    private int parsePositiveInteger(String rawNumber) {
        int parsedNumber = NumberParser.parseInt(rawNumber);
        NumberValidator.validatePositive(parsedNumber);
        return parsedNumber;
    }
}
