package lotto.model.customer;

import java.util.List;
import java.util.stream.Stream;

public class CustomLottoNumbers {
    private List<List<Integer>> customLottoNumbers;

    public CustomLottoNumbers(List<List<Integer>> customLottoNumbers) {
        this.customLottoNumbers = customLottoNumbers;
    }

    public Stream<List<Integer>> stream() {
        return customLottoNumbers.stream();
    }
}