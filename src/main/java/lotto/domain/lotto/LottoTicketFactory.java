package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static lotto.domain.lotto.LottoTicket.LOTTO_TICKET_SIZE;

public class LottoTicketFactory {
    private static final int START_INDEX = 0;

    private LottoTicketFactory() {
    }

    public static LottoTicket generateAutoLottoTicket() {
        List<Integer> numbersKeys = new ArrayList<>(LottoNumber.getKeys());
        Collections.shuffle(numbersKeys);
        return IntStream.range(START_INDEX, LOTTO_TICKET_SIZE)
                .mapToObj(i -> LottoNumber.valueOf(numbersKeys.get(i)))
                .collect(collectingAndThen(toList(), LottoTicket::new));
    }
}
