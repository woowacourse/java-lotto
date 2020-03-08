package lotto.domain;

import lotto.exceptions.PurchaseLottoMoneyException;
import lotto.exceptions.LottoMoneyException;

import java.util.Objects;

public class LottoMoney {
	public static final int POSITIVE_THRESHOLD = 0;
	public static final int LOTTO_PRICE = 1000;

	private final int purchaseMoney;

	private LottoMoney(final int purchaseMoney) {
		checkIsLessThanZero(purchaseMoney);
		checkIsMultipleOfThousand(purchaseMoney);

		this.purchaseMoney = purchaseMoney;
	}

	public static LottoMoney of(final int purchaseMoney) {
		return new LottoMoney(purchaseMoney);
	}

	public static LottoMoney ofLottoCount(final int count) {
		try {
			return new LottoMoney(LOTTO_PRICE * count);
		} catch (LottoMoneyException e) {
			throw new PurchaseLottoMoneyException();
		}
	}

	private void checkIsMultipleOfThousand(int purchaseMoney) {
		if (purchaseMoney % LOTTO_PRICE != 0) {
			throw new LottoMoneyException(purchaseMoney);
		}
	}

	private void checkIsLessThanZero(int purchaseMoney) {
		if (purchaseMoney < POSITIVE_THRESHOLD) {
			throw new LottoMoneyException(purchaseMoney);
		}
	}

	public LottoMoney subtract(LottoMoney other) {
		try {
			return new LottoMoney(purchaseMoney - other.purchaseMoney);
		} catch (LottoMoneyException e) {
			throw new PurchaseLottoMoneyException();
		}
	}

	public int countPurchasedTickets() {
		return purchaseMoney / LOTTO_PRICE;
	}

	public double divideBy(double totalEarning) {
		return totalEarning / purchaseMoney;
	}

	public boolean canNotPurchase(int ticketNumber) {
		return purchaseMoney < ticketNumber * LOTTO_PRICE;
	}

	public int getPurchaseMoney() {
		return purchaseMoney;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LottoMoney that = (LottoMoney) o;
		return purchaseMoney == that.purchaseMoney;
	}

	@Override
	public int hashCode() {
		return Objects.hash(purchaseMoney);
	}

	@Override
	public String toString() {
		return "LottoMoney{" +
				"purchaseMoney=" + purchaseMoney +
				'}';
	}
}
