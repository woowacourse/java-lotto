package domain.dto;

import java.util.Collections;
import java.util.List;

public class WinningNumberDto {

    private final List<Integer> numbers;
    private final int bonusNumber;

    private WinningNumberDto(List<Integer> numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningNumberDto of(List<Integer> numbers, int bonusNumber) {
        return new WinningNumberDto(numbers, bonusNumber);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
