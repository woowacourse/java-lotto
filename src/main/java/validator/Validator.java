package validator;

import static common.constant.NumberConstants.LOTTO_NUMBER_END;
import static common.constant.NumberConstants.LOTTO_NUMBER_START;
import static common.constant.RegexConstants.NUMBER_ONLY_REGEX;
import static common.utils.ValidationUtils.checkEmptyInput;
import static common.utils.ValidationUtils.checkInvalidForm;
import static common.utils.ValidationUtils.checkNullInput;
import static common.utils.ValidationUtils.checkOutOfRange;
import static common.utils.ValidationUtils.checkWhitespaceOnlyInput;

public class Validator {
    // 입력이 빈 입력인 경우
    public static void validateEmptyInput(String input) {
        checkNullInput(input, ErrorMessages.NULL_INPUT.getMessage());
        checkEmptyInput(input, ErrorMessages.EMPTY_STRING_INPUT.getMessage());
        checkWhitespaceOnlyInput(input, ErrorMessages.WHITESPACE_ONLY_INPUT.getMessage());
    }

    // 숫자 여부 유효성 검사
    public static void validateNumber(String input) {
        checkInvalidForm(input, NUMBER_ONLY_REGEX, ErrorMessages.NOT_NUMBER.getMessage());
    }

    // 로또 범위 유효성 검사
    public static void validateLottoNumberRange(int number) {
        checkOutOfRange(number, LOTTO_NUMBER_START, LOTTO_NUMBER_END,
                ErrorMessages.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
    }
}