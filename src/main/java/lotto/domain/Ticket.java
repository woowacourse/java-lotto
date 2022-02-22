package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ticket {

    private static final int PICKUP_COUNT = 6;

    private final List<LottoNumber> ticket;

    private Ticket(List<LottoNumber> ticket) {
        this.ticket = ticket;
    }

    public static Ticket getTicketByAuto() {
        List<LottoNumber> ticket = IntStream.range(0, PICKUP_COUNT)
                .mapToObj(shuffleLottoNumbers()::get)
                .collect(Collectors.toList());
        return new Ticket(ticket);
    }

    private static List<LottoNumber> shuffleLottoNumbers() {
        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbers();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }
}
