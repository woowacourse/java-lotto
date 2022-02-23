package view;

public class LottoNumberFormatValidator {
    private static final String REGEX_LOTTO_NUMBER_FORMAT = "^\\s*[0-9]{1,2}\\s*(\\s*,\\s*[0-9]{1,2}\\s*){5}$";
    static final String INVALID_LOTTO_NUMBER_FORMAT_MESSAGE = "당첨 번호는 반드시 6개의 숫자여야 합니다.";

    public void validate(String lottoNumber) {
        if (!lottoNumber.matches(REGEX_LOTTO_NUMBER_FORMAT)) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_FORMAT_MESSAGE);
        }
    }
}
