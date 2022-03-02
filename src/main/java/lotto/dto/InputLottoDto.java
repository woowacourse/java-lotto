package lotto.dto;

import java.util.Arrays;
import java.util.List;

public class InputLottoDto {
    private final List<String> inputNumbers;

    public InputLottoDto(final String[] inputNumbers) {
        this.inputNumbers = Arrays.asList(inputNumbers);
    }

    public List<String> getNumbers() {
        return inputNumbers;
    }
}
