package model;

import java.util.ArrayList;
import java.util.List;

public class LottoDTO {
    private final List<Integer> numbers;

    public LottoDTO(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
