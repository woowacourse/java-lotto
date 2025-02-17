package model;

import dto.LottoResultResponse;
import dto.TicketAmountResponse;

import static global.utils.Validator.validateNumberRange;

public class Ticket {

    private static final int TICKET_PRICE = 1_000;

    private final int purchaseMoney;

    public Ticket(final int purchaseMoney) {
        validatePurchaseMoney(purchaseMoney);
        this.purchaseMoney = purchaseMoney;
    }

    public int getTicketAmount() {
        return purchaseMoney / TICKET_PRICE;
    }

    public TicketAmountResponse createTicketResponse() {
        return new TicketAmountResponse(getTicketAmount());
    }

    public LottoResultResponse createROIResponse(final int totalPrice) {
        double ROI = calculateROI(totalPrice);
        return new LottoResultResponse(ROI, Heuristic.determine(ROI));
    }

    private void validatePurchaseMoney(final int purchaseMoney) {
        validateNumberRange(purchaseMoney, TICKET_PRICE, Integer.MAX_VALUE);
        validateDivisibility(purchaseMoney);
    }

    private double calculateROI(final int totalPrice) {
        return Math.round(((double) totalPrice / purchaseMoney) * 100) / 100.0;
    }

    private void validateDivisibility(final int number) {
        if (number % TICKET_PRICE != 0) {
            throw new IllegalArgumentException("금액은 " + TICKET_PRICE + "원 단위로 나누어 떨어져야 합니다.");
        }
    }
}
