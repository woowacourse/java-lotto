package domain.dto;

import java.util.ArrayList;
import java.util.List;

public class LottoDto {

    private final List<Integer> numbers;

    private LottoDto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static LottoDto from(List<Integer> numbers) {
        return new LottoDto(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
