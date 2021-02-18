package lotto.domain.ticketgenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.PurchasedLottoTickets;

public class LottoGenerator {
    private static final int ZERO = 0;
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> allNumbers;

    public LottoGenerator() {
        this.allNumbers = new ArrayList<>();
        for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
            allNumbers.add(new LottoNumber(i));
        }
    }

    public PurchasedLottoTickets generatePurchasedTickets(int numberOfTickets) {
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets();
        for (int i = 0; i < numberOfTickets; i++) {
            Collections.shuffle(allNumbers);
            LottoTicket newLottoTicket
                = new LottoTicket(allNumbers.subList(ZERO, LOTTO_NUMBERS_SIZE));
            purchasedLottoTickets.add(newLottoTicket);
        }
        return purchasedLottoTickets;
    }
}
