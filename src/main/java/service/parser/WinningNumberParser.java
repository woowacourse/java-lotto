package service.parser;

import constant.LottoConstants;
import java.util.Arrays;
import java.util.List;
import validator.Validator;

public class WinningNumberParser {

    public static List<Integer> parseWinningNumbers(String input) {
        Validator.validateEmptyInput(input); // 공백 검사
        List<String> rawNumbers = Arrays.asList(input.split(","));

        validateNotNumber(rawNumbers);
        List<Integer> numbers = rawToIntegers(rawNumbers);
        validateLottoNumberCount(numbers);
        validateDuplicate(numbers);
        validateOutOfRange(rawNumbers, numbers);

        return numbers;
    }

    private static void validateOutOfRange(List<String> rawNumbers, List<Integer> numbers) {
        for (int idx = 0; idx < rawNumbers.size(); idx++)
            Validator.checkOutOfRange(numbers.get(idx), LottoConstants.LOTTO_NUMBER_START, LottoConstants.LOTTO_NUMBER_END, "로또의 숫자가 1~45의 유효 범위를 벗어납니다.");
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (Validator.isDuplicates(numbers)){
            throw new IllegalArgumentException("중복된 내용이 존재합니다.");
        }
    }

    private static void validateLottoNumberCount(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.LOTTO_COUNT){
            throw new IllegalArgumentException("로또의 구성 숫자는 6개여야합니다.");
        }
    }

    private static List<Integer> rawToIntegers(List<String> rawNumbers) {
        return rawNumbers.stream()
                .map(Integer::parseInt)
                .toList();
    }

    private static void validateNotNumber(List<String> rawNumbers) {
        for (int i = 0; i < rawNumbers.size(); i++) {
            Validator.checkInvalidNumberForm(rawNumbers.get(i));
        }
    }
}
