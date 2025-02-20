package dto;

import java.util.Collections;
import java.util.List;

public record LottoDto(List<Integer> numbers) {
    public LottoDto(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    @Override
    public List<Integer> numbers() {
        return Collections.unmodifiableList(numbers);
    }
}
