package model.dto;

import java.util.List;

public class LottoRequestDto {
    private final List<Integer> numbers;

    public LottoRequestDto(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
