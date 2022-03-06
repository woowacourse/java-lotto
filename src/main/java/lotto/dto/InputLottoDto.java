package lotto.dto;

import java.util.List;

public class InputLottoDto {
    private final List<Integer> inputNumbers;

    public InputLottoDto(final List<Integer> inputNumbers) {
        this.inputNumbers = inputNumbers;
    }

    public List<Integer> getNumbers() {
        return inputNumbers;
    }
}
