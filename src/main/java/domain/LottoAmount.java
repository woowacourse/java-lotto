package domain;

import exception.InvalidLottoAmountException;
import exception.LackOfMoneyException;

public class LottoAmount {
	public static final int LOTTO_PRICE = 1000;
	public static final int ONE_LOTTO_TICKET = 0;
	private final int selfLottoAmount;
	private final int autoLottoAmount;

	public LottoAmount(Money purchaseMoney, int inputSelfNumberLottoAmount) {
		validate(purchaseMoney, inputSelfNumberLottoAmount);
		selfLottoAmount = inputSelfNumberLottoAmount;
		autoLottoAmount = (int)(purchaseMoney.division(LOTTO_PRICE) - inputSelfNumberLottoAmount);
		validateTotalAmount();
	}

	private void validateTotalAmount() {
		if (calculateTotalLottoAmount() < selfLottoAmount) {
			throw new InvalidLottoAmountException(InvalidLottoAmountException.EXCESS_SELF_LOTTO_AMOUNT);
		}
	}

	public int calculateTotalLottoAmount() {
		return selfLottoAmount + autoLottoAmount;
	}

	private void validate(Money purchaseMoney, int inputSelfNumberLottoAmount) {
		validateSelfLottoAmountRange(inputSelfNumberLottoAmount);
		validateLottoMoneyMinimumPrice(purchaseMoney);
	}

	private void validateLottoMoneyMinimumPrice(Money purchaseMoney) {
		if (purchaseMoney.isLessThan(LOTTO_PRICE)) {
			throw new LackOfMoneyException();
		}
	}

	private void validateSelfLottoAmountRange(int inputSelfNumberLottoAmount) {
		if (inputSelfNumberLottoAmount < ONE_LOTTO_TICKET) {
			throw new InvalidLottoAmountException(InvalidLottoAmountException.NEGATIVE_NUMBER);
		}
	}

	public int getSelfLottoAmount() {
		return selfLottoAmount;
	}

	public int getAutoLottoAmount() {
		return autoLottoAmount;
	}
}
