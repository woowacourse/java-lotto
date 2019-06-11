package lotto.domain.TicketModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberPool {
    private static final List<TicketNumber> ticketNumbers;

    static {
        ticketNumbers = new ArrayList<>();
        for (int i = LottoNumber.MIN_NUMBER; i <= LottoNumber.MAX_NUMBER; i++) {
            ticketNumbers.add(new LottoNumber(i));
        }
    }

    private LottoNumberPool() {

    }

    public static List<TicketNumber> random() {
        shuffle();
        return new ArrayList<>(ticketNumbers.subList(0, LottoNumbers.NUMBER_COUNT));
    }

    private static void shuffle() {
        Collections.shuffle(ticketNumbers);
    }
}
