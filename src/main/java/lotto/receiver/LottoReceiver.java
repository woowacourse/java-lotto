package lotto.receiver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.receiver.validator.LottoNumberValidator;
import lotto.receiver.validator.LottoValidator;

public class LottoReceiver {

    private static final String INPUT_NUMBERS_DELIMITER = ",";

    public static List<LottoNumber> receive(String input) {
        List<String> separatedInput = splitInput(input);
        LottoValidator.validate(separatedInput);
        List<Integer> numbers = parseInput(separatedInput);
        return convertToLottoNumbers(numbers);
    }


    private static List<String> splitInput(String input) {
        return Arrays.stream(input.split(INPUT_NUMBERS_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static List<Integer> parseInput(List<String> separatedInput) {
        return separatedInput.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        numbers.forEach(LottoNumberValidator::validate);
        return numbers.stream()
                .map(LottoNumber::findByNumber)
                .sorted()
                .collect(Collectors.toList());
    }
}
