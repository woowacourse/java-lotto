package model;

import dto.ROIResultResponse;
import dto.TicketAmountResponse;

import static utils.Validator.validateDivide;
import static utils.Validator.validateRange;

public class Ticket {

    private static final int TICKET_PRICE = 1000;
    
    private final int purchaseMoney;

    public Ticket(final int purchaseMoney) {
        validateRange(purchaseMoney, TICKET_PRICE, Integer.MAX_VALUE);
        validateDivide(purchaseMoney, TICKET_PRICE);
        this.purchaseMoney = purchaseMoney;
    }

    public int getTicketAmount() {
        return purchaseMoney / TICKET_PRICE;
    }

    public TicketAmountResponse createResponse() {
        return new TicketAmountResponse(getTicketAmount());
    }

    public ROIResultResponse createROIResponse(int totalPrice) {
        double ROI = calculateROI(totalPrice);
        return new ROIResultResponse(ROI, Heuristic.determine(ROI));
    }

    private double calculateROI(int totalPrice) {
        return Math.round(((double) totalPrice / purchaseMoney) * 100) / 100.0;
    }
}
