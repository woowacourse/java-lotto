package domain;

import domain.lotto.LottoTickets;
import java.util.List;

public class Wallet {

    private static final int LOTTO_TICKET_PRICE = 1000;

    private final int payment;

    public Wallet(final int payment, final int manualCount) {
        validate(payment, manualCount);
        this.payment = payment;
    }

    public LottoTickets purchaseLotto(List<List<Integer>> manualNumbers) {
        final int autoCount = payment / LOTTO_TICKET_PRICE - manualNumbers.size();
        return new LottoTickets(manualNumbers, autoCount);
    }

    public double calculateTotalProfitRate(int totalProfit) {
        return ((double) totalProfit) / payment;
    }

    private void validate(int payment, int manualQuantity) {
        validatePositive(payment);
        validatePossible(payment, manualQuantity);
    }

    private void validatePositive(int payment) {
        if (payment < 0) {
            throw new IllegalArgumentException("금액은 음의 정수 값을 가질 수 없습니다.");
        }
    }

    private void validatePossible(int payment, int manualQuantity) {
        if (payment / LOTTO_TICKET_PRICE < manualQuantity) {
            throw new IllegalArgumentException("투입한 금액보다 더 많은 양의 로또를 구매할 수 없습니다.");
        }
    }
}
