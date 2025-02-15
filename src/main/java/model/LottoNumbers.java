package model;

import java.util.ArrayList;
import java.util.List;

public record LottoNumbers(
        List<Integer> numbers
) {

    @Override
    public List<Integer> numbers() {
        return new ArrayList<>(numbers);
    }
}
