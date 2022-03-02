package domain.strategy;

import static domain.LottoNumber.*;

import domain.LottoTicket;
import domain.strategy.GenerateStrategy;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumberGenerateStrategy implements GenerateStrategy {
    private final static List<Integer> lottoNumbers = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            .boxed()
            .collect(Collectors.toList());

    @Override
    public Set<Integer> generateNumbers() {
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.stream()
                .limit(LottoTicket.LOTTO_TICKET_SIZE)
                .collect(Collectors.toSet());
    }
}
