package model.bonusball;

import java.util.Objects;

import model.exception.BonusBallException;
import utils.InputValidateUtils;

public class BonusBall {
    private final int number;

    public BonusBall(String number) {
        InputValidateUtils.inputBlank(number, BonusBallException.BLANK_ERROR.getMessage());
        InputValidateUtils.inputNumber(number, BonusBallException.NUMBER_ERROR.getMessage());
        InputValidateUtils.inputOutOfRange(number, BonusBallException.RANGE_ERROR.getMessage());
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
