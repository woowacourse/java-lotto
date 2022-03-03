package model.dto;

import java.util.List;

public class LottoNumberRequestDto {
    private final List<Integer> numbers;

    public LottoNumberRequestDto(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
