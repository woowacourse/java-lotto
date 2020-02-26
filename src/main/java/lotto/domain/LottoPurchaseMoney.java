package lotto.domain;

import java.util.Iterator;

import lotto.domain.exception.InvalidLottoPurchaseMoneyException;
import lotto.domain.exception.NotEnoughMoneyException;

public class LottoPurchaseMoney implements Iterator<Integer> {
	private static final int LOTTO_PRICE = 1_000;
	private static final int NO_SPEND = 0;

	private final int lottoPurchaseMoney;
	private int spendMoney;

	public LottoPurchaseMoney(String lottoPurchaseMoney) {
		validate(lottoPurchaseMoney);
		this.lottoPurchaseMoney = Integer.parseInt(lottoPurchaseMoney);
		this.spendMoney = NO_SPEND;
	}

	private void validate(String lottoPurchaseMoney) {
		if (isNotNumber(lottoPurchaseMoney)) {
			throw new NumberFormatException("숫자가 아닌 값을 입력하면 안됩니다.");
		}
		if (canNotBought(lottoPurchaseMoney)) {
			throw new InvalidLottoPurchaseMoneyException();
		}
	}

	private boolean isNotNumber(String lottoPurchaseMoney) {
		try {
			Integer.parseInt(lottoPurchaseMoney);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	private boolean canNotBought(String lottoPurchaseMoney) {
		return Integer.parseInt(lottoPurchaseMoney) < LOTTO_PRICE;
	}

	public int getBuyCount() {
		return lottoPurchaseMoney / LOTTO_PRICE;
	}

	public int getValue() {
		return lottoPurchaseMoney;
	}

	public void spend(int lottoCount) {
		final int price = LOTTO_PRICE * lottoCount;
		if (spendMoney + price > lottoPurchaseMoney) {
			throw new NotEnoughMoneyException();
		}
		spendMoney += price;
	}

	@Override
	public boolean hasNext() {
		return spendMoney < lottoPurchaseMoney;
	}

	@Override
	public Integer next() {
		return spendMoney += LOTTO_PRICE;
	}
}
