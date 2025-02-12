package view;

import common.NumberValidator;
import java.util.List;
import java.util.stream.Stream;

public class InputParser {

    public int parseBonusNumber(String rawBonusNumber) {
        return parsePositiveInteger(rawBonusNumber);
    }

    public List<Integer> parseWinningNumbers(String rawWinningNumbers) {
        String[] splitWinningNumbers = rawWinningNumbers.split(", ");
        return Stream.of(splitWinningNumbers)
                .map(this::parsePositiveInteger)
                .toList();
    }

    private int parsePositiveInteger(String rawNumber) {
        NumberValidator.validateInteger(rawNumber);
        int parsedNumber = Integer.parseInt(rawNumber);
        NumberValidator.validatePositive(parsedNumber);
        return parsedNumber;
    }
}
