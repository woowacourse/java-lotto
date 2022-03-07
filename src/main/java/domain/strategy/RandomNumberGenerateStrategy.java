package domain.strategy;

import domain.LottoNumber;
import domain.LottoTicket;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerateStrategy implements NumberGenerateStrategy {
    private final static List<Integer> lottoNumbers = IntStream
            .rangeClosed(LottoNumber.MIN_VALUE, LottoNumber.MAX_VALUE)
            .boxed()
            .collect(Collectors.toList());

    @Override
    public Set<Integer> generateNumbers() {
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.stream()
                .limit(LottoTicket.SIZE)
                .collect(Collectors.toSet());
    }
}
