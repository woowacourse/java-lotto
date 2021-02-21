package lotto.domain.ticketgenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;

public class LottoGenerator {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> allNumbers;

    public LottoGenerator() {
        this.allNumbers = new ArrayList<>();
        for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
            allNumbers.add(new LottoNumber(i));
        }
    }

    public AllPurchasedLottoTickets purchaseTickets(UserPurchase userPurchase) {
        LottoTickets automaticallyPurchasedLottoTickets = new LottoTickets();
        for (int i = 0; i < userPurchase.getNumberOfAutomaticallyPurchasedLottoTickets(); i++) {
            Collections.shuffle(allNumbers);
            LottoTicket automaticallyGeneratedLottoTicket
                = new LottoTicket(allNumbers.subList(0, LOTTO_NUMBERS_SIZE));
            automaticallyPurchasedLottoTickets.add(automaticallyGeneratedLottoTicket);
        }
        return new AllPurchasedLottoTickets(userPurchase.getManuallyPurchasedLottoTickets(),
            automaticallyPurchasedLottoTickets);
    }
}
