package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int ZERO = 0;
    private static final int SIZE = 6;
    private static final int ONE_TICKET_PRICE = 1000;
    private static final List<LottoNumber> allNumbers = new ArrayList<>();

    private final int numberOfTicket;

    static {
        for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
            allNumbers.add(new LottoNumber(i));
        }
    }

    public LottoGenerator(int purchasePrice) {
        validateExactlyDividedByOneTicketPrice(purchasePrice);
        this.numberOfTicket = purchasePrice / ONE_TICKET_PRICE;
    }

    private void validateExactlyDividedByOneTicketPrice(int purchasePrice) {
        if (purchasePrice % ONE_TICKET_PRICE != 0) {
            throw new IllegalArgumentException("구입 금액은 1000원 단위여야 합니다.");
        }
    }

    public List<LottoTicket> generatePurchasedTickets() {
        List<LottoTicket> purchasedTickets = new ArrayList<>();
        for (int i = 0; i < this.numberOfTicket; i++) {
            Collections.shuffle(allNumbers);
            purchasedTickets.add(new LottoTicket(new ArrayList<>(allNumbers.subList(ZERO, SIZE))));
        }
        return purchasedTickets;
    }

    public WinningLottoNumbers generateWinningLottoNumbers(LottoTicket lottoTicket,
        LottoNumber bonusNumber) {
        return new WinningLottoNumbers(lottoTicket, bonusNumber);
    }
}
