package service.parser;

import java.util.List;
import validator.ErrorMessages;
import validator.Validator;

public class BonusNumberParser {

    public static int parseBonusNumber(List<Integer> winningNumbers, String input) {
        Validator.validateEmptyInput(input); // 공백 검사
        Validator.validateNumber(input); // 숫자 구성 여부 검사
        int bonusNumber = Integer.parseInt(input);
        Validator.validateLottoNumberRange(bonusNumber);
        validateDuplicateWithBonusNumber(winningNumbers, bonusNumber);

        return bonusNumber;
    }

    private static void validateDuplicateWithBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessages.SAME_NUMBER.getMessage());
        }
    }
}