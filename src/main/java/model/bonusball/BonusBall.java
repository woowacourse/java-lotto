package model.bonusball;

import java.util.Objects;

import utils.InputValidateUtils;

public class BonusBall {
    private static final String BONUS_BALL_BLANK_ERROR_MESSAGE = "[Error]: 보너스 볼을 입력해주세요.";
    private static final String BONUS_BALL_NUMBER_ERROR_MESSAGE = "[Error]: 보너스 볼은 숫자여야 합니다.";
    private static final String BONUS_BALL_RANGE_ERROR_MESSAGE = "[Error]: 보너스 볼은 1~45의 숫자만 입력해주세요.";

    private final int number;

    public BonusBall(String number) {
        InputValidateUtils.inputBlank(number, BONUS_BALL_BLANK_ERROR_MESSAGE);
        InputValidateUtils.inputNumber(number, BONUS_BALL_NUMBER_ERROR_MESSAGE);
        InputValidateUtils.inputOutOfRange(number, BONUS_BALL_RANGE_ERROR_MESSAGE);
        this.number = makeBonusBallToNumber(number);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusBall bonusBall = (BonusBall) o;
        return number == bonusBall.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
