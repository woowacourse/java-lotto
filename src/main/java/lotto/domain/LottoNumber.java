package lotto.domain;

public class LottoNumber {
    public static final String NUMBER_TYPE_ERROR = "[ERROR] 숫자만 입력할 수 있습니다";
    public static final String NUMBER_RANGE_ERROR = "[ERROR] 숫자를 1 ~ 45 사이로 입력해주세요";
    public static final int MAXIMUM_NUMBER = 45;
    public static final int MINIMUM_NUMBER = 1;
    private final int number;

    public LottoNumber(String input) {
        number = validateNumberType(input);
        validateNumberRange();
    }

    private int validateNumberType(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException(NUMBER_TYPE_ERROR);
        }
    }

    private void validateNumberRange() {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
            throw new IllegalArgumentException(NUMBER_RANGE_ERROR);
        }
    }

    public boolean isContain(Lotto winLotto) {
        return winLotto.isContainNum(number);
    }

    public int getNumber() {
        return this.number;
    }
}
