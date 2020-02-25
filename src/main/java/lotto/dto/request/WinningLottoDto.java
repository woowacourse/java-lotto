package lotto.dto.request;

import java.util.Set;

public class WinningLottoDto {

    private final Set<Integer> numbers;
    private final int number;

    public WinningLottoDto(Set<Integer> numbers, int number) {
        this.numbers = numbers;
        this.number = number;
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }

    public int getNumber() {
        return number;
    }
}
