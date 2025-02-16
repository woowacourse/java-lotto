package model;

import dto.ReturnOfInvestmentResultResponse;
import dto.LottoAmountResponse;

import static model.utils.Validator.validateDivisibility;
import static model.utils.Validator.validateRange;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1_000;

    private final int purchaseMoney;

    public LottoMachine(final int purchaseMoney) {
        validatePurchaseMoney(purchaseMoney);
        this.purchaseMoney = purchaseMoney;
    }

    public int getTicketAmount() {
        return purchaseMoney / LOTTO_PRICE;
    }

    public LottoAmountResponse createLottoAmountResponse() {
        return new LottoAmountResponse(getTicketAmount());
    }

    public ReturnOfInvestmentResultResponse createReturnOfInvestmentResponse(final int totalPrice) {
        double returnOfInvestment = calculateReturnOfInvestment(totalPrice);
        return new ReturnOfInvestmentResultResponse(returnOfInvestment, InvestmentOutcome.determine(returnOfInvestment));
    }

    private static void validatePurchaseMoney(final int purchaseMoney) {
        validateRange(purchaseMoney, LOTTO_PRICE, Integer.MAX_VALUE);
        validateDivisibility(purchaseMoney, LOTTO_PRICE);
    }

    private double calculateReturnOfInvestment(final int totalPrice) {
        return Math.round(((double) totalPrice / purchaseMoney) * 100) / 100.0;
    }
}
