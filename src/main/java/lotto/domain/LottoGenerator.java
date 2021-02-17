package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int ZERO = 0;
    private static final int SIZE = 6;
    private final List<LottoNumber> allNumbers;

    public LottoGenerator() {
        this.allNumbers = new ArrayList<>();
        for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
            allNumbers.add(new LottoNumber(i));
        }
    }

    public PurchasedLottoTickets generatePurchasedTickets(int numberOfTicket) {
        PurchasedLottoTickets purchasedLottoTickets = new PurchasedLottoTickets();
        for (int i = 0; i < numberOfTicket; i++) {
            Collections.shuffle(allNumbers);
            purchasedLottoTickets.add(new LottoTicket(new ArrayList<>(allNumbers.subList(ZERO, SIZE))));
        }
        return purchasedLottoTickets;
    }
}
