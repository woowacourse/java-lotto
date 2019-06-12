package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class LottoNumberPool {
    private static final List<TicketNumber> ticketNumbers;

    static {
        ticketNumbers = new ArrayList<>();
        for (int i = LottoNumber.MIN_NUMBER; i <= LottoNumber.MAX_NUMBER; i++) {
            ticketNumbers.add(new LottoNumber(i));
        }
    }

    private LottoNumberPool() {

    }

    static List<TicketNumber> random() {
        shuffle();
        return new ArrayList<>(ticketNumbers.subList(0, LottoNumbers.NUMBER_COUNT));
    }

    private static void shuffle() {
        Collections.shuffle(ticketNumbers);
    }

    static TicketNumbers manual(List<Integer> intNumbers) {
        List<TicketNumber> ticketNumbers = new ArrayList<>();
        for (Integer intNumber : intNumbers) {
            ticketNumbers.add(new LottoNumber(intNumber));
        }
        return new LottoNumbers(ticketNumbers);
    }
}
