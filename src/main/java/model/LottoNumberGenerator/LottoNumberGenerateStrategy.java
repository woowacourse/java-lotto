package model.LottoNumberGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerateStrategy implements GenerateStrategy {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    public static final int MAX_SIZE = 6;

    @Override
    public List<Integer> generateNumbers() {
        List<Integer> numbers = IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER).boxed().collect(Collectors.toList());

        Collections.shuffle(numbers);

        return numbers.stream().limit(MAX_SIZE).collect(Collectors.toList());
    }
}
