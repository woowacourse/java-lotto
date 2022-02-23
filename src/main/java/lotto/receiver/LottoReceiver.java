package lotto.receiver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.exception.WinningNumbersException;

public class LottoReceiver {

    private static final String WINNING_NUMBERS_DELIMITER = ",";
    private static final int WINNING_NUMBERS_SIZE = 6;

    public static List<LottoNumber> receive(String input) {
        List<String> seperatedInput = splitInput(input);
        checkSize(seperatedInput);
        checkDuplication(seperatedInput);
        return convertToLottoNumbers(seperatedInput);
    }

    private static List<String> splitInput(String input) {
        return Arrays.stream(input.split(WINNING_NUMBERS_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static void checkSize(List<String> numbers) {
        if (!isCorrectSize(numbers)) {
            throw new WinningNumbersException(WinningNumbersException.WINNING_NUMBERS_SIZE_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectSize(List<String> numbers) {
        return numbers.size() == WINNING_NUMBERS_SIZE;
    }

    private static void checkDuplication(List<String> numbers) {
        if (isDuplication(numbers)) {
            throw new WinningNumbersException(WinningNumbersException.WINNING_NUMBERS_DUPLICATION_ERROR_MESSAGE);
        }
    }

    private static boolean isDuplication(List<String> numbers) {
        return new HashSet<>(numbers).size() != numbers.size();
    }

    private static List<LottoNumber> convertToLottoNumbers(List<String> numbers) {
        return numbers.stream()
                .map(LottoNumber::getLottoNumber)
                .sorted()
                .collect(Collectors.toList());
    }
}
