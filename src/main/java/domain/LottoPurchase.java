package domain;

/**
 * LottoPurchase.java
 * 로또 구매 로직을 담당하는 유틸리티 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon
 * @author Daeun Lee, github.com/da-nyee
 */
import java.util.ArrayList;
import java.util.List;

public class LottoPurchase {
    private final static int PRICE = 1000;

    public static LottoTickets buy(Money money) {
        List<LottoTicket> lottoTicket = new ArrayList<>();
        for (int i = 0; i < money.toLong() / PRICE; i++) {
            lottoTicket.add(LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers()));
        }
        return new LottoTickets(lottoTicket);
    }
}
