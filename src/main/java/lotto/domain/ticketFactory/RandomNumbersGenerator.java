package lotto.domain.ticketFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;


public class RandomNumbersGenerator implements NumbersGenerator {

    RandomNumbersGenerator() { }

    @Override
    public Set<LottoNumber> generateNumbers() {
        List<LottoNumber> numbers = TicketFactory.lottoNumbers;
        Collections.shuffle(numbers);
        return IntStream.rangeClosed(1,6)
            .mapToObj(numbers::get)
            .collect(Collectors.toSet());
    }
}
