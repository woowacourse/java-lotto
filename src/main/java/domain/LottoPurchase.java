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
    private Money budget;

    public LottoPurchase(Money money) {
        this.budget = money;
    }

    public boolean canAfford(Money pricePerItems, int quantity) {
        return this.budget.toLong() >= pricePerItems.multiply(quantity).toLong();
    }

    public Money remainBudget() {
        return this.budget;
    }

    public LottoTicket buyAutomatically() {
        this.budget = budget.subtract(LottoTicket.PRICE);
        return LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers());
    }

    public LottoTicket buyManually(List<Integer> lottoNumbers) throws IllegalArgumentException {
        LottoTicket lottoTicket = LottoTicket.valueOf(lottoNumbers);
        this.budget = budget.subtract(LottoTicket.PRICE);
        return lottoTicket;
    }
}
