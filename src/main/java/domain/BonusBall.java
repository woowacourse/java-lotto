package domain;


import org.apache.commons.lang3.StringUtils;

public class BonusBall {
    private static final int MIN_LOTTO_NUMBER_RANGE = 1;
    private static final int MAX_LOTTO_NUMBER_RANGE = 45;

    private LottoNumber bonusNumber;

    public BonusBall(String input) {
        validateNullOrBlank(input);
        int parseNumber = validateParseInteger(input);
        validateBonusNumberRange(parseNumber);
        this.bonusNumber = new LottoNumber(parseNumber);
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    private void validateBonusNumberRange(int parseNumber) {
        if (parseNumber < MIN_LOTTO_NUMBER_RANGE || parseNumber > MAX_LOTTO_NUMBER_RANGE) {
            throw new IllegalArgumentException("잘못된 범위의 숫자를 입력하였습니다.");
        }
    }

    private int validateParseInteger(String input) {
        int parseNumber;
        try {
            parseNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자를 입력하였습니다.");
        }
        return parseNumber;
    }

    private void validateNullOrBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("null 또는 빈 문자를 입력하였습니다.");
        }
    }
}
