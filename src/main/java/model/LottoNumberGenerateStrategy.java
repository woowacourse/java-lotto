package model;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerateStrategy implements GenerateStrategy {

    @Override
    public Set<Integer> generateNumbers() {
        List<Integer> numbers = generateRangeNumbers();
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(LottoTicket.LOTTO_TICKET_SIZE)
                .collect(Collectors.toSet());
    }

    private List<Integer> generateRangeNumbers() {
        return IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }
}
