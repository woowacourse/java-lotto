package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int ZERO = 0;
    private static final int SIZE = 6;
    private static final List<LottoNumber> allNumbers = new ArrayList<>();

    static {
        for (int i = LottoNumber.MINIMUM; i <= LottoNumber.MAXIMUM; i++) {
            allNumbers.add(new LottoNumber(i));
        }
    }

    public List<LottoTicket> generatePurchasedTickets(int numberOfTicket) {
        List<LottoTicket> purchasedTickets = new ArrayList<>();
        for (int i = 0; i < numberOfTicket; i++) {
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
