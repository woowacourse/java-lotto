package service.parser;

import static constant.NumberConstants.LOTTO_NUMBER_END;
import static constant.NumberConstants.LOTTO_NUMBER_START;
import static constant.RegexConstants.NUMBER_ONLY_REGEX;

import java.util.List;
import validator.ErrorMessages;
import validator.Validator;

public class BonusNumberParser {

    public static int parseBonusNumber(List<Integer> winningNumbers, String input) {
        Validator.validateEmptyInput(input); // 공백 검사
        Validator.validateInvalidForm(input, NUMBER_ONLY_REGEX, ErrorMessages.NOT_NUMBER.getMessage()); // 숫자 구성 여부 검사
        int bonusNumber = Integer.parseInt(input);
        Validator.validateOutOfRange(bonusNumber, LOTTO_NUMBER_START, LOTTO_NUMBER_END,
                ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
        validateDuplicateWithBonusNumber(winningNumbers, bonusNumber);

        return bonusNumber;
    }

    private static void validateDuplicateWithBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessages.SAME_NUMBER.getMessage());
        }
    }
}