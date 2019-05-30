package com.woowacourse.lotto.domain;

import java.util.Arrays;

public enum LottoRank {

	ZERO(0, 0),
	FIFTH(3, 5_000),
	FOURTH(4, 50_000),
	THIRD(5, 1_500_000),
	SECOND(5, 30_000_000),
	FIRST(6, 2_000_000_000);

	private int count;
	private int price;

	LottoRank(int count, int price) {
		this.count = count;
		this.price = price;
	}

	public int getPrice() {
		return this.price;
	}

	public int getCount() {
		return this.count;
	}

	public static LottoRank valueOf(int count) {
		return Arrays.stream(values()).filter(rank -> rank.count == count).findAny().orElse(ZERO);
	}
}
