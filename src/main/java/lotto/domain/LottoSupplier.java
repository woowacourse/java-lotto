package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoSupplier implements Supplier<List<Integer>> {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<Integer> lottoNumbers = IntStream
        .rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
        .boxed()
        .collect(Collectors.toList());

    @Override
    public List<Integer> get() {
        List<Integer> numbers = new ArrayList<>(lottoNumbers);
        Collections.shuffle(numbers);
        return new ArrayList<>(numbers.subList(0, LOTTO_NUMBER_COUNT));
    }
}