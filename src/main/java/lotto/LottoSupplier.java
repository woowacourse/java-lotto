package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoSupplier implements Supplier<List<Integer>> {
    private final List<Integer> lottoNumbers = IntStream.rangeClosed(1, 45).boxed().collect(
        Collectors.toList());

    @Override
    public List<Integer> get() {
        List<Integer> numbers = new ArrayList<>(lottoNumbers);
        Collections.shuffle(numbers);
        return numbers.subList(0, 6);
    }
}