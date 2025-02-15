package model;

import dto.ReturnOfInvestmentResultResponse;
import dto.TicketAmountResponse;

import static global.utils.Validator.validateDivisibility;
import static global.utils.Validator.validateRange;

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

    public ReturnOfInvestmentResultResponse createReturnOfInvestmentResponse(final int totalPrice) {
        double returnOfInvestment = calculateReturnOfInvestment(totalPrice);
        return new ReturnOfInvestmentResultResponse(returnOfInvestment, InvestmentOutcome.determine(returnOfInvestment));
    }

    private static void validatePurchaseMoney(final int purchaseMoney) {
        validateRange(purchaseMoney, TICKET_PRICE, Integer.MAX_VALUE);
        validateDivisibility(purchaseMoney, TICKET_PRICE);
    }

    private double calculateReturnOfInvestment(final int totalPrice) {
        return Math.round(((double) totalPrice / purchaseMoney) * 100) / 100.0;
    }
}
