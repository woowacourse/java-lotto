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
    public static LottoTicket buyAutomatically(Budget budget) {
        budget.buy(LottoTicket.PRICE, 1);
        LottoTicket lottoTicket = LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers());
        return lottoTicket;
    }
}
