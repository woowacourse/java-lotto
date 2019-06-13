package lotto.dto;

import java.util.List;

public class WinningNumberDto {
    private List<Integer> numbers;
    private int bonusBall;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int getBonusBall() {
        return bonusBall;
    }

    public void setBonusBall(final int bonusBall) {
        this.bonusBall = bonusBall;
    }
}
