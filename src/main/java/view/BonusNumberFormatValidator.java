package view;

public class BonusNumberFormatValidator {
    private static final String REGEX_BONUS_NUMBER_FORMAT = "^\\s*[0-9]{1,2}\\s*$";
    static final String INVALID_BONUS_NUMBER_FORMAT_MESSAGE = "보너스 번호는 반드시 6개의 숫자여야 합니다.";

    public void validate(String lottoNumber) {
        if (!lottoNumber.matches(REGEX_BONUS_NUMBER_FORMAT)) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_FORMAT_MESSAGE);
        }
    }
}
