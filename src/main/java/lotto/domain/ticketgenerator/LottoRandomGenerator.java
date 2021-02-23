package lotto.domain.ticketgenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;

public class LottoRandomGenerator {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> allNumbers;

    public LottoRandomGenerator() {
        this.allNumbers = new ArrayList<>();
        for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
            allNumbers.add(new LottoNumber(i));
        }
    }

    public LottoTickets generate(UserPurchase userPurchase) {
        LottoTickets lottoTickets = new LottoTickets();
        for (int i = 0; i < userPurchase.autoTicketsSize(); i++) {
            Collections.shuffle(allNumbers);
            LottoTicket randomTicket = new LottoTicket(allNumbers.subList(0, LOTTO_NUMBERS_SIZE));
            lottoTickets.add(randomTicket);
        }
        return lottoTickets;
    }
}
