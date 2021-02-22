package lotto.domain.ticketgenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;

public class LottoGenerator {
    private static final int ZERO = 0;
    private final List<LottoNumber> allNumbers;

    public LottoGenerator() {
        this.allNumbers = new ArrayList<>();
        for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
            allNumbers.add(new LottoNumber(i));
        }
    }

    public PurchasedLottoTickets generatePurchasedTickets(UserPurchase userPurchase) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < userPurchase.getNumberOfTicket(); i++) {
            Collections.shuffle(allNumbers);
            lottoTickets.add(new LottoTicket(new ArrayList<>(allNumbers.subList(ZERO, LottoTicket.SIZE))));
        }
        return new PurchasedLottoTickets(lottoTickets);
    }
}
