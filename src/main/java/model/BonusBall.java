package model;

public class BonusBall {
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final String BONUS_BALL_BLANK_ERROR_MESSAGE = "[Error]: 보너스 볼을 입력해주세요.";
    private static final String BONUS_BALL_NUMBER_ERROR_MESSAGE = "[Error]: 보너스 볼은 숫자여야 합니다.";
    private static final String BONUS_BALL_RANGE_ERROR_MESSAGE = "[Error]: 보너스 볼은 1~45의 숫자만 입력해주세요.";

    private final int number;

    public BonusBall(String number) {
        validateInputNumberBlank(number);
        validateInputBonusNumber(number);
        validateInputBonusNumberOutOfRange(number);
        this.number = makeBonusBallToNumber(number);
    }

    private void validateInputNumberBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException(BONUS_BALL_BLANK_ERROR_MESSAGE);
        }
    }

    private void validateInputBonusNumber(String number) {
        if (!number.matches(REGEX_NUMBER)) {
            throw new IllegalArgumentException(BONUS_BALL_NUMBER_ERROR_MESSAGE);
        }
    }

    private void validateInputBonusNumberOutOfRange(String number) {
        if (Integer.parseInt(number) < LOTTO_MIN_NUMBER || Integer.parseInt(number) > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(BONUS_BALL_RANGE_ERROR_MESSAGE);
        }
    }

    private int makeBonusBallToNumber(String number) {
        return Integer.parseInt(number);
    }

    public BonusBallDTO getBonusBallDTO() {
        return new BonusBallDTO(number);
    }

    public int getNumber() {
        return number;
    }

}
