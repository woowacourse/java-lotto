package lotto.domain;

import java.util.Iterator;

public class LottoBuyCount implements Iterator<Integer> {
	private final int fixedCount;
	private int buyCount;

	public LottoBuyCount(LottoPurchaseMoney lottoPurchaseMoney) {
		this(lottoPurchaseMoney, lottoPurchaseMoney.getRemainingBuyCount());
	}

	public LottoBuyCount(LottoPurchaseMoney lottoPurchaseMoney, String buyCount) {
		this(lottoPurchaseMoney, Integer.parseInt(buyCount));
	}

	public LottoBuyCount(LottoPurchaseMoney lottoPurchaseMoney, int buyCount) {
		lottoPurchaseMoney.spend(buyCount);
		this.fixedCount = buyCount;
		this.buyCount = buyCount;
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
