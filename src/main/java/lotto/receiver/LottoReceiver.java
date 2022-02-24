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
        return convertToLottoNumbers(separatedInput);
    }

    private static List<String> splitInput(String input) {
        return Arrays.stream(input.split(INPUT_NUMBERS_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static List<LottoNumber> convertToLottoNumbers(List<String> numbers) {
        numbers.forEach(LottoNumberValidator::validate);
        return numbers.stream()
                .map(Integer::parseInt)
                .map(LottoNumber::findByNumber)
                .sorted()
                .collect(Collectors.toList());
    }
}
