package lotto.domain.ticketFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class RandomTicketGenerator implements TicketGenerator {

    private final List<LottoNumber> lottoNumbers;

    public RandomTicketGenerator(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public LottoTicket generateTicket() {
        List<LottoNumber> numbers = lottoNumbers;
        Collections.shuffle(numbers);
        return new LottoTicket(IntStream.rangeClosed(1, 6)
            .mapToObj(numbers::get)
            .collect(Collectors.toSet()));
    }
}
