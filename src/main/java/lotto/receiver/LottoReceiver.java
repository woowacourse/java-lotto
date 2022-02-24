package lotto.receiver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.exception.LottoException;

public class LottoReceiver {

    private static final String INPUT_NUMBERS_DELIMITER = ",";
    private static final int LOTTO_SIZE = 6;

    public static List<LottoNumber> receive(String input) {
        List<String> separatedInput = splitInput(input);
        checkSize(separatedInput);
        checkDuplication(separatedInput);
        return convertToLottoNumbers(separatedInput);
    }

    private static List<String> splitInput(String input) {
        return Arrays.stream(input.split(INPUT_NUMBERS_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static void checkSize(List<String> numbers) {
        if (!isCorrectSize(numbers)) {
            throw new LottoException(LottoException.WINNING_NUMBERS_SIZE_ERROR_MESSAGE);
        }
    }

    private static boolean isCorrectSize(List<String> numbers) {
        return numbers.size() == LOTTO_SIZE;
    }

    private static void checkDuplication(List<String> numbers) {
        if (isDuplication(numbers)) {
            throw new LottoException(LottoException.WINNING_NUMBERS_DUPLICATION_ERROR_MESSAGE);
        }
    }

    private static boolean isDuplication(List<String> numbers) {
        return new HashSet<>(numbers).size() != numbers.size();
    }

    private static List<LottoNumber> convertToLottoNumbers(List<String> numbers) {
        return numbers.stream()
                .map(LottoNumber::getByString)
                .sorted()
                .collect(Collectors.toList());
    }
}
