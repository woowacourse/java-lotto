package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerateStrategy implements GenerateStrategy {
    @Override
    public List<Integer> generateNumbers() {
        List<Integer> numbers = generateRangerNumbers();
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(6)
                .collect(Collectors.toList());
    }

    private List<Integer> generateRangerNumbers() {
        return IntStream.rangeClosed(1, 45)
                .boxed()
                .collect(Collectors.toList());
    }
}
