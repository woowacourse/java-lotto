package domain;

import java.util.List;

/**
 * LottoPurchase.java
 * 로또 구매 로직을 담당하는 유틸리티 클래스
 *
 * @author Kimun Kim, github.com/tributetothemoon
 * @author Daeun Lee, github.com/da-nyee
 */

public class LottoPurchase {
    public static LottoTicket buyAutomatically(Budget budget) {
        budget.buy(LottoTicket.PRICE, 1);
        return LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers());
    }

    public static LottoTicket buyManually(List<Integer> lottoNumbers, Budget budget) {
        LottoTicket lottoTicket = LottoTicket.valueOf(lottoNumbers);
        budget.buy(LottoTicket.PRICE, 1);
        return lottoTicket;
    }
}
