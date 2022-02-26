package model;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerateStrategy implements GenerateStrategy {

    private static final int LOTTO_TICKET_SIZE = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    @Override
    public Set<Integer> generateNumbers() {
        List<Integer> numbers = generateRangerNumbers();
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(LOTTO_TICKET_SIZE)
                .collect(Collectors.toSet());
    }

    private List<Integer> generateRangerNumbers() {
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }
}
