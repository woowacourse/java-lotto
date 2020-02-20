package domain;

import spark.utils.StringUtils;

import java.util.List;

public class BonusBall {
    private static final int MIN_LOTTO_NUMBER_RANGE = 1;
    private static final int MAX_LOTTO_NUMBER_RANGE = 45;

    private int bonusNumber;

    public BonusBall(List<Integer> winningNumber, String input) {
        validateNullOrBlank(input);
        int parseNumber = validateParseInteger(input);
        validateBonusNumberRange(parseNumber);
        validateDuplicate(winningNumber, parseNumber);
        this.bonusNumber = parseNumber;
    }

    private void validateDuplicate(List<Integer> winningNumber, int parseNumber) {
        if (winningNumber.contains(parseNumber)) {
            throw new IllegalArgumentException("중복된 보너스 숫자를 입력하였습니다.");
        }
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

    public int getBonusNumber() {
        return this.bonusNumber;
    }
}
