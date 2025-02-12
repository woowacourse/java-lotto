package model;

import static utils.Validator.validateDivide;
import static utils.Validator.validateRange;

public class Ticket {

    private static final int TICKET_AMOUNT = 1000;
    
    private final int purchaseMoney;

    public Ticket(final int purchaseMoney) {
        validateRange(purchaseMoney, Integer.MAX_VALUE, TICKET_AMOUNT);
        validateDivide(purchaseMoney, TICKET_AMOUNT);
        this.purchaseMoney = purchaseMoney;
    }


}
