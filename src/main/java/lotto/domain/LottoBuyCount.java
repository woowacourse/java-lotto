package lotto.domain;

import java.util.Iterator;

import lotto.domain.exception.InvalidLottoBuyCountException;

public class LottoBuyCount implements Iterator<Integer> {
	private static final int MIN = 0;

	private final int fixedCount;
	private int buyCount;

	public LottoBuyCount(LottoPurchaseMoney lottoPurchaseMoney) {
		this(lottoPurchaseMoney, lottoPurchaseMoney.getRemainingBuyCount());
	}

	public LottoBuyCount(LottoPurchaseMoney lottoPurchaseMoney, String buyCount) {
		this(lottoPurchaseMoney, Integer.parseInt(buyCount));
	}

	public LottoBuyCount(LottoPurchaseMoney lottoPurchaseMoney, int buyCount) {
		validate(buyCount);
		lottoPurchaseMoney.spend(buyCount);
		this.fixedCount = buyCount;
		this.buyCount = buyCount;
	}

	private void validate(int buyCount) {
		if (buyCount < MIN) {
			throw new InvalidLottoBuyCountException();
		}
	}

	@Override
	public boolean hasNext() {
		return buyCount > 0;
	}

	@Override
	public Integer next() {
		return buyCount--;
	}

	public int getValue() {
		return fixedCount;
	}
}
