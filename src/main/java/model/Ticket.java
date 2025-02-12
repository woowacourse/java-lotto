package model;

import dto.TicketAmountResponse;

import static utils.Validator.validateDivide;
import static utils.Validator.validateRange;

public class Ticket {

    private static final int TICKET_PRICE = 1000;
    
    private final int purchaseMoney;

    public Ticket(final int purchaseMoney) {
        validateRange(purchaseMoney, Integer.MAX_VALUE, TICKET_PRICE);
        validateDivide(purchaseMoney, TICKET_PRICE);
        this.purchaseMoney = purchaseMoney;
    }

    public int getTicketAmount() {
        return purchaseMoney / TICKET_PRICE;
    }

    public TicketAmountResponse createResponse() {
        return new TicketAmountResponse(getTicketAmount());
    }
}
