package service.parser;

import constant.RegexConstants;
import java.util.List;
import validator.ErrorMessages;
import validator.Validator;

public class BonusNumberParser {

    public static final int RANGE_START = 1;
    public static final int RANGE_END = 45;

    public static int parseBonusNumber(List<Integer> winningNumbers, String input) {
        Validator.validateEmptyInput(input); // 공백 검사
        Validator.checkInvalidForm(input, RegexConstants.NUMBER_ONLY_REGEX, ErrorMessages.NOT_NUMBER.getMessage());

        int bonusNumber = Integer.parseInt(input);
        Validator.checkOutOfRange(bonusNumber, RANGE_START, RANGE_END, ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
        validateDuplicateWithBonusNumber(winningNumbers, bonusNumber);

        return bonusNumber;
    }

    private static void validateDuplicateWithBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)){
            throw new IllegalArgumentException(ErrorMessages.SAME_NUMBER.getMessage());
        }
    }
}