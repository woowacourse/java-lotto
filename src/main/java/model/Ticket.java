package model;

import dto.ROIResultResponse;
import dto.TicketAmountResponse;

import static utils.Validator.validateDivisibility;
import static utils.Validator.validateRange;

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

    public ROIResultResponse createROIResponse(final int totalPrice) {
        double ROI = calculateROI(totalPrice);
        return new ROIResultResponse(ROI, Heuristic.determine(ROI));
    }

    private static void validatePurchaseMoney(final int purchaseMoney) {
        validateRange(purchaseMoney, TICKET_PRICE, Integer.MAX_VALUE);
        validateDivisibility(purchaseMoney, TICKET_PRICE);
    }

    private double calculateROI(final int totalPrice) {
        return Math.round(((double) totalPrice / purchaseMoney) * 100) / 100.0;
    }
}
