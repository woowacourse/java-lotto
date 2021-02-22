package lotto.domain.ticketFactory;

import static lotto.domain.LottoNumber.LOTTO_NUMBER_MAX_LIMIT;
import static lotto.domain.LottoNumber.LOTTO_NUMBER_MIN_LIMIT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomTicketGenerator {

    private static final List<Integer> numbers;

    static {
        numbers = new ArrayList<>();
        for (int i = LOTTO_NUMBER_MIN_LIMIT; i <= LOTTO_NUMBER_MAX_LIMIT; i++) {
            numbers.add(i);
        }
    }

    public List<Integer> generateNumbers() {
        List<Integer> LottoNumbers = numbers;
        Collections.shuffle(LottoNumbers);
        return IntStream.rangeClosed(1, 6)
            .map(LottoNumbers::get)
            .boxed()
            .collect(Collectors.toList());
    }
}
