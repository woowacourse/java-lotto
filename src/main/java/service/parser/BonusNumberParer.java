package service.parser;

import java.util.List;
import validator.ErrorMessages;
import validator.Validator;

public class BonusNumberParer {
    public static final String NUMBER_ONLY_REGEX = "^[0-9]+$"; // 숫자만

    public static int parseBonusNumber(List<Integer> winningNumbers, String input) {
        Validator.validateEmptyInput(input); // 공백 검사
        Validator.checkInvalidForm(input, NUMBER_ONLY_REGEX, ErrorMessages.NOT_NUMBER.getMessage());

        int bonusNumber = Integer.parseInt(input);
        Validator.checkOutOfRange(bonusNumber, 1, 45, ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());

        if (winningNumbers.contains(bonusNumber)){
            throw new IllegalArgumentException(ErrorMessages.SAME_NUMBER.getMessage());
        }

        return bonusNumber;
    }

    // 리스트에 찾고자하는 요소가 있는지 확인
    public static <T> boolean hasElement(List<T> list, T element) {
        return list.contains(element);
    }
}